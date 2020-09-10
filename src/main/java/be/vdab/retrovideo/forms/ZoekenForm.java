package be.vdab.retrovideo.forms;
import javax.validation.constraints.NotNull;
/**
 * @Author Andre Komdeur
 */
public class ZoekenForm {
    @NotNull
    private final String part;
    public ZoekenForm(@NotNull String part) {
        this.part = part;
    }

    public String getPart() {
        return part;
    }

}
