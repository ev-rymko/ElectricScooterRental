package com.senla.finalProject;

import com.senla.finalProject.iDao.IScooterDao;
import com.senla.finalProject.model.Scooter;
import org.springframework.stereotype.Repository;

@Repository
public class ScooterDao extends AbstractDao<Scooter> implements IScooterDao {

    @Override
    public Class<Scooter> getEntityClass() {
        return Scooter.class;
    }
}
