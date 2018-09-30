package br.com.ufcg.service;

import br.com.ufcg.domain.Servico;
import br.com.ufcg.repository.ServicoRepository;
import br.com.ufcg.util.validadores.ServicoValidador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {


    @Autowired
    ServicoRepository servicoRepository;

    public Servico criarServico(Servico servico) throws Exception {
        ServicoValidador.valida(servico);
        Example<Servico> example = Example.of(servico);
        Servico hasServico = servicoRepository.findServico(servico.getData(), servico.getHorario(), servico.getId_cliente(), servico.getTipo());
        if(hasServico != null) {
            throw new Exception("Serviço já cadastrado no banco de dados.");
        }
     return servicoRepository.save(servico);
    }
}
