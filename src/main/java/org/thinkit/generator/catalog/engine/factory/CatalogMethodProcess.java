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

import org.thinkit.generator.catalog.engine.strategy.CatalogGetterMethodProcess;
import org.thinkit.generator.catalog.engine.strategy.CatalogMethodProcessContext;
import org.thinkit.generator.common.duke.factory.MethodProcess;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのメソッド処理を生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでメソッド処理を表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogMethodProcess extends MethodProcess {

    /**
     * 引数として渡された情報を基に {@link CatalogMethodProcess} クラスの新しいインスタンスを生成します。
     *
     * @param variableName 変数名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMethodProcess(@NonNull String variableName) {
        super(variableName);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMethodProcess} クラスの新しいインスタンスを生成し返却します。
     *
     * @param variableName 変数名
     * @return {@link CatalogMethodProcess} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static MethodProcess of(@NonNull String variableName) {
        return new CatalogMethodProcess(variableName);
    }

    @Override
    public String createResource() {
        return switch (super.getMethodType()) {
            case DEFAULT -> throw new UnsupportedOperationException(
                    "This operation is not supported for generating catalog classes.");
            case SETTER -> throw new UnsupportedOperationException(
                    "This operation is not supported for generating catalog classes.");
            case GETTER -> new CatalogMethodProcessContext(new CatalogGetterMethodProcess())
                    .toProcess(super.getProcess());
        };
    }
}
