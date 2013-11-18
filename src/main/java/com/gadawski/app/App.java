package com.gadawski.app;

import java.math.BigDecimal;

import org.hibernate.Session;

import com.gadawski.app.facts.Car;
import com.gadawski.util.db.HibernateUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Car car = new Car();
        car.setId(100L);
        car.setName("volvo");
        car.setPrice(BigDecimal.valueOf(1324));
        
        session.save(car);
        session.getTransaction().commit();
    }
}
