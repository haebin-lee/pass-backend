package com.lucy.pass.request;

import com.lucy.pass.repository.Meeting;
import lombok.Getter;


// todo: how to use map struct for request/response
@Getter
public class MeetingRequest {

    private String name;
    private String description;

    public Meeting toEntity() {
        return Meeting.builder()
                .name(this.name)
                .description(this.description)
                .build();
    }
}
