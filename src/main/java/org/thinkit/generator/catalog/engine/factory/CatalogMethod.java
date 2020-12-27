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

import org.thinkit.generator.common.duke.catalog.Modifier;
import org.thinkit.generator.common.duke.factory.FunctionDescription;
import org.thinkit.generator.common.duke.factory.Method;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのメソッドを生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでメソッドを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogMethod extends Method {

    /**
     * 引数として渡された情報を基に {@link CatalogMethod} クラスの新しいインスタンスを生成します。
     *
     * @param modifier          アクセス修飾子
     * @param returnType        返却型
     * @param methodName        メソッド名
     * @param methodDescription メソッドの説明
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMethod(@NonNull Modifier modifier, @NonNull String returnType, @NonNull String methodName,
            @NonNull FunctionDescription methodDescription) {
        super(modifier, returnType, methodName, methodDescription);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMethod} クラスの新しいインスタンスを生成し返却します。
     *
     * @param modifier          アクセス修飾子
     * @param returnType        返却型
     * @param methodName        メソッド名
     * @param methodDescription メソッドの説明
     * @return {@link CatalogMethod} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Method of(@NonNull Modifier modifier, @NonNull String returnType, @NonNull String methodName,
            @NonNull FunctionDescription methodDescription) {
        return new CatalogMethod(modifier, returnType, methodName, methodDescription);
    }

    @Override
    public String createResource() {
        return """
                %s
                @Override
                %s %s %s(%s) {
                    %s
                }""".formatted(super.getFunctionDescription().createResource(), this.getAccessLevel(),
                super.getReturnType(), super.getFunctionName(), super.getParameter(), super.getProcess());
    }

    /**
     * アクセス修飾子に応じてアクセスレベルを表現する文字列を返却します。
     *
     * @return アクセス修飾子を表現する文字列
     */
    private String getAccessLevel() {
        return switch (super.getModifier()) {
            case PUBLIC -> "public";
            case PROTECTED -> "protected";
            case PRIVATE -> "private";
            case PACKAGE, NONE -> "";
        };
    }
}
