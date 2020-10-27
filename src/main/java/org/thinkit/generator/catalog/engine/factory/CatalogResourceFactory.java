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

import org.thinkit.generator.common.catalog.Annotation;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Constructor;
import org.thinkit.generator.common.factory.resource.ConstructorProcess;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Description;
import org.thinkit.generator.common.factory.resource.DescriptionTag;
import org.thinkit.generator.common.factory.resource.EnumDefinition;
import org.thinkit.generator.common.factory.resource.Enumeration;
import org.thinkit.generator.common.factory.resource.Field;
import org.thinkit.generator.common.factory.resource.FieldDefinition;
import org.thinkit.generator.common.factory.resource.FunctionDescription;
import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Inheritance;
import org.thinkit.generator.common.factory.resource.Interface;
import org.thinkit.generator.common.factory.resource.Method;
import org.thinkit.generator.common.factory.resource.MethodProcess;
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
     * サポート外メッセージ
     */
    private static final String UNSUPPORTED_MESSAGE = "This method is not supposed to be called when Catalog class is created";

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
    public Copyright createCopyright(@NonNull String creator) {
        return CatalogCopyright.of(creator);
    }

    @Override
    @Deprecated
    public Copyright createCopyright(@NonNull String projectName, @NonNull String fileName, @NonNull String encoding,
            @NonNull String creator, @NonNull String creationDate) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public ClassDescription createClassDescription(@NonNull String creator, @NonNull String version) {
        return CatalogClassDescription.of(creator, version);
    }

    @Override
    @Deprecated
    public ClassDescription createClassDescription(@NonNull String description, @NonNull String creator,
            @NonNull String version) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public Description createDescription(@NonNull String description) {
        return CatalogDescription.of(description);
    }

    @Override
    public Inheritance createInheritance(@NonNull String literal) {
        return CatalogInheritance.of(literal);
    }

    @Override
    public Inheritance createInheritance(@NonNull String literal, @NonNull Generics generics) {
        return CatalogInheritance.of(literal, generics);
    }

    @Override
    public Interface createInterface(@NonNull String literal) {
        return CatalogInterface.of(literal);
    }

    @Override
    public Interface createInterface(@NonNull String literal, @NonNull Generics generics) {
        return CatalogInterface.of(literal, generics);
    }

    @Override
    public Generics createGenerics() {
        return CatalogGenerics.of();
    }

    @Override
    public EnumDefinition createEnumDefinition(@NonNull String literal) {
        return CatalogEnumDefinition.of(literal);
    }

    @Override
    public Enumeration createEnumeration(@NonNull EnumDefinition enumDefinition, @NonNull Description description) {
        return CatalogEnumeration.of(enumDefinition, description);
    }

    @Override
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName) {
        return CatalogFieldDefinition.of(dataType, variableName);
    }

    @Override
    @Deprecated
    public FieldDefinition createFieldDefinition(@NonNull String dataType, @NonNull String variableName,
            @NonNull String initialValue) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public Field createField(@NonNull FieldDefinition fieldDefinition, @NonNull Description description) {
        return CatalogField.of(fieldDefinition, description);
    }

    @Override
    public FunctionDescription createFunctionDescription(@NonNull String description) {
        return CatalogMethodDescription.of(description);
    }

    @Override
    @Deprecated
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public DescriptionTag createDescriptionTag(@NonNull String variableName, @NonNull String description,
            @NonNull Annotation annotation) {
        return CatalogDescriptionTag.of(variableName, description, annotation);
    }

    @Override
    public Constructor createConstructor(@NonNull String functionName,
            @NonNull FunctionDescription functionDescription) {
        return CatalogConstructor.of(functionName, functionDescription);
    }

    @Override
    public Parameter createParameter(@NonNull String dataType, @NonNull String variableName) {
        return CatalogParameter.of(dataType, variableName);
    }

    @Override
    public ConstructorProcess createConstructorProcess(String variableName) {
        return CatalogConstructorProcess.of(variableName);
    }

    @Override
    @Deprecated
    public ConstructorProcess createConstructorProcess(@NonNull String variableName, @NonNull String getterName) {
        throw new UnsupportedOperationException(UNSUPPORTED_MESSAGE);
    }

    @Override
    public Method createMethod(@NonNull String methodName, @NonNull FunctionDescription methodDescription) {
        return CatalogMethod.of(methodName, methodDescription);
    }

    @Override
    public MethodProcess createMethodProcess(@NonNull String variableName) {
        return CatalogMethodProcess.of(variableName);
    }

    @Override
    public Resource createResource(@NonNull Copyright copyright, @NonNull String packageName,
            @NonNull ClassDescription classDescription, @NonNull String resourceName) {
        return CatalogResource.of(copyright, packageName, classDescription, resourceName);
    }
}
