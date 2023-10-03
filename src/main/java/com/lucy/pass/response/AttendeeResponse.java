package com.lucy.pass.response;

import com.lucy.pass.repository.Attendee;
import lombok.Getter;

@Getter
public class AttendeeResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    public AttendeeResponse(Attendee attendee) {
        this.id = attendee.getId();
        this.name = attendee.getName();
        this.email = attendee.getEmail();
        this.phone = attendee.getPhone();
    }
}
