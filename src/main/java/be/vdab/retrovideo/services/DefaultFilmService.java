package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
@Service
public class DefaultFilmService implements FilmService {
    private final FilmRepository repository;

    DefaultFilmService(FilmRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Film> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Film> findByGenre(long genreId) {
        return repository.findByGenre(genreId);
    }

    @Override
    public Optional<Film> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void update(Film film) {
        repository.update(film);
    }
}
