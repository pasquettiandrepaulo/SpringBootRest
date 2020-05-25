package com.andre.compasso.service.cliente;

import com.andre.compasso.entity.cliente.Cliente;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.service.generic.IGenericService;

import java.util.List;

public interface IClienteService extends IGenericService<Cliente> {

    List<Cliente> getClientesByNomeCompleto(String nome) throws RuleException;

    void alterarNome(Integer id, String nome) throws RuleException;
}
