package com.reservation.tableproject.domain.partner;

import com.reservation.tableproject.BaseEntity;
import com.reservation.tableproject.domain.store.Store;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partners")
public class Partner extends BaseEntity {

    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Store> stores;
}
