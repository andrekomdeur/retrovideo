package be.vdab.retrovideo.controllers;
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
@RequestMapping("genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }
    @GetMapping("{id}")
    public ModelAndView genre(@PathVariable long id) {
        ModelAndView modelAndView = new ModelAndView("genre");
        genreService.findById(id).ifPresent(genre ->
                modelAndView
                        .addObject("genre", genre)
                        .addObject("films", genre.getFilms()));
        return modelAndView;
    }
}
