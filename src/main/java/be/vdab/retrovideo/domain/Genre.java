package be.vdab.retrovideo.domain;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * @Author Andre Komdeur
 */
public class Genre {
    private final long id;
    @NotBlank
    private final String naam;
    private Set<Film> films = new LinkedHashSet<>();

    public Genre(long id, @NotBlank String naam) {
        this.id = id;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Set<Film> getFilms() {
        return Collections.unmodifiableSet(films);
    }
}
