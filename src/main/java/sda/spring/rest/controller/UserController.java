package sda.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sda.spring.rest.model.dto.UserDto;

@Controller
public class UserController {

    @Autowired
    private com.sda.spring.mvc.service.UserService userService;

    @GetMapping("/adduser")
    public ModelAndView userView() {
        return new ModelAndView("adduser","userToInsert", new UserDto());
    }
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute UserDto userDto){
        userService.addUser(userDto);
        return "redirect:/index";
    }
}
