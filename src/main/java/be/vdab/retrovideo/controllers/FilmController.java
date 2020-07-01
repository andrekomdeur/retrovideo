package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.sessions.Mandje;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * @Author Andre Komdeur
 */
@Controller
@RequestMapping("film")
class FilmController {
    private final Mandje mandje;
    private final FilmService filmService;

    public FilmController(Mandje mandje, FilmService filmService) {
        this.mandje = mandje;
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
    public String voegToe(long id) {
        mandje.voegToe(id);
        return "redirect:/mandje";
    }
}
