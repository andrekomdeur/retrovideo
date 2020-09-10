package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.repositories.KlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
@Service
public class DefaultKlantService implements KlantService {
    private final KlantRepository repository;

    DefaultKlantService(KlantRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Klant> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Klant> findPart(String part) {
        return repository.findPart( part);
    }

    @Override
    public Optional<Klant> findById(long id) {
        return repository.findById(id);
    }
}
