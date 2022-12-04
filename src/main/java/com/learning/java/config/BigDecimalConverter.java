package com.learning.java.config;

import java.math.BigDecimal;
import java.util.Arrays;

import org.bson.types.Decimal128;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

@Configuration
public class BigDecimalConverter {

	@Bean
	public CustomConversions customConverions() {
		Converter<Decimal128, BigDecimal> decimal128ToBigDecimal = new Converter<Decimal128, BigDecimal>() {
			@Override
			public BigDecimal convert(Decimal128 s) {
				return s == null ? null : s.bigDecimalValue();
			}
		};

		Converter<BigDecimal, Decimal128> bigDecimalToDecimal128 = new Converter<BigDecimal, Decimal128>() {
			@Override
			public Decimal128 convert(BigDecimal s) {
				return s == null ? null : new Decimal128(s);
			}
		};

		return new CustomConversions(Arrays.asList(decimal128ToBigDecimal, bigDecimalToDecimal128));
	}

}
