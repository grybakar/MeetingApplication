package spring.homework.rest.MeetingApplication.exception;

import java.text.MessageFormat;

public class AttendeeNotFoundException extends RuntimeException{

    public AttendeeNotFoundException(Integer id) {
        super(MessageFormat.format("Could not find attendee with id: {0}", id));
    }
}
