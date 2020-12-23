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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.thinkit.generator.catalog.engine.catalog.CatalogType;
import org.thinkit.generator.catalog.engine.dto.CatalogCreator;
import org.thinkit.generator.catalog.engine.dto.CatalogDefinition;
import org.thinkit.generator.catalog.engine.dto.CatalogEnumeration;
import org.thinkit.generator.catalog.engine.dto.CatalogField;
import org.thinkit.generator.catalog.engine.dto.CatalogMatrix;
import org.thinkit.generator.catalog.engine.dto.CatalogMeta;
import org.thinkit.generator.catalog.engine.dto.CatalogResourceGroup;

/**
 * {@link CatalogResourceFormatter} のUnitテストを管理するテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class CatalogResourceFormatterTest {

    @Test
    void testFormatWhenCatalogTypeIsCatalog() {

        final List<CatalogEnumeration> catalogEnumerations = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            catalogEnumerations.add(CatalogEnumeration.of(String.format("TEST%s", i + 1), i, "",
                    String.format("Description %s", i + 1)));
        }

        final List<CatalogField> catalogFields = new ArrayList<>();
        catalogFields.add(CatalogField.of("code", "int", "The code"));

        final CatalogDefinition definition = CatalogDefinition.of(
                CatalogMeta.of("1.0.0", CatalogType.CATALOG, List.of("org.thinkit.api.catalog.Catalog")),
                "org.thinkit.generator.catalog.test", "TestCatalog", "", catalogEnumerations, catalogFields);

        final CatalogResourceGroup catalogResourceGroup = assertDoesNotThrow(() -> CatalogResourceFormatter
                .newInstance().format(CatalogMatrix.of(CatalogCreator.of("Shinya"), List.of(definition))));

        assertEquals(TEMPLATE_CATALOG_CLASS, catalogResourceGroup.get(0).getResource());
    }

    /**
     * カタログクラスのテンプレート
     */
    private static final String TEMPLATE_CATALOG_CLASS = """
            /*
             * Copyright 2020 Shinya.
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

            package org.thinkit.generator.catalog.test;

            import org.thinkit.api.catalog.Catalog;

            /**
             * This catalog class was created by Catalog Generator.
             *
             * <p>You may learn more about the Catalog API at
             *
             * <p>https://github.com/myConsciousness/catalog-api
             *
             * @author Shinya
             * @since 1.0.0
             */
            public enum TestCatalog implements Catalog<TestCatalog> {

                /** Description 1 */
                TEST1(0),

                /** Description 2 */
                TEST2(1),

                /** Description 3 */
                TEST3(2);

                /** The code */
                private int code;

                /**
                 * A constructor that generates the catalog {@link TestCatalog} .
                 *
                 * @param code The code
                 */
                TestCatalog(int code) {
                    this.code = code;
                }

                @Override
                public int getCode() {
                    return this.code;
                }
            }
            """;
}
