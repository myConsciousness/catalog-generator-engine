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
 * カタログクラスの作成者情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogCreator implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 2199009491477939717L;

    /**
     * 作成者
     */
    @Getter
    private String creator;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogCreator() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogCreator} クラスの新しいインスタンスを生成します。
     *
     * @param creator 作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogCreator(@NonNull String creator) {
        this.creator = creator;
    }

    /**
     * 引数として渡されたカタログ作成者の情報をコピーした {@link CatalogCreator} クラスの新しいインスタンスを生成します。
     *
     * @param catalogCreator カタログ作成者
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogCreator(@NonNull CatalogCreator catalogCreator) {
        this.creator = catalogCreator.getCreator();
    }

    /**
     * 引数として渡された情報を基に {@link CatalogCreator} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator 作成者
     * @return {@link CatalogCreator} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogCreator of(@NonNull String creator) {
        return new CatalogCreator(creator);
    }

    /**
     * 引数として渡されたカタログ作成者の情報をコピーした {@link CatalogCreator} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogCreator カタログ作成者
     * @return {@link CatalogCreator} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogCreator of(@NonNull CatalogCreator catalogCreator) {
        return new CatalogCreator(catalogCreator);
    }
}
