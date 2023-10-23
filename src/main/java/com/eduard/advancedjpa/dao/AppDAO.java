package com.eduard.advancedjpa.dao;

import com.eduard.advancedjpa.entity.Instructor;
import com.eduard.advancedjpa.entity.InstructorDetail;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteIntructorById(int theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(int theId);


}
