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

import org.thinkit.generator.common.factory.resource.Copyright;

import lombok.NonNull;

/**
 * カタログクラスの著作権を生成する処理を定義したファクトリークラスです。
 * <p>
 * {@link #createResource()} メソッドを使用することで著作権を表現する文字列を取得することができます。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class CatalogCopyright extends Copyright {

    /**
     * デフォルトコンストラクタ
     */
    private CatalogCopyright() {
    }

    /**
     * 引数として与えられた情報を基に {@link CatalogCopyright} クラスの新しいインスタンスを生成します。
     *
     * @param projectName  プロジェクト名
     * @param fileName     ファイル名
     * @param encoding     文字コード
     * @param creator      作成者
     * @param creationDate 作成日付
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private CatalogCopyright(@NonNull String projectName, @NonNull String fileName, @NonNull String encoding,
            @NonNull String creator, @NonNull String creationDate) {
        super(projectName, fileName, encoding, creator, creationDate);
    }

    /**
     * 引数として渡された情報を基に {@link CatalogCopyright} クラスの新しいインスタンスを生成し返却します。
     *
     * @param creator 作成者
     * @return {@link CatalogCopyright} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static Copyright of(@NonNull String creator) {
        return new CatalogCopyright("", "", "", creator, "");
    }

    @Override
    public String createResource() {
        return """
                /*
                 * Copyright 2020 %s.
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
                """.formatted(super.getCreator());
    }
}
