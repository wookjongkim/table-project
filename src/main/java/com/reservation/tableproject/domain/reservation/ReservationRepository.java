package com.reservation.tableproject.domain.reservation;


import com.reservation.tableproject.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Partner, Long> {

}
