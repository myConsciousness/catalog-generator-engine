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
import java.util.List;

import org.thinkit.framework.envali.annotation.NestedEntity;
import org.thinkit.framework.envali.annotation.RequireNonEmpty;
import org.thinkit.framework.envali.annotation.RequireNonNull;
import org.thinkit.framework.envali.entity.ValidatableEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * カタログ定義の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public final class CatalogDefinition implements ValidatableEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1501015289762242952L;

    /**
     * カタログメタ
     */
    @Getter
    @RequireNonNull
    @NestedEntity
    private CatalogMeta catalogMeta;

    /**
     * パッケージ名
     */
    @Getter
    @RequireNonEmpty
    private String packageName;

    /**
     * クラス名
     */
    @Getter
    @RequireNonNull
    @RequireNonEmpty
    private String className;

    /**
     * タグのデータ型
     */
    @Getter
    @RequireNonNull
    @Builder.Default
    private String tagDataType = "";

    /**
     * 列挙子グループ
     */
    @Getter
    @RequireNonNull
    @RequireNonEmpty
    @NestedEntity
    private List<CatalogEnumeration> catalogEnumerations;

    /**
     * フィールドグループ
     */
    @Getter
    @RequireNonNull
    @RequireNonEmpty
    @NestedEntity
    private List<CatalogField> catalogFields;
}
