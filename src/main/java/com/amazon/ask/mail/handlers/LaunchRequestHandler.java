package com.amazon.ask.mail.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

public class LaunchRequestHandler extends BaseHandler{
	
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = SpeechTextsConstants.WELCOME_SPEECH_TEXT;        
        return buildResponse(input, speechText, GeneralConstants.WELCOME_TITLE, false);
    }

}
