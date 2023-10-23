package com.eduard.advancedjpa;

import com.eduard.advancedjpa.dao.AppDAO;
import com.eduard.advancedjpa.entity.Instructor;
import com.eduard.advancedjpa.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//removeInstructor(appDAO);
			//findInstructorDetail(appDAO);
			deleteInstructorDetail(appDAO);
		};


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
		System.out.println("Finding instrucotr id: " + theId);

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



