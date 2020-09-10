package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Klant;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface KlantRepository {
    List<Klant> findAll();
    List<Klant> findPart(String part);
    Optional<Klant> findById(long id);
}
