package com.amazon.ask.mail.handlers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.mail.model.LastResponse;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

public abstract class BaseHandler implements RequestHandler {
	
	protected static final Logger Logger = LogManager.getLogger(BaseHandler.class);

	protected Optional<Response> buildResponse(HandlerInput handlerInput, String speechText, String title,
			boolean shouldEndSession) {
		// If we are ending session we should not have reprompt text
		boolean reprompt = !shouldEndSession;
		return buildResponse(handlerInput, speechText, title, shouldEndSession, reprompt);
	}

	Optional<Response> buildResponse(HandlerInput handlerInput, String speechText, String title,
			boolean shouldEndSession, boolean reprompt) {
		Logger.info("Building response, text : {},{}", speechText, shouldEndSession);
		storeResponse(handlerInput, speechText);
		ResponseBuilder builder = handlerInput.getResponseBuilder().withSpeech(speechText)
				.withShouldEndSession(shouldEndSession).withSimpleCard(title, speechText);
		if (reprompt) {
			builder.withReprompt(speechText);
		}
		return builder.build();
	}
	
	private void storeResponse(HandlerInput handlerInput, String response) {
		Map<String, Object> lastResponse = new HashMap();
		lastResponse.put("ValidSessionNeededForRepeat", validSessionNeededForRepeat());
		lastResponse.put("responseText", response);
		Optional.ofNullable(handlerInput.getAttributesManager().getSessionAttributes()).ifPresent(attr -> {
			Logger.info("Storing intent with attributes: {}", attr);
			attr.put(GeneralConstants.LAST_RESPONSE, lastResponse);
		});
	}
	
	boolean validSessionNeededForRepeat() {
		return true;
	}
	
	Optional<LastResponse> retrieveLastResponse(HandlerInput handlerInput) {
		Map<String, Object> attributes = handlerInput.getAttributesManager().getSessionAttributes();
		return Optional.ofNullable(attributes).map(a -> (Map) a.get(GeneralConstants.LAST_RESPONSE))
				.map(this::buildLastResponse);
	}
	
	private LastResponse buildLastResponse(Map m) {
		return new LastResponse((boolean) m.get("ValidSessionNeededForRepeat"), (String) m.get("responseText"));
	}

}
