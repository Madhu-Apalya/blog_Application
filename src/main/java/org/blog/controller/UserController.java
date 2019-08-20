package org.blog.controller;

import org.blog.model.User;
import org.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAllUser(Model model) {
    	List<User> user = userService.findAllUser();
        model.addAttribute("user", user);
        return "/user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUserWithId(@PathVariable Long id,
                                   Principal principal) {

        Optional<User> optionalUser = userService.findForId(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            System.out.println(user.getUsername());

            if (true) {			//isPrincipalOwnerOfPost(principal, post)
                userService.delete(user);
                return "redirect:/home";
            } else {
                return "/403";
            }

        } else {
            return "/error";
        }
    }
}
