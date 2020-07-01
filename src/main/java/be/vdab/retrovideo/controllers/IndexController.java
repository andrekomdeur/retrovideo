package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.services.FilmService;
import be.vdab.retrovideo.services.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * @Author Andre Komdeur
 */
@Controller
@RequestMapping("/")
class IndexController {
    private final GenreService genreService;
    private final FilmService filmService;

    IndexController(GenreService genreService,
                    FilmService filmService) {
        this.genreService = genreService;
        this.filmService = filmService;
    }

    @GetMapping
    public ModelAndView genres() {
        return new ModelAndView("index", "genres", genreService.findAll());
    }

    @GetMapping("{id}")
    public ModelAndView filmsVanGenre( @PathVariable Long id) {
        ModelAndView model = new ModelAndView(
                "index", "films", filmService.findByGenre(id));
        model.addObject("genres", genreService.findAll());
        genreService.findById(id).ifPresent(genre -> model
             .addObject("naam", genre.getNaam()));
        return model;
    }
}
