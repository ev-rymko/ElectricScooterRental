package com.senla.finalProject.iDao;

import com.senla.finalProject.model.Account;

public interface IAccountDao extends IGenericDao<Account> {

    Account getUserByLogin(String login);
}
