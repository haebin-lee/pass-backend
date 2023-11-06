package com.lucy.pass.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucy.pass.dto.VerificationMethod;
import com.lucy.pass.repository.Meeting;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


// todo: how to use map struct for request/response
@Getter
public class MeetingRequest {

    @NotEmpty
    private String name;
    private String description;
    private String qrUrl;
    private String key;
    private LocalDateTime eventAt;
    private VerificationMethod verificationMethod;
    private boolean registerNow;
    private List<AttendeeRequest> attendees;

    public Meeting toEntity() {
        return Meeting.builder()
                .name(this.name)
                .description(this.description)
                .qrUrl(this.qrUrl)
                .key(this.key)
                .eventAt(this.eventAt)
                .verificationMethod(verificationMethod)
                .registerNow(this.registerNow)
                .build();
    }
}
