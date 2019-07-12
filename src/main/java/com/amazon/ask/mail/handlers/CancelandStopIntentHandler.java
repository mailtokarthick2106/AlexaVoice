package com.amazon.ask.mail.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.mail.constants.IntentNameConstants;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.model.Response;

public class CancelandStopIntentHandler extends BaseHandler {
	
    @Override
    public boolean canHandle(HandlerInput input) {
		return input.matches(intentName(IntentNameConstants.AMAZON_STOP_INTENT)
				.or(intentName(IntentNameConstants.AMAZON_CANCEL_INTENT)));
    }

	@Override
	public Optional<Response> handle(HandlerInput input) {
		return buildResponse(input, SpeechTextsConstants.STOP_SPEECH_TEXT, GeneralConstants.STOP_CANCEL_CARD_TITLE,
				true);
	}
	
	boolean validSessionNeededForRepeat() {
		return false;
	}
}
