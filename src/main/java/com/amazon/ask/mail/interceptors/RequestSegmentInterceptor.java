package com.amazon.ask.mail.interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.interceptor.RequestInterceptor;
import com.amazon.ask.mail.constants.GeneralConstants;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.TraceID;

public class RequestSegmentInterceptor implements RequestInterceptor {
	private static final Logger Logger = LogManager.getLogger(RequestSegmentInterceptor.class);

	@Override
	public void process(HandlerInput input) {
		Request request = input.getRequestEnvelope().getRequest();	
		if(request instanceof IntentRequest) {
			IntentRequest intentRequest = (IntentRequest) request;
			AWSXRay.beginSegment(intentRequest.getIntent().getName());
		} else {
			AWSXRay.beginSegment(request.getType());
		}
		TraceID traceId = AWSXRay.getCurrentSegment().getTraceId();
		Logger.info("Calling RequestSegmentInterceptor, TraceId :{}", traceId.toString());
		ThreadContext.put(GeneralConstants.CORRELATION_KEY, traceId.toString());
	}

	

}
