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

import org.thinkit.generator.common.factory.resource.ClassDescription;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのクラスJavadocを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでクラスのJavadocを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogClassDescription extends ClassDescription {

    /**
     * 引数として渡された情報を基に {@link CatalogClassDescription} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator 作成者
     * @param version バージョン
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogClassDescription(@NonNull String creator, @NonNull String version) {
        super(creator, version);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogClassDescription} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator 作成者
     * @param version バージョン
     * @return {@link CatalogClassDescription} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static ClassDescription of(@NonNull String creator, @NonNull String version) {
        return new CatalogClassDescription(creator, version);
    }

    @Override
    public String createResource() {
        return """
                /**
                 * This catalog class was created by Catalog Generator.
                 * <p>
                 * You may learn more about the Catalog API at
                 *
                 *     https://github.com/myConsciousness/catalog-api
                 *
                 * @author %s
                 * @since %s
                 */""".formatted(super.getCreator(), super.getVersion());
    }
}
