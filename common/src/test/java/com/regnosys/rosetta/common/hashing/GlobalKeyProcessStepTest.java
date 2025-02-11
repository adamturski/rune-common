package com.regnosys.rosetta.common.hashing;

/*-
 * ==============
 * Rune Common
 * ==============
 * Copyright (C) 2018 - 2025 REGnosys
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

import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.Price;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.PriceAndPriceReference;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.FieldWithMetaPrice;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.MetaFields;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.ReferenceWithMetaPrice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GlobalKeyProcessStepTest {

    @Test
    void testSettingGlobalKeyOnFieldWithMeta() {
        // given
        String priceExternalKey = "price-1";
        PriceAndPriceReference priceAndPriceReference = PriceAndPriceReference.builder()
                .setPrice(FieldWithMetaPrice.builder()
                        .setValue(Price.builder()
                                .setRate(BigDecimal.ONE)
                                .build())
                        .setMeta(MetaFields.builder()
                                .setExternalKey(priceExternalKey)
                                .build())
                        .build()
                )
                .setResolvedPrice(ReferenceWithMetaPrice.builder()
                        .setExternalReference(priceExternalKey)
                        .build())
                .build();

        GlobalKeyProcessStep globalKeyProcessStep = new GlobalKeyProcessStep(NonNullHashCollector::new);

        // when
        globalKeyProcessStep.runProcessStep(PriceAndPriceReference.class, priceAndPriceReference);

        // then
        assertNotNull(priceAndPriceReference.getPrice().getMeta().getGlobalKey());
    }
}
