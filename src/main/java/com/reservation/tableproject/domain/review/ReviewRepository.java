package com.reservation.tableproject.domain.review;


import com.reservation.tableproject.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
