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
public final class CatalogResource {

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

    private CatalogResource() {
    }

    private CatalogResource(@NonNull String packageName, @NonNull String className, @NonNull String resource) {
        this.packageName = packageName;
        this.className = className;
        this.resource = resource;
    }

    private CatalogResource(@NonNull CatalogResource catalogResource) {
        this.packageName = catalogResource.getPackageName();
        this.className = catalogResource.getClassName();
        this.resource = catalogResource.getResource();
    }

    public static CatalogResource of(@NonNull String packageName, @NonNull String className, @NonNull String resource) {
        return new CatalogResource(packageName, className, resource);
    }

    public static CatalogResource of(@NonNull CatalogResource catalogResource) {
        return new CatalogResource(catalogResource);
    }
}
