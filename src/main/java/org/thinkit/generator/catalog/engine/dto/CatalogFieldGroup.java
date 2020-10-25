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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link CatalogField} クラスを管理するコレクションクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogFieldGroup extends ArrayList<CatalogField> {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -5862374285754251225L;

    /**
     * 初期容量が {@code 10} で設定された空のリストを生成します。
     */
    private CatalogFieldGroup() {
        super();
    }

    /**
     * 引数として渡された初期容量が設定された空のリストを生成します。
     *
     * @param initialCapacity リストの初期容量
     *
     * @throws IllegalArgumentException 初期容量が負数の場合
     */
    private CatalogFieldGroup(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * {@link CatalogField} クラスを総称型として持つコレクションを基に新しいリストを生成します。
     *
     * @param collection {@link CatalogField} クラスを総称型として持つコレクション
     *
     * @throws NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogFieldGroup(@NonNull Collection<? extends CatalogField> collection) {
        super(collection);
    }

    /**
     * 初期容量が {@code 10} で設定された空のリストを生成し返却します。
     *
     * @return {@link CatalogFieldGroup} クラスの新しいインスタンス
     */
    public static List<CatalogField> of() {
        return new CatalogFieldGroup();
    }

    /**
     * 引数として渡された初期容量が設定された空のリストを生成し返却します。
     *
     * @param initialCapacity リストの初期容量
     * @return {@link CatalogFieldGroup} クラスの新しいインスタンス
     *
     * @throws IllegalArgumentException 初期容量が負数の場合
     */
    public static List<CatalogField> of(int initialCapacity) {
        return new CatalogFieldGroup(initialCapacity);
    }

    /**
     * {@link CatalogField} クラスを総称型として持つコレクションを基に新しいリストを生成し返却します。
     *
     * @param collection {@link CatalogField} クラスを総称型として持つコレクション
     * @return {@link CatalogFieldGroup} クラスの新しいインスタンス
     *
     * @throws NullPointerException 引数として {@code null} が渡された場合
     */
    public static List<CatalogField> of(@NonNull Collection<? extends CatalogField> collection) {
        return new CatalogFieldGroup(collection);
    }
}
