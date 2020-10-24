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

import org.thinkit.generator.common.factory.resource.FieldDefinition;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
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
public final class CatalogFieldDefinition extends FieldDefinition {

    /**
     * 引数として渡された情報を基に {@link CatalogFieldDefinition} クラスの新しいインスタンスを生成します。
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogFieldDefinition(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        super(dataType, variableName, initialValue);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogFieldDefinition} クラスの新しいインスタンスを生成し返却します。
     *
     * @param dataType     データ型
     * @param variableName 変数名
     * @param initialValue 初期値
     * @return {@link CatalogFieldDefinition} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static FieldDefinition of(@NonNull String dataType, @NonNull String variableName) {
        return new CatalogFieldDefinition(dataType, variableName, "");
    }

    @Override
    public String createResource() {
        return """
                private %s %s;
                    """.formatted(super.getDataType(), super.getVariableName());
    }
}
