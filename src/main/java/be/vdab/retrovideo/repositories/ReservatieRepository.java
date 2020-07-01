package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Reservatie;

import java.util.List;
/**
 * @Author Andre Komdeur
 */
public interface ReservatieRepository {
    List<Reservatie> findAll();
    List<Reservatie> findByKlantId(Long klantId);

}
