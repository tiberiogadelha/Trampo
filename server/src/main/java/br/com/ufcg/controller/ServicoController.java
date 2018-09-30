package br.com.ufcg.controller;

import br.com.ufcg.domain.Cliente;
import br.com.ufcg.domain.Servico;
import br.com.ufcg.domain.enums.TipoStatus;
import br.com.ufcg.service.ServicoService;
import br.com.ufcg.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @RequestMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<Response> cadastrarServico(HttpServletRequest request, @RequestBody Servico servico) {

        Response response;

        try {
            Long userId = (Long) request.getAttribute("userId");

            Cliente cliente = new Cliente();
            cliente.setId(userId);
            servico.setId_cliente(cliente);
            servico.setStatus(TipoStatus.EM_ABERTO);

            Servico servicoCadastrado = servicoService.criarServico(servico);

            response = new Response("Serviço cadastrado com sucesso !", HttpStatus.OK.value(), servicoCadastrado);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch(Exception e) {
            response = new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
