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

package org.thinkit.generator.catalog.engine.factory;

import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Interface;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのインターフェースを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでインターフェースを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogInterface extends Interface {

    /**
     * 引数として渡された情報を基に {@link CatalogInterface} クラスの新しいインスタンスを生成します。
     *
     * @param literal インターフェース名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogInterface(@NonNull String literal) {
        super(literal);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogInterface} クラスの新しいインスタンスを生成します。
     *
     * @param literal  インターフェース名
     * @param generics 総称型
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogInterface(@NonNull String literal, @NonNull Generics generics) {
        super(literal, generics);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogInterface} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal インターフェース名
     * @return {@link CatalogInterface} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Interface of(@NonNull String literal) {
        return new CatalogInterface(literal);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogInterface} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal  インターフェース名
     * @param generics 総称型
     * @return {@link CatalogInterface} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Interface of(@NonNull String literal, @NonNull Generics generics) {
        return new CatalogInterface(literal, generics);
    }

    @Override
    public String createResource() {

        final StringBuilder _interface = new StringBuilder(super.getLiteral());
        final Generics generics = super.getGenerics();

        if (!generics.isEmpty()) {
            _interface.append(generics.createResource());
        }

        return _interface.toString();
    }
}
