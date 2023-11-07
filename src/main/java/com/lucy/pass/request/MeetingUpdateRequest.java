package com.lucy.pass.request;

import com.lucy.pass.dto.VerificationMethod;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class MeetingUpdateRequest {

    @NotEmpty
    private String name;
    private String description;
    private LocalDateTime eventAt;
    private VerificationMethod verificationMethod;
    private boolean registerNow;
    private List<AttendeeRequest> attendees;

    public List<AttendeeRequest> getAttendees() {
        return attendees == null ? new ArrayList<>() : attendees;
    }
}
