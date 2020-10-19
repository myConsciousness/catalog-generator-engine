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

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.Field;
import org.thinkit.generator.common.factory.resource.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * カタログクラスのフィールドを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでフィールドを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class CatalogField extends Field {

    @Override
    public String createResource() {

        super.validate();

        final List<Description> descriptions = super.getDescriptions();
        final List<FieldDefinition> fieldDefinitions = super.getFieldDefinitions();

        final String returnCode = Indentation.returnCode();
        final StringBuilder fields = new StringBuilder();

        for (int i = 0, size = descriptions.size(); i < size; i++) {
            final StringBuilder field = new StringBuilder();

            field.append(descriptions.get(i).createResource()).append(returnCode);
            field.append(fieldDefinitions.get(i).createResource()).append(returnCode);
            fields.append(field.toString());
        }

        fields.setLength(fields.length() - returnCode.length());

        return fields.toString();
    }
}
