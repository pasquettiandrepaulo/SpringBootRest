package com.andre.compasso.rest.cidade;

import com.andre.compasso.entity.cidade.Cidade;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.rest.util.ResponseMessage;
import com.andre.compasso.service.cidade.CidadeService;
import com.andre.compasso.service.cidade.CidadeService.TIPO_PESQUISA_CIDADE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.andre.compasso.rest.util.MessageUtil.*;
import static com.andre.compasso.service.cidade.CidadeService.TIPO_PESQUISA_CIDADE.ESTADO;
import static com.andre.compasso.service.cidade.CidadeService.TIPO_PESQUISA_CIDADE.NOME;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/cidade")
public class CidadeRest {

    @Autowired
    CidadeService iCidadeService;

    @GetMapping(value = "/consultarPorNome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarPorNome(@RequestParam("nome") String nome) {
        return this.getCidadesByParamAndField(nome, NOME);
    }

    @GetMapping(value = "/consultarPorEstado", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarPorEstado(@RequestParam("estado") String estado) {
        return this.getCidadesByParamAndField(estado, ESTADO);
    }

    private ResponseEntity<Object> getCidadesByParamAndField(String param, TIPO_PESQUISA_CIDADE tipoPesquisa) {

        if (param == null || param.trim().isEmpty())
            return ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), MENSAGEM_ERRO_PROCESSAR_PARAMETRO, tipoPesquisa.name().toLowerCase()));

        try {

         List<Cidade> cidades = this.iCidadeService.getCidadesByParamAndField(param, tipoPesquisa);
         return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_CONSULTA_SUCESSO, cidades));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }

    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cadastrar(@RequestBody Cidade cidade){
        try {

            this.iCidadeService.save(cidade);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_CADASTRO_SUCESSO, cidade));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }
}
