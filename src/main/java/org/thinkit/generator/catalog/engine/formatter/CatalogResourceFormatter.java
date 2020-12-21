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

import org.apache.commons.lang3.StringUtils;
import org.thinkit.framework.envali.Envali;
import org.thinkit.generator.catalog.engine.catalog.CatalogType;
import org.thinkit.generator.catalog.engine.dto.CatalogCreator;
import org.thinkit.generator.catalog.engine.dto.CatalogDefinition;
import org.thinkit.generator.catalog.engine.dto.CatalogEnumeration;
import org.thinkit.generator.catalog.engine.dto.CatalogField;
import org.thinkit.generator.catalog.engine.dto.CatalogMatrix;
import org.thinkit.generator.catalog.engine.dto.CatalogResource;
import org.thinkit.generator.catalog.engine.dto.CatalogResourceGroup;
import org.thinkit.generator.catalog.engine.factory.CatalogResourceFactory;
import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.ConstructorProcess;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.DescriptionTag;
import org.thinkit.generator.common.factory.resource.EnumDefinition;
import org.thinkit.generator.common.factory.resource.Enumeration;
import org.thinkit.generator.common.factory.resource.Field;
import org.thinkit.generator.common.factory.resource.FieldDefinition;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Interface;
import org.thinkit.generator.common.factory.resource.Method;
import org.thinkit.generator.common.factory.resource.Parameter;
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
 *      * // do something like
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
     * 雛形 : コンストラクタの説明
     */
    private static final String FMT_CONSTRUCTOR_DESCRIPTION = "A constructor that generates the catalog {@link %s} .";

    /**
     * 雛形 : Getterメソッドの説明
     */
    private static final String FMT_GETTER_DESCRIPTION = "Returns the field {@link #%s}.";

    /**
     * 雛形 : Getterメソッドの名称
     */
    private static final String FMT_GETTER_NAME = "get%s";

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
        Envali.validate(catalogMatrix);

        final CatalogCreator catalogCreator = catalogMatrix.getCatalogCreator();
        final String creator = catalogCreator.getCreator();

        final ResourceFactory factory = CatalogResourceFactory.getInstance();
        final Copyright copyright = factory.createCopyright(creator);

        final CatalogResourceGroup resources = CatalogResourceGroup.of();

        catalogMatrix.getCatalogDefinitions().forEach(catalogDefinition -> {
            resources.add(this.createCatalogResource(catalogDefinition.getCatalogMeta().getCatalogType(), copyright,
                    creator, catalogDefinition));
        });

        return resources;
    }

    /**
     * 引数として渡された情報を基にカタログクラスのリソースを生成し、生成されたリソースをデータクラス {@link CatalogResource}
     * に格納し返却します。
     *
     * @param catalogType       カタログ種別
     * @param copyright         著作権
     * @param creator           作成者
     * @param catalogDefinition カタログ定義
     * @return 生成されたカタログクラスのリソースが格納された {@link CatalogResource} オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogResource createCatalogResource(@NonNull CatalogType catalogType, @NonNull Copyright copyright,
            @NonNull String creator, @NonNull CatalogDefinition catalogDefinition) {

        final String packageName = catalogDefinition.getPackageName();
        final String className = catalogDefinition.getClassName();

        final ResourceFactory factory = CatalogResourceFactory.getInstance();
        final Resource resource = factory.createResource(copyright, packageName,
                factory.createClassDescription(creator, catalogDefinition.getCatalogMeta().getVersion()), className);
        resource.add(this.createInterface(catalogType, catalogDefinition));

        catalogDefinition.getCatalogEnumerations().forEach(catalogEnumeration -> {
            resource.add(this.createEnumeration(catalogType, catalogEnumeration));
        });

        final Constructor constructor = factory.createConstructor(className,
                factory.createFunctionDescription(String.format(FMT_CONSTRUCTOR_DESCRIPTION, className)));

        catalogDefinition.getCatalogFields().forEach(catalogField -> {
            resource.add(this.createField(catalogField));
            resource.add(this.createGetterMethod(catalogField));

            constructor.add(this.createDescriptionTag(catalogField));
            constructor.add(this.createParameter(catalogField));
            constructor.add(this.createConstructorProcess(catalogField));
        });

        resource.add(constructor);

        return CatalogResource.of(packageName, className, resource.createResource());
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

    /**
     * {@link CatalogField} クラスに格納されたリソース情報を基にカタログクラスのフィールドの定義オブジェクトを生成し返却します。
     *
     * @param catalogField カタログフィールド
     * @return フィールドオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Field createField(@NonNull CatalogField catalogField) {

        final ResourceFactory factory = CatalogResourceFactory.getInstance();
        final FieldDefinition fieldDefinition = factory.createFieldDefinition(catalogField.getDataType(),
                catalogField.getVariableName());

        return factory.createField(fieldDefinition, factory.createDescription(catalogField.getDescription()));
    }

    /**
     * {@link CatalogField} クラスに格納されたリソース情報を基にコンストラクタの引数アノテーション定義オブジェクトを生成し返却します。
     *
     * @param catalogField カタログフィールド
     * @return 引数アノテーションオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DescriptionTag createDescriptionTag(@NonNull CatalogField catalogField) {
        return CatalogResourceFactory.getInstance().createDescriptionTag(catalogField.getVariableName(),
                catalogField.getDescription(), Annotation.PARAM);
    }

    /**
     * {@link CatalogField} クラスに格納されたリソース情報を基に引数オブジェクトを生成し返却します。
     *
     * @param catalogField カタログフィールド
     * @return 引数オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Parameter createParameter(@NonNull CatalogField catalogField) {
        return CatalogResourceFactory.getInstance().createParameter(catalogField.getDataType(),
                catalogField.getVariableName());
    }

    /**
     * {@link CatalogField} クラスに格納されたリソース情報を基にコンストラクタの処理定義オブジェクトを生成し返却します。
     *
     * @param catalogField カタログフィールド
     * @return コンストラクタ処理オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ConstructorProcess createConstructorProcess(@NonNull CatalogField catalogField) {
        return CatalogResourceFactory.getInstance().createConstructorProcess(catalogField.getVariableName());
    }

    /**
     * {@link CatalogField} クラスに格納されたリソース情報を基にGetterメソッドの定義オブジェクトを生成し返却します。
     *
     * @param catalogField カタログフィールド
     * @return Getterメソッドオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Method createGetterMethod(@NonNull CatalogField catalogField) {

        final ResourceFactory factory = CatalogResourceFactory.getInstance();
        final String variableName = catalogField.getVariableName();

        final FunctionDescription methodDescription = factory
                .createFunctionDescription(String.format(FMT_GETTER_DESCRIPTION, variableName));
        final Method getterMethod = factory
                .createMethod(String.format(FMT_GETTER_NAME, this.toInitialUpperCase(variableName)), methodDescription);

        getterMethod.add(factory.createDescriptionTag("", catalogField.getDescription(), Annotation.RETURN));
        getterMethod.add(factory.createParameter(catalogField.getDataType(), variableName));
        getterMethod.add(factory.createMethodProcess(variableName).toGetter());

        return getterMethod;
    }

    private String toInitialUpperCase(String literal) {

        if (StringUtils.isEmpty(literal)) {
            return "";
        }

        if (literal.length() == 1) {
            return literal.toUpperCase();
        }

        return literal.substring(0, 1).toUpperCase() + literal.substring(1);
    }
}
