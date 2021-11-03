package com.senla.electric.scooter.rental.model;

import com.senla.electric.scooter.rental.enums.Subscription;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "rental_history")
@SequenceGenerator(name = "default_gen", sequenceName = "rent_seq", allocationSize = 1)
public class Rent extends AbstractEntity {

    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rental_point_id", nullable = false)
    private RentalPoint rentalPoint;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scooter_id", nullable = false)
    private Scooter scooter;
    @Column
    @Enumerated(EnumType.STRING)
    private Subscription subscription;
    @Column
    private int hours;
    @Column(name = "final_price", nullable = false)
    private double finalPrice;
    @Column(nullable = false)
    private double mileage;

    public Rent() {
    }

    public static class Builder {
        private Rent newRent;

        public Builder() {
            newRent = new Rent();
        }

        public Builder withId(Long id){
            newRent.id = id;
            return this;
        }

       public Builder withRentDate(LocalDateTime rentDate){
            newRent.rentDate = rentDate;
            return this;
       }

       public Builder withAccount(Account account){
            newRent.account = account;
            return this;
       }

        public Builder withRentalPoint(RentalPoint rentalPoint){
            newRent.rentalPoint = rentalPoint;
            return this;
        }

        public Builder withScooter(Scooter scooter){
            newRent.scooter = scooter;
            return this;
        }

        public Builder withSubscription(Subscription subscription){
            newRent.subscription = subscription;
            return this;
        }

        public Builder withHours(int hours){
            newRent.hours = hours;
            return this;
        }

        public Builder withPrice(double finalPrice){
            newRent.finalPrice = finalPrice;
            return this;
        }

        public Builder withMileage(double mileage){
            newRent.mileage = mileage;
            return this;
        }

        public Rent build() {
            return newRent;
        }
    }
}
