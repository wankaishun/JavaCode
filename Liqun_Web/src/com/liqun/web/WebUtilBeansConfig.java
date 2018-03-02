package com.liqun.web;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class WebUtilBeansConfig {
	
	@Bean
	public ObjectMapper jacksonObjectMapper() {
		Jackson2ObjectMapperBuilder builder = Jackson2ObjectMapperBuilder.json();
		ObjectMapper mapper = builder.build();

		// Long默认序列化为String
		SimpleModule module = new SimpleModule();
		module.addSerializer(Long.class, new LongToStringSerializer());
		mapper.registerModule(module);
		
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

//		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		return mapper;
	}

	public static class LongToStringSerializer extends JsonSerializer<Long> {

		@Override
		public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
				throws IOException, JsonProcessingException {
			jsonGenerator.writeObject(value.toString());
		}
	}
}
