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
    private final Timestamp reservatieMoment;

    public Reservatie(Long klantId, Long filmId, Timestamp reservatieMoment) {
        this.klantId = klantId;
        this.filmId = filmId;
        this.reservatieMoment = reservatieMoment;
    }

    public Long getKlantId() {
        return klantId;
    }

    public Long getFilmId() {
        return filmId;
    }

    public Timestamp getReservatieMoment() {
        return reservatieMoment;
    }
}
