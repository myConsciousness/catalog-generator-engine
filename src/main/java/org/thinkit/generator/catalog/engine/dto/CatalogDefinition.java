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

package org.thinkit.generator.catalog.engine.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.thinkit.framework.envali.annotation.NestedEntity;
import org.thinkit.framework.envali.annotation.RequireNonEmpty;
import org.thinkit.framework.envali.entity.ValidatableEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログ定義の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogDefinition implements ValidatableEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1501015289762242952L;

    /**
     * カタログメタ
     */
    @Getter
    @NestedEntity
    private CatalogMeta catalogMeta;

    /**
     * パッケージ名
     */
    @Getter
    @RequireNonEmpty
    private String packageName;

    /**
     * クラス名
     */
    @Getter
    @RequireNonEmpty
    private String className;

    /**
     * タグのデータ型
     */
    @Getter
    private String tagDataType;

    /**
     * 列挙子グループ
     */
    @Getter
    @RequireNonEmpty
    @NestedEntity
    private List<CatalogEnumeration> catalogEnumerations;

    /**
     * フィールドグループ
     */
    @Getter
    @RequireNonEmpty
    @NestedEntity
    private List<CatalogField> catalogFields;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogDefinition() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param catalogMeta         カタログメタ
     * @param packageName         パッケージ名
     * @param className           クラス名
     * @param tagDataType         タグのデータ型
     * @param catalogEnumerations カタログ列挙子グループ
     * @param catalogFields       カタログフィールドグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogDefinition(@NonNull CatalogMeta catalogMeta, @NonNull String packageName, @NonNull String className,
            @NonNull String tagDataType, @NonNull List<CatalogEnumeration> catalogEnumerations,
            @NonNull List<CatalogField> catalogFields) {
        this.catalogMeta = catalogMeta;
        this.packageName = packageName;
        this.className = className;
        this.catalogEnumerations = catalogEnumerations;
        this.catalogFields = catalogFields;
    }

    /**
     * 引数として渡されたカタログ情報をコピーした {@link CatalogDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param catalogDefinition カタログ定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogDefinition(@NonNull CatalogDefinition catalogDefinition) {
        this.catalogMeta = CatalogMeta.of(catalogDefinition.getCatalogMeta());
        this.packageName = catalogDefinition.getPackageName();
        this.className = catalogDefinition.getClassName();
        this.tagDataType = catalogDefinition.getTagDataType();
        this.catalogEnumerations = new ArrayList<>(catalogDefinition.getCatalogEnumerations());
        this.catalogFields = new ArrayList<>(catalogDefinition.getCatalogFields());
    }

    /**
     * 引数として渡された情報を基に {@link CatalogDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogMeta         カタログメタ
     * @param packageName         パッケージ名
     * @param className           クラス名
     * @param tagDataType         タグのデータ型
     * @param catalogEnumerations カタログ列挙子グループ
     * @param catalogFields       カタログフィールドグループ
     * @return {@link CatalogDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogDefinition of(@NonNull CatalogMeta catalogMeta, @NonNull String packageName,
            @NonNull String className, @NonNull String tagDataType,
            @NonNull List<CatalogEnumeration> catalogEnumerations, @NonNull List<CatalogField> catalogFields) {
        return new CatalogDefinition(catalogMeta, packageName, className, tagDataType, catalogEnumerations,
                catalogFields);
    }

    /**
     * 引数として渡されたカタログ情報をコピーした {@link CatalogDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogDefinition カタログ定義
     * @return {@link CatalogDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogDefinition of(@NonNull CatalogDefinition catalogDefinition) {
        return new CatalogDefinition(catalogDefinition);
    }
}
