package com.lucy.pass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    Optional<Attendee> findByMeetingAndName(Meeting meeting, String name);
    Optional<Attendee> findByMeetingAndEmail(Meeting meeting, String email);
    Optional<Attendee> findByMeetingAndPhone(Meeting meeting, String phone);
}
