package com.eduard.advancedjpa.dao;

import com.eduard.advancedjpa.entity.Instructor;
import com.eduard.advancedjpa.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
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

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteIntructorById(int theId) {

        //retrieve the instructor
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        //delete the instructor
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        //retrieve instructor detail
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class,theId);


        // remove the associated object reference
        // break bi-directional link
        tempInstructorDetail.getInstructor().setInstructorDetail(null);

        //delete the instructor detail
        //when we have cascade all, the instructor detail deletion will also trigger the associated instructor deletion
        entityManager.remove(tempInstructorDetail);
    }
}
 