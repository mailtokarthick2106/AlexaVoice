package com.amazon.ask.mail;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.mail.exception.ExceptionIntentHandler;
import com.amazon.ask.mail.handlers.CancelandStopIntentHandler;
import com.amazon.ask.mail.handlers.CreateAppointmentIntentHandler;
import com.amazon.ask.mail.handlers.CreateMeetingIntentHandler;
import com.amazon.ask.mail.handlers.CreateTaskIntentHandler;
import com.amazon.ask.mail.handlers.FallbackIntentHandler;
import com.amazon.ask.mail.handlers.HelpIntentHandler;
import com.amazon.ask.mail.handlers.LaunchRequestHandler;
import com.amazon.ask.mail.handlers.RepeatIntentHandler;
import com.amazon.ask.mail.handlers.SessionEndedRequestHandler;
import com.amazon.ask.mail.interceptors.RequestSegmentInterceptor;
import com.amazon.ask.mail.interceptors.ResponseSegmentInterceptor;

public class MailStreamHandler extends SkillStreamHandler {

    @SuppressWarnings("unchecked")
	private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new CreateTaskIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler(),
                        new RepeatIntentHandler(),
                        new CreateMeetingIntentHandler(),
                        new CreateAppointmentIntentHandler(),
                        new FallbackIntentHandler())
                .addExceptionHandler(new ExceptionIntentHandler())
                .addRequestInterceptor(new RequestSegmentInterceptor())
                .addResponseInterceptor(new ResponseSegmentInterceptor())                
                .withSkillId("amzn1.ask.skill.68f6468b-555d-43ad-aae5-d89c1516c020")
                .build();
    }

    public MailStreamHandler() {
        super(getSkill());
    }

}
