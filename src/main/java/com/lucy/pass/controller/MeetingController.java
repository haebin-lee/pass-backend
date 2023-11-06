package com.lucy.pass.controller;

import com.lucy.pass.repository.Meeting;
import com.lucy.pass.request.AttendeeConfirmRequest;
import com.lucy.pass.request.AttendeeRequest;
import com.lucy.pass.request.MeetingRequest;
import com.lucy.pass.request.MeetingUpdateRequest;
import com.lucy.pass.response.MeetingResponse;
import com.lucy.pass.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MeetingController {

    private final GroupService groupService;

    @GetMapping("/meetings")
    public ResponseEntity<List<MeetingResponse>> findMeetings(
            @RequestParam(required = false) String key
    ) {
        List<Meeting> meetings = groupService.findGroups(key);
        return ResponseEntity.ok(meetings.stream()
                .map(MeetingResponse::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/meetings/{id}")
    public ResponseEntity<MeetingResponse> findMeeting(
            @PathVariable Long id
    ) {
        Meeting meeting = groupService.findMeetingById(id);
        return ResponseEntity.ok(new MeetingResponse(meeting));
    }

    @PostMapping("/meetings")
    public ResponseEntity<MeetingResponse> addMeetings(
            @RequestBody MeetingRequest request
    ) {
        Meeting meeting = groupService.addGroup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new MeetingResponse(meeting));
    }

    @PutMapping("/meetings/{id}")
    public ResponseEntity<MeetingResponse> updateMeeting(
            @PathVariable Long id,
            @RequestBody MeetingUpdateRequest request
    ) {
        groupService.updateGroup(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/meetings/{id}/attendees")
    public ResponseEntity<Void> addAttendees(
            @PathVariable Long id,
            @RequestBody List<AttendeeRequest> requests
    ) {
        groupService.addAttendee(id, requests);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/meetings/{key}/attendees/confirm")
    public ResponseEntity<Boolean> confirmAttendee(
            @PathVariable String key,
            @RequestBody AttendeeConfirmRequest request) {
        boolean confirm = groupService.confirmAttendee(key, request);
        return ResponseEntity.ok(confirm);
    }
}
