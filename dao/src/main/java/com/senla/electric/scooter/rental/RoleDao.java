package com.senla.finalProject;

import com.senla.finalProject.iDao.IRoleDao;
import com.senla.finalProject.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RoleDao extends AbstractDao<Role> implements IRoleDao {

    @Override
    public Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    public Role findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> rolesCriteria = cb.createQuery(Role.class);
        Root<Role> roleRoot = rolesCriteria.from(Role.class);
        rolesCriteria.select(roleRoot);
        Predicate predicateForName
                = cb.equal(roleRoot.get("name"), name);
        rolesCriteria.where(predicateForName);
        List<Role> roles = entityManager.createQuery(rolesCriteria)
                .getResultList();
        return roles.get(0);
    }
}
