package ru.itis.javalab.servlet;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.javalab.services.BCrypterService;
import ru.itis.javalab.services.UsersService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UsersService usersService;
    private BCrypterService bCrypterService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        usersService = (UsersService) servletContext.getAttribute("userService");
        this.bCrypterService = (BCrypterService) servletContext.getAttribute("bCrypterService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password").trim();
        String password_repeat = req.getParameter("password_repeat").trim();
        if (bCrypterService.checkPassword(password, password_repeat)){
            resp.sendRedirect("/users");
        } else resp.sendRedirect("/login");
    }

}
