package br.com.fiap.controllers;

import br.com.fiap.model.Car;
import br.com.fiap.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping
    public String menu(Model model){
        model.addAttribute("cars",carService.findAll());
        return "cars/menu";
    }

    @GetMapping("/edit")
    public String listCars(Model model){
        model.addAttribute("cars",carService.findAll());
        return "cars/carList";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("car", new Car());
        return "cars/form";
    }

    @PostMapping
    public String saveCar(@ModelAttribute Car car) {
        carService.save(car);
        return "redirect:cars/edit";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Car car = carService.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encotrado Id: " + id));
        model.addAttribute("car", car);
        return "cars/form";
    }

    @GetMapping("/update/{id}")
    public String updateCar(@PathVariable Long id, @ModelAttribute Car car) {
        carService.save(car);
        return "cars/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        carService.deleteById(id);
        return "redirect:/cars/edit";
    }
}
