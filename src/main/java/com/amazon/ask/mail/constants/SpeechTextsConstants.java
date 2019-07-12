package com.amazon.ask.mail.constants;

public class SpeechTextsConstants {
	
	public static final String WELCOME_SPEECH_TEXT = "Welcome to innovation lab.<audio src=\"soundbank://soundlibrary/human/amzn_sfx_crowd_applause_01\"/> You can schedule a meeting, create an appointment, add tasks to calender. What can i do for you?";
	public static final String HELP_SPEECH_TEXT =  "<voice name=\"Brian\">You can schedule a meeting, create an appointment, add tasks to calender</voice>";
	public static final String STOP_SPEECH_TEXT = "<emphasis level=\"strong\">Goodbye</emphasis>";
	public static final String FALLBACK_SPEECH_TEXT = "Sorry, I don't know what you have asked. You can say try saying help!";
	public static final String NO_REPEAT_SPEECH_TEXT = "I am Sorry, There is nothing to repeat at this time";
	public static final String APPOINTMENT_SPEECH_TEXT = "Congratulations, your Appointment was created successfully. Your Appointment Details : Subject : ";
	public static final String MEETING_SPEECH_TEXT = "Congratulations, your Meeting was created successfully. Your Meeting Details : Subject : ";
	public static final String TASK_SPEECH_TEXT = "Congratulations, your task was created successfully. Your Task Details : Subject : ";
	public static final String UNHANDLED_EXCEPTION_SPEECH_TEXT = "An Error Occured while handling your mail request. Please try again later.";
	public static final String START_DATE = " , Start Date : "; 
	public static final String DUE_DATE = " , Due Date : "; 
	public static final String START_TIME = " , Start Time : "; 
	public static final String END_TIME = " , End Time : "; 
	public static final String LOCATION = " , Location : "; 
	public static final String PRIORITY = ", Priority: ";	
	public static final String RECEIPIENTS = " , Receipients : ";
	public static final String PAUSE = "<break time=\"2s\"/>";
	public static final String LOW_PROSODY_START = "<prosody pitch=\"low\">";
	public static final String LOW_PROSODY_END = "</prosody>";
	public static final String HIGH_PROSODY_START = "<prosody pitch=\"fast\">";
	public static final String HIGH_PROSODY_END = "</prosody>";
	public static final String MEDIUM_PROSODY_START = "<prosody pitch=\"medium\">";
	public static final String MEDIUM_PROSODY_END = "</prosody>";
}
