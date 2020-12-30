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

import org.thinkit.common.catalog.Brace;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.duke.factory.ClassBody;
import org.thinkit.generator.common.duke.factory.ClassDescription;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスのボディ部を生成する処理を定義したファクトリークラスです。
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogClassBody extends ClassBody {

    /**
     * 改行
     */
    private static final String RETURN = Indentation.RETURN.getTag();

    /**
     * コンストラクタ
     *
     * @param classDescription クラスの説明
     * @param resourceName     リソース名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogClassBody(@NonNull ClassDescription classDescription, @NonNull String resourceName) {
        super(classDescription, resourceName);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogClassBody} クラスの新しいインスタンスを生成し返却します。
     *
     * @param classDescription クラスの説明
     * @param resourceName     リソース名
     * @return {@link CatalogClassBody} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    protected static ClassBody of(@NonNull ClassDescription classDescription, @NonNull String resourceName) {
        return new CatalogClassBody(classDescription, resourceName);
    }

    @Override
    public String createResource() {

        final StringBuilder classBody = new StringBuilder();

        this.createClassDescription(classBody);
        this.createClassBody(classBody);

        return classBody.toString();
    }

    /**
     * クラスの説明を表現する文字列リソースを生成しカタログのクラスボディへ追加します。
     *
     * @param classBody カタログのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createClassDescription(@NonNull StringBuilder classBody) {
        classBody.append(super.getClassDescription().createResource());
        classBody.append(RETURN);
    }

    /**
     * クラスのボディ部を表現する文字列リソースを生成しカタログのクラスボディへ追加します。
     *
     * @param classBody カタログのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createClassBody(@NonNull StringBuilder classBody) {

        if (this.isAppliedLombok()) {
            super.getAnnotations().forEach(annotation -> {
                classBody.append(annotation.createResource());
                classBody.append(RETURN);
            });
        }

        classBody.append(String.format("public enum %s implements %s {", super.getResourceName(),
                super.getInterfaces().get(0).createResource()));
        classBody.append(RETURN).append(RETURN);

        this.createEnumeration(classBody);
        this.createField(classBody);

        if (!this.isAppliedLombok()) {
            this.createConstructor(classBody);
            this.createMethod(classBody);
        }

        classBody.append(Brace.END.getTag());
        classBody.append(RETURN);
    }

    /**
     * 列挙子を表現する文字列リソースを生成しカタログのクラスボディへ追加します。
     *
     * @param classBody カタログのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createEnumeration(@NonNull StringBuilder classBody) {
        super.getEnumerations().forEach(enumeration -> {
            classBody.append(String.format("%s,", enumeration.createResource()));
            classBody.append(RETURN).append(RETURN);
        });

        classBody.setLength(classBody.length() - (1 + RETURN.length() * 2));
        classBody.append(Delimiter.SEMICOLON.getTag());
        classBody.append(RETURN).append(RETURN);
    }

    /**
     * フィールドを表現する文字列リソースを生成しカタログのクラスボディへ追加します。
     *
     * @param classBody カタログの
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createField(@NonNull StringBuilder classBody) {
        super.getFields().forEach(field -> {
            classBody.append(field.createResource());
            classBody.append(RETURN).append(RETURN);
        });

        classBody.setLength(classBody.length() - RETURN.length());
    }

    /**
     * コンストラクタを表現する文字列リソースを生成しカタログのクラスボディへ追加します。
     *
     * @param classBody カタログのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createConstructor(@NonNull StringBuilder classBody) {
        super.getConstructors().forEach(constructor -> {
            classBody.append(constructor.createResource());
            classBody.append(RETURN).append(RETURN);
        });

        classBody.setLength(classBody.length() - RETURN.length());
    }

    /**
     * メソッドを表現する文字列リソースを生成しカタログのクラスボディへ追加します。
     *
     * @param classBody カタログのクラスボディ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private void createMethod(@NonNull StringBuilder classBody) {
        super.getMethods().forEach(method -> {
            classBody.append(method.createResource());
            classBody.append(RETURN).append(RETURN);
        });

        classBody.setLength(classBody.length() - RETURN.length());
    }
}
