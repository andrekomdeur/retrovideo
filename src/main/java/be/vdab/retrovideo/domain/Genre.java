package be.vdab.retrovideo.domain;
import javax.validation.constraints.NotBlank;
/**
 * @Author Andre Komdeur
 */
public class Genre {
    private final long id;
    @NotBlank
    private final String naam;

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
}
