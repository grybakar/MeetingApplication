package spring.homework.rest.MeetingApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.homework.rest.MeetingApplication.entity.Attendee;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {
}
