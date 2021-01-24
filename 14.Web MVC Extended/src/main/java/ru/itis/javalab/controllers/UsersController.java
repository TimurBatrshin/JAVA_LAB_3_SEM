package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.services.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getUsersPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("users", usersService.getAllUser());
        return modelAndView;
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ModelAndView addUser(UserDto user) {
        usersService.addUser(user);
        return new ModelAndView("redirect:/users");
    }

    @RequestMapping(value = "/users/{user-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDto getUser(@PathVariable("user-id") Long userId) {
        return usersService.getUser(userId);
    }
    @RequestMapping(value = "/users/json", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserDto> addUserFromJson(@RequestBody UserDto user){
        usersService.addUser(user);
        return usersService.getAllUser();
    }

}
