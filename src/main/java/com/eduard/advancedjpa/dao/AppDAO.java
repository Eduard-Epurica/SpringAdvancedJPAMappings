package com.eduard.advancedjpa.dao;

import com.eduard.advancedjpa.entity.Course;
import com.eduard.advancedjpa.entity.Instructor;
import com.eduard.advancedjpa.entity.InstructorDetail;
import com.eduard.advancedjpa.entity.Student;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    void save(Course theCourse);

    Instructor findInstructorById(int theId);

    void deleteIntructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor theInstructor);

    void update(Course theCourse);

    Course findCourseById(int Id);


    void deleteCourseById(int theId);

     Course findCourseAndReviewsById(int theID);

     Course findCourseAndStudentsById(int theID);

     Student findStundentAndCourseById(int theID);

     void update(Student tempStudent);

    void deleteStudentById(int theId);

}
