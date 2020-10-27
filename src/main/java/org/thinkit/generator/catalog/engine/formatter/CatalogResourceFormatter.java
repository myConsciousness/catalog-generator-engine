/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.catalog.engine.formatter;

import org.thinkit.generator.catalog.engine.catalog.CatalogType;
import org.thinkit.generator.catalog.engine.dto.CatalogCreator;
import org.thinkit.generator.catalog.engine.dto.CatalogDefinition;
import org.thinkit.generator.catalog.engine.dto.CatalogEnumeration;
import org.thinkit.generator.catalog.engine.dto.CatalogMatrix;
import org.thinkit.generator.catalog.engine.dto.CatalogMeta;
import org.thinkit.generator.catalog.engine.dto.CatalogResource;
import org.thinkit.generator.catalog.engine.dto.CatalogResourceGroup;
import org.thinkit.generator.catalog.engine.factory.CatalogResourceFactory;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.EnumDefinition;
import org.thinkit.generator.common.factory.resource.Enumeration;
import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Interface;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;
import org.thinkit.generator.common.formatter.ResourceFormatter;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link CatalogMatrix} クラスに格納されたリソース情報を基にカタログリソースを生成する処理を定義したフォーマッタークラスです。
 * <p>
 * {@link #newInstance()} メソッドを使用することで {@link CatalogResourceFormatter}
 * クラスの新しいインスタンスを生成することができます。 {@link CatalogResourceFormatter}
 * クラスの新しいインスタンスを生成した後は {@link #format(CatalogMatrix)}
 * メソッドを呼び出し整形処理を行ってください。整形処理が正常終了した場合は生成されたリソースが格納された
 * {@link CatalogResourceGroup} が返却されます。
 *
 * <pre>
 * 操作例:
 * <code>
 * CatalogResourceFormatter.newInstance().format(catalogMatrix).foreach(catalogResource -> {
 *      // do something like
 *      catalogResource.getPackageName();
 *      catalogResource.getClassName();
 *      catalogResource.getResource();
 * });
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogResourceFormatter implements ResourceFormatter<CatalogMatrix, CatalogResourceGroup> {

    /**
     * デフォルトコンストラクタ
     */
    private CatalogResourceFormatter() {
    }

    /**
     * {@link CatalogResourceFormatter} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link CatalogResourceFormatter} クラスの新しいインスタンス
     */
    public static ResourceFormatter<CatalogMatrix, CatalogResourceGroup> newInstance() {
        return new CatalogResourceFormatter();
    }

    @Override
    public CatalogResourceGroup format(@NonNull CatalogMatrix catalogMatrix) {

        final CatalogMeta catalogMeta = catalogMatrix.getCatalogMeta();

        final CatalogCreator catalogCreator = catalogMatrix.getCatalogCreator();
        final String creator = catalogCreator.getCreator();

        final ResourceFactory factory = CatalogResourceFactory.getInstance();
        final Copyright copyright = factory.createCopyright(creator);

        final CatalogResourceGroup catalogResourceGroup = CatalogResourceGroup.of();
        final CatalogType catalogType = catalogMeta.getCatalogType();

        catalogMatrix.getCatalogDefinitionGroup().forEach(catalogDefinition -> {

            final String packageName = catalogDefinition.getPackageName();
            final String className = catalogDefinition.getClassName();

            final Resource resource = factory.createResource(copyright, packageName, factory.createClassDescription(
                    creator, catalogDefinition.getPackageName(), catalogDefinition.getVersion()), className);
            resource.add(this.createInterface(catalogType, catalogDefinition));

            catalogDefinition.getCatalogEnumerationGroup().forEach(catalogEnumeration -> {
                resource.add(this.createEnumeration(catalogType, catalogEnumeration));
            });

            catalogDefinition.getCatalogFieldGroup().forEach(catalogField -> {
                // TODO: フィールド定義

                // TODO: コンストラクタ

                // TODO: Getter メソッド
            });

            catalogResourceGroup.add(CatalogResource.of(packageName, className, resource.createResource()));
        });

        return catalogResourceGroup;
    }

    /**
     * 引数として渡された {@code catalogType} のカタログ種別から対応するインターフェースの定義オブジェクトを生成し返却します。
     *
     * @param catalogType       カタログ種別
     * @param catalogDefinition カタログ定義
     * @return {@code catalogType} のカタログ種別に対応するインターフェースの定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Interface createInterface(@NonNull CatalogType catalogType, @NonNull CatalogDefinition catalogDefinition) {

        final ResourceFactory factory = CatalogResourceFactory.getInstance();

        return switch (catalogType) {
            case CATALOG -> {
                final Generics generics = factory.createGenerics().add(catalogDefinition.getClassName());
                yield factory.createInterface(catalogType.getTag(), generics);
            }

            case BI_CATALOG -> {
                final Generics generics = factory.createGenerics().add(catalogDefinition.getClassName())
                        .add(catalogDefinition.getTagDataType());
                yield factory.createInterface(catalogType.getTag(), generics);
            }
        };
    }

    /**
     * 引数として渡された {@code catalogType} のカタログ種別から対応する列挙子の定義オブジェクトを生成し返却します。
     *
     * @param catalogType        カタログ種別
     * @param catalogEnumeration カタログ列挙子
     * @return {@code catalogType} のカタログ種別に対応する列挙子の定義オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Enumeration createEnumeration(@NonNull CatalogType catalogType,
            @NonNull CatalogEnumeration catalogEnumeration) {

        final ResourceFactory factory = CatalogResourceFactory.getInstance();

        return switch (catalogType) {
            case CATALOG -> {
                final EnumDefinition enumDefinition = factory.createEnumDefinition(catalogEnumeration.getLiteral())
                        .add(catalogEnumeration.getCode());
                yield factory.createEnumeration(enumDefinition,
                        factory.createDescription(catalogEnumeration.getDescription()));
            }

            case BI_CATALOG -> {
                final EnumDefinition enumDefinition = factory.createEnumDefinition(catalogEnumeration.getLiteral())
                        .add(catalogEnumeration.getCode()).add(catalogEnumeration.getTag());
                yield factory.createEnumeration(enumDefinition,
                        factory.createDescription(catalogEnumeration.getDescription()));
            }
        };
    }
}
