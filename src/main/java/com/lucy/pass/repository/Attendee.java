package com.lucy.pass.repository;

import com.lucy.pass.dto.AttendanceStatus;
import com.lucy.pass.request.AttendeeRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(schema = "pass", name = "attendee")
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @NotEmpty
    private String name;

    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @Builder
    public Attendee(Meeting meeting
            , String name
            , String email
            , String phone
            , AttendanceStatus status) {
        this.meeting = meeting;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
    }

    public Attendee(Meeting meeting, AttendeeRequest request) {
        this.meeting = meeting;
        this.name = request.getName();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.status = request.getStatus();
    }
}
