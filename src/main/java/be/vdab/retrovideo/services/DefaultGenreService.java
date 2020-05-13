package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Genre;
import be.vdab.retrovideo.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
@Service
public class DefaultGenreService implements GenreService {
    private final GenreRepository repository;

    DefaultGenreService(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return repository.findById(id);
    }
}
