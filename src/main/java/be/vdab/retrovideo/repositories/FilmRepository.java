package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Film;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface FilmRepository {
    List<Film> findAll();
    List<Film> findByGenre(Long genreId);
    Optional<Film> findById(long id);
}
