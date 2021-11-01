package com.senla.finalProject;

import com.senla.finalProject.iDao.IRentalPointDao;
import com.senla.finalProject.model.RentalPoint;
import com.senla.finalProject.model.Scooter;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class RentalPointDao extends AbstractDao<RentalPoint> implements IRentalPointDao {

    @Override
    public Class<RentalPoint> getEntityClass() {
        return RentalPoint.class;
    }

    @Override
    public List<RentalPoint> getAllInCountry(String country) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentalPoint> criteriaQuery = cb.createQuery(RentalPoint.class);
        Root<RentalPoint> rentalPointRoot = criteriaQuery.from(RentalPoint.class);
        criteriaQuery.select(rentalPointRoot);
        Predicate predicateForCountry
                = cb.equal(rentalPointRoot.get("country"), country);
        criteriaQuery.where(predicateForCountry);
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<RentalPoint> getAllInCity(String city) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RentalPoint> criteriaQuery = cb.createQuery(RentalPoint.class);
        Root<RentalPoint> rentalPointRoot = criteriaQuery.from(RentalPoint.class);
        criteriaQuery.select(rentalPointRoot);
        Predicate predicateForCity
                = cb.equal(rentalPointRoot.get("city"), city);
        criteriaQuery.where(predicateForCity);
        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<Scooter> getDetails(Long id) {
        return getById(id).getScooters();
    }
}
