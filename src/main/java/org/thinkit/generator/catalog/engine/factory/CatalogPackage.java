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

import org.thinkit.generator.common.duke.factory.Package;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログのパッケージ定義を生成するファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することでパッケージを表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogPackage extends Package {

    /**
     * コンストラクタ
     *
     * @param packageName パッケージ名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogPackage(@NonNull String packageName) {
        super(packageName);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogPackage} クラスの新しいインスタンスを生成し返却します。
     *
     * @param packageName パッケージ名
     * @return {@link CatalogPackage} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static Package of(@NonNull String packageName) {
        return new CatalogPackage(packageName);
    }

    @Override
    public String createResource() {
        return String.format("package %s;", super.getPackageName());
    }
}
