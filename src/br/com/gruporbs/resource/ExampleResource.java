package br.com.gruporbs.resource;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.gruporbs.controller.ExampleController;
import br.com.gruporbs.controller.exception.ExampleException;
import br.com.gruporbs.model.Example;

/**
 * Classe resource respons�vel pelo m�dulo exemplo, atrav�s desta classe podemos
 * expor como servi�o (utilizando sa�da em formato json) os endpoints de acesso
 * ao meu sistema. Para que possamos ter como sa�da algo representando REST de 
 * forma correta � necess�rio que as requisi��es sejam algo representativo, ao 
 * utilizar RESTful sobre Http iremos respeitar m�todos POST, GET, DELETE para
 * a��es b�sicas de um C. R. U. D. C�digo utilizado na disciplina de Gest�o de TI
 * M�dulo de Tecnologia aplicada aos Neg�cios da Feevale em Mar�o/2014.
 * Utilizamos como implementa��o Rest a api Jersey pressumindo-se inclusa em um
 * runtime de servidor glassfish (3.1.2 ou superior). 
 * Ser� utilizado JAXRS {@link https://jcp.org/en/jsr/detail?id=339} {@link https://jax-rs-spec.java.net/}
 * 
 * @author isaias_alves <isaias@wswork.com.br> 0022464
 * @version 1.0
 * 
 */
@Path("/example") 
@RequestScoped
public class ExampleResource {
	
	@EJB
	ExampleController controller;
	
	/**
	 * Efetua a cria��o de um novo objeto do tipo example
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descri��o do exemplo
	 * @return Resposta Http (sucesso ou erro) para quem consumir o servi�o
	 */
	@POST
    @Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
    public Response create(    @FormParam("name") final String name, 
    		                   @FormParam("age") final Integer age, 
    		                   @FormParam("city") final String city, 
    		                   @FormParam("description") final String description) {
		
		/**
		 * Garantir estado representativo (no caso protocolo http)
		 * � uma caracter�stica de um bom webservice REST (neste caso RESTful).
		 */
		try {
			
			final Example savedExample = handleNewExampleParams(null, name, age, city, description);
			controller.save(savedExample);
			return Response.ok(savedExample).build();
			
		} catch (ExampleException exampleCreateError) {
			exampleCreateError.printStackTrace();
			return getDefaultResponseErrorObject();
		}
	}
	
	/**
	 * Efetua a cria��o de um novo objeto do tipo example
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descri��o do exemplo
	 * @return Resposta Http (sucesso ou erro) para quem consumir o servi�o
	 */
	@GET
    @Path("/read")
	@Produces(MediaType.APPLICATION_JSON)
    public Response read( @QueryParam("id") final Long id) {
		
		try {
			
			return Response.ok(controller.findById(id)).build();

		} catch (ExampleException exampleReadError) {
			exampleReadError.printStackTrace();
			return getDefaultResponseErrorObject();
		}
	}
	
	
	/**
	 * Efetua a cria��o de um novo objeto do tipo example
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descri��o do exemplo
	 * @return Resposta Http (sucesso ou erro) para quem consumir o servi�o
	 */
	@POST
    @Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
    public Response update(    @FormParam("id") final Long id,
    						   @FormParam("name") final String name, 
    		                   @FormParam("age") final Integer age, 
    		                   @FormParam("city") final String city, 
    		                   @FormParam("description") final String description) {
		
		try {
			
			final Example updatedExample = handleNewExampleParams(id, name, age, city, description);
			controller.save(updatedExample);
			return Response.ok(updatedExample).build();
			
		} catch (ExampleException exampleCreateError) {
			exampleCreateError.printStackTrace();
			return getDefaultResponseErrorObject();
		}
	}
	
	/**
	 * Remove um registro de 
	 * @param id
	 * @param name
	 * @param age
	 * @param city
	 * @param description
	 * @return
	 */
	@DELETE
    @Path("/delete")
	@Produces(MediaType.APPLICATION_JSON)
    public Response delete(   @FormParam("id") final Long id) {
		try {
			
			final Example deletedExample = handleNewExampleParams(id, null, null, null, null);
			controller.delete(deletedExample);
			return Response.ok(deletedExample).build();
			
		} catch (ExampleException exampleCreateError) {
			exampleCreateError.printStackTrace();
			return getDefaultResponseErrorObject();
		}
	}
	
	/**
	 * Auxilia a setagem de valores em objeto exemplo, visa n�o ocorrer replica��o
	 * desnecess�ria de c�digo, tornando sua leitura mais simplificada
	 * @param id identificador do exemplo
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descri��o completa do que representa o exemplo
	 * @return
	 */
	protected Example handleNewExampleParams( final Long id, final String name, final Integer age, final String city, final String description) {
	
		Example example = new Example();
		
		example.setId(id);
		example.setName(name);
		example.setAge(age);
		example.setCity(city);
		example.setDescription(description);
		
		return example;
	}
	
	/**
	 * Constr�i um objeto response com as informa��es de erro necess�rias
	 * nesse caso podemos customizar a sa�da colocando alguns atributos
	 * de cabe�alho, dependendo da necessidade do client (que poder� ser js
	 * outro webservice client, etc etc etc.).
	 * @return Objeto Response representando a mensagem de erro a ser enviada
	 * � interface
	 */
	protected Response getDefaultResponseErrorObject() {	
		return Response.serverError()
				.status(Response.Status.INTERNAL_SERVER_ERROR).build();
	}

	public ExampleController getController() {
		return controller;
	}

	public void setController(ExampleController controller) {
		this.controller = controller;
	}

	
}
