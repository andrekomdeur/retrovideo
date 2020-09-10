package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Film;
import be.vdab.retrovideo.exceptions.FilmNietGevondenException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
/**
 * @Author Andre Komdeur
 */
@JdbcTest
@Import(JdbcFilmRepository.class)
@Sql("/insertFilms.sql")
class JdbcFilmRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String FILMS = "films";
    private final JdbcFilmRepository repository;

    public JdbcFilmRepositoryTest(JdbcFilmRepository repository) {
        this.repository = repository;
    }

    private long idVanTestFilm() {
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel='test'", Long.class);
    }

    /*
    insert into genres(id, naam ) VALUES (99,'test');
    insert into films(genreid,titel,voorraad,gereserveerd,prijs) VALUES (99,'test',5,1,4);
    insert into films(genreid,titel,voorraad,gereserveerd,prijs) VALUES (99,'test2',5,2,5);
     */
    @Test
    void update() {
        long id = idVanTestFilm();
        Film film = new Film(id, 99, "test", 5, 2, BigDecimal.valueOf(4));
        repository.update(film);
        assertThat(super.jdbcTemplate.queryForObject(
                "select gereserveerd from films where id=?", Long.class, id)).isEqualTo(3L);
    }

    @Test
    void updateOnbestaandeFilm() {
        assertThatExceptionOfType(FilmNietGevondenException.class)
                .isThrownBy(() -> repository.update(new Film(-1L, 99, "test", 5, 2, BigDecimal.valueOf(4))));
    }

    @Test
    void findById() {
        assertThat(repository
                .findById(idVanTestFilm())
                .get()
                .getTitel())
                .isEqualTo("test");
    }

    @Test
    void findByOnbestaandeIdVindtGeenFilm() {
        assertThat(repository.findById(-1)).isEmpty();
    }

    @Test
    void findByGenre() {
        assertThat(repository.findByGenre(99L))
                .hasSize(super.countRowsInTableWhere(FILMS, "genreid=99"))
                .allSatisfy(film ->
                        assertThat(film.getGenreId()).isEqualTo(99L))
                .extracting(film -> film.getTitel()).isSorted();
    }
}
