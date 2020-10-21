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

import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Resource;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogResource extends Resource {

    private CatalogResource(Copyright copyright, String packageName, ClassDescription classDescription,
            String resourceName) {
        super(copyright, packageName, classDescription, resourceName);
    }

    @Override
    public String createResource() {
        return null;
    }
}
