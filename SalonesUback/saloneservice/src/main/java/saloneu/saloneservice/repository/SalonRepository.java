package saloneu.saloneservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saloneu.saloneservice.entity.Salon;

public interface SalonRepository extends JpaRepository<Salon, Long> {
}
