package br.com.gruporbs.dao;

import static org.junit.Assert.fail;
import org.junit.Test;

import br.com.gruporbs.model.Example;

/**
 * Testa as funcionalidades do nosso DAO estatico
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 */
public class ExampleDAOTest {

	ExampleDAO exampleDAO = ExampleDAO.getInstance();
	final Long testId = 22464l;
	/**
	 * Verifica se um método de busca fará retorno de um objeto exemplo válido
	 */
	@Test
	public void checkIfFindMethodReturnAValidExample() {
		
		Example foundExample = exampleDAO.findExampleById(testId);
		if (!(foundExample.getId()!=null && foundExample.getName()!=null)) {
			fail("A busca de exemplos esta retornando valores inválidos, verifique sua implementação");
		}
		
	}
	
	/**
	 * Quando há a remoção de um exemplo é preciso garantir que 
	 * o objeto removido nao existe mais em bamco de dados
	 * como estamos usando valores estaticos o Mock do dao nao 
	 * é necessário aqui, quando tivermos um banco será necessário
	 * este mock, para que a exclusão não ocorra espeficicamente
	 */
	@Test
	public void checkIfDeletedExampleExists() {
		
		Example deletedExample = new Example();
		deletedExample.setId(testId);
		
		exampleDAO.deleteExample(deletedExample);
		
		if (exampleDAO.example!=null) {
			fail("O método de exclusão não esta OK, pois segue existindo o item removido.");
		}
		
	}
	
	/**
	 * Verifica se um exemplo válido pode ser corretamente inserido
	 */
	@Test
	public void checkIfExampleIsCorrectlyInserted() {
		
		exampleDAO.insert(getValidExampleFromThisTest());
		
		if (!(exampleDAO.example!=null && exampleDAO.example.getId().equals(testId))) {
			fail("A inserção de um objeto válido não pôde ocorrer, verifique implementação deste código.");
		}
	}
	
	/**
	 * A atualização recisa realmente alterar um valor de objeto, caso contrário
	 * há confirmação de problema em seu funcionamento.
	 */
	@Test
	public void checkIfUpdateMethodReallyChangeValueOfDAO() {
		
		Example updateExample = getValidExampleFromThisTest();
		
		final String changedValue = "Estou alterando o nome para ver se mudou";
		
		updateExample.setName(changedValue); // Legal seria testar todos os campos, mas a efetividade ja se comprova pois se estivessemos
											 // usando um componente de BD usando ORM (object relational mapping) se um campo for alterado
											 // é lógico que os demais também seriam, tendo vista que o mapeamento construído com construção
											 // do contexto de deploy da aplicação iria gerar erro em tempo de deploy... por isso 1 campo testado ja seria suficiente
		
		exampleDAO.update(updateExample);
		
		if (!(exampleDAO.example!= null && exampleDAO.example.getName().equals(changedValue))) {
			fail("Ao alterar um valor não foi possível constatar efeito algum, verifique o UPDATE do DAO.");
		}
		
	}
	

	/**
	 * Pega um exemplo válido para utilizar nesses testes
	 * @return
	 */
	protected Example getValidExampleFromThisTest() {
		
		Example validExample = new Example();
		
		validExample.setId(testId);
		validExample.setAge(23);
		validExample.setCity("Caçapava do sul - RS");
		validExample.setDescription("Uma descrição válida para ser inserida...");
		validExample.setName("Isaías A. Alves - 0022464, acresse meu blog www.thesimplework.blogspot.com");
		
		return validExample;
	}
	
	
}
