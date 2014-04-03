package br.com.gruporbs;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runners.Suite;

import br.com.gruporbs.controller.ExampleControllerBeanTest;
import br.com.gruporbs.dao.ExampleDAOTest;
import br.com.gruporbs.resource.ExampleResourceTest;

/**
 * Suite de testes que une os testes necess�rios para o m�dulo
 * exemplo, reune unitariamente todos os sub-componentes de um
 * m�dulo exemplo
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ExampleResourceTest.class, ExampleControllerBeanTest.class,ExampleDAOTest.class})
public class ExampleSuiteTest {
	
	/** 
	 * M�todo que poder� disparar a execu��o de todos os testes da Suite Example
	 * No momento da execu��o do Build do pacote, todos os testes precisam
	 * estar OK, caso contr�rio n�o ser� poss�vel empacotar esse projeto 
	 * via Maven
	 * @param args argumentos (n�o usados).
	 */
	public static void main(String[] args) {
	      Result result = JUnitCore.runClasses(ExampleSuiteTest.class);
	      for (Failure failure : result.getFailures()) {
	         System.out.println(failure.toString());
	      }
	      System.out.println(result.wasSuccessful());
	   }
}