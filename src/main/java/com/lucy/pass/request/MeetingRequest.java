package com.lucy.pass.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lucy.pass.repository.Meeting;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;


// todo: how to use map struct for request/response
@Getter
public class MeetingRequest {

    @NotEmpty
    private String name;
    private String description;
    private String qrUrl;
    private String validationUrl;
    private LocalDateTime eventAt;
    private boolean registerNow;

    public Meeting toEntity() {
        return Meeting.builder()
                .name(this.name)
                .description(this.description)
                .qrUrl(this.qrUrl)
                .validationUrl(this.validationUrl)
                .eventAt(this.eventAt)
                .registerNow(this.registerNow)
                .build();
    }
}
