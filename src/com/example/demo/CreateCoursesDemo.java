package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Course;
import com.example.entity.Instructor;
import com.example.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {
		
		//Create a session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		//Create a session
		Session session = factory.getCurrentSession();
		
		try {	
			
			//Start a transaction
			session.beginTransaction();
						
			//get instructor from DB
			Instructor instructorTemp = session.get(Instructor.class, 1);
			
			//Create some courses:
			Course c1 = new Course("Reading manga ");
			Course c2 = new Course("Gumpla modeling");
			Course c3 = new Course("Painting gumpla models");		
			instructorTemp.add(c1);
			instructorTemp.add(c2);
			instructorTemp.add(c3);	
			
			//Saving courses
			session.save(c1);
			session.save(c2);
			session.save(c3 );			
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}//Close try. 
		
		finally {
			session.close();
			factory.close();
		}//Close finally. 
		
	}//Close main method.

}//Close CreateStudenDemo class.
