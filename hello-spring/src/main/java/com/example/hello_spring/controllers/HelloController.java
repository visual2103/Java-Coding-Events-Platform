package com.example.hello_spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to handle various greetings in the Spring Boot application.
 */
@Controller
public class HelloController {

    /**
     * Handles requests to /goodbye and returns a farewell message.
     *
     * @return A string saying goodbye.
     */
    @GetMapping("goodbye")
    @ResponseBody
    public String goodbye() {
        return "Goodbye, Spring!";
    }

    /**
     * Handles requests to /hello-query with a query parameter `name`.
     * Example: /hello-query?name=Miro
     *
     * @param name The name provided in the query parameter.
     * @return A personalized greeting message.
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "hello-query")
    @ResponseBody
    public String helloWithQueryParam(@RequestParam(required = false, defaultValue = "Guest") String name) {
        return "Hello, " + name + "!";
    }

    /**
     * Handles requests to /hello/{name}.
     * Example: /hello/Miro
     *
     * @param name The name provided in the URL path.
     * @return A personalized greeting message.
     */
    @GetMapping("hello/{name}")
    @ResponseBody
    public String helloWithPathParam(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    /**
     * Displays a simple HTML form that submits a greeting request to /hello-query.
     *
     * @return HTML content with a form.
     */
    @GetMapping("form")
    @ResponseBody
    public String helloForm() {
        return "<html>" +
                "<body>" +
                "<form action='/hello-query' method='get'>" +
                "<input type='text' name='name' placeholder='Enter your name'>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }
}
