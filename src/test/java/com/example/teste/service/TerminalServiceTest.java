package com.example.teste.service;

import com.example.teste.domain.Terminal;
import com.example.teste.repository.InfoRepository;
import com.example.teste.service.exception.LogicNaoEncontradoException;
import com.example.teste.service.impl.InfoServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class TerminalServiceTest {

    private static final Integer LOGIC = 44332211;
    private static final String SERIAL = "123";
    private static final String MODEL = "PWWIN";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @MockBean
    private InfoRepository infoRepository;

    public InfoService sut;

    private Terminal terminal;

    private Integer logic;

    @Before
    public void setUp() throws Exception{
        sut = new InfoServiceImpl(infoRepository);

        terminal = new Terminal();
        terminal.setLogic(LOGIC);
        terminal.setSerial(SERIAL);
        terminal.setModel(MODEL);

        when(infoRepository.findByLogic(LOGIC)).thenReturn(Optional.empty());
    }

    @Test
    public void deve_salvar_info_no_repositorio() throws Exception {
        sut.salvar(terminal);
        verify(infoRepository).save(terminal);
    }

    @Test
    public void deve_procurar_info_pelo_atributo_logic() throws Exception {
        when(infoRepository.findByLogic(LOGIC)).thenReturn(Optional.of(terminal));

        assertThat(terminal).isNotNull();
        assertThat(terminal.getLogic()).isEqualTo(LOGIC);
        assertThat(terminal.getModel()).isEqualTo(MODEL);
    }

    @Test(expected = LogicNaoEncontradoException.class)
    public void deve_retornar_exececao_quando_nao_encontrar_atributo_logic() throws Exception {
        sut.buscarLogic(logic);
    }

    @Test
    public void deve_dados_dentro_da_execessao() throws Exception{
        expectedException.expect(LogicNaoEncontradoException.class);
        expectedException.expectMessage("Não existe info com o parametro "+logic);
        sut.buscarLogic(logic);
    }
}
