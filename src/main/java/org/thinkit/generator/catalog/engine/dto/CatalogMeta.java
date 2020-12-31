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
import org.thinkit.framework.envali.annotation.RequireNonNull;
import org.thinkit.framework.envali.entity.ValidatableEntity;
import org.thinkit.generator.catalog.engine.catalog.CatalogType;
import org.thinkit.generator.common.duke.catalog.LombokState;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * カタログメタの情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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
    @RequireNonNull
    @Builder.Default
    private CatalogType catalogType = CatalogType.CATALOG;

    /**
     * 依存パッケージリスト
     */
    @Getter
    @RequireNonNull
    @Builder.Default
    private List<String> dependentPackages = new ArrayList<>(0);

    /**
     * Lombok適用状態
     */
    @Getter
    @RequireNonNull
    @Builder.Default
    private LombokState lombokState = LombokState.NONE;
}
