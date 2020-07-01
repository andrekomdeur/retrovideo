package be.vdab.retrovideo.sessions;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
/**
 * @Author Andre Komdeur
 */
@Component
@SessionScope
public class Mandje implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Long> ids = new LinkedHashSet<>();

    public void voegToe(long id) {
        ids.add(id);
    }
    public void verwijder(long id) {
        ids.remove( id );
    }
    public boolean bevat(long id) {
        return ids.contains(id);
    }
    public boolean isGevuld() {
        return ! ids.isEmpty();
    }
    public void verwijderen(long[] ids) {
        Arrays.stream(ids).forEach(id -> this.ids.remove( id ));
    }

}
