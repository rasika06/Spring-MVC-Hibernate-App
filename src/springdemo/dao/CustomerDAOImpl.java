package springdemo.dao;

import java.util.List;

import org.apache.catalina.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	//inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	
	public List<Customer> getCustomers() {
		// get current hibernate session
		org.hibernate.Session currentSession=sessionFactory.getCurrentSession();
		
		
		//create a query
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastname",
															Customer.class);
		
		//get result of the query
		List<Customer> customers=theQuery.getResultList();
		
		//return the result
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		org.hibernate.Session currentSession=sessionFactory.getCurrentSession();
		//save the customer to the database
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomers(int theId) {
		
		//get current hibernate session
		org.hibernate.Session currentSession=sessionFactory.getCurrentSession();
		
		//retireve/read object from databse using the id(primary key)
		Customer theCustomer=currentSession.get(Customer.class, theId);
		
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		// get current hibernate session
		org.hibernate.Session currentSession=sessionFactory.getCurrentSession();
		
		//delete customer object from database using id.
		Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current hibernate session
        org.hibernate.Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // search by name only if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstname) like :theName or lower(lastname) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
        
    }
	

}
