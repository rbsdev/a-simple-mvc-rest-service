package br.com.gruporbs.controller;

import java.util.List;
import javax.ejb.Local;

import br.com.gruporbs.controller.exception.ExampleException;
import br.com.gruporbs.model.Example;

/**
 * Interface que especifica o funcionamento de um ExampleController
 * um Example Controller completo deverá implementar todas as características
 * desta interface para ser considerado válido. A anotação @Local define
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
	 * @throws ExampleException Em caso de problemas no salvamento será lançada uma ExampleException
	 * com maiores informações sobre o erro
	 */
	public Example save(Example example) throws ExampleException;
	
	/**
	 * Efetua a busca de um objeto Exemplo pelo seu identificador
	 * @param id identificador de um objeto exemplo
	 * @return Objeto exemplo que foi encontrado, caso não encontre será retornado nulo
	 * @throws ExampleException Caso ocorra algum erro inesperado (exceto a não localização) será gerada
	 * uma Example Exception com maiores detalhes
	 */
	public Example findById( final Long id) throws ExampleException;
	
	/**
	 * Efetua a remoção de um objeto do tipo Example
	 * @param example Objeto que deverá ser deletado, é importante frisar que o identificador 
	 * interno deste objeto precisa ser setado para que o método possa ser executado de forma
	 * correta.
	 * @throws ExampleException Em caso de problemas será lançada uma Example Exception com informações
	 * sobre a remoção e/ou problemas decorrentes
	 */
	public void delete(Example example) throws ExampleException;
	
	/**
	 * Efetua uma listagem de exemplos
	 * @param criteria Objeto do tipo criteria que será utilizado para filtrar o conteúdo listado
	 * @return Lista de objetos encontrados
	 * @throws Erro gerado na listagem
	 */
	public List<Example> listExamples(Example criteria) throws ExampleException;
}
