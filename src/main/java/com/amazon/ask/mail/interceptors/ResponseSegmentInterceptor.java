package com.amazon.ask.mail.interceptors;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.ResponseInterceptor;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.model.Response;
import com.amazonaws.xray.AWSXRay;

public class ResponseSegmentInterceptor implements ResponseInterceptor {
	private static final Logger Logger = LogManager.getLogger(ResponseSegmentInterceptor.class);

	@Override
	public void process(HandlerInput input, Optional<Response> response) {
		ThreadContext.put(GeneralConstants.CORRELATION_KEY, "");
		Logger.info("Calling ResponseSegmentInterceptor");
		AWSXRay.endSegment();		
	}

	

}
