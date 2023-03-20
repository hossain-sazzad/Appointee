package com.appointee.repositories;
import com.appointee.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    public List<Schedule> findByDoctor_IdAndIsActive(Long id, Boolean isActive);
}
