package com.senla.finalProject.iDao;

import com.senla.finalProject.model.Account;
import com.senla.finalProject.model.Rent;

import java.util.List;

public interface IRentDao extends IGenericDao<Rent> {

    List<Rent> getRentalHistoryForAdministrator(Long scooterId);

    List<Rent> getRentalHistoryForClient(Account account);
}
