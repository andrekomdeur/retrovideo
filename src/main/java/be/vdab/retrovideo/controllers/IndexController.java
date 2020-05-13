package be.vdab.retrovideo.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.DayOfWeek;
import java.time.LocalDate;
/**
 * @Author Andre Komdeur
 */
@Controller
@RequestMapping("/")
class IndexController {
    private final AlbumService albumService;

    IndexController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public ModelAndView albums() {

        return new ModelAndView("index", "albums", albumService.findAll());
    }
}
