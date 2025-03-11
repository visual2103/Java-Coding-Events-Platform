//package com.example.hello_spring.controllers;
//
//
//import org.springframework.ui.Model;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
//
// */
//@Controller  //tells spring boot that this class represents a web controller
////daca nu l avem, chiar daca scriem metodele bine, spring boot n o sa l recunoasca
////@ResponseBody  //daca toate clasele asteapta un raspuns, punem aici
////@RequestMapping("hello")  //fiecare method din clasa incepe cu /hello
//
//public class HelloController
//{
//    //@GetMapping  //gestionează cereri HTTP de tip GET.
//    //daca accesam ardacina aplicatiei, raspunde asta, pt ca getMapping nu are path specificat
//    //met ce ruleaza by defaul
////    @GetMapping("hello") //handles request at path /hello
////    @ResponseBody //se returneaza un raspuns
////    public String hello(){
////        return "Hello, Spring!";
////    }
//
//
//    //raspuns dinamic = takes a pice of data from request and give it a dif response based on this data
//    //handles request of the form /hello?name=Miro
//    @RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
//    public String hello(@RequestParam String name, Model model){
//        //model paseaza datele sintre controller si view
//        String thegreeting = "Hello, " + name + "!";
//        model.addAttribute("greeting", thegreeting);  //primul parametru trebuie sa fie la fel ca cel din hello.html
//        return "hello";
//
//    }
//
//    //responds to /hello/miro
//    @GetMapping("hello/{name}")
//    public String helloAgain(@PathVariable String name, Model model) //the avriable from here must be exactly like that one in GetMapping
//    {
//        model.addAttribute("greeting", "Hello, " + name + "!");
//        return "hello";
//
//    }
//    // /hello/form
//    @GetMapping("form")
//    //@ResponseBody nu l folosim, ca sa poata gasi template ul form. responseBody iti zice sa nu cauti template, ci doar sa returnezi string ul
//    public String helloForm(){
//        return "form";
//    }
//
//    @GetMapping("hello-names")
//    public String helloNames(Model model){
//        List<String> names = new ArrayList<>();
//        names.add("Miro");
//        names.add("Nina");
//        names.add("Vlad");
//        model.addAttribute("names", names); //template would see "names".
//        return "hello-list";
//    }
//
//
//    //post request
//}
