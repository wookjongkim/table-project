package com.reservation.tableproject.domain.user;


import com.reservation.tableproject.domain.partner.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Partner, Long> {

}
