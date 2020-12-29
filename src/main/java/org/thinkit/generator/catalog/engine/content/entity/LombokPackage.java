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

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツ「LombokPackage」の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class LombokPackage implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 8598410837453283600L;

    /**
     * パッケージ名
     */
    @Getter
    private String packageName;

    /**
     * デフォルトコンストラクタ
     */
    private LombokPackage() {
    }

    /**
     * コンストラクタ
     *
     * @param packageName パッケージ名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private LombokPackage(@NonNull String packageName) {
        this.packageName = packageName;
    }

    /**
     * コピーコンストラクタ
     *
     * @param lombokPackage Lombokパッケージ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private LombokPackage(@NonNull LombokPackage lombokPackage) {
        this.packageName = lombokPackage.getPackageName();
    }

    /**
     * 引数として渡された情報を基に {@link LombokPackage} クラスの新しいインスタンスを生成し返却します。
     *
     * @param packageName パッケージ名
     * @return {@link LombokPackage} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static LombokPackage of(@NonNull String packageName) {
        return new LombokPackage(packageName);
    }

    /**
     * 引数として渡された {@code lombokPackage} オブジェクトの情報を基に {@link LombokPackage}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param lombokPackage Lombokパッケージ
     * @return {@link LombokPackage} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static LombokPackage of(@NonNull LombokPackage lombokPackage) {
        return new LombokPackage(lombokPackage);
    }
}
