package com.lucy.pass.repository;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public final class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    @OneToMany(mappedBy = "meeting")
    private List<Attendee> attendees = new ArrayList<>();

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Meeting(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }

    public List<Attendee> getAttendees() {
        return this.attendees != null ? attendees : new ArrayList<>();
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void addAttendees(List<Attendee> attendees) {
        this.attendees.clear();
        this.attendees = attendees;
    }
}
