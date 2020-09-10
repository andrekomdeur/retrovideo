package be.vdab.retrovideo.sessions;
import be.vdab.retrovideo.domain.Klant;
import be.vdab.retrovideo.services.KlantService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.List;
/**
 * @Author Andre Komdeur
 */
@Component
@SessionScope
public class ZoekDeKlanten implements Serializable {
    private static final long serialVersionUID = 1L;
    private KlantService klantService;
    private List<Klant> klanten;

    public ZoekDeKlanten(KlantService ks) {
        klantService = ks;
    }

    public void zoeken(String part) {
        klanten = klantService.findPart(part);
    }

    public KlantService getKlantService() {
        return klantService;
    }

    public List<Klant> getKlanten() {
        return klanten;
    }
}
