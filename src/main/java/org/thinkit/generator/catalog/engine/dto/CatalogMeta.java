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
import java.util.ArrayList;
import java.util.List;

import org.thinkit.framework.envali.annotation.RequireNonEmpty;
import org.thinkit.framework.envali.entity.ValidatableEntity;
import org.thinkit.generator.catalog.engine.catalog.CatalogType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログメタの情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class CatalogMeta implements ValidatableEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 4542966670384352515L;

    /**
     * バージョン
     */
    @Getter
    @RequireNonEmpty
    private String version;

    /**
     * カタログ種別
     */
    @Getter
    private CatalogType catalogType;

    /**
     * 依存パッケージリスト
     */
    @Getter
    private List<String> dependentPackages = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    private CatalogMeta() {
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMeta} クラスの新しいインスタンスを生成します。
     *
     * @param version           バージョン
     * @param catalogType       カタログ種別
     * @param dependentPackages 依存パッケージリスト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMeta(@NonNull String version, @NonNull CatalogType catalogType,
            @NonNull List<String> dependentPackages) {
        this.version = version;
        this.catalogType = catalogType;
        this.dependentPackages = dependentPackages;
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMeta} クラスの新しいインスタンスを生成します。
     *
     * @param catalogMeta カタログメタ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMeta(@NonNull CatalogMeta catalogMeta) {
        this.version = catalogMeta.getVersion();
        this.catalogType = catalogMeta.getCatalogType();
        this.dependentPackages = new ArrayList<>(catalogMeta.getDependentPackages());
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMeta} クラスの新しいインスタンスを生成し返却します。
     *
     * @param version           バージョン
     * @param catalogType       カタログ種別
     * @param dependentPackages 依存パッケージリスト
     * @return {@link CatalogMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogMeta of(@NonNull String version, @NonNull CatalogType catalogType,
            @NonNull List<String> dependentPackages) {
        return new CatalogMeta(version, catalogType, dependentPackages);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMeta} クラスの新しいインスタンスを生成し返却します。
     *
     * @param catalogMeta カタログメタ
     * @return {@link CatalogMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogMeta of(@NonNull CatalogMeta catalogMeta) {
        return new CatalogMeta(catalogMeta);
    }
}
