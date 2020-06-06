package sda.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import sda.spring.rest.model.dto.UserDto;
import sda.spring.rest.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/adduser")
    public ModelAndView userView() {
        return new ModelAndView("adduser", "userToInsert", new UserDto());
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute UserDto userDto) {
        userService.addUser(userDto);
        return "redirect:/index";
    }

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        return new ModelAndView("users", "userList", userService.getAllUsers());
    }

    @PostMapping("/deleteUser")
    public String deleteUser(@ModelAttribute("user") UserDto user) {
        userService.deleteUser(user.getId());
        return "redirect:/users";
    }
}
