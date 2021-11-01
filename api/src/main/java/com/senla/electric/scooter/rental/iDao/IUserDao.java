package com.senla.finalProject.iDao;

import com.senla.finalProject.model.User;

public interface IUserDao extends IGenericDao<User> {

    User getUserByEmail(String email);

}
