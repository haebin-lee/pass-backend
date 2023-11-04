package com.lucy.pass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findAllByOrderByCreatedAtDesc();
    Optional<Meeting> findByKey(String key);
}
