package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/panel")
public class HallController {

    private final HallRepository hallRepository;

    @Autowired
    public HallController(@Qualifier("hallRepositoryImpl") HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @GetMapping(value = "/halls")
    public String showAllHalls(Model model) {
        List<Hall> halls = hallRepository.findAll();
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
        if (hall == null || hall.getSerialNumber() == null || hall.getSeatsNumber() == null) {
            model.addAttribute("error", "Please enter all data");
            return "addHall";
        } else if (hallRepository.getBySerialNumber(hall.getSerialNumber()) != null) {
            model.addAttribute("error", "A hall with this number already exists");
            return "addHall";
        } else {
            hallRepository.save(hall);
        }
        return "redirect:/admin/panel/halls";
    }

}
