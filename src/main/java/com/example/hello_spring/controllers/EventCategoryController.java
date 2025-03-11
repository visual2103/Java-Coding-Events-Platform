package com.example.hello_spring.controllers;

import com.example.hello_spring.data.EventCategoryRepository;
import com.example.hello_spring.model.EventCategory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("categories")
public class EventCategoryController
{
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    // Afisam toate categoriile
    @GetMapping
    public String displayAllCategories(Model model) {
        model.addAttribute("categories", eventCategoryRepository.findAll());
        return "categories/index";
    }

    // Afisam formularul de creare categorie
    @GetMapping("create")
    public String displayCreateCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute("eventCategory", new EventCategory());
        return "categories/create";
    }


    // Salvăm o categorie după ce a fost trimis formularul
    @PostMapping("create")
    public String createCategory(@ModelAttribute @Valid EventCategory eventCategory, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "categories/create"; // Asigură-te că numele șablonului este corect
        }

        eventCategoryRepository.save(eventCategory);
        return "redirect:/categories";
    }

}
