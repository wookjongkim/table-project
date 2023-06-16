package com.reservation.tableproject.domain.store;


import com.reservation.tableproject.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Partner, Long> {

}
