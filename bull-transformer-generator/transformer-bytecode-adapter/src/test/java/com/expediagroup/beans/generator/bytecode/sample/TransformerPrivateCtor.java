/**
 * Copyright (C) 2019-2021 Expedia, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expediagroup.beans.generator.bytecode.sample;

import static lombok.AccessLevel.PRIVATE;

import com.expediagroup.beans.generator.core.Transformer;
import com.expediagroup.beans.generator.core.sample.javabean.Destination;
import com.expediagroup.beans.generator.core.sample.javabean.Source;

import lombok.NoArgsConstructor;

/**
 * Non-compliant transformer implementation: constructor is not accessible.
 */
@NoArgsConstructor(access = PRIVATE)
public final class TransformerPrivateCtor implements Transformer<Source, Destination> {
    @Override
    public Destination transform(final Source source) {
        return null;
    }
}