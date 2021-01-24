package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.models.User;
import ru.itis.javalab.services.UsersService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SignUpController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/sign-up", method = RequestMethod.GET)
    public ModelAndView getUsersPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("sign-up");
        return modelAndView;
    }

    @RequestMapping(value = "/sign-up", method = RequestMethod.POST)
    public ModelAndView addUser(User user, HttpServletRequest request) {
        String password = request.getParameter("password");
        String password_repeat = request.getParameter("password_repeat");

        if (password.equals(password_repeat)){
            usersService.addUser(user);
            return new ModelAndView("redirect:/sign_in");
        }else {
            return new ModelAndView("redirect:/sign-up");

        }
    }


}
