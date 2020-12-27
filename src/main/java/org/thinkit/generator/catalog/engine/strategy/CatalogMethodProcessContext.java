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

package org.thinkit.generator.catalog.engine.factory.strategy;

import org.thinkit.generator.common.factory.resource.strategy.MethodProcessContext;
import org.thinkit.generator.common.factory.resource.strategy.MethodProcessStrategy;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * メソッド処理を生成する際のストラテジーを判断するコンテキストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogMethodProcessContext extends MethodProcessContext {

    /**
     * 引数として渡されたスタロテジーを基に {@link CatalogMethodProcessContext} の新しいインスタンスを生成します。
     *
     * @param methodProcessStrategy メソッド処理ストラテジー
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public CatalogMethodProcessContext(@NonNull MethodProcessStrategy methodProcessStrategy) {
        super(methodProcessStrategy);
    }

    @Override
    public String toProcess(@NonNull String variableName) {
        return super.getMethodProcessStrategy().toProcess(variableName);
    }
}
