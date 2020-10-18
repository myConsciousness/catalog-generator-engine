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
import org.thinkit.generator.common.factory.resource.Description;

import lombok.NonNull;

public final class CatalogClassDescription extends ClassDescription {

    /**
     * 初期バージョン
     */
    private static final String INITIAL_VERSION = "1.0";

    private CatalogClassDescription(@NonNull String description, @NonNull String creator, @NonNull String version) {
        super(description, creator, version);
    }

    public static Description of(@NonNull String creator, @NonNull String version) {
        return new CatalogClassDescription("", creator, version);
    }

    @Override
    public String createResource() {
        return """
                /**
                 * This catalog class was created using the Catalog Generator.
                 *
                 * @author %s
                 * @since %s
                 * @version %s
                 */
                """.formatted(super.getCreator(), INITIAL_VERSION, super.getVersion());
    }
}
