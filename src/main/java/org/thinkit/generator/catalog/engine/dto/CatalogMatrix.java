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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public final class CatalogMatrix {

    /**
     * カタログメタ
     */
    @Getter
    private CatalogMeta catalogMeta;

    /**
     * カタログ作成者
     */
    @Getter
    private CatalogCreator catalogCreator;

    /**
     * カタログ定義グループ
     */
    @Getter
    private CatalogDefinitionGroup catalogDefinitionGroup;

    private CatalogMatrix() {
    }

    private CatalogMatrix(@NonNull CatalogMeta catalogMeta, @NonNull CatalogCreator catalogCreator,
            @NonNull CatalogDefinitionGroup catalogDefinitionGroup) {
        this.catalogMeta = catalogMeta;
        this.catalogCreator = catalogCreator;
        this.catalogDefinitionGroup = catalogDefinitionGroup;
    }

    private CatalogMatrix(@NonNull CatalogMatrix catalogMatrix) {
        this.catalogMeta = CatalogMeta.of(catalogMatrix.getCatalogMeta());
        this.catalogCreator = CatalogCreator.of(catalogMatrix.getCatalogCreator());
        this.catalogDefinitionGroup = CatalogDefinitionGroup.of(catalogMatrix.getCatalogDefinitionGroup());
    }

    public static CatalogMatrix of() {
        return new CatalogMatrix();
    }

    public static CatalogMatrix of(@NonNull CatalogMeta catalogMeta, @NonNull CatalogCreator catalogCreator,
            @NonNull CatalogDefinitionGroup catalogDefinitionGroup) {
        return new CatalogMatrix(catalogMeta, catalogCreator, catalogDefinitionGroup);
    }

    public static CatalogMatrix of(@NonNull CatalogMatrix catalogMatrix) {
        return new CatalogMatrix(catalogMatrix);
    }
}
