package br.com.gruporbs.resource;

import org.junit.Test;
import org.mockito.Mockito;

import br.com.gruporbs.controller.ExampleControllerBean;
import br.com.gruporbs.controller.exception.ExampleException;
import br.com.gruporbs.model.Example;

/**
 * Classe que possui testes referentes à resources (serviços)
 * que representam a interface dessa aplicação
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 *
 */
public class ExampleResourceTest {

	/**
	 * Testa a chegada de todos os recursos no controlador, criando
	 * um mock do controller e assim podendo constatar o correto
	 * vínculo.
	 * @throws ExampleException 
	 */
	@Test
	public void checkResourcePathToController() throws ExampleException {
		
		ExampleControllerBean controllerMock = Mockito.mock(ExampleControllerBean.class);
		
		ExampleResource resourceClass = new ExampleResource(); // Aqui faço o que o contexto faria, instancio o resource e defino o controller (um mock)
															   // assim consigo fazer meus testes.
		
		resourceClass.setController(controllerMock);
		
		final Long exampleIdTest = 22464l; // valor qualquer, meu velho codigo matricula na feevale (0022464).
		Example exampleTest = new Example();
		exampleTest.setAge(28);
		exampleTest.setName("Isaias");
		exampleTest.setDescription("Testar é preciso!!! Junit é necessário!");
		exampleTest.setCity("Montenegro - A terra da bergamota");

		/* Testo aqui CREATE */
		resourceClass.create(exampleTest.getName(), exampleTest.getAge(), exampleTest.getCity(), exampleTest.getDescription());
		Mockito.verify(controllerMock).save(exampleTest);
		
		/* Testo aqui READ */
		resourceClass.read(exampleIdTest);
		Mockito.verify(controllerMock).findById(exampleIdTest);
		
		/*  Testo aqui UPDATE (precisarei do id) */
		exampleTest.setId(exampleIdTest);
		resourceClass.update(exampleTest.getId(),exampleTest.getName(), exampleTest.getAge(), exampleTest.getCity(), exampleTest.getDescription());
		Mockito.verify(controllerMock).save(exampleTest);
		
	}
	
}
