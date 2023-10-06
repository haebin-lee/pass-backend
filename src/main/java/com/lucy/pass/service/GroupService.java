package com.lucy.pass.service;

import com.lucy.pass.repository.Attendee;
import com.lucy.pass.repository.AttendeeRepository;
import com.lucy.pass.repository.Meeting;
import com.lucy.pass.repository.MeetingRepository;
import com.lucy.pass.request.AttendeeRequest;
import com.lucy.pass.request.MeetingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// todo: design throw object

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GroupService {

    private final MeetingRepository meetingRepository;
    private final AttendeeRepository attendeeRepository;

    public List<Meeting> findGroups() {
        return meetingRepository.findAllByOrderByCreatedAtDesc();
    }

    public Meeting findMeetingById(Long id) {
        return meetingRepository.findById(id).get();
    }

    @Transactional
    public void addGroup(MeetingRequest request) {
        Meeting meeting = request.toEntity();
        meetingRepository.save(meeting);
    }

    @Transactional
    public void updateGroup(Long id, MeetingRequest request) {
        Meeting meeting = findMeetingById(id);
        meeting.update(request.getName(), request.getDescription());
    }

    @Transactional
    public void addAttendee(Long id, List<AttendeeRequest> requests) {
        Meeting meeting = findMeetingById(id);
        List<Attendee> attendees = requests.stream()
                .map(m -> m.toEntity(meeting))
                .toList();
        attendeeRepository.saveAll(attendees);
        meeting.addAttendees(attendees);
    }
}
