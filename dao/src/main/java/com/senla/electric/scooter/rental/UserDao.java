package com.senla.finalProject;

import com.senla.finalProject.iDao.IUserDao;
import com.senla.finalProject.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDao extends AbstractDao<User> implements IUserDao {
    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public User getUserByEmail(String email) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot);
        Predicate predicateForName
                = cb.equal(userRoot.get("email"), email);
        criteriaQuery.where(predicateForName);
        List<User> users = entityManager.createQuery(criteriaQuery)
                .getResultList();
        if(users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

}
