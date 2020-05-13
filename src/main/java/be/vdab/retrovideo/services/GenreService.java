package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Genre;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface GenreService {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
}