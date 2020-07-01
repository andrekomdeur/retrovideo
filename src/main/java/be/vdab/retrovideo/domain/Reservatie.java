package be.vdab.retrovideo.domain;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;
/**
 * @Author Andre Komdeur
 */
public class Reservatie {
    private final Long klantId;
    private final Long filmId;
    @PastOrPresent
    @DateTimeFormat
    private final Timestamp reservatie;

    public Reservatie(Long klantId, Long filmId, Timestamp reservatie) {
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

    public Timestamp getReservatie() {
        return reservatie;
    }
}
