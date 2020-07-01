package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author Andre Komdeur
 */
@Service
@Transactional
public class DefaultReservatieService implements ReservatieService{
private final ReservatieRepository repository;
    DefaultReservatieService(ReservatieRepository repo) {
        this.repository = repo;
    }

    @Override
    public List<Reservatie> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Reservatie> findByKlantId(long klantId) {
        return repository.findByKlantId(klantId);
    }


}
