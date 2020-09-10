package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Klant;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 * @Author Andre Komdeur
 */
@Repository
public class JdbcKlantRepository implements KlantRepository{
    private final JdbcTemplate template;
    private final RowMapper<Klant> klantMapper =
            (result, rowNum) -> new Klant(
                    result.getLong("id"),
                    result.getString("familienaam"),
                    result.getString("voornaam"),
                    result.getString("straatnummer"),
                    result.getString("postcode"),
                    result.getString("gemeente"));

    public JdbcKlantRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public Optional<Klant> findById(long id) {
        try {
            String sql = "select id, familienaam,voornaam,straatnummer,postcode,gemeente from klanten where id=?";
            return Optional.of(template.queryForObject(sql, klantMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }
    @Override
    public List<Klant> findAll() {
        try {
            String sql = "select * from klanten order by familienaam";
            return template.query(sql, klantMapper);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
    @Override
    public List<Klant> findPart( String part) {
            List<Klant> lijstje;
            String sql = "select * from klanten where familienaam like "
                    + "\'%" + part + "%\'" + " order by familienaam";
        lijstje = template.query(sql, klantMapper);
        return lijstje;
    }
}
