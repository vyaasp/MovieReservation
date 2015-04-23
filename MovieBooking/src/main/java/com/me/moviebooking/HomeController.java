package com.me.moviebooking;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
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
import com.me.moviebooking.dao.MovieDAO;
import com.me.moviebooking.model.BookingSelection;
import com.me.moviebooking.model.Cinema;
import com.me.moviebooking.model.Customer;
import com.me.moviebooking.model.CustomerMoviePrefs;
import com.me.moviebooking.model.Movie;
import com.me.moviebooking.model.MoviePrefs;
import com.me.moviebooking.model.Showtime;
import com.me.moviebooking.model.UserPreferences;
import com.me.moviebooking.validator.CustomerValidator;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private CustomerDAO customerDao;
	
	@Autowired
	private MovieDAO movieDao;
	
//	@Autowired
//    @Qualifier("customerValidator")
//    private Validator validator;
// 
//    @InitBinder
//    private void initBinder(WebDataBinder binder) {
//        binder.setValidator(validator);
//    }
//    
//    @ModelAttribute("customer")
//    public Customer createEmployeeModel() {
//        return new Customer();
//    }
	
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
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public String validate(Model model, Customer customer,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		logger.info("Validation Page.!");
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
	public String registartion(Model model,
			HttpServletRequest request) throws Exception {
		logger.info("Registration Page.!");
		model.addAttribute("customer", new Customer());
		return "registration";
	}
	
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public String login(Model model, @Valid @ModelAttribute("customer") Customer customer,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) {
		logger.info("Registered.!");
		if (result.hasErrors()) {
            logger.info("Returning registration.jsp page");
            return "registration";
        }
		try{
		customerDao.createCustomer(customer);
		}catch(ConstraintViolationException cve){
			logger.info("Returning registration.jsp page");
            return "registration";
		}
		model.addAttribute("customer", customer);
		return "login";
	}
	
	@RequestMapping(value = "/selectmovie", method = RequestMethod.POST)
	public String booking(Model model, @ModelAttribute("customer") Customer customer,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		logger.info("Booking Page.!");
		List<Showtime> showtimes = movieDao.getShowTimes();
		MoviePrefs mp = new MoviePrefs(customer,
				showtimes, new UserPreferences());
		model.addAttribute("mp", mp);
		return "booking";
	}
	
	@RequestMapping(value = "/booktickets", method = RequestMethod.POST)
	public String showScreens(Model model, @Valid @ModelAttribute("mp") MoviePrefs mp,
			BindingResult result, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		System.out.println(mp.getUp().getMovieId());
		System.out.println(mp.getUp().getScreenId());
		System.out.println(mp.getUp().getCinemaId());
		System.out.println(mp.getUp().getTime());
		logger.info("Screens Page.!");
		if (result.hasErrors()) {
            logger.info("Returning registration.jsp page");
            return "booking";
        }
		Showtime showtime = movieDao.getBookingShow(mp.getUp());
		model.addAttribute("bs", new BookingSelection(mp.getCustomer(),
				showtime, 0, 0));
		return "tickets";
	}
}
