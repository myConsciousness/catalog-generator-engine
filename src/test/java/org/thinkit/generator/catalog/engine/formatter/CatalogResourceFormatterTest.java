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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
import org.thinkit.generator.common.duke.catalog.LombokState;

/**
 * {@link CatalogResourceFormatter} のUnitテストを管理するテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
public final class CatalogResourceFormatterTest {

    @Test
    void testFormatWhenCatalogTypeIsCatalog() {

        final CatalogResourceGroup catalogResourceGroup = assertDoesNotThrow(
                () -> CatalogResourceFormatter.newInstance()
                        .format(CatalogMatrix.of(CatalogCreator.of("Shinya"), List.of(this.getCatalogDefinition()))));

        assertNotNull(catalogResourceGroup);
        assertTrue(catalogResourceGroup.size() == 1);
        assertEquals("org.thinkit.generator.catalog.test", catalogResourceGroup.get(0).getPackageName());
        assertEquals("TestCatalog", catalogResourceGroup.get(0).getClassName());
        assertEquals(TEMPLATE_CATALOG_CLASS, catalogResourceGroup.get(0).getResource());
    }

    @Test
    void testFormatWhenCatalogTypeIsCatalogWithLombok() {

        final CatalogResourceGroup catalogResourceGroup = assertDoesNotThrow(
                () -> CatalogResourceFormatter.newInstance().format(
                        CatalogMatrix.of(CatalogCreator.of("Shinya"), List.of(this.getCatalogDefinitionWithLombok()))));

        assertNotNull(catalogResourceGroup);
        assertTrue(catalogResourceGroup.size() == 1);
        assertEquals("org.thinkit.generator.catalog.test", catalogResourceGroup.get(0).getPackageName());
        assertEquals("TestCatalog", catalogResourceGroup.get(0).getClassName());
        assertEquals(TEMPLATE_LOMBOK_CATALOG_CLASS, catalogResourceGroup.get(0).getResource());
    }

    @Test
    void testFormatWhenCatalogTypeIsBiCatalog() {

        final CatalogResourceGroup catalogResourceGroup = assertDoesNotThrow(
                () -> CatalogResourceFormatter.newInstance()
                        .format(CatalogMatrix.of(CatalogCreator.of("Shinya"), List.of(this.getBiCatalogDefintiion()))));

        assertNotNull(catalogResourceGroup);
        assertTrue(catalogResourceGroup.size() == 1);
        assertEquals("org.thinkit.generator.catalog.test", catalogResourceGroup.get(0).getPackageName());
        assertEquals("TestBiCatalog", catalogResourceGroup.get(0).getClassName());
        assertEquals(TEMPLATE_BICATALOG_CLASS, catalogResourceGroup.get(0).getResource());
    }

    @Test
    void testFormatWhenCatalogTypeIsBiCatalogWithLombok() {

        final CatalogResourceGroup catalogResourceGroup = assertDoesNotThrow(
                () -> CatalogResourceFormatter.newInstance().format(CatalogMatrix.of(CatalogCreator.of("Shinya"),
                        List.of(this.getBiCatalogDefintiionWithLombok()))));

        assertNotNull(catalogResourceGroup);
        assertTrue(catalogResourceGroup.size() == 1);
        assertEquals("org.thinkit.generator.catalog.test", catalogResourceGroup.get(0).getPackageName());
        assertEquals("TestBiCatalog", catalogResourceGroup.get(0).getClassName());
        assertEquals(TEMPLATE_LOMBOK_BICATALOG_CLASS, catalogResourceGroup.get(0).getResource());
    }

    @Test
    void testFormatWhenMultipleDefinitions() {

        final List<String> templates = List.of(TEMPLATE_CATALOG_CLASS, TEMPLATE_LOMBOK_CATALOG_CLASS,
                TEMPLATE_BICATALOG_CLASS, TEMPLATE_LOMBOK_BICATALOG_CLASS);

        final CatalogResourceGroup catalogResourceGroup = assertDoesNotThrow(
                () -> CatalogResourceFormatter.newInstance()
                        .format(CatalogMatrix.of(CatalogCreator.of("Shinya"),
                                List.of(this.getCatalogDefinition(), this.getCatalogDefinitionWithLombok(),
                                        this.getBiCatalogDefintiion(), this.getBiCatalogDefintiionWithLombok()))));

        assertNotNull(catalogResourceGroup);
        assertTrue(catalogResourceGroup.size() == 4);

        for (int i = 0, size = catalogResourceGroup.size(); i < size; i++) {
            assertEquals(templates.get(i), catalogResourceGroup.get(i).getResource());
        }
    }

    private CatalogDefinition getCatalogDefinition() {

        final List<CatalogEnumeration> catalogEnumerations = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            catalogEnumerations.add(CatalogEnumeration.of(String.format("TEST%s", i + 1), i, "",
                    String.format("Description %s", i + 1)));
        }

        final List<CatalogField> catalogFields = new ArrayList<>();
        catalogFields.add(CatalogField.of("code", "int", "The code"));

        return CatalogDefinition.of(CatalogMeta.of("1.0.0", CatalogType.CATALOG, List.of(), LombokState.NONE),
                "org.thinkit.generator.catalog.test", "TestCatalog", "", catalogEnumerations, catalogFields);
    }

    private CatalogDefinition getCatalogDefinitionWithLombok() {

        final List<CatalogEnumeration> catalogEnumerations = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            catalogEnumerations.add(CatalogEnumeration.of(String.format("TEST%s", i + 1), i, "",
                    String.format("Description %s", i + 1)));
        }

        final List<CatalogField> catalogFields = new ArrayList<>();
        catalogFields.add(CatalogField.of("code", "int", "The code"));

        return CatalogDefinition.of(CatalogMeta.of("1.0.0", CatalogType.CATALOG, List.of(), LombokState.LOMBOK),
                "org.thinkit.generator.catalog.test", "TestCatalog", "", catalogEnumerations, catalogFields);
    }

    private CatalogDefinition getBiCatalogDefintiion() {

        final List<CatalogEnumeration> catalogEnumerations = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            catalogEnumerations.add(CatalogEnumeration.of(String.format("TEST%s", i + 1), i,
                    String.format("tag %s", i + 1), String.format("Description %s", i + 1)));
        }

        final List<CatalogField> catalogFields = new ArrayList<>();
        catalogFields.add(CatalogField.of("code", "int", "The code"));
        catalogFields.add(CatalogField.of("tag", "String", "The tag"));

        return CatalogDefinition.of(CatalogMeta.of("1.0.0", CatalogType.BI_CATALOG, List.of(), LombokState.NONE),
                "org.thinkit.generator.catalog.test", "TestBiCatalog", "String", catalogEnumerations, catalogFields);
    }

    private CatalogDefinition getBiCatalogDefintiionWithLombok() {

        final List<CatalogEnumeration> catalogEnumerations = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            catalogEnumerations.add(CatalogEnumeration.of(String.format("TEST%s", i + 1), i,
                    String.format("tag %s", i + 1), String.format("Description %s", i + 1)));
        }

        final List<CatalogField> catalogFields = new ArrayList<>();
        catalogFields.add(CatalogField.of("code", "int", "The code"));
        catalogFields.add(CatalogField.of("tag", "String", "The tag"));

        return CatalogDefinition.of(CatalogMeta.of("1.0.0", CatalogType.BI_CATALOG, List.of(), LombokState.LOMBOK),
                "org.thinkit.generator.catalog.test", "TestBiCatalog", "String", catalogEnumerations, catalogFields);
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

    /**
     * バイナリーカタログクラスのテンプレート
     */
    private static final String TEMPLATE_BICATALOG_CLASS = """
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

            import org.thinkit.api.catalog.BiCatalog;

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
            public enum TestBiCatalog implements BiCatalog<TestBiCatalog, String> {

                /** Description 1 */
                TEST1(0, "tag 1"),

                /** Description 2 */
                TEST2(1, "tag 2"),

                /** Description 3 */
                TEST3(2, "tag 3");

                /** The code */
                private int code;

                /** The tag */
                private String tag;

                /**
                 * A constructor that generates the catalog {@link TestBiCatalog} .
                 *
                 * @param code The code
                 * @param tag The tag
                 */
                TestBiCatalog(int code, String tag) {
                    this.code = code;
                    this.tag = tag;
                }

                @Override
                public int getCode() {
                    return this.code;
                }

                @Override
                public String getTag() {
                    return this.tag;
                }
            }
            """;

    /**
     * Lombokを使用したカタログクラスのテンプレート
     */
    private static final String TEMPLATE_LOMBOK_CATALOG_CLASS = """
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
            import lombok.Getter;
            import lombok.RequiredArgsConstructor;

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
            @RequiredArgsConstructor
            public enum TestCatalog implements Catalog<TestCatalog> {

                /** Description 1 */
                TEST1(0),

                /** Description 2 */
                TEST2(1),

                /** Description 3 */
                TEST3(2);

                /** The code */
                @Getter private final int code;
            }
            """;

    /**
     * バイナリーカタログクラスのテンプレート
     */
    private static final String TEMPLATE_LOMBOK_BICATALOG_CLASS = """
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

            import org.thinkit.api.catalog.BiCatalog;
            import lombok.Getter;
            import lombok.RequiredArgsConstructor;

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
            @RequiredArgsConstructor
            public enum TestBiCatalog implements BiCatalog<TestBiCatalog, String> {

                /** Description 1 */
                TEST1(0, "tag 1"),

                /** Description 2 */
                TEST2(1, "tag 2"),

                /** Description 3 */
                TEST3(2, "tag 3");

                /** The code */
                @Getter private final int code;

                /** The tag */
                @Getter private final String tag;
            }
            """;
}
