package br.com.feevale.controller;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.feevale.controller.exception.ExampleException;
import br.com.feevale.dao.ExampleDAO;
import br.com.feevale.model.Example;

/**
 * Efetua testes no controlador do m�dulo Example
 * 
 * @author isaias_alves <isaias@wswork.com.br>
 * @version 1.0
 */
public class ExampleControllerBeanTest {

	Long defaultTestId;

	Example exampleTest;
	
	@Mock
	private ExampleDAO exampleDAOMock = new ExampleDAO();

	private ExampleControllerBean controller = null;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		defaultTestId = 0022464l;
		controller = new ExampleControllerBean();
		exampleTest = new Example();
	}

	/**
	 * Quando a execu��o da leitura de um Example for feita � preciso
	 * certificar-se de que o metodo referente � leitura no dao ser� chamado
	 */
	@Test
	public void daoReadMethodMustBeCalled() {
		try {
			
			exampleDAOMock = Mockito.mock(ExampleDAO.class);
			controller.exampleDAO = exampleDAOMock;
			
			@SuppressWarnings("unused")
			final Example foundExample = controller.findById(defaultTestId);
			
			Mockito.verify(exampleDAOMock).findExampleById(defaultTestId);
		} catch (ExampleException e) {
			fail("Erro ao verificar chamada de m�todo de leitura");
		}
	}
	
	
	/**
	 * Quando a execu��o da cria��o de um Example for feita � preciso
	 * certificar-se de que o metodo referente � leitura no dao ser� chamado
	 */
	@Test
	public void daoCreateMethodMustBeCalled() {
		try {
			
			exampleDAOMock = Mockito.mock(ExampleDAO.class);
			controller.exampleDAO = exampleDAOMock;
			
			exampleTest = createNewValidExample();
			
			@SuppressWarnings("unused")
			final Example foundExample = controller.save(exampleTest);
			
			Mockito.verify(exampleDAOMock).insert(exampleTest);
		} catch (ExampleException e) {
			fail("Erro ao verificar chamada de m�todo de grava��o " + e.getMessage());
		}
	}
	
	@Test(expected=ExampleException.class)
	public void checkValidationFunctionForValidAndInvalidExamples() throws ExampleException {
		
		// Um exemplo inv�lido precisa obrigatoriamente gerar uma exception na hora de salvar
		try {
			controller.save(createNewInvalidExample());
		} catch (ExampleException e) {
			throw e;
		}
	}
	
	/**
	 * Cria um objeto exemplo com valores v�lidos
	 * @return
	 */
	protected Example createNewValidExample() {
		
		Example validExample = new Example();
		
		validExample.setName("Nome v�lido.");;
		validExample.setId(this.defaultTestId);
		validExample.setAge(12);
		validExample.setDescription("a b c d e f g h i j k l m n o p q r s t u v z y z");
		validExample.setCity("Montenegro - RS");
		
		return validExample;
	}
	
	/**
	 * Cria um objeto exemplo com valores invalidos
	 * @return
	 */
	protected Example createNewInvalidExample() {
		Example invalidExample = new Example();

		invalidExample.setAge(-1);
		invalidExample.setCity("a");
		invalidExample.setDescription(null);
		
		return invalidExample;
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
}
