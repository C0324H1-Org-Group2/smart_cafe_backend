package com.group2.smart_cafe_backend.repositories;

import com.group2.smart_cafe_backend.models.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
}
