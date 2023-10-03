package com.lucy.pass.service;

import com.lucy.pass.repository.Meeting;
import com.lucy.pass.repository.MeetingRepository;
import com.lucy.pass.request.MeetingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// todo: design throw object

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class GroupService {

    private final MeetingRepository meetingRepository;

    public List<Meeting> findGroups() {
        return meetingRepository.findAll();
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
        findMeetingById(id).update(request.getName(), request.getDescription());
    }
}
