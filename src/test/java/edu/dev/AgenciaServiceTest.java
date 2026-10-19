package edu.dev;

import edu.dev.domain.Agencia;
import edu.dev.domain.Endereco;
import edu.dev.domain.http.AgenciaBank;
import edu.dev.domain.http.SituacaoCadastral;
import edu.dev.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import edu.dev.repository.AgenciaRepository;
import edu.dev.service.AgenciaService;
import edu.dev.service.http.SituacaoCadastralService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class AgenciaServiceTest {

    @InjectMock
    private AgenciaRepository agenciaRepository;

    @InjectMock
    @RestClient
    private SituacaoCadastralService situacaoCadastralService;

    @Inject
    private AgenciaService agenciaService;

    @Test
    public void deveNaoCadastrarQuandoClienteRetornarNull() {

        Agencia agencia = criarAgencia();
        Mockito.when(situacaoCadastralService.buscarPorCnpj("123")).thenReturn(null);

        Assertions.assertThrows(AgenciaNaoAtivaOuNaoEncontradaException.class,
                () -> agenciaService.cadastrar(agencia));

        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }

    @Test
    public void deveCadastrarQuandoClienteRetornarSituacaoCadastralAtiva() {
        Agencia agencia = criarAgencia();
        Mockito.when(situacaoCadastralService.buscarPorCnpj("123")).thenReturn(criarAgenciaHttp());

        agenciaService.cadastrar(agencia);

        Mockito.verify(agenciaRepository).persist(agencia);
    }

    private Agencia criarAgencia() {
        Endereco endereco = new Endereco(1, "jorge zarur", "teste", "teste", 1);
        return new Agencia(1, "Agencia Teste", "Razao social teste", "123", endereco);
    }

    private AgenciaBank criarAgenciaHttp() {
        return new AgenciaBank("Agencia Teste", "123", "Razao social teste", SituacaoCadastral.ATIVO    );
    }
}
