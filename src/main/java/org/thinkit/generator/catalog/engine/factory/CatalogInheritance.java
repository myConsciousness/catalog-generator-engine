package org.thinkit.generator.catalog.engine.factory;

import org.thinkit.generator.common.factory.resource.Generics;
import org.thinkit.generator.common.factory.resource.Inheritance;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * カタログクラスの継承を生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することで継承を表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogInheritance extends Inheritance {

    private CatalogInheritance(@NonNull String literal) {
        super(literal);
    }

    private CatalogInheritance(@NonNull String literal, @NonNull Generics generics) {
        super(literal, generics);
    }

    @Override
    public String createResource() {
        return null;
    }
}
