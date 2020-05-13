package be.vdab.retrovideo.domain;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
/**
 * @Author Andre Komdeur
 */
public class Klant {
    private final long id;
    @NotBlank
    private final String familieNaam;
    @NotBlank
    private final String voorNaam;
    @NotBlank
    private final String straatNummer;
    @NotBlank
    private final String postcode;
    @NotBlank
    private final String gemeente;

    public Klant(long id,
                 @NotBlank String familieNaam,
                 @NotBlank String voorNaam,
                 @NotBlank String straatNummer,
                 @NotBlank String postcode,
                 @NotBlank String gemeente) {
        this.id = id;
        this.familieNaam = familieNaam;
        this.voorNaam = voorNaam;
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public long getId() {
        return id;
    }

    public String getFamilieNaam() {
        return familieNaam;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public String getStraatNummer() {
        return straatNummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }

    public String getNaam() {
        return voorNaam + ' ' + familieNaam;
    }
}
