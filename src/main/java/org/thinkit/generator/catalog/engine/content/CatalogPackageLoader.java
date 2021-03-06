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

package org.thinkit.generator.catalog.engine.content;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.generator.catalog.engine.catalog.CatalogType;
import org.thinkit.generator.catalog.engine.content.entity.CatalogPackage;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「CatalogPackage」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor(staticName = "of")
@ContentMapping(content = "org/thinkit/generator/catalog/engine/CatalogPackage")
public final class CatalogPackageLoader implements Content<CatalogPackage> {

    /**
     * カタログ種別
     */
    @NonNull
    private final CatalogType catalogType;

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * カタログパッケージ
         */
        CATALOG_PACKAGE(Key.catalogPackage);

        /**
         * 検索キー
         */
        private final Key key;

        @Override
        public String getString() {
            return this.key.name();
        }

        /**
         * キー要素
         */
        private enum Key {
            catalogPackage;
        }
    }

    /**
     * コンテンツ条件定数
     */
    @RequiredArgsConstructor
    private enum ContentCondition implements Condition {

        /**
         * カタログ種別コード
         */
        CATALOG_TYPE_CODE(Key.catalogTypeCode);

        /**
         * 検索キー
         */
        private final Key key;

        @Override
        public String getString() {
            return this.key.name();
        }

        /**
         * キー要素
         */
        private enum Key {
            catalogTypeCode;
        }
    }

    @Override
    public CatalogPackage execute() {

        final List<Map<String, String>> content = this.loadContent(this);

        if (content.isEmpty()) {
            throw new IllegalStateException();
        }

        return CatalogPackage.builder().packageName(content.get(0).get(ContentAttribute.CATALOG_PACKAGE.getString()))
                .build();
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.CATALOG_PACKAGE);
    }

    @Override
    public List<Map<Condition, String>> getConditions() {
        return List.of(Map.of(ContentCondition.CATALOG_TYPE_CODE, String.valueOf(this.catalogType.getCode())));
    }
}
