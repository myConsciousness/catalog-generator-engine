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
import org.thinkit.generator.common.duke.catalog.LombokState;

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
    private List<String> dependentPackages;

    /**
     * Lombok適用状態
     */
    @Getter
    private LombokState lombokState;

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
     * @param lombokState       Lombok適用状態
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogMeta(@NonNull String version, @NonNull CatalogType catalogType,
            @NonNull List<String> dependentPackages, @NonNull LombokState lombokState) {
        this.version = version;
        this.catalogType = catalogType;
        this.dependentPackages = dependentPackages;
        this.lombokState = lombokState;
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
        this.lombokState = catalogMeta.getLombokState();
    }

    /**
     * 引数として渡された情報を基に {@link CatalogMeta} クラスの新しいインスタンスを生成し返却します。
     *
     * @param version           バージョン
     * @param catalogType       カタログ種別
     * @param dependentPackages 依存パッケージリスト
     * @param lombokState       Lombok適用状態
     * @return {@link CatalogMeta} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static CatalogMeta of(@NonNull String version, @NonNull CatalogType catalogType,
            @NonNull List<String> dependentPackages, @NonNull LombokState lombokState) {
        return new CatalogMeta(version, catalogType, dependentPackages, lombokState);
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
