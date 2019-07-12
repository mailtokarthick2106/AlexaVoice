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

public class CreateAppointmentIntentHandler extends BaseHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(IntentNameConstants.CUSTOM_CREATE_APPOINTMENTS));
    }

    @Override
	public Optional<Response> handle(HandlerInput input) {
		IntentRequest intentReq = (IntentRequest) input.getRequestEnvelope().getRequest();
		Map<String, Slot> slotMap = intentReq.getIntent().getSlots();

		Slot subject = slotMap.get(SlotNameConstants.SUBJECT);
		Slot location = slotMap.get(SlotNameConstants.LOCATION);
		Slot startTime = slotMap.get(SlotNameConstants.START_TIME);
		Slot endTime = slotMap.get(SlotNameConstants.END_TIME);

		String speechText = SpeechTextsConstants.APPOINTMENT_SPEECH_TEXT + subject.getValue().toString()
				+ SpeechTextsConstants.START_TIME + startTime.getValue().toString() + SpeechTextsConstants.END_TIME
				+ endTime.getValue().toString() + SpeechTextsConstants.LOCATION + location.getValue().toString()
				+ SpeechTextsConstants.PAUSE + SpeechTextsConstants.STOP_SPEECH_TEXT;		
		return buildResponse(input, speechText, GeneralConstants.APPOINTMENTS_CARD_TITLE, true);
	}

}
