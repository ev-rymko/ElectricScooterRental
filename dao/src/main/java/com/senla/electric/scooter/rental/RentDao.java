package com.senla.finalProject;

import com.senla.finalProject.iDao.IRentDao;
import com.senla.finalProject.model.Account;
import com.senla.finalProject.model.Rent;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RentDao extends AbstractDao<Rent> implements IRentDao {

    @Override
    public Class<Rent> getEntityClass() {
        return Rent.class;
    }

    @Override
    public List<Rent> getRentalHistoryForAdministrator(Long scooterId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rent> criteriaQuery = cb.createQuery(Rent.class);
        Root<Rent> rentRoot = criteriaQuery.from(Rent.class);
        criteriaQuery.select(rentRoot);
        Predicate predicateForScooterId
                = cb.equal(rentRoot.get("scooter"), scooterId);
        criteriaQuery.where(predicateForScooterId);
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<Rent> getRentalHistoryForClient(Account account) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Rent> criteriaQuery = cb.createQuery(Rent.class);
        Root<Rent> rentRoot = criteriaQuery.from(Rent.class);
        criteriaQuery.select(rentRoot);
        Predicate predicateForAccount
                = cb.equal(rentRoot.get("account"), account.getId());
        criteriaQuery.where(predicateForAccount);
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }
}
