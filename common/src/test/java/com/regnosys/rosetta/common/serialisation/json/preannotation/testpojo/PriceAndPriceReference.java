package com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo;

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

import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.FieldWithMetaPrice;
import com.regnosys.rosetta.common.serialisation.json.preannotation.testpojo.metafields.ReferenceWithMetaPrice;
import com.rosetta.model.lib.RosettaModelObject;
import com.rosetta.model.lib.RosettaModelObjectBuilder;
import com.rosetta.model.lib.annotations.RosettaClass;
import com.rosetta.model.lib.meta.Key;
import com.rosetta.model.lib.meta.RosettaMetaData;
import com.rosetta.model.lib.path.RosettaPath;
import com.rosetta.model.lib.process.BuilderMerger;
import com.rosetta.model.lib.process.BuilderProcessor;
import com.rosetta.model.lib.process.Processor;

import java.util.Objects;

import static java.util.Optional.ofNullable;

/**
 * @version test
 */
@RosettaClass
public interface PriceAndPriceReference extends RosettaModelObject {


	/*********************** Getter Methods  ***********************/
	FieldWithMetaPrice getPrice();
	ReferenceWithMetaPrice getResolvedPrice();

	/*********************** Build Methods  ***********************/
	PriceAndPriceReference build();
	
	PriceAndPriceReferenceBuilder toBuilder();
	
	static PriceAndPriceReferenceBuilder builder() {
		return new PriceAndPriceReferenceBuilderImpl();
	}

	/*********************** Utility Methods  ***********************/
	@Override
	default RosettaMetaData<? extends PriceAndPriceReference> metaData() {
		return null;
	}
	
	@Override
	default Class<? extends PriceAndPriceReference> getType() {
		return PriceAndPriceReference.class;
	}
	
	
	        @Override
	        default void process(RosettaPath path, Processor processor) {
	        	processRosetta(path.newSubPath("price"), processor, FieldWithMetaPrice.class, getPrice());
				processRosetta(path.newSubPath("resolvedPrice"), processor, ReferenceWithMetaPrice.class, getResolvedPrice());
			}
	        

	/*********************** Builder Interface  ***********************/
	interface PriceAndPriceReferenceBuilder extends PriceAndPriceReference, RosettaModelObjectBuilder {
		FieldWithMetaPrice.FieldWithMetaPriceBuilder getOrCreatePrice();
		FieldWithMetaPrice.FieldWithMetaPriceBuilder getPrice();
		PriceAndPriceReference.PriceAndPriceReferenceBuilder setPrice(FieldWithMetaPrice price0);
		PriceAndPriceReference.PriceAndPriceReferenceBuilder setPriceValue(Price price1);
		ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder getOrCreateResolvedPrice();
		ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder getResolvedPrice();
		PriceAndPriceReference.PriceAndPriceReferenceBuilder setResolvedPrice(ReferenceWithMetaPrice resolvedPrice0);
		PriceAndPriceReference.PriceAndPriceReferenceBuilder setResolvedPriceValue(Price resolvedPrice1);

		@Override
		default void process(RosettaPath path, BuilderProcessor processor) {
			processRosetta(path.newSubPath("price"), processor, FieldWithMetaPrice.FieldWithMetaPriceBuilder.class, getPrice());
			processRosetta(path.newSubPath("resolvedPrice"), processor, ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder.class, getResolvedPrice());
		}
		

		PriceAndPriceReferenceBuilder prune();
	}

	/*********************** Immutable Implementation of PriceAndPriceReference  ***********************/
	class PriceAndPriceReferenceImpl implements PriceAndPriceReference {
		private final FieldWithMetaPrice price;
		private final ReferenceWithMetaPrice resolvedPrice;
		
		protected PriceAndPriceReferenceImpl(PriceAndPriceReferenceBuilder builder) {
			this.price = ofNullable(builder.getPrice()).map(f->f.build()).orElse(null);
			this.resolvedPrice = ofNullable(builder.getResolvedPrice()).map(f->f.build()).orElse(null);
		}
		
		@Override
		public FieldWithMetaPrice getPrice() {
			return price;
		}

		@Override
		public ReferenceWithMetaPrice getResolvedPrice() {
			return resolvedPrice;
		}
		
		@Override
		public PriceAndPriceReference build() {
			return this;
		}
		
		@Override
		public PriceAndPriceReferenceBuilder toBuilder() {
			PriceAndPriceReferenceBuilder builder = builder();
			setBuilderFields(builder);
			return builder;
		}
		
		protected void setBuilderFields(PriceAndPriceReferenceBuilder builder) {
			ofNullable(getPrice()).ifPresent(builder::setPrice);
			ofNullable(getResolvedPrice()).ifPresent(builder::setResolvedPrice);
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceAndPriceReference _that = getType().cast(o);
		
			if (!Objects.equals(price, _that.getPrice())) return false;
			if (!Objects.equals(resolvedPrice, _that.getResolvedPrice())) return false;
			
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (price != null ? price.hashCode() : 0);
			_result = 31 * _result + (resolvedPrice != null ? resolvedPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceAndPriceReference {" +
				"price=" + this.price +
				"resolvedPrice=" + this.resolvedPrice +
			'}';
		}
	}

	/*********************** Builder Implementation of PriceAndPriceReference  ***********************/
	class PriceAndPriceReferenceBuilderImpl implements PriceAndPriceReferenceBuilder {
	
		protected FieldWithMetaPrice.FieldWithMetaPriceBuilder price;
		protected ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder resolvedPrice;
	
		public PriceAndPriceReferenceBuilderImpl() {
		}
	
		@Override
		public FieldWithMetaPrice.FieldWithMetaPriceBuilder getPrice() {
			return price;
		}
		
		@Override
		public FieldWithMetaPrice.FieldWithMetaPriceBuilder getOrCreatePrice() {
			FieldWithMetaPrice.FieldWithMetaPriceBuilder result;
			if (price!=null) {
				result = price;
			}
			else {
				result = price = FieldWithMetaPrice.builder();
				result.getOrCreateMeta().toBuilder().addKey(Key.builder().setScope("DOCUMENT"));
			}
			
			return result;
		}
	
		@Override
		public PriceAndPriceReference.PriceAndPriceReferenceBuilder setPrice(FieldWithMetaPrice price) {
			this.price = price==null?null:price.toBuilder();
			return this;
		}
		@Override
		public PriceAndPriceReference.PriceAndPriceReferenceBuilder setPriceValue(Price price) {
			this.getOrCreatePrice().setValue(price);
			return this;
		}

		@Override
		public ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder getResolvedPrice() {
			return resolvedPrice;
		}

		@Override
		public ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder getOrCreateResolvedPrice() {
			ReferenceWithMetaPrice.ReferenceWithMetaPriceBuilder result;
			if (resolvedPrice!=null) {
				result = resolvedPrice;
			}
			else {
				result = resolvedPrice = ReferenceWithMetaPrice.builder();
			}

			return result;
		}

		@Override
		public PriceAndPriceReference.PriceAndPriceReferenceBuilder setResolvedPrice(ReferenceWithMetaPrice resolvedPrice) {
			this.resolvedPrice = resolvedPrice==null?null:resolvedPrice.toBuilder();
			return this;
		}
		@Override
		public PriceAndPriceReference.PriceAndPriceReferenceBuilder setResolvedPriceValue(Price resolvedPrice) {
			this.getOrCreateResolvedPrice().setValue(resolvedPrice);
			return this;
		}
		
		@Override
		public PriceAndPriceReference build() {
			return new PriceAndPriceReferenceImpl(this);
		}
		
		@Override
		public PriceAndPriceReferenceBuilder toBuilder() {
			return this;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceAndPriceReferenceBuilder prune() {
			if (price!=null && !price.prune().hasData()) price = null;
			if (resolvedPrice!=null && !resolvedPrice.prune().hasData()) resolvedPrice = null;
			return this;
		}
		
		@Override
		public boolean hasData() {
			if (getPrice()!=null && getPrice().hasData()) return true;
			if (getResolvedPrice()!=null && getResolvedPrice().hasData()) return true;
			return false;
		}
	
		@SuppressWarnings("unchecked")
		@Override
		public PriceAndPriceReferenceBuilder merge(RosettaModelObjectBuilder other, BuilderMerger merger) {
			PriceAndPriceReferenceBuilder o = (PriceAndPriceReferenceBuilder) other;
			
			merger.mergeRosetta(getPrice(), o.getPrice(), this::setPrice);
			merger.mergeRosetta(getResolvedPrice(), o.getResolvedPrice(), this::setResolvedPrice);
			
			return this;
		}
	
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || !(o instanceof RosettaModelObject) || !getType().equals(((RosettaModelObject)o).getType())) return false;
		
			PriceAndPriceReference _that = getType().cast(o);
		
			if (!Objects.equals(price, _that.getPrice())) return false;
			if (!Objects.equals(resolvedPrice, _that.getResolvedPrice())) return false;
			return true;
		}
		
		@Override
		public int hashCode() {
			int _result = 0;
			_result = 31 * _result + (price != null ? price.hashCode() : 0);
			_result = 31 * _result + (resolvedPrice != null ? resolvedPrice.hashCode() : 0);
			return _result;
		}
		
		@Override
		public String toString() {
			return "PriceAndPriceReferenceBuilder {" +
				"price=" + this.price +
				"resolvedPrice=" + this.resolvedPrice +
			'}';
		}
	}
}
