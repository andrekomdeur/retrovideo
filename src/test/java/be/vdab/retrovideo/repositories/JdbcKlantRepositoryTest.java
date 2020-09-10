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
@Import(JdbcKlantRepository.class)
@Sql("/insertKlanten.sql")
public class JdbcKlantRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String KLANTEN = "klanten";
    private final JdbcKlantRepository repository;

    public JdbcKlantRepositoryTest(JdbcKlantRepository repository) {
        this.repository = repository;
    }

    private long idVanTestKlant() {
        return super.jdbcTemplate.queryForObject(
                "select id from klanten where familienaam='Test2'", Long.class);
    }
    @Test
    void findById() {
        assertThat(repository
                .findById(idVanTestKlant())
                .get().getFamilieNaam())
                .isEqualTo("Test2");
    }

    @Test
    void findByOnbestaandeIdVindtGeenKlant() {
        assertThat(repository.findById(-1)).isEmpty();
    }

    @Test
    void findAll() {
        assertThat(repository.findAll()).hasSize(super.countRowsInTable(KLANTEN))
                .extracting(entry -> entry.getFamilieNaam())
                .isSorted();
    }

    @Test
    void findByPart() {
        assertThat(repository.findPart("Test"))
                .hasSize(super.countRowsInTableWhere(KLANTEN, "familienaam like \'%Test%\'"))
                .allSatisfy(klant ->
                        assertThat(klant.getGemeente()).isEqualTo("Testplaats"))
                .extracting(klant -> klant.getFamilieNaam()).isSorted();
    }

}
