package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
/**
 * @Author Andre Komdeur
 */

@Controller
@RequestMapping("mandje")
public class MandjeController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    private final FilmService filmService;

    public MandjeController( FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping
    public ModelAndView toonMandje( @CookieValue(name = "mandje", required = false) String koekje) {
        Mandje mandje = new Mandje();
        if (koekje != null) mandje.cookieNaarMandje(koekje);
        List<Film> filmsInMandje = filmService.findAll();
        ModelAndView modelAndView = new ModelAndView("mandje");
//                .addObject("filmsInMandje", filmsInMandje);
        if (mandje.isGevuld()) {
            modelAndView.addObject("filmsInMandje",
                    filmsInMandje.stream().filter(film -> mandje.bevat(film.getId()))
                            .collect(Collectors.toList()));
        }
        return modelAndView;
    }
    @PostMapping("verwijderen")
    public ModelAndView verwijderen(long[] id,
                                    @CookieValue(name = "mandje", required = false) String koekje,
                                    HttpServletResponse response) {
        Mandje mandje = new Mandje();
        if (koekje != null) mandje.cookieNaarMandje(koekje);
        if (id != null) {

            Cookie cookie = new Cookie("mandje","");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);

            mandje.verwijderen(id);

            cookie = new Cookie("mandje", mandje.mandjeNaarCookie());
            cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
        return new ModelAndView("redirect:/mandje");
    }
}
