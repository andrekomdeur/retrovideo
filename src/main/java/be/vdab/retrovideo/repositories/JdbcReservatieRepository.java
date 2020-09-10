package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Reservatie;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author Andre Komdeur
 */
@Repository
public class JdbcReservatieRepository implements ReservatieRepository{
    private final SimpleJdbcInsert insert;
    private final JdbcTemplate template;
    private final RowMapper<Reservatie> reservatieMapper =
            (result, rowNum) -> new Reservatie(
                    result.getLong("klantid"),
                    result.getLong("filmid"),
                    result.getTimestamp("reservatie"));

    public JdbcReservatieRepository(JdbcTemplate template) {
        this.template = template;
        this.insert = new SimpleJdbcInsert(template);
        insert.withTableName("reservaties");
    }
    @Override
    public int create(Reservatie reservatie) {
        Map<String, Object > kolomWaarden = new HashMap<>();
        kolomWaarden.put("klantid",reservatie.getKlantId());
        kolomWaarden.put("filmid",reservatie.getFilmId());
        kolomWaarden.put("reservatie",reservatie.getReservatieMoment());
        return insert.execute(kolomWaarden);
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
            String sql = "select * from reservaties";
            return template.query(sql, reservatieMapper);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

}
