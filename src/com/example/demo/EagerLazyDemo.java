package com.example.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.entity.Course;
import com.example.entity.Instructor;
import com.example.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			//Start a transaction.
			session.beginTransaction();
						
			//get instructor from DB.
			Instructor instructor = session.get(Instructor.class, 1);
			
			System.out.println("Instructor : "+instructor);
			
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
		}//Close try. 
		
		finally {
			session.close();
			factory.close();
		}//Close finally. 
		
	}//Close main method.

}//Close CreateStudenDemo class.
