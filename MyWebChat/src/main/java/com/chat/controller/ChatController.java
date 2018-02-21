package com.chat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.chat.data.AllDataService;
import com.chat.model.UserModel;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@RequestMapping()
	public ModelAndView start() {
		UserModel model = new UserModel();
		User user = new User();
		model.setUser(user);
		return new ModelAndView("login","model",model);
	}
	@RequestMapping(value="login", method=RequestMethod.POST)
	public ModelAndView login(UserModel model,HttpSession session) {
		List<User> users = AllDataService.allUsersData();
		for(User user:users) {
			if(user.getUsername().equals(model.getUser().getUsername()) && user.getPassword().equals(model.getUser().getPassword())) {
				session.setAttribute("nameSession", user.getName());
				model.setUser(user);
				return new ModelAndView("loginSuccess", "model", model);
			}
		}
		return start();
	}

}
