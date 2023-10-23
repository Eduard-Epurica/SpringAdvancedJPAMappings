package com.eduard.advancedjpa;

import com.eduard.advancedjpa.dao.AppDAO;
import com.eduard.advancedjpa.entity.Course;
import com.eduard.advancedjpa.entity.Instructor;
import com.eduard.advancedjpa.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedjpaApplication {

	public static void main(String[] args) {

		SpringApplication.run(AdvancedjpaApplication.class, args);

	}

	//this method is annoated with @Bean
	//spring will inject the AppDAO automatically
	//so there is no need for Autowired
	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			// createInstructor(appDAO);

			// findInstructor(appDAO);

			// removeInstructor(appDAO);

			// findInstructorDetail(appDAO);

			// deleteInstructorDetail(appDAO);

			// createInstructorWithCourses(appDAO);

			// findInstructorWithCourses(appDAO);

			// findCoursesForInstructor(appDAO);

			// findInstructorWithCoursesJoinFetch(appDAO);

			//updateInstructor(appDAO);

			// updateCourse(appDAO);

			 deleteCourse(appDAO);

		};


	}

	private void deleteCourse(AppDAO appDAO) {
		int theId=11;

		System.out.println("Deleting course is: " + theId);

		appDAO.deleteCourseById(theId);

		System.out.println("DONE! DELETE! BYE!");
	}

	private void updateCourse(AppDAO appDAO) {

		int theId = 11;

		//find the course
		System.out.println("Finding course id: " + theId);
		Course course = appDAO.findCourseById(theId);

		//update the course
		course.setTitle("Bannna Pie 101");
		appDAO.update(course);

		System.out.println("Updated Course");

	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		//find the instructor
		System.out.println("Finding instructor id: " + theId);
		Instructor instructor = appDAO.findInstructorById(theId);

		//update the instructor
		instructor.setLastName("TESTER");
		appDAO.update(instructor);

		System.out.println("Updated Instructor");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId= 1;
		System.out.println("Finding instructor ID: " + theId);

		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("Instructor: " + tempInstructor);

		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId= 1;
		System.out.println("Finding instructor ID: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);

		//find courses for instructor
		System.out.println("Finding courses for instructor id: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		//associate the objects (the list of courses with the instructor)
		tempInstructor.setCourses(courses);

		System.out.println("The associated courses: " + tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId= 1;
		System.out.println("Finding instructor ID: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("the associated courses " + tempInstructor.getCourses());

		System.out.println("Done");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		//create the Instructor and Instructor detail
		Instructor tempInstructor = new Instructor("Eduard","Epurica","eduard@gmail.com");

		InstructorDetail tempInstructordetail = new InstructorDetail("http://eduard.com/youtube", "Luv 2 play");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructordetail);


		//create some courses
		Course tempCourse1 = new Course("Air Guitar - The ultimate guide");
		Course tempCourse2 = new Course("Math 101");
		Course tempCourse3 = new Course("Biology 101");
		Course tempCourse4 = new Course("Gaming 101");

		//add courses to instructor
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);
		tempInstructor.add(tempCourse3);
		tempInstructor.add(tempCourse4);

		//save the instructor
		//
		//Note:this will also save the details object and the course object(s)
		//because of CascadeType.PERSIST
		//
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("Saving courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Done");


	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		appDAO.deleteInstructorDetailById(3);
		System.out.println("Deleted instructorDetail");
	}

	private void findInstructorDetail(AppDAO appDAO) {

		// get the instructor detail object
		int theId=2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);

		// print the instructor detail
		System.out.println("tempInstructorDetail: " + tempInstructorDetail);

		// print the associated instructor
		System.out.println("Associated instructor " + tempInstructorDetail.getInstructor());

		System.out.println("Done");

	}

	private void removeInstructor(AppDAO appDAO) {

		int theId=1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		appDAO.deleteIntructorById(theId);

		System.out.println("Instructor deleted: " + tempInstructor);

	}

	private void findInstructor(AppDAO appDAO) {

		int theId=1;
		System.out.println("Finding instrucotr id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("the associated instructorDetail only: " + tempInstructor.getInstructorDetail());

	}

	private void createInstructor(AppDAO appDAO) {

		//create the Instructor and Instructor detail
		Instructor tempInstructor = new Instructor("Chad","Darby","darby@gmail.com");

		InstructorDetail tempInstructordetail = new InstructorDetail("http://luv2code.com/youtube", "LUv 2 code");

		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructordetail);

		//save the instructor
		//
		//Note:this will also save the details object
		//because of CascadeType.ALL
		//
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done");
	}

}



