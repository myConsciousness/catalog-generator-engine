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

import org.thinkit.generator.common.dto.ResourceMatrix;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのマトリクスを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogMatrix implements ResourceMatrix, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2514600086374154281L;

    /**
     * カタログメタ
     */
    @Getter
    private CatalogMeta catalogMeta;

    /**
     * カタログ作成者
     */
    @Getter
    private CatalogCreator catalogCreator;

    /**
     * カタログ定義グループ
     */
    @Getter
    private CatalogDefinitionGroup catalogDefinitionGroup;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogMatrix() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMatrix} クラスの新しいインスタンスを生成します。
     *
     * @param catalogMeta            カタログメタ
     * @param catalogCreator         カタログ作成者
     * @param catalogDefinitionGroup カタログ定義グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMatrix(@NonNull CatalogMeta catalogMeta, @NonNull CatalogCreator catalogCreator,
            @NonNull CatalogDefinitionGroup catalogDefinitionGroup) {
        this.catalogMeta = catalogMeta;
        this.catalogCreator = catalogCreator;
        this.catalogDefinitionGroup = catalogDefinitionGroup;
    }

    /**
     * 引数として渡されたカタログマトリクスをコピーした {@link CatalogMatrix} クラスの新しいインスタンスを生成します。
     *
     * @param catalogMatrix カタログマトリクス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMatrix(@NonNull CatalogMatrix catalogMatrix) {
        this.catalogMeta = CatalogMeta.of(catalogMatrix.getCatalogMeta());
        this.catalogCreator = CatalogCreator.of(catalogMatrix.getCatalogCreator());
        this.catalogDefinitionGroup = CatalogDefinitionGroup.of(catalogMatrix.getCatalogDefinitionGroup());
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMatrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogMeta            カタログメタ
     * @param catalogCreator         カタログ作成者
     * @param catalogDefinitionGroup カタログ定義グループ
     * @return {@link CatalogMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogMatrix of(@NonNull CatalogMeta catalogMeta, @NonNull CatalogCreator catalogCreator,
            @NonNull CatalogDefinitionGroup catalogDefinitionGroup) {
        return new CatalogMatrix(catalogMeta, catalogCreator, catalogDefinitionGroup);
    }

    /**
     * 引数として渡されたカタログマトリクスの情報をコピーした {@link CatalogMatrix} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogMatrix カタログマトリクス
     * @return {@link CatalogMatrix} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogMatrix of(@NonNull CatalogMatrix catalogMatrix) {
        return new CatalogMatrix(catalogMatrix);
    }
}
