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
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class SpringBootRestApplicationTests {

	@Autowired
	ICidadeService iCidadeService;

	@Autowired
	IClienteService iClienteService;

	@Test
	void testeA() {
		try {
			Cidade cidade = new Cidade("Cidate Teste", "TEST");
			assertNotNull("cadastrar cidade - nome", cidade.getNome());
			assertNotNull("cadastrar cidade - estado", cidade.getEstado());
			this.iCidadeService.save(cidade);
			assertNotNull("cadastrar cidade - cidade", cidade.getId());
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeB(){
		try {
			Cidade cidade = this.iCidadeService.findById(1);
			assertNotNull("cadastrar cliente - cidade", cidade);
			Cliente cliente = new Cliente("Cliente Teste", EnumSexo.MASCULINO.getCodigo(), new Date(), 25, 1);
			assertNotNull("cadastrar cliente - nome completo",cliente.getNomeCompleto());
			assertNotNull("cadastrar cliente - sexo",cliente.getSexo());
			assertNotNull("cadastrar cliente - datanacimento",cliente.getDataNascimento());
			assertNotNull("cadastrar cliente - idade",cliente.getIdade());
			this.iClienteService.save(cliente);
			assertNotNull("cadastrar cliente - cliente", cliente.getId());
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeC(){
		try {
			List<Cidade> cidadesNome = this.iCidadeService.getCidadesByParamAndField("Cidate Teste", NOME);
			assertNotNull("consultar cidade - nome", cidadesNome);
			List<Cidade> cidadesEstado = this.iCidadeService.getCidadesByParamAndField("TEST", ESTADO);
			assertNotNull("consultar cidade - estado", cidadesEstado);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeD(){
		try {
			List<Cliente> clientes = this.iClienteService.getClientesByNomeCompleto("Cliente Teste");
			assertNotNull("consultar cliente - nome", clientes);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeE(){
		try {
			Cliente cliente = this.iClienteService.findById(1);
			assertNotNull("consultar cliente - id", cliente);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeF(){
		try {
			Cliente cliente1 = this.iClienteService.findById(1);
			this.iClienteService.alterarNome(1, "Cliente Teste Alterado");
			Cliente cliente2 = this.iClienteService.findById(1);
			assertNotEquals("alterar nome cliente", cliente1.getNomeCompleto(), cliente2.getNomeCompleto());
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testeG(){
		try {
			this.iClienteService.delete(1);
			boolean exists = this.iClienteService.existsById(1);
			assertEquals("deletar cliente",false, exists);
		} catch (RuleException e) {
			e.printStackTrace();
		}
	}
}