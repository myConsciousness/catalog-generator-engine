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

@ToString
@EqualsAndHashCode
public final class CatalogResourceFormatter implements ResourceFormatter<CatalogMatrix, CatalogResourceGroup> {

    @Override
    public CatalogResourceGroup format(@NonNull CatalogMatrix arg0) {
        return null;
    }
}
