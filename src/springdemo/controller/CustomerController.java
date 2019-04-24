package springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springdemo.dao.CustomerDAO;
import springdemo.entity.Customer;
import springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	//need to inject customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get the customers from the service
		List<Customer> theCustomers=customerService.getCustomers();
		
		//add those customers to model
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer=new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		
		//add the customer using our service
		
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
		}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel) {
		
		//get the customer from the service
		Customer theCustomer=customerService.getCustomers(theId);
		
		//set customer as model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		//send customer to the form
		
		return "customer-form";
		}
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                    Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);
                
        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";        
    }
	
	
}
