package com.lucy.pass.response;

import com.lucy.pass.dto.VerificationMethod;
import com.lucy.pass.repository.Meeting;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class MeetingResponse {

    private final Long id;
    private final String name;
    private final String description;
    private final List<AttendeeResponse> attendees;
    private final String qrUrl;
    private final String key;
    private final LocalDateTime eventAt;
    private final boolean registerNow;
    private final VerificationMethod verificationMethod;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public MeetingResponse(Meeting meeting) {
        this.id = meeting.getId();
        this.name = meeting.getName();
        this.description = meeting.getDescription();
        this.attendees = meeting.getAttendees().stream()
                .map(AttendeeResponse::new)
                .collect(Collectors.toList());
        this.qrUrl = meeting.getQrUrl();
        this.key = meeting.getKey();
        this.eventAt = meeting.getEventAt();
        this.registerNow = meeting.isRegisterNow();
        this.verificationMethod = meeting.getVerificationMethod();
        this.createdAt = meeting.getCreatedAt();
        this.updatedAt = meeting.getUpdatedAt();
    }
}
