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

package org.thinkit.generator.catalog.engine.content.entity;

import java.util.ArrayList;
import java.util.Collection;

import org.thinkit.framework.content.entity.ContentEntity;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツ「LombokPackage」の集合情報を管理するリストです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class LombokPackageGroup extends ArrayList<LombokPackage> implements ContentEntity {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2064042930959679387L;

    /**
     * 初期容量10の空のリストを作成します。
     */
    private LombokPackageGroup() {
        super();
    }

    /**
     * 指定された初期容量で空のリストを作成します。
     *
     * @param initialCapacity 初期容量
     *
     * @exception IllegalArgumentException 初期容量が負数の場合
     */
    private LombokPackageGroup(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * 指定したコレクションの要素を含むリストを、コレクションのイテレータによって返された順に作成します。
     *
     * @param collection 複製元のコレクション
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private LombokPackageGroup(Collection<? extends LombokPackage> collection) {
        super(collection);
    }

    /**
     * 初期容量10の空の {@link LombokPackageGroup} を作成します。
     *
     * @return 初期容量10を持つ空の {@link LombokPackageGroup}
     */
    public static LombokPackageGroup newInstance() {
        return new LombokPackageGroup();
    }

    /**
     * 指定された初期容量で空の {@link LombokPackageGroup} を作成します。
     *
     * @param initialCapacity 初期容量
     * @return 指定された初期容量を持つ空の {@link LombokPackageGroup}
     *
     * @exception IllegalArgumentException 初期容量が負数の場合
     */
    public static LombokPackageGroup of(int initialCapacity) {
        return new LombokPackageGroup(initialCapacity);
    }

    /**
     * 指定したコレクションの要素を含む {@link LombokPackageGroup} を、コレクションのイテレータによって返された順に作成します。
     *
     * @param collection 複製元のコレクション
     * @return 指定したコレクションの要素を含む {@link LombokPackageGroup}
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static LombokPackageGroup of(Collection<? extends LombokPackage> collection) {
        return new LombokPackageGroup(collection);
    }
}
