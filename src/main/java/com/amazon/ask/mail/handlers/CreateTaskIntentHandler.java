package com.amazon.ask.mail.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.mail.constants.IntentNameConstants;
import com.amazon.ask.mail.constants.SlotNameConstants;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class CreateTaskIntentHandler extends BaseHandler {	

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(IntentNameConstants.CUSTOM_CREATE_TASK));
    }

    @Override
	public Optional<Response> handle(HandlerInput input) {

		IntentRequest intentReq = (IntentRequest) input.getRequestEnvelope().getRequest();
		Map<String, Slot> slotMap = intentReq.getIntent().getSlots();

		String subject = slotMap.get(SlotNameConstants.SUBJECT).getValue().toString();
		String startDate = slotMap.get(SlotNameConstants.START_DATE).getValue().toString();
		String dueDate = slotMap.get(SlotNameConstants.DUE_DATE).getValue().toString();
		String priority = slotMap.get(SlotNameConstants.PRIORITY).getValue().toString();
		StringBuilder speechText = new StringBuilder();
		speechText.append(SpeechTextsConstants.TASK_SPEECH_TEXT);
		speechText.append(subject);
		speechText.append(SpeechTextsConstants.START_DATE);
		speechText.append(startDate);
		speechText.append(SpeechTextsConstants.DUE_DATE);
		speechText.append(dueDate);
		speechText.append(SpeechTextsConstants.PRIORITY);
		if(priority.equalsIgnoreCase(GeneralConstants.LOW)) {
			speechText.append(SpeechTextsConstants.LOW_PROSODY_START);
			speechText.append(priority);
			speechText.append(SpeechTextsConstants.LOW_PROSODY_END);
		} else if(priority.equalsIgnoreCase(GeneralConstants.HIGH)) {
			speechText.append(SpeechTextsConstants.HIGH_PROSODY_START);
			speechText.append(priority);
			speechText.append(SpeechTextsConstants.HIGH_PROSODY_END);
		} else {
			speechText.append(SpeechTextsConstants.MEDIUM_PROSODY_START);
			speechText.append(priority);
			speechText.append(SpeechTextsConstants.MEDIUM_PROSODY_END);
		}		
		speechText.append(SpeechTextsConstants.PAUSE);
		speechText.append(SpeechTextsConstants.STOP_SPEECH_TEXT);											
		return buildResponse(input, speechText.toString(), GeneralConstants.TASK_CARD_TITLE, true);
	}

}
