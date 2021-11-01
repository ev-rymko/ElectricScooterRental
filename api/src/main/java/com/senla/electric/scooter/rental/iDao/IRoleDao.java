package com.senla.finalProject.iDao;

import com.senla.finalProject.model.Role;

public interface IRoleDao extends IGenericDao<Role> {

    Role findByName(String name);
}
