package com.lucy.pass.response;

import com.lucy.pass.repository.Meeting;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MeetingResponse {

    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MeetingResponse(Meeting meeting) {
        this.name = meeting.getName();
        this.description = meeting.getDescription();
        this.createdAt = meeting.getCreatedAt();
        this.updatedAt = meeting.getUpdatedAt();
    }
}
