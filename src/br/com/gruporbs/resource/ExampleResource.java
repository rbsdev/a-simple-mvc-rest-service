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
 * Classe resource responsável pelo módulo exemplo, através desta classe podemos
 * expor como serviço (utilizando saída em formato json) os endpoints de acesso
 * ao meu sistema. Para que possamos ter como saída algo representando REST de 
 * forma correta é necessário que as requisições sejam algo representativo, ao 
 * utilizar RESTful sobre Http iremos respeitar métodos POST, GET, DELETE para
 * ações básicas de um C. R. U. D. Código utilizado na disciplina de Gestão de TI
 * Módulo de Tecnologia aplicada aos Negócios da Feevale em Março/2014.
 * Utilizamos como implementação Rest a api Jersey pressumindo-se inclusa em um
 * runtime de servidor glassfish (3.1.2 ou superior). 
 * Será utilizado JAXRS {@link https://jcp.org/en/jsr/detail?id=339} {@link https://jax-rs-spec.java.net/}
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
	 * Efetua a criação de um novo objeto do tipo example
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descrição do exemplo
	 * @return Resposta Http (sucesso ou erro) para quem consumir o serviço
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
		 * é uma característica de um bom webservice REST (neste caso RESTful).
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
	 * Efetua a criação de um novo objeto do tipo example
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descrição do exemplo
	 * @return Resposta Http (sucesso ou erro) para quem consumir o serviço
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
	 * Efetua a criação de um novo objeto do tipo example
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descrição do exemplo
	 * @return Resposta Http (sucesso ou erro) para quem consumir o serviço
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
	 * Auxilia a setagem de valores em objeto exemplo, visa não ocorrer replicação
	 * desnecessária de código, tornando sua leitura mais simplificada
	 * @param id identificador do exemplo
	 * @param name nome do exemplo
	 * @param age idade do exemplo
	 * @param city cidade do exemplo
	 * @param description descrição completa do que representa o exemplo
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
	 * Constrói um objeto response com as informações de erro necessárias
	 * nesse caso podemos customizar a saída colocando alguns atributos
	 * de cabeçalho, dependendo da necessidade do client (que poderá ser js
	 * outro webservice client, etc etc etc.).
	 * @return Objeto Response representando a mensagem de erro a ser enviada
	 * à interface
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
