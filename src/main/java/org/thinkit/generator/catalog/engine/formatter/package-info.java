/**
 * 与えられた入力を実際に解析可能なカタログリソースへ整形するフォーマッタークラスを管理するパッケージです。
 *
 * <pre>
 * 操作例:
 * <code>
 * CatalogResourceFormatter.newInstance().format(catalogMatrix).foreach(catalogResource -> {
 *      * // do something like
 *      catalogResource.getPackageName();
 *      catalogResource.getClassName();
 *      catalogResource.getResource();
 * });
 * </code>
 * </pre>
 */
package org.thinkit.generator.catalog.engine.formatter;
