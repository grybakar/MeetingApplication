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

import java.util.List;


@RestController
@RequestMapping("/meetings")
public class MeetingController {

    Logger LOGGER = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private AttendeeRepository attendeeRepository;

    @GetMapping("/findAll")
    public List<Meeting> meetings() {
        LOGGER.warn("FIND ALL MEETINGS");
        return meetingRepository.findAll();
    }

    @GetMapping("/findById/{meetingId}")
    public Meeting getMeetingById(@PathVariable int id) {
        LOGGER.info("FINDING MEETING WITH Id - {}", id);
        return meetingRepository
                .findById(id)
                .orElseThrow(() -> new MeetingNotFoundException(id));
    }

    @PostMapping("/add")
    public Meeting postMeeting(@RequestBody Meeting meeting) {
        LOGGER.info("NEW MEETING ADDED {}", meeting);
        return meetingRepository.save(meeting);
    }

    @DeleteMapping("/delete/{id}")
    public List<Meeting> deleteMeeting(@PathVariable int id) {
        meetingRepository.deleteById(id);
        LOGGER.warn("MEETING WITH ID: {}, DELETED", id);
        return meetingRepository.findAll();
    }

    @PutMapping("/update/{meetingId}")
    public Meeting updateMeeting(@RequestBody Meeting meeting, @PathVariable int meetingId) {
        LOGGER.info("UPDATING MEETING WITH ID: {}, full info {}", meetingId, meeting);
        Meeting meetingToUpdate = meetingRepository
                .findById(meetingId)
                .orElseThrow(() -> new MeetingNotFoundException(meetingId));
        meetingToUpdate.setTitle(meeting.getTitle());
        return meetingRepository.save(meetingToUpdate);
    }
}