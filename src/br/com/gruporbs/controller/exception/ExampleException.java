package br.com.feevale.controller.exception;

/**
 * Exception que trata a geração de erros possíveis em um módulo
 * do tipo Example, quando o controller identificar os possíveis
 * erros encontrados os atributos internos deverão ser setados
 * para que exista a correta interpretação e consequente tratamento
 * dos erros ocasionados no módulo.
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 */
public class ExampleException extends Exception {

	private static final long serialVersionUID = -7326069562778711461L;
	private String message;
	
	/**
	 * Enumera as mensagens possíveis em um erro gerado
	 * por um objeto do tipo Example
	  * @author isaias_alves <isaias@wswork.com.br> 0022464
	 * @version 1.0
	 */
	public enum ExampleErrorType {
		
		CREATE_ERROR("Erro ao criar um objeto exemplo."),
		READ_ERROR("Erro ao ler um objeto exemplo."),
		UPDATE_ERROR("Erro ao atualizar um objeto exemplo."),
		DELETE_ERROR("Erro ao deletar um objeto exemplo."),
		INVALID_CRITERIA("Atributo de busca inválido."),
		VALIDATION_ERROR("Erro de validação, o objeto recebido é invalido para a operação.");
		
		private String message;
		
		ExampleErrorType(String message) {
			
			this.message = message;
			
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ExampleException(ExampleErrorType errorType) {
		
		this.message = errorType.getMessage();
		
	}
}
