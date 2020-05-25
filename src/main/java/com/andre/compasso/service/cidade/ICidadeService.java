package com.andre.compasso.service.cidade;

import com.andre.compasso.entity.cidade.Cidade;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.service.cidade.CidadeService.TIPO_PESQUISA_CIDADE;
import com.andre.compasso.service.generic.IGenericService;

import java.util.List;

public interface ICidadeService extends IGenericService<Cidade> {

    List<Cidade> getCidadesByParamAndField(String param, TIPO_PESQUISA_CIDADE tipoPesquisaCidade) throws RuleException;

    boolean existsById(Integer integer);
}
