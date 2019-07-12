package com.amazon.ask.mail.handlers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.mail.constants.IntentNameConstants;
import com.amazon.ask.mail.constants.SpeechTextsConstants;
import com.amazon.ask.mail.util.HandlerUtil;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.ui.SsmlOutputSpeech;
import com.amazon.ask.request.Predicates;

public class HelpIntentHandlerTest {
	private HelpIntentHandler helpIntentHandler;
	private HandlerInput handlerInput;

	@Before
	public void setup() throws Exception{
		helpIntentHandler = new HelpIntentHandler();
		handlerInput = HandlerUtil.getRequestBody("/help-request.json");
	}
	
	@Test
	public void testHelpIntentName() {
		assertNotNull(handlerInput);
		boolean isHelpIntent = helpIntentHandler.canHandle(handlerInput);
		Assert.assertEquals(true, isHelpIntent);
	}
	
	@Test
	public void testHandleRequestBody() {
		assertNotNull(handlerInput);
		Assert.assertEquals("ApplicationID Not Matching", "amzn1.ask.skill.68f6468b-555d-43ad-aae5-d89c1516c020",
				handlerInput.getRequestEnvelope().getContext().getSystem().getApplication().getApplicationId());
		Assert.assertEquals("SessionID Not Matching", "amzn1.echo-api.session.788eed79-6155-47e5-8046-05f29129ea32",
				handlerInput.getRequestEnvelope().getSession().getSessionId());
		assertNull(handlerInput.getRequestEnvelope().getContext().getSystem().getUser().getAccessToken());
		Assert.assertEquals("User ID Not Matching",
				"amzn1.ask.account.AEZFP23KMZ2E5HPTKJU7J5CZ2PMUKNIWKZEDOQH5IDNRGHCKOTJRG4YJWTNEOHCXHSDMNGV5YAEORQOP3V4J2AIZ4IPUKQIX35GAWRUVWVIBGJKH7CQORBS7DGA5DHI2DD7VNZUX6RD7A4RLWYKGSW5GWCIY7R7IHFNSE3G4ZBXTCUVE25BMRZWZZT5L5V43KVIRHB5K5WF3HYA",
				handlerInput.getRequestEnvelope().getContext().getSystem().getUser().getUserId());
		Assert.assertEquals("Device ID Not Matching",
				"amzn1.ask.device.AFISTDYVK3XJQJYESRPPKZZZGYVTL2FR5M6K4RYK2UDADFP432HOSNP2YQ5PYNNXYBR2T4RIODRSBY3AWO4BBU72ECBXYNBTTFQTB67W3MMBXVVFL4BIBOGS6UHQDMHPFZMTP47N4ZVRAZ3OYLPHVDMYOR5KKRMRNWHUJG6T57CBU4IDBUIZ2",
				handlerInput.getRequestEnvelope().getContext().getSystem().getDevice().getDeviceId());
		Assert.assertEquals("Help Intent Name not matching", true, handlerInput.matches(Predicates.intentName(IntentNameConstants.AMAZON_HELP_INTENT)));
	}
	
	@Test
	public void testHandleResponseBody() {
		Optional<Response> skillResponse = helpIntentHandler.handle(handlerInput);
		Response response = skillResponse.orElse(null);
		assertNotNull(response);
		SsmlOutputSpeech actualOutputSpeech = (SsmlOutputSpeech) response.getOutputSpeech();
		SsmlOutputSpeech actualRepromptActualSpeech = (SsmlOutputSpeech) response.getReprompt().getOutputSpeech();
		Assert.assertEquals("SSML", actualOutputSpeech.getType());
		Assert.assertEquals("<speak>"+SpeechTextsConstants.HELP_SPEECH_TEXT+"</speak>", actualOutputSpeech.getSsml());
		Assert.assertEquals("SSML", actualRepromptActualSpeech.getType());
		Assert.assertEquals("<speak>"+SpeechTextsConstants.HELP_SPEECH_TEXT+"</speak>", actualRepromptActualSpeech.getSsml());
	}
	
	
}
