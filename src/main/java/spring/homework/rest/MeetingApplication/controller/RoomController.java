//package spring.homework.rest.MeetingApplication.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import spring.homework.rest.MeetingApplication.entity.Meeting;
//import spring.homework.rest.MeetingApplication.entity.Room;
//import spring.homework.rest.MeetingApplication.repository.MeetingRepository;
//import spring.homework.rest.MeetingApplication.repository.RoomRepository;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/rooms")
//public class RoomController {
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//    @Autowired
//    private MeetingRepository meetingRepository;
//
//    @PostMapping
//    public Room postRoom(@RequestBody Room room) {
//        return roomRepository.save(room);
//    }
//
//    @GetMapping
//    public List<Room> getRooms() {
//        return roomRepository.findAll();
//    }
//
//    @PutMapping("/{roomId}/meeting/{meetingId}")
//    public Room assignRoomToMeeting(@PathVariable int roomId, @PathVariable int meetingId) {
//        Room room = roomRepository.findById(roomId).get();
//        Meeting meeting = meetingRepository.findById(meetingId).get();
//        room.assignMeeting(meeting);
//        return roomRepository.save(room);
//    }
//}
