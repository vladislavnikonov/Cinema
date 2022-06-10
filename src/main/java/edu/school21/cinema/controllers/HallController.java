package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.services.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/panel")
public class HallController {

    private final HallService hallService;

    @Autowired
    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @GetMapping(value = "/halls")
    public String showAllHalls(Model model) {
        List<Hall> halls = hallService.findAll();
        model.addAttribute("halls", halls);
        return "halls";
    }

    @GetMapping(value = "/addHall")
    public String addHall(Model model) {
        Hall hall = new Hall();
        model.addAttribute("hall", hall);
        return "addHall";
    }

    @PostMapping(value = "/saveHall")
    public String saveHall(Model model, @ModelAttribute("hall") Hall hall) {
        if (!hallService.create(hall)) {
            model.addAttribute("error", "A hall with this number already exists");
            return "addHall";
        }
        return "redirect:/admin/panel/halls";
    }

}
