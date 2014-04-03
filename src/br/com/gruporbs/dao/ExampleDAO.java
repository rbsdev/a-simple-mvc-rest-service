package br.com.gruporbs.dao;

import br.com.gruporbs.model.Example;

/**
 * Essa � uma classe DAO comum, n�o possui nenhuma implementa��o em banco de
 * dados Relacional ou de qualquer outra esp�cie, ser� utilizada apenas para
 * gerar dados est�ticos aos exemplos do trabalho. N�o vou cri�-lo como EJB
 * Stateless pois ter� dados fixos e estaticos.
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
	 * M�todo popula um evento com valores estaticos e fixos esses valores ser�o
	 * usados no nosso c�digo de exemplo
	 * 
	 * @return Retorna um objeto do tipo exemplo populado com "lero-lero"
	 *         values.
	 */
	protected Example createStaticExampleWithValue() {
		example = new Example();
		example.setId(22464l);
		example.setName("Exemplo de c�digo para a feevale");
		example.setAge(1500);
		example.setCity("Montenegro-RS");

		StringBuilder exampleDescription = new StringBuilder();

		exampleDescription
				.append("Esse exemplo de c�digo representa a utiliza��o da linguagem de programa��o Java,");
		exampleDescription
				.append(" al�m de APIs e frameworks padr�o de mercado. � importante tamb�m que haja");
		exampleDescription
				.append("o correto gerenciamento de depend�ncias, para isso utilizamos");
		exampleDescription
				.append("builds elaborados via Maven, uma maneira eficiente e o pr�ximo passo");
		exampleDescription
				.append(" para que seja poss�vel integra��o cont�nua e builds automatizados");
		exampleDescription
				.append(" porque sua empresa n�o pode perder tempo correndo atr�s do rabo");
		exampleDescription
				.append(" testes automatizados garantem que seu projeto n�o gerar� erros");
		exampleDescription
				.append(" as tecnologias est�o a� para serem usadas! A qualidade do seu trabalho ");
		exampleDescription
				.append(" garante a tranquilidade de seu sono! A produtividade pode ser alcan�ada com ");
		exampleDescription
				.append(" com uma 50% de dedica��o e 50% de qualidade, assim n�o abra�amos o mundo para produzir e gerar $$$, fazemos isso mantendo a vida social.");

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

		/* O �nico exemplo � o 22464, coincidentemente meu numero de matricula */
		if (id.equals(22464l)) {
			return createStaticExampleWithValue();
		} else { // N�o achou outro exemplo.. Afinal de contas n�o vou
					// implementar mais do que isso
			return null;
		}

	}

	/**
	 * Efetua a remo��o do exemplo, caso exista
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
	 *            objeto exemplo contendo dados � atualizar
	 * @return Objeto exemplo que foi atualizado..
	 */
	public Example update(final Example exampleToUpdate) {

		Example example = findExampleById(exampleToUpdate.getId());

		example.setDescription(exampleToUpdate.getDescription());
		example.setCity(exampleToUpdate.getCity());
		example.setAge(exampleToUpdate.getAge());
		example.setName(exampleToUpdate.getName());

		// manteria aqui outros atributos de relacionamento (talvez lazy ou
		// eagers em JPA, ou ent�o faria buscas
		// alternativas em um �ndice.. etc etc etc..

		this.example = example;

		syncronizeExample();

		return example;
	}

	/**
	 * Sincroniza com objeto estatico o exemplo populado nas execu��es de
	 * atualiza��o (insert / update / delete).
	 */
	protected void syncronizeExample() {
		staticExampleDAO = new ExampleDAO();
		staticExampleDAO.example = this.example;
	}

	/** Meu exemplo a ser mantido */
	private static ExampleDAO staticExampleDAO;

	/**
	 * Retorna inst�ncia est�tica de um objeto ExampleDAO,
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
