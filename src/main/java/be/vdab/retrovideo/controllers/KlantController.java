package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.KlantService;
import be.vdab.retrovideo.services.ReservatieService;
import be.vdab.retrovideo.sessions.Mandje;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
/**
 * @Author Andre Komdeur
 */
@Controller
@RequestMapping("klant")
public class KlantController {
    private static final int EEN_JAAR_IN_SECONDEN = 31_536_000;
    private KlantService klantService;
    private ReservatieService reservatieService;
    private FilmService filmService;

    public KlantController(KlantService klantService,
                           ReservatieService reservatieService,
                           FilmService filmService) {
        this.klantService = klantService;
        this.reservatieService = reservatieService;
        this.filmService = filmService;
    }

    @GetMapping("{id}")
    public ModelAndView bevestigen(@PathVariable long id,
                                   @CookieValue(name = "mandje", required = false) String koekje) {
        final Logger logger = LoggerFactory.getLogger(this.getClass());
        Mandje mandje = new Mandje();
        if (koekje != null) mandje.cookieNaarMandje(koekje);
        ModelAndView modelAndView = new ModelAndView("bevestigen");
        modelAndView.addObject("aantal", mandje.aantal());
        klantService.findById(id).ifPresent(klant -> {
            modelAndView.addObject("klant", klant);
        });
        return modelAndView;
    }
    @PostMapping("reserveren")
    public ModelAndView reserveren(long id,
           @CookieValue(name = "mandje",
                        required = false) String koekje,
           HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("rapport");

        List<String> mislukkingen = new ArrayList<>();
        Mandje mandje = new Mandje();
        mandje.cookieNaarMandje(koekje);
        mislukkingen = mandje.reserveren(id,reservatieService,filmService);

        Cookie cookie = new Cookie("mandje","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        modelAndView.addObject("mislukkingen", mislukkingen);
        return modelAndView;
    }
}
