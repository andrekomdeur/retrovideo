package be.vdab.retrovideo.domain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
/**
 * @Author Andre Komdeur
 */
public class Reservatie {
    private final Long klantId;
    private final Long filmId;
    @PastOrPresent
    @DateTimeFormat(style = "S-")
    private final LocalDate reservatie;

    public Reservatie(Long klantId, Long filmId, @PastOrPresent LocalDate reservatie) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatie = reservatie;
    }

    public Long getKlantId() {
        return klantId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public LocalDate getReservatie() {
        return reservatie;
    }
}
