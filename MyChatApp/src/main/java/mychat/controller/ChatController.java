package mychat.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import mychat.business.MyChatDAO;
import mychat.data.MyData;
import mychat.model.UserModel;

@Controller
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private MyChatDAO myChatDAO = null;
	@RequestMapping(value="", method=RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("username")==null) {
			UserModel model = new UserModel();
			return new ModelAndView("loginForm", "model", model);
		}
		String username =(String) session.getAttribute("username");
		UserModel model = this.myChatDAO.getUserByUsername(username);
		return new ModelAndView("chatForm", "model", model);
		
	}
	@RequestMapping(value="", method=RequestMethod.POST)
	public ModelAndView loginProcess(UserModel model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		for(UserModel user:MyData.allUser()) {
			if(user.getUsername().equals(model.getUsername()) && user.getPassword().equals(model.getPassword())) {
				httpSession.setAttribute("login", true);
				httpSession.setAttribute("username", user.getUsername());
				return new ModelAndView("chatForm", "model", model);
			}
		}
		return login(request,response);
	}

}