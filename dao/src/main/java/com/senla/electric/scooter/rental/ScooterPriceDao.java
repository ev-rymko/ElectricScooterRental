package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.iDao.IScooterPriceDao;
import com.senla.electric.scooter.rental.model.ScooterPrice;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class ScooterPriceDao extends AbstractDao<ScooterPrice> implements IScooterPriceDao {
    @Override
    public Class<ScooterPrice> getEntityClass() {
        return ScooterPrice.class;
    }

    @Override
    public ScooterPrice findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ScooterPrice> scooterPriceCriteriaQuery = cb.createQuery(ScooterPrice.class);
        Root<ScooterPrice> scooterPriceRoot = scooterPriceCriteriaQuery.from(ScooterPrice.class);
        scooterPriceCriteriaQuery.select(scooterPriceRoot);
        Predicate predicateForName
                = cb.equal(scooterPriceRoot.get("scooterType"), name);
        scooterPriceCriteriaQuery.where(predicateForName);
        List<ScooterPrice> scooterPrices = entityManager.createQuery(scooterPriceCriteriaQuery)
                .getResultList();
        return scooterPrices.get(0);
    }
}
