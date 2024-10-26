package saloneu.saloneservice.controller;

import org.springframework.web.bind.annotation.*;
import saloneu.saloneservice.entity.Salon;
import saloneu.saloneservice.service.SalonService;

import java.util.List;

@RestController
@RequestMapping("/salones")
@CrossOrigin(origins = "http://localhost:4321")
public class SalonController {

    private final SalonService salonService;

    public SalonController(SalonService salonService) {
        this.salonService = salonService;
    }

    @PostMapping
    public Salon crearSalon(@RequestBody Salon salon) {
        return salonService.crearSalon(salon);
    }

    @GetMapping
    public List<Salon> getAll() {
        return salonService.getSalons();
    }
}
