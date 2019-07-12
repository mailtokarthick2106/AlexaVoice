package com.amazon.ask.mail.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.mail.constants.IntentNameConstants;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.model.Response;

public class FallbackIntentHandler extends BaseHandler {	

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(IntentNameConstants.AMAZON_FALLBACK_INTENT));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = SpeechTextsConstants.FALLBACK_SPEECH_TEXT;        
        return buildResponse(input, speechText, GeneralConstants.FALLBACK_CARD_TITLE, false);
    }

}
