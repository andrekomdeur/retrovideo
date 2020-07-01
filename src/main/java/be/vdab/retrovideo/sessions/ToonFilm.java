package be.vdab.retrovideo.sessions;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
/**
 * @Author Andre Komdeur
 */
@Component
@SessionScope
public class ToonFilm implements Serializable {
    private static final long serialVersionUID = 1L;

    public ToonFilm() {
    }
    public void getFilm(long id ){

    }

}
