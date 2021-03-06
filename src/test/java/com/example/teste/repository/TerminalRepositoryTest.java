package com.example.teste.repository;

import com.example.teste.domain.Terminal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.springframework.boot .test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class TerminalRepositoryTest {
    
    @Autowired
    private TerminalRepository sut;

    @Test
    public void deve_procurar_um_terminal_pelo_atributo_logic() throws Exception {
        Optional<Terminal> optional = sut.findByLogic(44332211);

        assertThat(optional.isPresent()).isTrue();

        Terminal terminal = optional.get();
        assertThat(terminal.getModel()).isEqualTo("PWWIN");
        assertThat(terminal.getLogic()).isEqualTo(44332211);
        assertThat(terminal.getSerial()).isEqualTo("123");
        assertThat(terminal.getSam()).isEqualTo(0);
        assertThat(terminal.getPtid()).isEqualTo("F04A2E4088B");
        assertThat(terminal.getPlat()).isEqualTo(4);
        assertThat(terminal.getVersion()).isEqualTo("8.00b3");
        assertThat(terminal.getMxr()).isEqualTo(0);
        assertThat(terminal.getMxf()).isEqualTo(16777216);
        assertThat(terminal.getVerfm()).isEqualTo("PWWIN");


    }

    @Test
    public void nao_deve_retornar_objeto_se_o_atributo_logic_nao_for_encontrado() throws Exception {
        Optional<Terminal> optional = sut.findByLogic(431);

        assertThat(optional.isPresent()).isFalse();
    }


}
