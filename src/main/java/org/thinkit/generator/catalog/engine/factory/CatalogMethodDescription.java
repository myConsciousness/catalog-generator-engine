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
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.duke.factory.FunctionDescription;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのメソッドのJavadocを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでメソッドのJavadocを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogMethodDescription extends FunctionDescription {

    /**
     * 引数として渡された情報を基に {@link CatalogMethodDescription} クラスの新しいインスタンスを生成します。
     *
     * @param description 説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMethodDescription(@NonNull String description) {
        super(description);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMethodDescription} クラスの新しいインスタンスを生成し返却します。
     *
     * @param description 説明
     * @return {@link CatalogMethodDescription} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static FunctionDescription of(@NonNull String description) {
        return new CatalogMethodDescription(description);
    }

    @Override
    public String createResource() {

        final String description = super.getDescription();

        if (StringUtils.isEmpty(description)) {
            return "";
        }

        final StringBuilder methodDescription = new StringBuilder();
        final String returnCode = Indentation.RETURN.getTag();

        methodDescription.append("/**").append(returnCode);
        methodDescription.append(" * ").append(description).append(returnCode);

        if (super.hasAnnotation()) {
            methodDescription.append(" *").append(returnCode);

            super.getDescriptionTags().forEach(descriptionTag -> {
                methodDescription.append(" * ").append(descriptionTag.createResource());
                methodDescription.append(returnCode);
            });

            methodDescription.setLength(methodDescription.length() - returnCode.length());
        }

        methodDescription.append(" */");

        return methodDescription.toString();
    }
}
