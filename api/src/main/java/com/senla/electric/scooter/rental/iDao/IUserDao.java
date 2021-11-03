package com.senla.electric.scooter.rental.iDao;

import com.senla.electric.scooter.rental.model.User;

public interface IUserDao extends IGenericDao<User> {

    User getUserByEmail(String email);

}
