package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.iDao.IAccountDao;
import com.senla.electric.scooter.rental.model.Account;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class AccountDao extends AbstractDao<Account> implements IAccountDao {

    @Override
    public Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account getUserByLogin(String login) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = cb.createQuery(Account.class);
        Root<Account> accountRoot = criteriaQuery.from(Account.class);
        criteriaQuery.select(accountRoot);
        Predicate predicateForName
                = cb.equal(accountRoot.get("login"), login);
        criteriaQuery.where(cb.isNotNull(accountRoot.get("login")), predicateForName);
        List<Account> accountList = entityManager.createQuery(criteriaQuery)
                .getResultList();
        if(accountList != null && accountList.size() > 0) {
            return accountList.get(0);
        } else {
            return null;
        }
    }
}
