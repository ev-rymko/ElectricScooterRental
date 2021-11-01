package com.senla.finalProject.iDao;

import com.senla.finalProject.model.ScooterPrice;

public interface IScooterPriceDao extends IGenericDao<ScooterPrice> {

    ScooterPrice findByName(String name);
}
