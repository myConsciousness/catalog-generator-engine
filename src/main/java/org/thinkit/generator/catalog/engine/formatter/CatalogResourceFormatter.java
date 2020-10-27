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

package org.thinkit.generator.catalog.engine.formatter;

import org.thinkit.generator.catalog.engine.dto.CatalogMatrix;
import org.thinkit.generator.catalog.engine.dto.CatalogResourceGroup;
import org.thinkit.generator.common.formatter.ResourceFormatter;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * {@link CatalogMatrix} クラスに格納されたリソース情報を基にカタログリソースを生成する処理を定義したフォーマッタークラスです。
 * <p>
 * {@link #newInstance()} メソッドを使用することで {@link CatalogResourceFormatter}
 * クラスの新しいインスタンスを生成することができます。 {@link CatalogResourceFormatter}
 * クラスの新しいインスタンスを生成した後は {@link #format(CatalogMatrix)}
 * メソッドを呼び出し整形処理を行ってください。整形処理が正常終了した場合は生成されたリソースが格納された
 * {@link CatalogResourceGroup} が返却されます。
 *
 * <pre>
 * 操作例:
 * <code>
 * CatalogResourceFormatter.newInstance().format(catalogMatrix).foreach(catalogResource -> {
 *      // do something like
 *      catalogResource.getPackageName();
 *      catalogResource.getClassName();
 *      catalogResource.getResource();
 * });
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogResourceFormatter implements ResourceFormatter<CatalogMatrix, CatalogResourceGroup> {

    /**
     * デフォルトコンストラクタ
     */
    private CatalogResourceFormatter() {
    }

    /**
     * {@link CatalogResourceFormatter} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link CatalogResourceFormatter} クラスの新しいインスタンス
     */
    public static ResourceFormatter<CatalogMatrix, CatalogResourceGroup> newInstance() {
        return new CatalogResourceFormatter();
    }

    @Override
    public CatalogResourceGroup format(@NonNull CatalogMatrix catalogMatrix) {
        return CatalogResourceGroup.of();
    }
}
