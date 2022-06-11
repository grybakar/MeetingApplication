package spring.homework.rest.MeetingApplication.enums;


import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MeetRooms {

    SNICKERS(5),
    WHISKAS(10),
    MARS(3),
    KITEKAT(6),
    CHAPPY(7);

    final int capacity;

    MeetRooms(int capacity) {
        this.capacity = capacity;
    }
}
