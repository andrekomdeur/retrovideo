package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
/**
 * @Author Andre Komdeur
 */
@Controller
@RequestMapping("film")
class FilmController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }
    @GetMapping("{id}")
    public ModelAndView film(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("film");
        filmService.findById(id).ifPresent(film -> {
            modelAndView.addObject(film);
        });
        return modelAndView;
    }
    @PostMapping("toevoegen")
    public ModelAndView voegToe(long id,
                          @CookieValue(name = "mandje", required = false) String koekje,
                          HttpServletResponse response) {
        Mandje mandje = new Mandje();
        if (koekje != null) mandje.cookieNaarMandje(koekje);
//      verwijderen
        Cookie cookie = new Cookie("mandje","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        mandje.voegToe(id);
        cookie = new Cookie("mandje", mandje.mandjeNaarCookie());
        cookie.setMaxAge(EEN_JAAR_IN_SECONDEN);
        cookie.setPath("/");
        response.addCookie(cookie);

        return new ModelAndView("redirect:/mandje");
    }
}
