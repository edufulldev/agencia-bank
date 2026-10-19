package edu.dev.service;

import edu.dev.domain.Agencia;
import edu.dev.domain.http.AgenciaBank;
import edu.dev.domain.http.SituacaoCadastral;
import edu.dev.exceptions.AgenciaNaoAtivaOuNaoEncontradaException;
import edu.dev.repository.AgenciaRepository;
import edu.dev.service.http.SituacaoCadastralService;
import io.micrometer.core.instrument.MeterRegistry;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralService situacaoCadastralService;

    private  final AgenciaRepository agenciaRepository;
    private final MeterRegistry meterRegistry;

    AgenciaService(AgenciaRepository agenciaRepository, MeterRegistry meterRegistry) {

        this.agenciaRepository = agenciaRepository;
        this.meterRegistry = meterRegistry;
    }

    public void cadastrar(Agencia agencia) {
        AgenciaBank agenciaHttp = situacaoCadastralService.buscarPorCnpj(agencia.getCnpj());

        if(agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            Log.info("A agencia com o CNPJ" + agencia.getCnpj() + " foi cadastrada");
            meterRegistry.counter("agencia_add_counter").increment();
            agenciaRepository.persist(agencia);
        } else  {
            Log.info("A agencia com o CNPJ" + agencia.getCnpj() + " não foi cadastrada");
            meterRegistry.counter("agencia_nao_add_counter").increment();
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    public void deleter(Long id) {
        Log.info("A agencia com o ID" + id + " foi deletada");
        agenciaRepository.deleteById(id);
    }

    public void alterar(Agencia agencia) {
        Log.info("A agencia com o CNPJ" + agencia.getCnpj() + " foi alterada");
       agenciaRepository.update(
               "nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4",
               agencia.getNome(),
               agencia.getRazaoSocial(),
               agencia.getCnpj(),
               agencia.getId()
       );
    }
}
