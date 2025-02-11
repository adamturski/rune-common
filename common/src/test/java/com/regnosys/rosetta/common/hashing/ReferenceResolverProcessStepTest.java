package com.regnosys.rosetta.common.hashing;

import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.Price;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.PriceAndPriceReference;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.FieldWithMetaPrice;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.MetaFields;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.ReferenceWithMetaPrice;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReferenceResolverProcessStepTest {

    @Test
    void testResolvingExternalReferenceWhenSourceIsFieldWithMeta() {
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

        ReKeyProcessStep reKeyProcessStep = new ReKeyProcessStep(new GlobalKeyProcessStep(NonNullHashCollector::new));
        ReferenceResolverProcessStep referenceResolverProcessStep = new ReferenceResolverProcessStep(ReferenceConfig.noScopeOrExcludedPaths());

        // when
        reKeyProcessStep.runProcessStep(PriceAndPriceReference.class, priceAndPriceReference);
        referenceResolverProcessStep.runProcessStep(PriceAndPriceReference.class, priceAndPriceReference);

        // then
        assertNotNull(priceAndPriceReference.getResolvedPrice().getValue());
    }
}
