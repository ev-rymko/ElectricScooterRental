package com.senla.electric.scooter.rental.iDao;

import com.senla.electric.scooter.rental.model.Role;

public interface IRoleDao extends IGenericDao<Role> {

    Role findByName(String name);
}
