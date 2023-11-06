package com.lucy.pass.service;

import com.lucy.pass.dto.VerificationMethod;
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
import java.util.stream.Collectors;

// todo: design throw object

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GroupService {

    private final MeetingRepository meetingRepository;
    private final AttendeeRepository attendeeRepository;

    public List<Meeting> findGroups(String key) {
        if (key == null)
            return meetingRepository.findAllByOrderByCreatedAtDesc();
        return meetingRepository.findByKey(key).stream().collect(Collectors.toList());
    }

    public Meeting findMeetingById(Long id) {
        return meetingRepository.findById(id).orElseThrow(() -> new BusinessException(PassException.NOT_FOUND_MEETING));
    }

    @Transactional
    public Meeting addGroup(MeetingRequest request) {
        Meeting meeting = request.toEntity();
        meetingRepository.save(meeting);
        addAttendee(meeting, request.getAttendees());
        return meeting;
    }

    @Transactional
    public void updateGroup(Long id, MeetingUpdateRequest request) {
        Meeting meeting = findMeetingById(id);
        meeting.update(request);
    }

    @Transactional
    public void addAttendee(Long id, List<AttendeeRequest> requests) {
        Meeting meeting = findMeetingById(id);
        addAttendee(meeting, requests);
    }

    @Transactional
    public void addAttendee(Meeting meeting,  List<AttendeeRequest> requests) {
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
        Meeting meeting = mOptional.get();
        Optional<Attendee> attendee;
        if (VerificationMethod.NAME == meeting.getVerificationMethod()) {
            attendee = attendeeRepository.findByMeetingAndName(mOptional.get(), request.getName());
        } else if (VerificationMethod.EMAIL == meeting.getVerificationMethod()) {
            attendee = attendeeRepository.findByMeetingAndEmail(mOptional.get(), request.getEmail());
        } else if (VerificationMethod.PHONE == meeting.getVerificationMethod()){
            attendee = attendeeRepository.findByMeetingAndPhone(mOptional.get(), request.getPhone());
        } else {
            attendee = attendeeRepository.findByMeetingAndName(meeting, request.getName());
        }
        if (attendee.isPresent()) {
            attendee.get().confirmAttendance();
            return true;
        }
        return false;
    }
}
