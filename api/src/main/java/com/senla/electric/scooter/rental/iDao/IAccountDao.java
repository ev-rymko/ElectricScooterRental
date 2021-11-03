package com.senla.electric.scooter.rental.iDao;

import com.senla.electric.scooter.rental.model.Account;

public interface IAccountDao extends IGenericDao<Account> {

    Account getUserByLogin(String login);
}
