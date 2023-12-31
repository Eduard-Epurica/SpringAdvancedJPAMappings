package com.eduard.advancedjpa;

import com.eduard.advancedjpa.dao.AppDAO;
import com.eduard.advancedjpa.entity.*;
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

			// deleteCourse(appDAO);

			 deleteStudent(appDAO);

			// createCourseAndReviews(appDAO);

			// retrieveCourseAndReviews(appDAO);

			// deleteCourseAndReviews(appDAO);

			// createCourseAndStudents(appDAO);

			// findCourseAndStudents(appDAO);

			// findStudentAndCourses(appDAO);

			// addMoreCoursesForStudent(appDAO);


		};


	}

	private void deleteStudent(AppDAO appDAO) {

		int theId=3;

		System.out.println("Deleting Student: " + theId);

		appDAO.deleteStudentById(theId);

		System.out.println("DONE! DELETE! BYE!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {

		Student tempStudent = appDAO.findStundentAndCourseById(2);

		//create more courses
		Course nC1 = new Course("Mohito Class");
		Course nC2 = new Course("Burrito Class");

		//connect new courses to student
		tempStudent.addCourse(nC1);
		tempStudent.addCourse(nC2);

		//update the database
		System.out.println("Saving student: " + tempStudent);
		System.out.println("With courses " + tempStudent.getCourses());
		appDAO.update(tempStudent);
		System.out.println("Done!!!!");


	}

	private void findStudentAndCourses(AppDAO appDAO) {
		Student tempStudent = appDAO.findStundentAndCourseById(2);

		System.out.println("Loaded student: " + tempStudent);
		System.out.println("Courses: " + tempStudent.getCourses());

		System.out.println("Done!");

	}

	private void findCourseAndStudents(AppDAO appDAO) {

		Course tempCourse = appDAO.findCourseAndStudentsById(10);

		System.out.println("Loaded course: " + tempCourse);
		System.out.println("Students: " + tempCourse.getStudents());

		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {

		// create a course
		Course theCourse = new Course("PotatoFarms");

		// create the students
		Student st1 = new Student("Eduard", "Epurica", "ediepurica@gmail.com");
		Student st2 = new Student("Ana", "Safta", "anasafta@gmail.com");
		Student st3 = new Student("Claudiu", "Epurica", "clauepi@gmail.com");
		Student st4 = new Student("ANdrew", "Moore", "andrewmoore@gmail.com");

		// add students to the course
		theCourse.addStudent(st1);
		theCourse.addStudent(st2);
		theCourse.addStudent(st3);
		theCourse.addStudent(st4);

		// save the course and associated students
		System.out.println("Saving the course and students...");
		System.out.println("Course: " + theCourse);
		System.out.println("Students: " + theCourse.getStudents());
		appDAO.save(theCourse);
		System.out.println("Done!!!");


	}

	private void deleteCourseAndReviews(AppDAO appDAO) {

		System.out.println("Deleting course id: " + 10);

		appDAO.deleteCourseById(10);

		System.out.println("Done...");

	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {

		// get the course and reviews
		Course theCourse = appDAO.findCourseAndReviewsById(10);

		// print the course
		System.out.println(theCourse);

		// print the reviews
		System.out.println(theCourse.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		//create a course
		Course theCourse = new Course("Panificaiton 101");

		//add some reviews
		theCourse.addReview(new Review("Great course...loved it"));
		theCourse.addReview(new Review("Bad course...hated it"));
		theCourse.addReview(new Review("Decent course...was ok"));
		theCourse.addReview(new Review("Great course...job well done"));


		//save the course... and leverage the cascade all
		System.out.println("Saving the course...");
		System.out.println(theCourse);
		System.out.println(theCourse.getReviews());

		appDAO.save(theCourse);
		System.out.println("Done!");


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



