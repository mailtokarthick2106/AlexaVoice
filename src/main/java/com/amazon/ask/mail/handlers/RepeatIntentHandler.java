package com.amazon.ask.mail.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.mail.constants.IntentNameConstants;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.mail.model.LastResponse;
import com.amazon.ask.model.Response;

public class RepeatIntentHandler extends BaseHandler {
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(IntentNameConstants.AMAZON_REPEAT_INTENT));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	LastResponse lastResponse = retrieveLastResponse(input).orElse(null); 
        String speechText = SpeechTextsConstants.WELCOME_SPEECH_TEXT;
        
        if(lastResponse != null) {
        	if(lastResponse.isSessionCheckRequired()) {
        		speechText = lastResponse.getResponseText();
        	}
        } else {
        	speechText = SpeechTextsConstants.NO_REPEAT_SPEECH_TEXT;
        }        
        return buildResponse(input, speechText, GeneralConstants.REPEAT_CARD_TITLE, false);
    }
}
