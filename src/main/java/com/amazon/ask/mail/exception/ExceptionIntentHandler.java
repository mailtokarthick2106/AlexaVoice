package com.amazon.ask.mail.exception;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.amazon.ask.dispatcher.exception.ExceptionHandler;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.exception.AskSdkException;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.model.Response;

public class ExceptionIntentHandler implements ExceptionHandler{

	private static final Logger Logger = LogManager.getLogger(ExceptionIntentHandler.class);
	@Override
	public boolean canHandle(HandlerInput input, Throwable throwable) {
		return throwable instanceof AskSdkException;
	}

	@Override
	public Optional<Response> handle(HandlerInput input, Throwable throwable) {
		Logger.error("Exception Message :{},{}",throwable.getMessage(),throwable);
		return input.getResponseBuilder()
                .withSpeech(SpeechTextsConstants.UNHANDLED_EXCEPTION_SPEECH_TEXT)
                .withShouldEndSession(true).build();
	}

}
