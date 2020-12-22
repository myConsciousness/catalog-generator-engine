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

import org.apache.commons.lang3.StringUtils;
import org.thinkit.generator.common.factory.resource.EnumDefinition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスの列挙子定義を生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することで列挙子定義を表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogEnumDefinition extends EnumDefinition {

    /**
     * 引数として渡された情報を基に {@link CatalogEnumDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param literal 列挙子名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogEnumDefinition(@NonNull String literal) {
        super(literal);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogEnumDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param literal 列挙子名
     * @return {@link CatalogEnumDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static EnumDefinition of(@NonNull String literal) {
        return new CatalogEnumDefinition(literal);
    }

    @Override
    public String createResource() {

        final String codeValue = super.getCodeValue();

        if (StringUtils.isEmpty(codeValue)) {
            return super.getLiteral();
        }

        return """
                %s(%s)""".formatted(super.getLiteral(), codeValue);
    }
}
