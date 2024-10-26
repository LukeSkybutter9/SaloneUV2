package saloneu.saloneservice.service;

import org.springframework.stereotype.Service;
import saloneu.saloneservice.entity.Salon;
import saloneu.saloneservice.repository.SalonRepository;
import java.util.List;

@Service
public class SalonServiceImpl implements SalonService {

    private final SalonRepository salonRepository;

    public SalonServiceImpl(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    @Override
    public Salon crearSalon(Salon salon) {
        return salonRepository.save(salon);
    }

    @Override
    public List<Salon> getSalons() {
        return salonRepository.findAll();
    }
}
