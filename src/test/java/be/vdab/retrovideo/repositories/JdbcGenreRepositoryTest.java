package be.vdab.retrovideo.repositories;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @Author Andre Komdeur
 */
@JdbcTest
@Import(JdbcGenreRepository.class)
public class JdbcGenreRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String GENRES = "genres";
    private final JdbcGenreRepository repository;

    public JdbcGenreRepositoryTest(JdbcGenreRepository repository) {
        this.repository = repository;
    }

    private long idVanTestGenre() {
        return super.jdbcTemplate.queryForObject(
                "select id from genres where naam='Thriller'", Long.class);
    }
    @Test
    void findById() {
        assertThat(repository
                .findById(idVanTestGenre())
                .get()
                .getNaam())
                .isEqualTo("Thriller");
    }

    @Test
    void findByOnbestaandeIdVindtGeenGenre() {
        assertThat(repository.findById(-1)).isEmpty();
    }

    @Test
    void findAllGeeftAlleGenresGesorteerdOpNaam() {
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(GENRES))
                .extracting(entry -> entry.getNaam())
                .isSorted();
    }}
