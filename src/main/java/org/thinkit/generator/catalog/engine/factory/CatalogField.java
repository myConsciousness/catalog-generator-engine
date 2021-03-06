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
import org.thinkit.generator.common.duke.factory.Description;
import org.thinkit.generator.common.duke.factory.Field;
import org.thinkit.generator.common.duke.factory.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのフィールドを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでフィールドを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogField extends Field {

    /**
     * 引数として渡された情報を基に {@link CatalogField} クラスの新しいインスタンスを生成します。
     *
     * @param fieldDefinition フィールド定義
     * @param description     フィールドの説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogField(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        super(fieldDefinition, description);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogField} クラスの新しいインスタンスを生成し返却します。
     *
     * @param fieldDefinition フィールド定義
     * @param description     フィールドの説明
     * @return {@link CatalogField} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Field of(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        return new CatalogField(fieldDefinition, description);
    }

    @Override
    public String createResource() {

        final StringBuilder field = new StringBuilder();
        final String returnCode = Indentation.RETURN.getTag();

        field.append(super.getDescription().createResource()).append(returnCode);

        if (this.isAppliedLombok()) {
            super.getAnnotations().forEach(annotation -> {
                field.append(annotation.createResource());
                field.append(returnCode);
            });
        }

        field.append(super.getFieldDefinition().createResource());

        return field.toString();
    }
}
