package com.amazon.ask.mail.util;

import static java.util.stream.Collectors.joining;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.RequestEnvelope;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HandlerUtil {
	public static HandlerInput getRequestBody(String jsonResource, String ...dynamicElements) throws Exception {
		String requestBody = String.format(
				Files.lines(Paths.get(HandlerUtil.class.getResource(jsonResource).toURI())).collect(joining(" ")),
				dynamicElements);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RequestEnvelope envelope = mapper.readValue(requestBody, RequestEnvelope.class);
		return HandlerInput.builder().withRequestEnvelope(envelope).build();
	}

}
