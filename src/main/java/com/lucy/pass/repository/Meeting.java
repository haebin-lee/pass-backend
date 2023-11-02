package com.lucy.pass.repository;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "pass", name = "meeting")
public final class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    private String description;

    @OneToMany(mappedBy = "meeting")
    private List<Attendee> attendees = new ArrayList<>();

    private String qrUrl;

    private String validationUrl;

    private LocalDateTime eventAt;

    private boolean registerNow;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Meeting(Long id,
                   String name,
                   String description,
                   String qrUrl,
                   String validationUrl,
                   LocalDateTime eventAt,
                   boolean registerNow) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qrUrl = qrUrl;
        this.validationUrl = validationUrl;
        this.eventAt = eventAt;
        this.registerNow = registerNow;
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
