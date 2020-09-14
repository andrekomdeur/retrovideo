package be.vdab.retrovideo.domain;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
/**
 * @Author Andre Komdeur
 */
public class Film {
    private final long id;
    private final long genreId;
    @NotBlank
    private final String titel;
    @PositiveOrZero
    private final long voorraad;
    @PositiveOrZero
    private final long gereserveerd;
    @NotNull
    @PositiveOrZero
    @NumberFormat(pattern = "0.00")
    private final BigDecimal prijs;
    private final boolean verwijderen;

    public Film(long id, long genreId, @NotBlank String titel, @PositiveOrZero long voorraad, @PositiveOrZero long gereserveerd, @NotNull @PositiveOrZero BigDecimal prijs) {
        this.id = id;
        this.genreId = genreId;
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
        this.verwijderen = false;
    }

    public long getId() {
        return id;
    }

    public long getGenreId() {
        return genreId;
    }

    public String getTitel() {
        return titel;
    }

    public long getVoorraad() {
        return voorraad;
    }

    public long getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }
}

