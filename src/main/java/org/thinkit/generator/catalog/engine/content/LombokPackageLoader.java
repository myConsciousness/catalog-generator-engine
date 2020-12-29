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

import java.util.Map;
import java.util.Set;

import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.generator.catalog.engine.content.entity.LombokPackage;
import org.thinkit.generator.catalog.engine.content.entity.LombokPackageGroup;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * コンテンツ「LombokPackage」をロードするクラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode
@ContentMapping(content = "org/thinkit/generator/catalog/engine/LombokPackage")
public final class LombokPackageLoader implements Content<LombokPackageGroup> {

    /**
     * デフォルトコンストラクタ
     */
    private LombokPackageLoader() {
    }

    /**
     * {@link LombokPackageLoader} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link LombokPackageLoader} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Content<LombokPackageGroup> of() {
        return new LombokPackageLoader();
    }

    /**
     * コンテンツ要素定数
     */
    @RequiredArgsConstructor
    private enum ContentAttribute implements Attribute {

        /**
         * カタログパッケージ
         */
        LOMBOK_PACKAGE(Key.lombokPackage);

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
            lombokPackage;
        }
    }

    @Override
    public LombokPackageGroup execute() {

        final LombokPackageGroup lombokPackageGroup = LombokPackageGroup.of(0);

        this.loadContent(this).forEach(content -> {
            lombokPackageGroup.add(LombokPackage.of(content.get(ContentAttribute.LOMBOK_PACKAGE.getString())));
        });

        return lombokPackageGroup;
    }

    @Override
    public Set<Attribute> getAttributes() {
        return Set.of(ContentAttribute.LOMBOK_PACKAGE);
    }

    @Override
    public Map<Condition, String> getConditions() {
        return Map.of();
    }
}
