/**
 * Copyright (C) 2019-2023 Expedia, Inc.
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
package com.expediagroup.beans.sample.immutable;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Sample immutable object containing extremely complex map.
 */
@AllArgsConstructor
@Getter
@ToString
public class ImmutableToFooMap {
    private final Map<String, String> sampleMap;
    private final Map<String, List<String>> complexMap;
    private final Map<String, Map<String, String>> veryComplexMap;
    private final Map<ImmutableToFooSimple, Map<String, String>> extremeComplexMap;
    private final Map unparametrizedMap;
}
