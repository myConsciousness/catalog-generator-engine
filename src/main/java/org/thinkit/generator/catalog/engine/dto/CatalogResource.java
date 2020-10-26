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

package org.thinkit.generator.catalog.engine.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのリソースを管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogResource implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 2931361227819156382L;

    /**
     * パッケージ名
     */
    @Getter
    private String packageName;

    /**
     * クラス名
     */
    @Getter
    private String className;

    /**
     * リソース
     */
    @Getter
    private String resource;

    /**
     * デフォルトコンストラクタ
     */
    private CatalogResource() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogResource} クラスの新しいインスタンスを生成します。
     *
     * @param packageName パッケージ名
     * @param className   クラス名
     * @param resource    リソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogResource(@NonNull String packageName, @NonNull String className, @NonNull String resource) {
        this.packageName = packageName;
        this.className = className;
        this.resource = resource;
    }

    /**
     * 引数として渡されたカタログリソースをコピーした {@link CatalogResource} クラスの新しいインスタンスを生成します。
     *
     * @param catalogResource カタログリソース
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogResource(@NonNull CatalogResource catalogResource) {
        this.packageName = catalogResource.getPackageName();
        this.className = catalogResource.getClassName();
        this.resource = catalogResource.getResource();
    }

    /**
     * 引数として渡された情報を基に {@link CatalogResource} クラスの新しいインスタンスを生成します。
     *
     * @param packageName パッケージ名
     * @param className   クラス名
     * @param resource    リソース
     * @return {@link CatalogResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogResource of(@NonNull String packageName, @NonNull String className, @NonNull String resource) {
        return new CatalogResource(packageName, className, resource);
    }

    /**
     * 引数として渡されたカタログリソースをコピーした {@link CatalogResource} クラスの新しいインスタンスを生成します。
     *
     * @param catalogResource カタログリソース
     * @return {@link CatalogResource} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogResource of(@NonNull CatalogResource catalogResource) {
        return new CatalogResource(catalogResource);
    }
}
