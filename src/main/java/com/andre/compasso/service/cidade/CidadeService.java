package com.andre.compasso.service.cidade;

import com.andre.compasso.entity.cidade.Cidade;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.repository.cidade.ICidadeRepository;
import com.andre.compasso.rest.util.MessageUtil;
import com.andre.compasso.rest.util.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.andre.compasso.rest.util.MessageUtil.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class CidadeService implements ICidadeService {

    public enum TIPO_PESQUISA_CIDADE{
        NOME, ESTADO;
    }

    @Autowired
    ICidadeRepository iCidadeRepository;

    @Override
    public void save(Cidade cidade) throws RuleException {
        this.validarCidade(cidade);
        this.iCidadeRepository.save(cidade);
    }

    private void validarCidade(Cidade cidade) throws RuleException{

        if(cidade != null && cidade.getId() != null)
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), MENSAGEM_ALTERACAO_INVALIDA, "Não é possível alterar cidade")));

        if (cidade.getNome() == null || cidade.getNome().isEmpty())
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "nome"), "nome")));

        if (cidade.getEstado() == null || cidade.getEstado().isEmpty())
            throw new RuleException(ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), String.format(MENSAGEM_ERRO_CAMPO_INVALIDO, "estado"), "estado")));
    }

    @Override
    public Cidade findById(Integer id){
        return this.iCidadeRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Cidade> getCidadesByParamAndField(String param, TIPO_PESQUISA_CIDADE tipoPesquisaCidade) throws RuleException {

        List<Cidade> cidades = new ArrayList();

        switch (tipoPesquisaCidade){
            case NOME:
                cidades = this.iCidadeRepository.getCidadesByNome(param);
                break;
            case ESTADO:
                cidades = this.iCidadeRepository.getCidadesByEstado(param);
                break;
            default:
                break;
        }

        if (cidades.isEmpty())
            throw new RuleException(ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpStatus.NO_CONTENT.value(), MENSAGEM_CONSULTA_SEM_DADOS, new ArrayList())));

        return cidades;
    }

    @Override
    public boolean existsById(Integer id) {
        return this.iCidadeRepository.existsById(id);
    }
}
