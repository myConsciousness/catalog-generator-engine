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
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.ConstructorProcess;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.DescriptionTag;
import org.thinkit.generator.common.factory.resource.EnumDefinition;
import org.thinkit.generator.common.factory.resource.Enumeration;
import org.thinkit.generator.common.factory.resource.FieldDefinition;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Inheritance;
import org.thinkit.generator.common.factory.resource.Interface;
import org.thinkit.generator.common.factory.resource.Parameter;
import org.thinkit.generator.common.factory.resource.Resource;
import org.thinkit.generator.common.factory.resource.ResourceFactory;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスの各コンポーネントクラスを生成するファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogResourceFactory extends ResourceFactory {

    /**
     * デフォルトコンストラクタ
     */
    private CatalogResourceFactory() {
    }

    /**
     * {@link CatalogResourceFactory} のシングルトンインスタンスを返却します。
     *
     * @return {@link CatalogResourceFactory} のシングルトンインスタンス
     */
    public static ResourceFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * {@link CatalogResourceFactory} のシングルトンインスタンスを保持するインナークラスです。<br>
     * {@link CatalogResourceFactory} シングルトンインスタンスは初回参照時にメモリに読み込まれます。
     */
    private static class InstanceHolder {

        /**
         * シングルトンインスタンス
         */
        private static final ResourceFactory INSTANCE = new CatalogResourceFactory();
    }

    @Override
    public Copyright createCopyright(@NonNull String projectName, @NonNull String fileName, @NonNull String encoding,
            @NonNull String creator, @NonNull String creationDate) {
        return null;
    }

    @Override
    public ClassDescription createClassDescription(@NonNull String description, @NonNull String creator,
            @NonNull String version) {
        return null;
    }

    @Override
    public Description createDescription(@NonNull String description) {
        return null;
    }

    @Override
    public Inheritance createInheritance(@NonNull String literal) {
        return null;
    }

    @Override
    public Inheritance createInheritance(@NonNull String literal, @NonNull Generics generics) {
        return null;
    }

    @Override
    public Interface createInterface(@NonNull String literal) {
        return null;
    }

    @Override
    public Interface createInterface(@NonNull String literal, @NonNull Generics generics) {
        return null;
    }

    @Override
    public Generics createGenerics() {
        return null;
    }

    @Override
    public EnumDefinition createEnumDefinition(@NonNull String literal) {
        return null;
    }

    @Override
    public Enumeration createEnumeration(@NonNull EnumDefinition enumDefinition, @NonNull Description description) {
        return null;
    }

    @Override
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        return null;
    }

    @Override
    public FunctionDescription createFunctionDescription(@NonNull String description) {
        return null;
    }

    @Override
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description) {
        return null;
    }

    @Override
    public Constructor createConstructor(@NonNull String functionName,
            @NonNull FunctionDescription functionDescription) {
        return null;
    }

    @Override
    public Parameter createParameter(@NonNull String dataType, @NonNull String variableName) {
        return null;
    }

    @Override
    public ConstructorProcess createConstructorProcess(String variableName) {
        return null;
    }

    @Override
    public ConstructorProcess createConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        return null;
    }

    @Override
    public Resource createResource(@NonNull Copyright copyright, @NonNull String packageName,
            @NonNull ClassDescription classDescription, @NonNull String resourceName) {
        return null;
    }
}
