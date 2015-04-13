package com.me.moviebooking;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.moviebooking.dao.CustomerDAO;
import com.me.moviebooking.model.Customer;
import com.me.moviebooking.validator.CustomerValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
    @Qualifier("customerValidator")
    private Validator validator;
 
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    @ModelAttribute("customer")
    public Customer createEmployeeModel() {
        // ModelAttribute value should be same as used in the empSave.jsp
        return new Customer();
    }
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		logger.info("First Page sucessfully called.!");
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "login";
	}
	
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public String login(Model model, @Validated Customer customer,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) {
		logger.info("Registered.!");
		boolean newUser = customerDao.createCustomer(customer);
		model.addAttribute("customer", customer);
		return "login";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String validate(Model model, @Validated Customer customer,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		logger.info("Validation Page.!");
		logger.info("User:"+customer.getUserName());
		if (result.hasErrors()) {
            logger.info("Returning custSave.jsp page");
            return "login";
        }
		Customer customerFromDb = customerDao.isValidCustomer(customer.getUserName(),
				customer.getPassword());
		if(customerFromDb!=null){
			model.addAttribute("customer", customer);
			return "login";
		}else{
			return "home";
		}
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registartion(Model model, @Valid Customer customer,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		logger.info("Registration Page.!");
		model.addAttribute("customer", customer);
		return "registration";
	}
}
