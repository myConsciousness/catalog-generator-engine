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

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.EnumDefinition;
import org.thinkit.generator.common.factory.resource.Enumeration;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスの列挙子を生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することで列挙子を表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogEnumeration extends Enumeration {

    /**
     * 引数として渡された情報を基に {@link CatalogEnumeration} クラスの新しいインスタンスを生成します。
     *
     * @param enumDefinition 列挙子定義
     * @param description    列挙子の説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogEnumeration(@NonNull EnumDefinition enumDefinition, @NonNull Description description) {
        super(enumDefinition, description);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogEnumeration} クラスの新しいインスタンスを生成し返却します。
     *
     * @param enumDefinition 列挙子定義
     * @param description    列挙子の説明
     * @return {@link CatalogEnumeration} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Enumeration of(@NonNull EnumDefinition enumDefinition, @NonNull Description description) {
        return new CatalogEnumeration(enumDefinition, description);
    }

    @Override
    public String createResource() {

        final StringBuilder enumeration = new StringBuilder();
        enumeration.append(super.getDescription().createResource()).append(Indentation.returnCode());
        enumeration.append(super.getEnumDefinition().createResource());

        return enumeration.toString();
    }
}
