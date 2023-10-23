package dao;

import entity.Instructor;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class AppDAOImpl implements AppDAO{

    //define field to entity manager
    private EntityManager entityManager;

    //inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
       //this will also cascade to the Instructo Detail beacuse of CascadeType.all
        entityManager.persist(theInstructor);
    }
}
 