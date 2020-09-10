package be.vdab.retrovideo.sessions;
import be.vdab.retrovideo.domain.Reservatie;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.ReservatieService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
/**
 * @Author Andre Komdeur
 */
@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private Set<Long> ids = new LinkedHashSet<>();

    public void cookieNaarMandje(String koekje) {
        if (!koekje.isEmpty()) {
            String[] components = koekje.split("-");
            for (String component : components) {
                ids.add(Long.parseLong(component));
            }
        }
    }

    public String mandjeNaarCookie() {
        StringBuilder builder = new StringBuilder();
        for (Long lId : ids) {
            if (builder.length() > 0)
                builder.append("-");
            builder.append(lId);
        }
        return builder.toString();
    }

    @Transactional
    public List<String> reserveren(Long idKlant, ReservatieService reservatieService, FilmService filmService) {
        List<String> mislukkingen = new ArrayList<>();
        ;
        for (Long idFilm : ids) {
            filmService.findById(idFilm).ifPresent(
                    film -> {
                        if (film.getVoorraad() - film.getGereserveerd() >= 1) {
                            if (0 == reservatieService.create(
                                    new Reservatie(
                                            idKlant, idFilm, new Timestamp(
                                            System.currentTimeMillis()))))
                                mislukkingen.add(film.getTitel());
                            else {
                                filmService.update(film);
                            }
                        } else mislukkingen.add(film.getTitel());
                    }
            );
        }
        return mislukkingen;
    }

    public void voegToe(long id) {
        ids.add(id);
    }

    public boolean bevat(long id) {
        return ids.contains(id);
    }

    public boolean isGevuld() {
        return !ids.isEmpty();
    }

    public int aantal() {
        return ids.size();
    }

    public void verwijderen(long[] ids) {
        Arrays.stream(ids).forEach(id -> this.ids.remove(id));
    }
}
