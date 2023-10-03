package com.lucy.pass.repository;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Attendee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;

    @NotEmpty
    private String name;

    private String email;

    private String phone;

    @Builder
    public Attendee(Meeting meeting, String name, String email, String phone) {
        this.meeting = meeting;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
