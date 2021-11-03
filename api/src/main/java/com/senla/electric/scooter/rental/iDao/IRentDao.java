package com.senla.electric.scooter.rental.iDao;

import com.senla.electric.scooter.rental.model.Account;
import com.senla.electric.scooter.rental.model.Rent;

import java.util.List;

public interface IRentDao extends IGenericDao<Rent> {

    List<Rent> getRentalHistoryForAdministrator(Long scooterId);

    List<Rent> getRentalHistoryForClient(Account account);
}
