package com.senla.electric.scooter.rental;

import com.senla.electric.scooter.rental.iDao.IScooterDao;
import com.senla.electric.scooter.rental.model.Scooter;
import org.springframework.stereotype.Repository;

@Repository
public class ScooterDao extends AbstractDao<Scooter> implements IScooterDao {

    @Override
    public Class<Scooter> getEntityClass() {
        return Scooter.class;
    }
}
