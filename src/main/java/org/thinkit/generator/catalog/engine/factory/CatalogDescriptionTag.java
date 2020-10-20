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

import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.DescriptionTag;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのJavadocアノテーションを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでJavadocアノテーションを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogDescriptionTag extends DescriptionTag {

    /**
     * 引数として渡された情報を基に {@link CatalogDescriptionTag} クラスの新しいインスタンスを生成します。
     *
     * @param variableName 変数名
     * @param description  説明
     * @param annotation   アノテーション
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogDescriptionTag(@NonNull String variableName, @NonNull String description,
            @NonNull Annotation annotation) {
        super(variableName, description, annotation);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogDescriptionTag} クラスの新しいインスタンスを生成し返却します。
     *
     * @param variableName 変数名
     * @param description  説明
     * @param annotation   アノテーション
     * @return {@link CatalogDescriptionTag} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DescriptionTag of(@NonNull String variableName, @NonNull String description,
            @NonNull Annotation annotation) {
        return new CatalogDescriptionTag(variableName, description, annotation);
    }

    @Override
    public String createResource() {

        final Annotation annotation = super.getAnnotation();

        return switch (annotation) {
            case PARAM -> """
                    %s %s %s
                    """.formatted(annotation.getAnnotation(), super.getVariableName(), super.getDescription());

            default -> """
                    %s %s
                    """.formatted(annotation.getAnnotation(), super.getDescription());
        };
    }
}
