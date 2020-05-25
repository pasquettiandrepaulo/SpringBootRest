package com.andre.compasso.rest.cliente;

import com.andre.compasso.entity.cliente.Cliente;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.rest.util.ResponseMessage;
import com.andre.compasso.service.cidade.CidadeService;
import com.andre.compasso.service.cliente.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.andre.compasso.rest.util.MessageUtil.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("/api/cliente")
public class ClienteRest {

    @Autowired
    ClienteService clienteService;

    @Autowired
    CidadeService cidadeService;

    @GetMapping(value = "/consultarPorNomeCompleto", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarPorNomeCompleto(@RequestParam("nomeCompleto") String nomeCompleto) {

        if (nomeCompleto == null || nomeCompleto.trim().isEmpty())
            return ResponseEntity.badRequest().body(new ResponseMessage(BAD_REQUEST.value(), MENSAGEM_ERRO_PROCESSAR_PARAMETRO, "nomeCompleto"));

        try {

           List<Cliente> clientes = this.clienteService.getClientesByNomeCompleto(nomeCompleto);
           return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_CONSULTA_SUCESSO, clientes));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }

    @GetMapping(value = "/consultarPorId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> consultarPorId(@RequestParam("id") Integer id) {
        try {

            Cliente cliente = this.clienteService.findById(id);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_CONSULTA_SUCESSO, cliente));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }

    @PostMapping(value = "/cadastrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> cadastrar(@RequestBody Cliente cliente){
        try {

            this.clienteService.save(cliente);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_CADASTRO_SUCESSO, cliente));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }

    @DeleteMapping(value = "/deletar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletar(@RequestParam("id") Integer id){
        try {

            this.clienteService.delete(id);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_DELETADO_SUCESSO, id));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }

    @PutMapping(value = "/alterarNome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> alterarNome(@RequestParam("id") Integer id, @RequestParam("nome") String nome){
        try {

            this.clienteService.alterarNome(id, nome);
            return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK.value(), MENSAGEM_ALTERACAO_SUCESSO, id));

        } catch (RuleException e) {
            return e.getResponse();
        }
    }
}
