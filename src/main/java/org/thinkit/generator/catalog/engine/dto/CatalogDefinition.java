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
public final class CatalogDefinition implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 3282738129006012540L;

    /**
     * パッケージ名
     */
    @Getter
    private String packageName;

    /**
     * クラス名
     */
    @Getter
    private String className;

    /**
     * 列挙子グループ
     */
    @Getter
    private CatalogEnumerationGroup catalogEnumerationGroup;

    /**
     * フィールドグループ
     */
    @Getter
    private CatalogFieldGroup catalogFieldGroup;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogDefinition() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param packageName             パッケージ名
     * @param className               クラス名
     * @param catalogEnumerationGroup カタログ列挙子グループ
     * @param catalogFieldGroup       カタログフィールドグループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogDefinition(@NonNull String packageName, @NonNull String className,
            @NonNull CatalogEnumerationGroup catalogEnumerationGroup, @NonNull CatalogFieldGroup catalogFieldGroup) {
        this.packageName = packageName;
        this.className = className;
        this.catalogEnumerationGroup = catalogEnumerationGroup;
        this.catalogFieldGroup = catalogFieldGroup;
    }

    /**
     * 引数として渡されたカタログ情報をコピーした {@link CatalogDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param catalogDefinition カタログ定義
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogDefinition(@NonNull CatalogDefinition catalogDefinition) {
        this.packageName = catalogDefinition.getPackageName();
        this.className = catalogDefinition.getClassName();
        this.catalogEnumerationGroup = CatalogEnumerationGroup.of(catalogDefinition.getCatalogEnumerationGroup());
        this.catalogFieldGroup = CatalogFieldGroup.of(catalogDefinition.getCatalogFieldGroup());
    }

    /**
     * 引数として渡された情報を基に {@link CatalogDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param packageName             パッケージ名
     * @param className               クラス名
     * @param catalogEnumerationGroup カタログ列挙子グループ
     * @param catalogFieldGroup       カタログフィールドグループ
     * @return {@link CatalogDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogDefinition of(@NonNull String packageName, @NonNull String className,
            @NonNull CatalogEnumerationGroup catalogEnumerationGroup, @NonNull CatalogFieldGroup catalogFieldGroup) {
        return new CatalogDefinition(packageName, className, catalogEnumerationGroup, catalogFieldGroup);
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
