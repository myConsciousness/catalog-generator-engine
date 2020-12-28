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

import org.thinkit.generator.common.duke.factory.AnnotationParameter;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのアノテーション引数を生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogAnnotationParameter extends AnnotationParameter {

    /**
     * コンストラクタ
     *
     * @param fieldName フィールド名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogAnnotationParameter(@NonNull String fieldName) {
        super(fieldName);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogAnnotationParameter} クラスの新しいインスタンスを生成し返却します。
     *
     * @param fieldName フィールド名
     * @return {@link CatalogAnnotationParameter} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static AnnotationParameter of(@NonNull String fieldName) {
        return new CatalogAnnotationParameter(fieldName);
    }

    @Override
    public String createResource() {
        return String.format("%s = %s", super.createParameter());
    }
}
