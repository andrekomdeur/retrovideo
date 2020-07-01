package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface FilmService {
    List<Film> findAll();
    List<Film> findByGenre(long genreId);
    Optional<Film> findById(long id);
}
