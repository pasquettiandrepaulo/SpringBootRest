package com.andre.compasso.service.cliente;

import com.andre.compasso.entity.cliente.Cliente;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.repository.cliente.IClienteRepository;
import com.andre.compasso.rest.util.ResponseMessage;
import com.andre.compasso.service.cidade.ICidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.andre.compasso.rest.util.MessageUtil.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    IClienteRepository iClienteRepository;

    @Autowired
    ICidadeService iCidadeService;

    @Override
    public List<Cliente> getClientesByNomeCompleto(String nome) throws RuleException {

        List<Cliente> clientes = this.iClienteRepository.getClientesByNomeCompleto(nome);

        if (clientes.isEmpty())
            throw new RuleException(ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.NO_CONTENT.value(), MENSAGEM_CONSULTA_SEM_DADOS, new ArrayList())));

        return clientes;
    }

    @Override
    public void alterarNome(Integer id, String nome) throws RuleException {

        boolean exists = this.iClienteRepository.existsById(id);
        if (!exists)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "id"), id)));

        if (nome == null || nome.isEmpty())
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "nome"), nome)));

        this.iClienteRepository.alterarNome(id, nome);
    }

    @Override
    public void save(Cliente cliente) throws RuleException {

        this.validarCliente(cliente);
        cliente.setCidade(this.iCidadeService.findById(cliente.getCidade().getId()));
        this.iClienteRepository.save(cliente);
    }

    private void validarCliente(Cliente cliente) throws RuleException {

        if (cliente.getId() != null)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), MENSAGEM_ALTERACAO_INVALIDA, "Não é possível alterar cliente")));

        if (cliente.getNomeCompleto() == null || cliente.getNomeCompleto().isEmpty())
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "nomeCompleto"), cliente.getNomeCompleto())));

        if (cliente.getIdade() == null)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "idade"), cliente.getIdade() )));

        if (cliente.getIdade() == 0 || cliente.getIdade() >= 120)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "idade"), cliente.getIdade())));

        if (cliente.getSexo() == null)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "idade"), cliente.getIdade())));

        if (cliente.getCidade() == null)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "cidade"), cliente.getCidade())));

        if (cliente.getCidade().getId() != null)
            if (!this.iCidadeService.existsById(cliente.getCidade().getId()))
                throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "cidade"), cliente.getCidade().getId())));
    }

    @Override
    public Cliente findById(Integer id) throws RuleException {

        if (id == null || id == 0)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_PROCESSAR_PARAMETRO, "id"), id)));

        boolean exists = this.iClienteRepository.existsById(id);
        if (exists)
            return this.iClienteRepository.findById(id).get();
        else
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "id"), id)));
    }

    @Override
    public void delete(Integer id) throws RuleException {

        if (id == null || id == 0)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_PROCESSAR_PARAMETRO, "id"), id)));

        boolean exists = this.iClienteRepository.existsById(id);
        if (exists)
            this.iClienteRepository.deleteById(id);
        else
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "id"), id)));
    }

    @Override
    public boolean existsById(Integer id) {
        return this.iClienteRepository.existsById(id);
    }
}
