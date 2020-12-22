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

import com.google.googlejavaformat.java.Formatter;
import com.google.googlejavaformat.java.FormatterException;
import com.google.googlejavaformat.java.JavaFormatterOptions;
import com.google.googlejavaformat.java.JavaFormatterOptions.Style;

import org.thinkit.common.catalog.Indentation;
import org.thinkit.generator.common.factory.resource.ClassDescription;
import org.thinkit.generator.common.factory.resource.Copyright;
import org.thinkit.generator.common.factory.resource.Resource;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = false)
public final class CatalogResource extends Resource {

    private CatalogResource(@NonNull Copyright copyright, @NonNull String packageName,
            @NonNull ClassDescription classDescription, @NonNull String resourceName) {
        super(copyright, packageName, classDescription, resourceName);
    }

    protected static Resource of(@NonNull Copyright copyright, @NonNull String packageName,
            @NonNull ClassDescription classDescription, @NonNull String resourceName) {
        return new CatalogResource(copyright, packageName, classDescription, resourceName);
    }

    @Override
    public String createResource() {

        final StringBuilder resource = new StringBuilder();
        final String returnCode = Indentation.RETURN.getTag();

        resource.append(super.getCopyright().createResource());
        resource.append(returnCode);

        resource.append(String.format("package %s;", super.getPackageName()));
        resource.append(returnCode).append(returnCode);

        resource.append(super.getClassDescription().createResource());
        resource.append(returnCode);

        resource.append(String.format("public enum %s implements %s {", super.getResourceName(),
                super.getInterfaces().get(0).createResource()));
        resource.append(returnCode).append(returnCode);

        super.getEnumerations().forEach(enumeration -> {
            resource.append(String.format("%s,", enumeration.createResource()));
            resource.append(returnCode).append(returnCode);
        });

        resource.setLength(resource.length() - (1 + returnCode.length() * 2));
        resource.append(";");
        resource.append(returnCode).append(returnCode);

        super.getFields().forEach(field -> {
            resource.append(field.createResource());
            resource.append(returnCode).append(returnCode);
        });

        resource.setLength(resource.length() - returnCode.length());

        super.getConstructors().forEach(constructor -> {
            resource.append(constructor.createResource());
            resource.append(returnCode).append(returnCode);
        });

        resource.setLength(resource.length() - returnCode.length());

        super.getMethods().forEach(method -> {
            resource.append(method.createResource());
            resource.append(returnCode).append(returnCode);
        });

        resource.setLength(resource.length() - returnCode.length());

        resource.append("}");
        resource.append(returnCode);

        try {
            return new Formatter(JavaFormatterOptions.builder().style(Style.AOSP).build())
                    .formatSource(resource.toString());
        } catch (FormatterException e) {
            throw new IllegalStateException(e);
        }
    }
}
