package be.vdab.retrovideo.services;
import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;
/**
 * @Author Andre Komdeur
 */
public interface ReservatieService {
    List<Reservatie> findAll();
    List<Reservatie> findByKlantId(long klantId);
    int create(Reservatie reservatie);
}
