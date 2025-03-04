package org.finos.rune.serialization;

/*-
 * ==============
 * Rune Common
 * ==============
 * Copyright (C) 2018 - 2024 REGnosys
 * ==============
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==============
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Injector;
import com.regnosys.rosetta.tests.util.CodeGeneratorTestHelper;
import com.rosetta.model.lib.RosettaModelObject;
import org.finos.rune.mapper.RuneJsonObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

import static org.finos.rune.serialization.RuneSerializerTestHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuneJsonSerializerRoundTripTest {
    public static final String TEST_TYPE = "rune-serializer-round-trip-test";
    private static DynamicCompiledClassLoader dynamicCompiledClassLoader;
    public static final String NAMESPACE_PREFIX = TEST_MODEL_NAME + ".test.passing.";

    private static CodeGeneratorTestHelper helper;
    private ObjectMapper objectMapper;

    @BeforeAll
    static void beforeAll() {
        Injector injector = setupInjector();
        helper = injector.getInstance(CodeGeneratorTestHelper.class);
        dynamicCompiledClassLoader = new DynamicCompiledClassLoader();
    }

    @BeforeEach
    void setUp() {
        objectMapper = new RuneJsonObjectMapper();
        objectMapper.setTypeFactory(objectMapper.getTypeFactory().withClassLoader(dynamicCompiledClassLoader));
    }

    @ParameterizedTest(name = "{0} - {1}")
    @MethodSource("testCases")
    public void testSerializationRoundTrip(String group, String testCaseName, Class<? extends RosettaModelObject> rosettaRootType, String jsonString) {
        RosettaModelObject deserializedObject = fromJson(jsonString, rosettaRootType);
        String serializedjsonString = toJson(deserializedObject);
        assertEquals(jsonString, serializedjsonString, testCaseName + ": Serialization round trip failed");
    }

    public static Stream<Arguments> testCases() {
        return groups(TEST_TYPE).stream()
                .flatMap(groupPath -> {
                            List<Path> rosettas = listFiles(groupPath, ".rosetta");
                            String groupName = groupPath.getFileName().toString();
                    Class<RosettaModelObject> rootDataType = generateCompileAndGetRootDataType(NAMESPACE_PREFIX, groupName, rosettas, helper, dynamicCompiledClassLoader);

                            return listFiles(groupPath, ".json").stream()
                                    .map(jsonPath -> Arguments.of(
                                            groupName,
                                            jsonPath.getFileName().toString(),
                                            rootDataType,
                                            readAsString(jsonPath)
                                    ));
                        }
                );
    }

    private <T extends RosettaModelObject> T fromJson(String runeJson, Class<T> type) {
        try {
            return objectMapper.readValue(runeJson, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T extends RosettaModelObject> String toJson(T runeObject) {
        try {
            return objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(runeObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
