package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
/**
 * @Author Andre Komdeur
 */

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private final Mandje mandje;
    private final FilmService filmService;

    public MandjeController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
        this.filmService = filmService;
    }
    @GetMapping
    public ModelAndView toonMandje() {
        List<Film> filmsInMandje = filmService.findAll();
        ModelAndView modelAndView = new ModelAndView("mandje")
                .addObject("filmsInMandje", filmsInMandje);
        if (mandje.isGevuld()) {
            modelAndView.addObject("filmsInMandje",
                    filmsInMandje.stream().filter(film -> mandje.bevat(film.getId()))
                            .collect(Collectors.toList()));
        }
        return modelAndView;
    }
    @PostMapping("verwijderen")
    public ModelAndView verwijderen(long[] id) {
        if (id != null) {
            mandje.verwijderen(id);
        }
        return new ModelAndView("redirect:/mandje");
    }
}
/*
@Controller
@RequestMapping("mandje")
class MandjeController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    private final FilmService filmService;
    private Set<Film> films = new LinkedHashSet<>();
    private final Cookie cookie = new Cookie("mandje", films.toString());

    public MandjeController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ModelAndView films(@PathVariable Long id,
                              @CookieValue(name = "mandje", required = false)
                                      String filmstring, HttpServletResponse response) {
        filmService.findById(id).ifPresent(film -> {
            films.add(film);
            cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
            cookie.setPath("/");
            response.addCookie(cookie);
        });
        return new ModelAndView("mandje", "films", films);
    }
}
*/
