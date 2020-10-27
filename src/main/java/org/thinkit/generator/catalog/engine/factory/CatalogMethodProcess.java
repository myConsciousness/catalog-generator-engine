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

import org.thinkit.generator.common.factory.resource.MethodProcess;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのメソッド処理を生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでメソッド処理を表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogMethodProcess extends MethodProcess {

    private CatalogMethodProcess(@NonNull String variableName) {
        super(variableName);
    }

    protected static MethodProcess of(@NonNull String variableName) {
        return new CatalogMethodProcess(variableName);
    }

    @Override
    public String createResource() {
        return null;
    }
}
