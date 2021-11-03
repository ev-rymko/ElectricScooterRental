package com.senla.electric.scooter.rental.iDao;

import com.senla.electric.scooter.rental.model.ScooterPrice;

public interface IScooterPriceDao extends IGenericDao<ScooterPrice> {

    ScooterPrice findByName(String name);
}
