package br.com.gruporbs.controller;

import java.util.List;
import javax.ejb.Local;

import br.com.gruporbs.controller.exception.ExampleException;
import br.com.gruporbs.model.Example;

/**
 * Interface que especifica o funcionamento de um ExampleController
 * um Example Controller completo dever� implementar todas as caracter�sticas
 * desta interface para ser considerado v�lido. A anota��o @Local define
 * essa interface como Interface local de um EJB
 * @author isaias_alves isaias <isaias.alves@gruporbs.com.br>
 * @version 1.0
 */
@Local
public interface ExampleController {

	/**
	 * Efetua o salvamento de um novo exemplo
	 * @param example Objeto exemplo a ser salvo no banco de dados
	 * @return Objeto exemplo que foi salvo
	 * @throws ExampleException Em caso de problemas no salvamento ser� lan�ada uma ExampleException
	 * com maiores informa��es sobre o erro
	 */
	public Example save(Example example) throws ExampleException;
	
	/**
	 * Efetua a busca de um objeto Exemplo pelo seu identificador
	 * @param id identificador de um objeto exemplo
	 * @return Objeto exemplo que foi encontrado, caso n�o encontre ser� retornado nulo
	 * @throws ExampleException Caso ocorra algum erro inesperado (exceto a n�o localiza��o) ser� gerada
	 * uma Example Exception com maiores detalhes
	 */
	public Example findById( final Long id) throws ExampleException;
	
	/**
	 * Efetua a remo��o de um objeto do tipo Example
	 * @param example Objeto que dever� ser deletado, � importante frisar que o identificador 
	 * interno deste objeto precisa ser setado para que o m�todo possa ser executado de forma
	 * correta.
	 * @throws ExampleException Em caso de problemas ser� lan�ada uma Example Exception com informa��es
	 * sobre a remo��o e/ou problemas decorrentes
	 */
	public void delete(Example example) throws ExampleException;
	
	/**
	 * Efetua uma listagem de exemplos
	 * @param criteria Objeto do tipo criteria que ser� utilizado para filtrar o conte�do listado
	 * @return Lista de objetos encontrados
	 * @throws Erro gerado na listagem
	 */
	public List<Example> listExamples(Example criteria) throws ExampleException;
}
