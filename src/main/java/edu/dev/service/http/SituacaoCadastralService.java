package edu.dev.service.http;

import edu.dev.domain.http.AgenciaBank;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/situacao-cadastral")
@RegisterRestClient(configKey = "situacao-cadastral-api")
public interface SituacaoCadastralService {

    @GET
    @Path("{cnpj}")
    AgenciaBank buscarPorCnpj(String cnpj);
}
