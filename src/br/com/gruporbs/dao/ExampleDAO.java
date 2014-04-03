package br.com.gruporbs.dao;

import br.com.gruporbs.model.Example;

/**
 * Essa é uma classe DAO comum, não possui nenhuma implementação em banco de
 * dados Relacional ou de qualquer outra espécie, será utilizada apenas para
 * gerar dados estáticos aos exemplos do trabalho. Não vou criá-lo como EJB
 * Stateless pois terá dados fixos e estaticos.
 * 
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 */
public class ExampleDAO {

	/**
	 * Meu Objeto exemplo default
	 */
	protected Example example = new Example();

	public ExampleDAO() {
	
	}

	/**
	 * Método popula um evento com valores estaticos e fixos esses valores serão
	 * usados no nosso código de exemplo
	 * 
	 * @return Retorna um objeto do tipo exemplo populado com "lero-lero"
	 *         values.
	 */
	protected Example createStaticExampleWithValue() {
		example = new Example();
		example.setId(22464l);
		example.setName("Exemplo de código para a feevale");
		example.setAge(1500);
		example.setCity("Montenegro-RS");

		StringBuilder exampleDescription = new StringBuilder();

		exampleDescription
				.append("Esse exemplo de código representa a utilização da linguagem de programação Java,");
		exampleDescription
				.append(" além de APIs e frameworks padrão de mercado. É importante também que haja");
		exampleDescription
				.append("o correto gerenciamento de dependências, para isso utilizamos");
		exampleDescription
				.append("builds elaborados via Maven, uma maneira eficiente e o próximo passo");
		exampleDescription
				.append(" para que seja possível integração contínua e builds automatizados");
		exampleDescription
				.append(" porque sua empresa não pode perder tempo correndo atrás do rabo");
		exampleDescription
				.append(" testes automatizados garantem que seu projeto não gerará erros");
		exampleDescription
				.append(" as tecnologias estão aí para serem usadas! A qualidade do seu trabalho ");
		exampleDescription
				.append(" garante a tranquilidade de seu sono! A produtividade pode ser alcançada com ");
		exampleDescription
				.append(" com uma 50% de dedicação e 50% de qualidade, assim não abraçamos o mundo para produzir e gerar $$$, fazemos isso mantendo a vida social.");

		example.setDescription(exampleDescription.toString());

		return example;
	}

	/**
	 * Efetua a busca de um objeto do tipo exemplo
	 * 
	 * @param id
	 *            identificador do exemplo
	 * @return Exemplo encontrado
	 */
	public Example findExampleById(final Long id) {

		/* O único exemplo é o 22464, coincidentemente meu numero de matricula */
		if (id.equals(22464l)) {
			return createStaticExampleWithValue();
		} else { // Não achou outro exemplo.. Afinal de contas não vou
					// implementar mais do que isso
			return null;
		}

	}

	/**
	 * Efetua a remoção do exemplo, caso exista
	 * 
	 * @param example
	 *            exemplo a remover
	 */
	public void deleteExample(Example example) {

		if (findExampleById(example.getId()) != null) {
			this.example = null;
		}

		syncronizeExample();
	}

	/**
	 * Insere um novo exemplo (na memoria local), objeto example estatico na JVM
	 * que executaria esse projeto
	 * 
	 * @param example
	 * @return
	 */
	public Example insert(Example example) {

		this.example = example;

		syncronizeExample();

		return this.example;
	}

	/**
	 * Atualiza o exemplo do contexto local com o que foi recebido por
	 * parametro...
	 * 
	 * @param exampleToUpdate
	 *            objeto exemplo contendo dados à atualizar
	 * @return Objeto exemplo que foi atualizado..
	 */
	public Example update(final Example exampleToUpdate) {

		Example example = findExampleById(exampleToUpdate.getId());

		example.setDescription(exampleToUpdate.getDescription());
		example.setCity(exampleToUpdate.getCity());
		example.setAge(exampleToUpdate.getAge());
		example.setName(exampleToUpdate.getName());

		// manteria aqui outros atributos de relacionamento (talvez lazy ou
		// eagers em JPA, ou então faria buscas
		// alternativas em um índice.. etc etc etc..

		this.example = example;

		syncronizeExample();

		return example;
	}

	/**
	 * Sincroniza com objeto estatico o exemplo populado nas execuções de
	 * atualização (insert / update / delete).
	 */
	protected void syncronizeExample() {
		staticExampleDAO = new ExampleDAO();
		staticExampleDAO.example = this.example;
	}

	/** Meu exemplo a ser mantido */
	private static ExampleDAO staticExampleDAO;

	/**
	 * Retorna instância estática de um objeto ExampleDAO,
	 * 
	 * @return Objeto ExampleDAO estatico em meu contexto (seja ele qual for
	 *         definido na pratica)
	 */
	public static ExampleDAO getInstance() {

		if (staticExampleDAO == null) {
			staticExampleDAO = new ExampleDAO();
		}
		return staticExampleDAO;
	}
}
