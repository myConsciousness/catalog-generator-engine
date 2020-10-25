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
 * カタログクラスのフィールドを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogField implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 7511871519889143587L;

    /**
     * 変数名
     */
    @Getter
    private String variableName;

    /**
     * データ型
     */
    @Getter
    private String dataType;

    /**
     * 説明
     */
    @Getter
    private String description;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogField() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogField} クラスの新しいインスタンスを生成します。
     *
     * @param variableName 変数名
     * @param dataType     データ型
     * @param description  説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogField(@NonNull String variableName, @NonNull String dataType, @NonNull String description) {
        this.variableName = variableName;
        this.dataType = dataType;
        this.description = description;
    }

    /**
     * 引数として渡された情報を基に {@link CatalogField} クラスの新しいインスタンスを生成します。
     *
     * @param catalogField カタログフィールド
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogField(@NonNull CatalogField catalogField) {
        this.variableName = catalogField.getVariableName();
        this.dataType = catalogField.getDataType();
        this.description = catalogField.getDescription();
    }

    /**
     * 引数として渡された情報を基に {@link CatalogField} クラスの新しいインスタンスを生成し返却します。
     *
     * @param variableName 変数名
     * @param dataType     データ型
     * @param description  説明
     * @return {@link catalogField} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogField of(@NonNull String variableName, @NonNull String dataType, @NonNull String description) {
        return new CatalogField(variableName, dataType, description);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogField} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogField カタログフィールド
     * @return {@link CatalogField} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogField of(@NonNull CatalogField catalogField) {
        return new CatalogField(catalogField);
    }
}
