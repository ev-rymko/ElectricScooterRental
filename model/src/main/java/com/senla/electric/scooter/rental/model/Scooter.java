package com.senla.finalProject.model;

import com.senla.finalProject.enums.ScooterType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "scooters")
@SequenceGenerator(name = "default_gen", sequenceName = "scooter_seq", allocationSize = 1)
public class Scooter extends AbstractEntity {

    @Column(name = "scooter_type")
    @Enumerated(EnumType.STRING)
    private ScooterType type;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String details;
    @Column(name = "scooter_condition", nullable = false)
    private int condition = 5;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_point_id", nullable = false)
    private RentalPoint rentalPoint;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "scooter")
    private List<Rent> rentList;

    public Scooter() {
    }
}
