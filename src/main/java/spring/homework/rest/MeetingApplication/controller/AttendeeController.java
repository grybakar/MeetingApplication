package spring.homework.rest.MeetingApplication.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.homework.rest.MeetingApplication.entity.Attendee;
import spring.homework.rest.MeetingApplication.entity.Meeting;
import spring.homework.rest.MeetingApplication.exception.AttendeeNotFoundException;
import spring.homework.rest.MeetingApplication.exception.MeetingNotFoundException;
import spring.homework.rest.MeetingApplication.repository.AttendeeRepository;
import spring.homework.rest.MeetingApplication.repository.MeetingRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/attendees")
public class AttendeeController {

    Logger LOGGER = LoggerFactory.getLogger(AttendeeController.class);

    @Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/findAll")
    public List<Attendee> getAttendees() {
        LOGGER.warn("FIND ALL ATTENDEES");
        List<Attendee> all = attendeeRepository.findAll();
        return all;
    }

    @GetMapping("/findAll/meeting/{meetingId}")
        public List<Attendee> getAttendeesByMeeting(@PathVariable Integer meetingId) {
        Meeting meetingToBeFiltered = meetingRepository
                .findById(meetingId)
                .orElseThrow(() -> new MeetingNotFoundException(meetingId));
        return new ArrayList<>(meetingToBeFiltered.getAttendees());
    }

    @GetMapping("/find/{id}")
    public Attendee getAttendeeById(@PathVariable int id) {
        LOGGER.info("FINDING ATTENDEE WITH Id - {}", id);
        return attendeeRepository
                .findById(id)
                .orElseThrow(() -> new AttendeeNotFoundException(id));
    }

    @PostMapping("/add")
    public Attendee PostAttendee(@RequestBody Attendee attendee) {
        LOGGER.info("NEW ATTENDEE ADDED {}", attendee);
        return attendeeRepository.save(attendee);
    }

    @DeleteMapping("/delete/{id}")
    public List<Attendee> deleteAttendeeById(@PathVariable int id) {
        attendeeRepository.deleteById(id);
        LOGGER.warn("ATTENDEE WITH ID: {}, DELETED", id);
        return attendeeRepository.findAll();
    }

    @PutMapping("/update/{attendeeId}")
    public Attendee updateAttendee(@RequestBody Attendee attendee, @PathVariable int attendeeId) {
        LOGGER.info("UPDATING ATTENDEE WITH ID: {}, full info {}", attendeeId, attendee);
        Attendee attendeeToUpdate = attendeeRepository
                .findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException(attendeeId));
        attendeeToUpdate.setEmail(attendee.getEmail());
        attendeeToUpdate.setName(attendee.getName());
        return attendeeRepository.save(attendeeToUpdate);
    }

    @PutMapping("/assignMeeting/{attendeeId}/meetings/{meetingId}")
    public Attendee assignMeetingToAttendee(@PathVariable Integer attendeeId, @PathVariable Integer meetingId) {
        LOGGER.info("ASSIGNING MEETING WITH ID: {}, TO ATTENDEE WITH ID: {}", meetingId, attendeeId);
        Attendee attendeeToAssign = attendeeRepository
                .findById(attendeeId)
                .orElseThrow(() -> new AttendeeNotFoundException(attendeeId));
        Meeting meetingToAssign = meetingRepository
                .findById(meetingId)
                .orElseThrow(() -> new MeetingNotFoundException(meetingId));
        attendeeToAssign.setMeeting(meetingToAssign);
        return attendeeRepository.save(attendeeToAssign);
    }

}
