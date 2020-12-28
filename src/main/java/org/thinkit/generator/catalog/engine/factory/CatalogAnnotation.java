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

import java.util.List;

import org.thinkit.common.catalog.Delimiter;
import org.thinkit.generator.common.duke.catalog.AnnotationPattern;
import org.thinkit.generator.common.duke.factory.Annotation;
import org.thinkit.generator.common.duke.factory.AnnotationParameter;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのアノテーションを生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogAnnotation extends Annotation {

    /**
     * コンストラクタ
     *
     * @param annotationPattern アノテーションパターン
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogAnnotation(@NonNull AnnotationPattern annotationPattern) {
        super(annotationPattern);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogAnnotation} クラスの新しいインスタンスを生成し返却します。
     *
     * @param annotationPattern アノテーションパターン
     * @return {@link CatalogAnnotation} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Annotation of(@NonNull AnnotationPattern annotationPattern) {
        return new CatalogAnnotation(annotationPattern);
    }

    @Override
    public String createResource() {

        final List<AnnotationParameter> annotationParameters = super.getAnnotationParameters();

        if (annotationParameters == null) {
            return super.getAnnotationPattern().getTag();
        }

        final StringBuilder parameters = new StringBuilder();
        final String comma = Delimiter.COMMA.getTag();

        annotationParameters.forEach(parameter -> {
            parameters.append(parameter.createResource());
            parameters.append(comma);
        });

        parameters.setLength(parameters.length() - comma.length());

        return String.format("%s(%s)", super.getAnnotationPattern().getTag(), parameters.toString());
    }
}
