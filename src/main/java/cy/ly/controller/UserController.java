package cy.ly.controller;

import cy.ly.service.WeiUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    WeiUsersService weiUsersService;
}
