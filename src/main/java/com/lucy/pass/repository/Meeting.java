package com.lucy.pass.repository;


import com.lucy.pass.request.MeetingUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(mappedBy = "meeting", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Attendee> attendees = new ArrayList<>();

    private String qrUrl;

    @Column(unique=true)
    private String key;

    private LocalDateTime eventAt;

    private boolean registerNow;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Builder
    public Meeting(Long id,
                   String name,
                   String description,
                   String qrUrl,
                   String key,
                   LocalDateTime eventAt,
                   boolean registerNow) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qrUrl = qrUrl;
        this.key = key;
        this.eventAt = eventAt;
        this.registerNow = registerNow;
        this.createdAt = LocalDateTime.now();
    }

    public List<Attendee> getAttendees() {
        return this.attendees != null ? attendees : new ArrayList<>();
    }

    public void update(MeetingUpdateRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.eventAt = request.getEventAt();
        this.registerNow = request.isRegisterNow();
        this.updatedAt = LocalDateTime.now();

        this.attendees.clear();
        this.attendees = request.getAttendees().stream()
                .map(attendee -> new Attendee(this, attendee))
                .collect(Collectors.toList());
    }

    public void addAttendees(List<Attendee> attendees) {
        this.attendees.clear();
        this.attendees = attendees;
    }
}
