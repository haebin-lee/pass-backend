package com.lucy.pass.repository;


import com.lucy.pass.dto.VerificationMethod;
import com.lucy.pass.request.AttendeeRequest;
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

    @OneToMany(mappedBy = "meeting", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Attendee> attendees = new ArrayList<>();

    private String qrUrl;

    @Column(unique=true)
    private String key;

    private LocalDateTime eventAt;

    @Enumerated(EnumType.STRING)
    private VerificationMethod verificationMethod;

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
                   boolean registerNow,
                   VerificationMethod verificationMethod) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qrUrl = qrUrl;
        this.key = key;
        this.eventAt = eventAt;
        this.registerNow = registerNow;
        this.verificationMethod = verificationMethod;
        this.createdAt = LocalDateTime.now();
    }

    public List<Attendee> getAttendees() {
        return this.attendees != null ? attendees : new ArrayList<>();
    }

    public void update(MeetingUpdateRequest request) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.eventAt = request.getEventAt();
        this.verificationMethod = request.getVerificationMethod();
        this.registerNow = request.isRegisterNow();
        this.updatedAt = LocalDateTime.now();

        this.attendees.clear();
        List<Attendee> updated = request.getAttendees().stream()
                .map(attendeeData -> new Attendee(this, attendeeData))
                .toList();
        this.attendees.addAll(updated);
    }

    public void addAttendees(List<Attendee> attendees) {
        this.attendees.clear();
        this.attendees = attendees;
    }
}
