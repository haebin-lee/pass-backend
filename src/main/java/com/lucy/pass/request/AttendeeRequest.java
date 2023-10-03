package com.lucy.pass.request;

import com.lucy.pass.repository.Attendee;
import com.lucy.pass.repository.Meeting;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class AttendeeRequest {

    @NotEmpty
    private String name;

    private String email;

    private String phone;

    public Attendee toEntity(Meeting meeting) {
        return Attendee.builder()
                .meeting(meeting)
                .name(this.name)
                .email(this.email)
                .phone(this.phone)
                .build();
    }
}
