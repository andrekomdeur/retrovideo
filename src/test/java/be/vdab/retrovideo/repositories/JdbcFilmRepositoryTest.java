package be.vdab.retrovideo.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @Author Andre Komdeur
 */
@JdbcTest
@Import(JdbcFilmRepository.class)
@Sql("/insertFilms.sql")
public class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String FILMS = "films";
    private final JdbcFilmRepository repository;

    public JdbcFilmRepositoryTest(JdbcFilmRepository repository) {
        this.repository = repository;
    }
    private long idVanTestFilm() {
        return super.jdbcTemplate.queryForObject(
                "select id from films where naam='test'", Long.class);
    }

    @Test
    void findAllGeeftAlleFilmsGesorteerdOpId() {
        assertThat(repository.findAll())
                .hasSize(super.countRowsInTable(FILMS))
                .extracting(film->film.getTitel()).isSorted();
    }
    @Test
    void findById() {
        assertThat(repository.findById(idVanTestFilm()).get().getTitel()).isEqualTo("test");
    }
    @Test
    void findByOnbestaandeIdVindtGeenFilm() {
        assertThat(repository.findById(-1)).isEmpty();
    }
    @Test
    void findByGenre() {
        assertThat(repository.findByGenre(1L))
                .hasSize(super.countRowsInTableWhere(FILMS, "genreid is 1"))
                .allSatisfy(film ->
                        assertThat(film.getGenreId()).isEqualTo(1L))
                .extracting(film->film.getTitel()).isSorted();
    }
}
