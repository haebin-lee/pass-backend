package com.lucy.pass.response;

import com.lucy.pass.repository.Attendee;
import lombok.Getter;

@Getter
public class AttendeeResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String phone;

    public AttendeeResponse(Attendee attendee) {
        this.id = attendee.getId();
        this.name = attendee.getName();
        this.email = attendee.getEmail();
        this.phone = attendee.getPhone();
    }
}
