package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Genre;
import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
public interface GenreRepository {
    Optional<Genre> findById(long id);
    List<Genre> findAll();
}
