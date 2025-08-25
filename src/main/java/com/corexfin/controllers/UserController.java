package com.corexfin.controllers;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.corexfin.models.User;
import com.corexfin.repositories.AccountRepository;
import com.corexfin.services.UserService;

@Controller
public class UserController 
{
	@Autowired private UserService userService;
	@Autowired private AccountRepository accountRepository;
	
	@GetMapping("registration")
	public String getRegistrationView()
	{
		return "user/registration/registration-form";
	}
	@PostMapping("registration.do")
	public String registerUser(User user,Model model)
	{
		int accountno=userService.saveUser(user);
		model.addAttribute("accountno",accountno);
		model.addAttribute("username",user.getFirstname()+" "+user.getLastname());
		model.addAttribute("cdate",LocalDate.now());
		return "user/registration/registration-success";
	}
	@PostMapping("login.do")
	public ModelAndView authenticateUser(String userid,String password,HttpSession ses)
	{
		User user=userService.getUser(userid);
		ModelAndView modelAndView=new ModelAndView();
		if(user==null)
		{
			modelAndView.setViewName("user/login/login-form");
			modelAndView.addObject("msg","Entered userid does not exist");
			modelAndView.addObject("uid",userid);
			return modelAndView;
		}
		if(!user.getPassword().equals(password))
		{
			modelAndView.setViewName("user/login/login-form");
			modelAndView.addObject("msg","Entered password is wrong");
			modelAndView.addObject("uid",userid);
			return modelAndView;
		}
		ses.setAttribute("username",user.getFirstname()+" "+user.getLastname());
		int accountno=accountRepository.getAccountNoByUserId(userid);
		ses.setAttribute("accountno",accountno);
		//ses.setMaxInactiveInterval(20);
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}
	@GetMapping("logout.do")
	public String logoutUser(HttpSession ses,Model model)
	{
		model.addAttribute("username",ses.getAttribute("username"));
		ses.invalidate();
		return "user/login/logout-success";
	}
	@GetMapping("sessionExpired")
	public String sessionExpried(HttpSession ses,Model model)
	{
		model.addAttribute("username",ses.getAttribute("username"));
		ses.invalidate();
		return "user/login/session-expire";
	}
}
