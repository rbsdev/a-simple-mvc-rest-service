package br.com.feevale.model;

/**
 * Classe que representa o modelo do meu módulo "Example", poderá
 * ser utilizada como DTO ou VO, assim como uma Entity. Cabe lembrar
 * que em um contexto de Data Access Object essa entidade com suas anotações
 * executadas (por ex. JPA), deste modo será assim reconhecida pelo seu correto
 * escopo. Em escopo de interface terá utilização como transporte de dados/encapsulamento (DTO).
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 */
public class Example {

	private Long id;
	
	private String name;
	
	private Integer age;
	
	private String city;
	
	private String description;

	
	/**
	 * Obtém identificador de um objeto exemplo
	 * @return Identificador localizado, em caso de não persistido, retornará null
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Seta valor recebido em objeto interno id
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtém valor do atributo name de um objeto exemplo
	 * @return Valor encontrado.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Seta um novo valor ao atributo nome de um objeto exemplo
	 * @param name valor a ser setado em atributo interno
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtém valor da idade do meu exemplo, esse valor é numérico e 
	 * possui uma limitação grande para uma idade (por usar tipo Integeriv
	 * porém poderia por exemplo representar a idade de uma civilização,
	 * universo, planeta, o que elavaria à uma escala máxima (de suporte ao tipo Integer)
	 * o que talvez exigisse por exemplo refactor para uso de um atributo Long ou BigDecimal...
	 * @return Idade encontrada
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * Define um valor para o atributo idade
	 * @param age idade a ser setada em objeto interno
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Obtém o nome da cidade de objeto exemplo
	 * @return Nome da cidade
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Seta valor da cidade
	 * @param city Valor da cidade encontrado
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Obtém uma descrição completa da cidade
	 * @return String contendo descrição da cidade
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Seta valor de descrição à objeto interno
	 * @param description descrição de um objeto exemplo
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Example other = (Example) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Example [id=" + id + ", name=" + name + ", age=" + age
				+ ", city=" + city + ", description=" + description + "]";
	}
	
}
