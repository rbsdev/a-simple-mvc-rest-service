package br.com.feevale.controller.exception;

/**
 * Exception que trata a gera��o de erros poss�veis em um m�dulo
 * do tipo Example, quando o controller identificar os poss�veis
 * erros encontrados os atributos internos dever�o ser setados
 * para que exista a correta interpreta��o e consequente tratamento
 * dos erros ocasionados no m�dulo.
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 */
public class ExampleException extends Exception {

	private static final long serialVersionUID = -7326069562778711461L;
	private String message;
	
	/**
	 * Enumera as mensagens poss�veis em um erro gerado
	 * por um objeto do tipo Example
	  * @author isaias_alves <isaias@wswork.com.br> 0022464
	 * @version 1.0
	 */
	public enum ExampleErrorType {
		
		CREATE_ERROR("Erro ao criar um objeto exemplo."),
		READ_ERROR("Erro ao ler um objeto exemplo."),
		UPDATE_ERROR("Erro ao atualizar um objeto exemplo."),
		DELETE_ERROR("Erro ao deletar um objeto exemplo."),
		INVALID_CRITERIA("Atributo de busca inv�lido."),
		VALIDATION_ERROR("Erro de valida��o, o objeto recebido � invalido para a opera��o.");
		
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
