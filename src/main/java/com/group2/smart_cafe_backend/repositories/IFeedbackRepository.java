package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {
    @Query(nativeQuery = true,value = "SELECT * FROM feedbacks WHERE feedback_date = :date")
    Feedback findFeedbackByDate( @Param("date") LocalDate date);
}
