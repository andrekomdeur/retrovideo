package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Author Andre Komdeur
 */
@Repository
public class JdbcReservatieRepository implements ReservatieRepository{
    private final JdbcTemplate template;
    private final RowMapper<Reservatie> reservatieMapper =
            (result, rowNum) -> new Reservatie(
                    result.getLong("klantid"),
                    result.getLong("genreid"),
                    result.getTimestamp("reservatie"));

    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
    }
    @Override
    public List<Reservatie> findByKlantId(Long klantId) {
        try {
            String sql = "select * from reservaties where klantid = ? order by reservatie";
            return template.query(sql, reservatieMapper, klantId);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Reservatie> findAll() {
        try {
            String sql = "select * from reservaties order by titel";
            return template.query(sql, reservatieMapper);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

}
