package be.vdab.retrovideo.controllers;
import be.vdab.retrovideo.forms.ZoekenForm;
import be.vdab.retrovideo.sessions.ZoekDeKlanten;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
/**
 * @Author Andre Komdeur
 */
@Controller
@RequestMapping("zoekdeklanten")
public class ZoekController {
    private ZoekDeKlanten zoekDeKlanten;

    public ZoekController(ZoekDeKlanten zoekDeKlanten) {
        this.zoekDeKlanten = zoekDeKlanten;
    }

    @GetMapping("show")
    public ModelAndView zoekDeKlanten(@Valid ZoekenForm form, Errors errors) {
        return new ModelAndView("zoekdeklanten", "zoekDeKlanten", zoekDeKlanten)
                .addObject("ZoekenForm", new ZoekenForm(""));
    }

    @PostMapping(value = "zoeken")
    public ModelAndView zoeken(@Valid ZoekenForm form, Errors errors) {
        if (errors.hasErrors()) {
            return new ModelAndView("zoekdeklanten", "zoekDeKlanten", zoekDeKlanten);
        }
        zoekDeKlanten.zoeken(form.getPart());
        return new ModelAndView("redirect:/zoekdeklanten/show");
    }
}
