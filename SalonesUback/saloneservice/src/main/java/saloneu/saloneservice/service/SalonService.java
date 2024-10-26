package saloneu.saloneservice.service;


import saloneu.saloneservice.entity.Salon;

import java.util.List;

public interface SalonService {

    public Salon crearSalon(Salon salon);

    public List<Salon> getSalons();

}
