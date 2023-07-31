package com.example.withth.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;

@Controller
public class AuthBaseController {
    @ModelAttribute
    public void checkAuthentication(HttpSession session, HttpServletResponse response) throws IOException {
        if (session.getAttribute("AuthUsername") == null) {
            response.sendRedirect("/login");
        }
    }
}