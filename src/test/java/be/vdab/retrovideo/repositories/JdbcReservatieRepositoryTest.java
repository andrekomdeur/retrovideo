package be.vdab.retrovideo.repositories;
import be.vdab.retrovideo.domain.Reservatie;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * @Author Andre Komdeur
 */
@JdbcTest
@Import(JdbcReservatieRepository.class)
@Sql("/insertReservaties.sql")
public class JdbcReservatieRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String RESERVATIES = "reservaties";
    private final JdbcReservatieRepository repository;

    public JdbcReservatieRepositoryTest(JdbcReservatieRepository repository) {
        this.repository = repository;
    }

    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject(
                "select id from klanten where familienaam='Test'", Long.class);
    }
    private long idVanTestFilm() {
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel='Test'", Long.class);
    }
    private long idVanTestFilm2() {
        return super.jdbcTemplate.queryForObject(
                "select id from films where titel='Test2'", Long.class);
    }

    private Timestamp testReservatieMoment() {
        return new Timestamp(System.currentTimeMillis());
    }

    @Test
    void create() {
        int res = repository.create(new Reservatie(
                idVanTestKlant(), idVanTestFilm(), testReservatieMoment()));
        assertThat(res).isEqualTo(1);
        assertThat(super.countRowsInTableWhere(RESERVATIES, "klantid=" + idVanTestKlant())).isOne();
    }
    @Test
    void findByKlantId() {
        repository.create(new Reservatie(
                idVanTestKlant(), idVanTestFilm(), testReservatieMoment()));
        repository.create(new Reservatie(
                idVanTestKlant(), idVanTestFilm2(), testReservatieMoment()));
        assertThat(repository.findByKlantId(idVanTestKlant()))
                .hasSize(super.jdbcTemplate.queryForObject(
                        "select count(*) from reservaties where klantid=?"
                        , Integer.class, idVanTestKlant()))
                .extracting(res -> res.getKlantId())
                .isSorted();
    }
    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(RESERVATIES))
                .extracting(entry -> entry.getFilmId());
    }

}
