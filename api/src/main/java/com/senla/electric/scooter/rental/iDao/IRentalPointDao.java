package com.senla.finalProject.iDao;

import com.senla.finalProject.model.RentalPoint;
import com.senla.finalProject.model.Scooter;

import java.util.List;

public interface IRentalPointDao extends IGenericDao<RentalPoint> {

    List<RentalPoint> getAllInCountry(String country);

    List<RentalPoint> getAllInCity(String city);

    List<Scooter> getDetails(Long id);
}
