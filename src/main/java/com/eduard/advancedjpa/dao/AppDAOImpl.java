package com.eduard.advancedjpa.dao;

import com.eduard.advancedjpa.entity.Course;
import com.eduard.advancedjpa.entity.Instructor;
import com.eduard.advancedjpa.entity.InstructorDetail;
import com.eduard.advancedjpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

        // get the courses for this specific instructor
        List<Course> courses = tempInstructor.getCourses();

        // break association of all courses for the instructor
        // if we dont remove the instructor from the courses we will have a constraint violation
        for(Course course:courses){
            course.setInstructor(null);
        }

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

    //Finding courses by the instructor ID
    //By default this just returns the courses that match the ID, they are not automatically associated with the instructor
    //We need to associate the instructor with the actual list of courses we retrieve from this method
    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        //create a custom query for retrieving the courses by instructor ID
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE instructor.id = :data", Course.class);
        query.setParameter("data",theId);

        //execute query
        List<Course> courses = query.getResultList();

        return courses;

    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        //create query
        //i means instructor
        TypedQuery<Instructor> query = entityManager.createQuery(
                                                    "SELECT i FROM Instructor i "
                                                        +"JOIN FETCH i.courses "
                                                            +"JOIN FETCH i.instructorDetail "
                                                            +"WHERE i.id= :data", Instructor.class);

        query.setParameter("data",theId);

        //execute query
        Instructor instructor = query.getSingleResult();

        return instructor;

    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {

        //delete the instructor
        entityManager.merge(theInstructor);

    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int Id) {
        return entityManager.find(Course.class, Id);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        //retrieve the course
        Course tempCourse = entityManager.find(Course.class,theId);

        //delete the course
        entityManager.remove(tempCourse);

    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        //retrieve the student
        Student tempStudent = entityManager.find(Student.class,theId);

        //delete the course
        entityManager.remove(tempStudent);

    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        //This will save the course and associated reviews due to CASCADE type ALL
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsById(int theID) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c from Course c "
                +"JOIN FETCH c.reviews "
                +"WHERE c.id = :data", Course.class);

        query.setParameter("data",theID);

        // execute query
        Course course = query.getSingleResult();

        return course;

    }

    @Override
    public Course findCourseAndStudentsById(int theID) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "SELECT c from Course c "
                        +"JOIN FETCH c.students "
                        +"WHERE c.id = :data", Course.class);

        query.setParameter("data",theID);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findStundentAndCourseById(int theID) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "SELECT s FROM Student s "
                        +"JOIN FETCH s.courses "
                        +"WHERE s.id = :data", Student.class);

        query.setParameter("data",theID);

        // execute query
        Student theStudent = query.getSingleResult();

        return theStudent;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }
}
 