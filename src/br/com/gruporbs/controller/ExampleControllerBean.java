package br.com.gruporbs.controller;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import br.com.gruporbs.controller.exception.ExampleException;
import br.com.gruporbs.controller.exception.ExampleException.ExampleErrorType;
import br.com.gruporbs.dao.ExampleDAO;
import br.com.gruporbs.model.Example;

/**
 * Implementação para controller do módulo Example, representa em um
 * contexto EJB um SessionBean Stateless, provendo funcionalidades
 * para o funcionamento escalável e eficiente de um controller
 * @author isaias_alves isaias <isaias.alves@gruporbs.com.br>
 * @version 1.0
 */
@Stateless
public class ExampleControllerBean implements ExampleController {

	/**
	 * Instancia objeto ExampleDAO através de implementação (estatica)
	 * desenvolvida para ele (esse objeto poderá representar um banco de dados
	 * relacionao, nosql, filesystem, memória, ou seja, qualquer tipo disponível
	 * para armazenamento dos dados).
	 */
	protected ExampleDAO exampleDAO = ExampleDAO.getInstance();
	
	/**
	 * (veja documentação na interface)
	 * @see ExampleController#save(Example)
	 */
	@Override
	public Example save(Example example) throws ExampleException {
		
		validateExample(example);
		
		final Example foundExample = exampleDAO.findExampleById(example.getId());
		
		if ( foundExample == null ) {
			
			exampleDAO.insert(example);
			
		} else {
			
			exampleDAO.update(example);
		
		}
		
		return example;
	}

	/**
	 * (veja documentação na interface)
	 * @see ExampleController#findById(Long)
	 */
	@Override
	public Example findById(final Long id) throws ExampleException {
		
		final Example exampleFromDAO = exampleDAO.findExampleById(id);
		
		if (exampleFromDAO != null) {
			
			if (exampleFromDAO.getAge()==null) {
				throw new ExampleException(ExampleErrorType.READ_ERROR);
			}
			
		}
		
		return exampleFromDAO;
		
	}

	/**
	  * (veja documentação na interface)
	 * @see ExampleController#delete(Long)
	 */
	@Override
	public void delete(final Example example) throws ExampleException {
		
		exampleDAO.deleteExample(example);
		
		final Example deletedItem = this.findById(example.getId());
		
		if (deletedItem!=null) {
			throw new ExampleException(ExampleErrorType.DELETE_ERROR);
		}
		
	}

	/**
	  * (veja documentação na interface)
	 * @see ExampleController#listExamples(Example)
	 */
	@Override
	public List<Example> listExamples(final Example criteria) throws ExampleException {
		
		if (criteria == null) {
			throw new ExampleException( ExampleErrorType.INVALID_CRITERIA );
		}
		
		List<Example> examples = new LinkedList<Example>();
		
		examples.add(exampleDAO.findExampleById(22464l));
		examples.add(exampleDAO.findExampleById(1l));
		examples.add(exampleDAO.findExampleById(2l));
		examples.add(exampleDAO.findExampleById(3l));
		
		return examples;
		
	}
	
	/**
	 * Verifica se o exemplo recebido é válido, caso não seja lança 
	 * exception coerente com os problemas encontrados.
	 * @param example exemplo a ser validado
	 * @throws Erro encontrado durante validação
	 */
	protected void validateExample(final Example example) throws ExampleException {
		
		boolean isValid = true;
		
		if (!( example.getAge()!= null && (example.getAge() > -1))) { // Idade precisa ter um valor válido
			isValid=false;
		}
		
		if (example.getCity()==null) { // Cidade é obrigatoria
			isValid = false;
		}
		
		if (!(example.getDescription()!=null && example.getDescription().trim().length() > 3)) { // descrição precisa ser maior
			isValid = false;
		}
		
		if ( example.getName() ==null ) { // Nome é obrigatorio
			isValid = false;
		}
		
		if ( !isValid ) {
			throw new ExampleException(ExampleErrorType.VALIDATION_ERROR);
		}

	}
	
}
