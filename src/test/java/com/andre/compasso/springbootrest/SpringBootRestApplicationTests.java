package com.andre.compasso.springbootrest;

import com.andre.compasso.entity.cidade.Cidade;
import com.andre.compasso.entity.cliente.Cliente;
import com.andre.compasso.entity.cliente.EnumSexo;
import com.andre.compasso.exception.RuleException;
import com.andre.compasso.repository.cidade.ICidadeRepository;
import com.andre.compasso.repository.cliente.IClienteRepository;
import com.andre.compasso.service.cidade.CidadeService;
import com.andre.compasso.service.cidade.ICidadeService;
import com.andre.compasso.service.cliente.IClienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.util.AssertionErrors;

import java.util.Date;
import java.util.List;

import static com.andre.compasso.service.cidade.CidadeService.TIPO_PESQUISA_CIDADE.ESTADO;
import static com.andre.compasso.service.cidade.CidadeService.TIPO_PESQUISA_CIDADE.NOME;
import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.springframework.test.util.AssertionErrors.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpringBootRestApplicationTests {

	@Autowired
	ICidadeService iCidadeService;

	@Autowired
	ICidadeRepository iCidadeRepository;

	@Autowired
	IClienteService iClienteService;

	@Order(1)
	@Test
	void cadastrarCidade() {
		Cidade cidade = new Cidade("Cidate Teste", "TEST");
		assertNotNull("cadastrar cidade - nome", cidade.getNome());
		assertNotNull("cadastrar cidade - estado", cidade.getEstado());
		this.iCidadeRepository.saveAndFlush(cidade);
		assertNotNull("cadastrar cidade - cidade", cidade.getId());
	}

	@Order(2)
	@Test
	public void cadastrarCliente(){
		try {
			Cidade cidade = this.iCidadeService.findById(1);
			assertNull("cadastrar cliente - cidade null", cidade);
			Cliente cliente = new Cliente("Cliente Teste", EnumSexo.MASCULINO, new Date(), 25, cidade);
			assertNotNull("cadastrar cliente - nome completo",cliente.getNomeCompleto());
			assertNotNull("cadastrar cliente - sexo",cliente.getSexo());
			assertNotNull("cadastrar cliente - datanacimento",cliente.getDataNascimento());
			assertNotNull("cadastrar cliente - idade",cliente.getIdade());
			assertNotNull("cadastrar cliente - cidade",cliente.getCidade());
			this.iClienteService.save(cliente);
			assertNotNull("cadastrar cliente - cliente", cliente.getId());
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Order(3)
	@Test
	public void consultarCidadePorParametro(){
		try {
			List<Cidade> cidadesNome = this.iCidadeService.getCidadesByParamAndField("Cidate Teste", NOME);
			assertNotNull("consultar cidade - nome", cidadesNome);
			List<Cidade> cidadesEstado = this.iCidadeService.getCidadesByParamAndField("TEST", ESTADO);
			assertNotNull("consultar cidade - estado", cidadesEstado);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Order(4)
	@Test
	public void consultarClientePorNome(){
		try {
			List<Cliente> clientes = this.iClienteService.getClientesByNomeCompleto("Cliente Teste");
			assertNotNull("consultar cliente - nome", clientes);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Order(5)
	@Test
	public void consultarClientePorId(){
		try {
			Cliente cliente = this.iClienteService.findById(1);
			assertNotNull("consultar cliente - id", cliente);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Order(6)
	@Test
	public void alterarNomeCliente(){
		try {
			Cliente cliente1 = this.iClienteService.findById(1);
			this.iClienteService.alterarNome(1, "Cliente Teste Alterado");
			Cliente cliente2 = this.iClienteService.findById(1);
			assertNotEquals("alterar nome cliente", cliente1.getNomeCompleto(), cliente2.getNomeCompleto());
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Order(7)
	@Test
	public void deletarCliente(){
		try {
			this.iClienteService.delete(1);
			Cliente cliente = this.iClienteService.findById(1);
			assertNotNull("deletar cliente", cliente);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}
}