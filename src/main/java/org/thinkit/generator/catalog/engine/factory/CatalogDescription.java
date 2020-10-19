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

import org.thinkit.generator.common.factory.resource.Description;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのJavadocを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでJavadocを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogDescription extends Description {

    /**
     * 引数として渡された情報を基に {@link CatalogDescription} クラスの新しいインスタンスを生成します。
     *
     * @param description 説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogDescription(@NonNull String description) {
        super(description);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogDescription} クラスの新しいインスタンスを生成し返却します。
     *
     * @param description 説明
     * @return {@link CatalogDescription} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Description of(@NonNull String description) {
        return new CatalogDescription(description);
    }

    @Override
    public String createResource() {
        return """
                /**
                 * %s
                 */
                """.formatted(super.getDescription());
    }
}
