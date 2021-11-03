package com.senla.electric.scooter.rental.iDao;

import com.senla.electric.scooter.rental.model.RentalPoint;
import com.senla.electric.scooter.rental.model.Scooter;

import java.util.List;

public interface IRentalPointDao extends IGenericDao<RentalPoint> {

    List<RentalPoint> getAllInCountry(String country);

    List<RentalPoint> getAllInCity(String city);

    List<Scooter> getDetails(Long id);
}
