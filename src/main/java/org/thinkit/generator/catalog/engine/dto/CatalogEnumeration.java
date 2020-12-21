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

import org.thinkit.framework.envali.annotation.RequireNonEmpty;
import org.thinkit.framework.envali.annotation.RequirePositive;
import org.thinkit.framework.envali.entity.ValidatableEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスの列挙子を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogEnumeration implements ValidatableEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 4143684643475167418L;

    /**
     * 列挙子リテラル
     */
    @Getter
    @RequireNonEmpty
    private String literal;

    /**
     * コード値
     */
    @Getter
    @RequirePositive
    private int code;

    /**
     * タグ
     */
    @Getter
    private String tag;

    /**
     * 説明
     */
    @Getter
    @RequireNonEmpty
    private String description;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogEnumeration() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogEnumeration} クラスの新しいインスタンスを生成します。
     *
     * @param literal     列挙子リテラル
     * @param code        コード値
     * @param tag         タグ
     * @param description 説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogEnumeration(@NonNull String literal, int code, @NonNull String tag, @NonNull String description) {
        this.literal = literal;
        this.code = code;
        this.tag = tag;
        this.description = description;
    }

    /**
     * 引数として渡された情報を基に {@link CatalogEnumeration} クラスの新しいインスタンスを生成します。
     *
     * @param catalogEnumeration カタログ列挙子
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogEnumeration(@NonNull CatalogEnumeration catalogEnumeration) {
        this.literal = catalogEnumeration.getLiteral();
        this.code = catalogEnumeration.getCode();
        this.tag = catalogEnumeration.getTag();
        this.description = catalogEnumeration.getDescription();
    }

    /**
     * 引数として渡された情報を基に {@link CatalogEnumeration} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal     列挙子リテラル
     * @param code        コード値
     * @param tag         タグ
     * @param description 説明
     * @return {@link CatalogEnumeration} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogEnumeration of(@NonNull String literal, int code, @NonNull String tag,
            @NonNull String description) {
        return new CatalogEnumeration(literal, code, tag, description);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogEnumeration} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogEnumeration カタログ列挙子
     * @return {@link CatalogEnumeration} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogEnumeration of(@NonNull CatalogEnumeration catalogEnumeration) {
        return new CatalogEnumeration(catalogEnumeration);
    }
}
