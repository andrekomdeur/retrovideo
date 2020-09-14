package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.exceptions.FilmNietGevondenException;
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
public class JdbcFilmRepository implements FilmRepository {
    private final JdbcTemplate template;
    private final RowMapper<Film> filmMapper =
            (result, rowNum) -> new Film(
                    result.getLong("id"),
                    result.getLong("genreid"),
                    result.getString("titel"),
                    result.getInt("voorraad"),
                    result.getInt("gereserveerd"),
                    result.getBigDecimal("prijs"));

    public JdbcFilmRepository(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void update(Film film) {
        String sql = "update films set gereserveerd=? where id=?";
        if (template.update(sql,
                film.getGereserveerd() + 1,
                film.getId()) == 0) {
            throw new FilmNietGevondenException();
        }
    }

    @Override
    public Optional<Film> findById(long id) {
        try {
            String sql = "select id, genreid, titel" +
                    ", voorraad,gereserveerd,prijs from films where id=?";
            return Optional.of(template.queryForObject(sql, filmMapper, id));
        } catch (IncorrectResultSizeDataAccessException ex) {
            return Optional.empty();
        }
    }

    @Override
    public List<Film> findByGenre(Long genreId) {
        try {
            String sql = "select * from films where genreid = ? order by titel";
            return template.query(sql, filmMapper, genreId);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Film> findAll() {
        try {
            String sql = "select * from films order by titel";
            return template.query(sql, filmMapper);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }
}
