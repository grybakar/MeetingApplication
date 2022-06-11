package spring.homework.rest.MeetingApplication.exception;

import java.text.MessageFormat;

public class MeetingNotFoundException extends RuntimeException {

    public MeetingNotFoundException(Integer meetingId) {
        super(MessageFormat.format("Could not find meeting with id: {0}", meetingId));
    }
}
