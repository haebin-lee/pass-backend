package com.lucy.pass.service;

import com.lucy.pass.exception.BusinessException;
import com.lucy.pass.exception.PassException;
import com.lucy.pass.repository.Attendee;
import com.lucy.pass.repository.AttendeeRepository;
import com.lucy.pass.repository.Meeting;
import com.lucy.pass.repository.MeetingRepository;
import com.lucy.pass.request.AttendeeConfirmRequest;
import com.lucy.pass.request.AttendeeRequest;
import com.lucy.pass.request.MeetingRequest;
import com.lucy.pass.request.MeetingUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return meetingRepository.findById(id).orElseThrow(() -> new BusinessException(PassException.NOT_FOUND_MEETING));
    }

    @Transactional
    public Meeting addGroup(MeetingRequest request) {
        Meeting meeting = request.toEntity();
        return meetingRepository.save(meeting);
    }

    @Transactional
    public void updateGroup(Long id, MeetingUpdateRequest request) {
        Meeting meeting = findMeetingById(id);
        meeting.update(request);
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

    @Transactional
    public boolean confirmAttendee(String key, AttendeeConfirmRequest request) {

        Optional<Meeting> mOptional = meetingRepository.findByKey(key);
        if (mOptional.isEmpty()) return false;
        Optional<Attendee> aOptional = attendeeRepository.findByMeetingAndName(mOptional.get(), request.getName());
        if (aOptional.isPresent()) {
            Attendee attendee = aOptional.get();
            attendee.confirmAttendance();
            return true;
        }
        return false;
    }
}
