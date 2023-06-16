package com.reservation.tableproject.domain.store;

import com.reservation.tableproject.BaseEntity;
import com.reservation.tableproject.domain.partner.Partner;
import com.reservation.tableproject.domain.reservation.Reservation;
import com.reservation.tableproject.domain.review.Review;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stores")
public class Store extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partner_id")
    private Partner partner;

    private String name;
    private String location;
    private String description;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Review> reviews;
}
