package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IFeedbackRepository extends JpaRepository<Feedback,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM feedbacks WHERE feedback_date = :date")
    List<Feedback> findFeedbackByDate(@Param("date") LocalDate date);

    @Query("SELECT f.code FROM Feedback f ORDER BY f.code DESC LIMIT 1")
    String findMaxFeedbackCode();

}
