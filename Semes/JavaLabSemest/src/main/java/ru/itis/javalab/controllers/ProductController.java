package ru.itis.javalab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itis.javalab.models.Product;
import ru.itis.javalab.services.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String getProfilePage(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }
}


