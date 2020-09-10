package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Klant;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface KlantService {
    List<Klant> findAll();
    List<Klant> findPart(String part);
    Optional<Klant> findById(long id);
}
