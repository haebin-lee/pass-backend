package com.lucy.pass.response;

import com.lucy.pass.repository.Attendee;
import com.lucy.pass.repository.Meeting;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MeetingResponse {

    private Long id;
    private String name;
    private String description;
    private List<AttendeeResponse> attendees;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MeetingResponse(Meeting meeting) {
        this.id = meeting.getId();
        this.name = meeting.getName();
        this.description = meeting.getDescription();
        this.attendees = meeting.getAttendees().stream()
                .map(AttendeeResponse::new)
                .collect(Collectors.toList());
        this.createdAt = meeting.getCreatedAt();
        this.updatedAt = meeting.getUpdatedAt();
    }
}
