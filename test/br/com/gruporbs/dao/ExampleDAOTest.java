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
	 * Verifica se um m�todo de busca far� retorno de um objeto exemplo v�lido
	 */
	@Test
	public void checkIfFindMethodReturnAValidExample() {
		
		Example foundExample = exampleDAO.findExampleById(testId);
		if (!(foundExample.getId()!=null && foundExample.getName()!=null)) {
			fail("A busca de exemplos esta retornando valores inv�lidos, verifique sua implementa��o");
		}
		
	}
	
	/**
	 * Quando h� a remo��o de um exemplo � preciso garantir que 
	 * o objeto removido nao existe mais em bamco de dados
	 * como estamos usando valores estaticos o Mock do dao nao 
	 * � necess�rio aqui, quando tivermos um banco ser� necess�rio
	 * este mock, para que a exclus�o n�o ocorra espeficicamente
	 */
	@Test
	public void checkIfDeletedExampleExists() {
		
		Example deletedExample = new Example();
		deletedExample.setId(testId);
		
		exampleDAO.deleteExample(deletedExample);
		
		if (exampleDAO.example!=null) {
			fail("O m�todo de exclus�o n�o esta OK, pois segue existindo o item removido.");
		}
		
	}
	
	/**
	 * Verifica se um exemplo v�lido pode ser corretamente inserido
	 */
	@Test
	public void checkIfExampleIsCorrectlyInserted() {
		
		exampleDAO.insert(getValidExampleFromThisTest());
		
		if (!(exampleDAO.example!=null && exampleDAO.example.getId().equals(testId))) {
			fail("A inser��o de um objeto v�lido n�o p�de ocorrer, verifique implementa��o deste c�digo.");
		}
	}
	
	/**
	 * A atualiza��o recisa realmente alterar um valor de objeto, caso contr�rio
	 * h� confirma��o de problema em seu funcionamento.
	 */
	@Test
	public void checkIfUpdateMethodReallyChangeValueOfDAO() {
		
		Example updateExample = getValidExampleFromThisTest();
		
		final String changedValue = "Estou alterando o nome para ver se mudou";
		
		updateExample.setName(changedValue); // Legal seria testar todos os campos, mas a efetividade ja se comprova pois se estivessemos
											 // usando um componente de BD usando ORM (object relational mapping) se um campo for alterado
											 // � l�gico que os demais tamb�m seriam, tendo vista que o mapeamento constru�do com constru��o
											 // do contexto de deploy da aplica��o iria gerar erro em tempo de deploy... por isso 1 campo testado ja seria suficiente
		
		exampleDAO.update(updateExample);
		
		if (!(exampleDAO.example!= null && exampleDAO.example.getName().equals(changedValue))) {
			fail("Ao alterar um valor n�o foi poss�vel constatar efeito algum, verifique o UPDATE do DAO.");
		}
		
	}
	

	/**
	 * Pega um exemplo v�lido para utilizar nesses testes
	 * @return
	 */
	protected Example getValidExampleFromThisTest() {
		
		Example validExample = new Example();
		
		validExample.setId(testId);
		validExample.setAge(23);
		validExample.setCity("Ca�apava do sul - RS");
		validExample.setDescription("Uma descri��o v�lida para ser inserida...");
		validExample.setName("Isa�as A. Alves - 0022464, acresse meu blog www.thesimplework.blogspot.com");
		
		return validExample;
	}
	
	
}
