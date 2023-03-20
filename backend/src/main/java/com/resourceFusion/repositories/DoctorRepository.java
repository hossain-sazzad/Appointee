package com.resourceFusion.repositories;

import com.resourceFusion.models.Doctor;
import com.resourceFusion.models.Specialty;
import com.resourceFusion.models.geo.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.Date;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("select d from  Doctor d join d.serviceArea sa join d.specialties sp join Schedule sch " +
            "on sch.doctor = d " +
            "where (:city is null or sa = :city) and " +
            "(:speciality is null or :speciality = sp) and " +
            "(:dayOfWeek is null or :dayOfWeek = sch.dayOfWeek) and " +
            "(:searchKey is null or d.name like :searchKey%)  " +
            "group by d")
    public Page<Doctor> findDoctors (
            Optional<City> city,
            Optional<Specialty> specialty,
            Optional<DayOfWeek> dayOfWeek,
            Optional<String> searchKey,
            Pageable pageable
    );


}
