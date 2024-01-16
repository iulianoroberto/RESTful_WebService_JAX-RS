package it.unisannio.eshop; 

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Consumes("application/json")
@Produces("application/json")
@Path("/items") // Viene impiegata per realizzare il mapping tra gli URI presenti nei messaggi di richiesta HTTP e i metodi che si implemntano nell'interfaccia.
public interface EShopService {
	@GET // Il path è quello ereditato dall'interfaccia (/items)
	public Items getItemList();
	
	@POST
	public Response createItem(Item item);
	
	@GET
	@Path("/{code}")
	public Item getItemDetails(@PathParam("code") String code);
	
	@DELETE
	@Path("/{code}")
	public void deleteItem(@PathParam("code") String code);
	
	@POST
	@Path("/{code}/orders/")
	public Response orderItem(@PathParam("code") String code);
	
	/*
	 * Ho il metodo getOrder con due parametri: il codice dell'item di cui voglio l'ordine e l'id
	 * Utilizzo GET
	 * Il percorso prevede due parametri, che sono il codice dell'item e dell'ordine
	 * 
	 */
	@GET
	@Path("/{code}/orders/{oid}")
	public Order getOrder(@PathParam("code") String code, @PathParam("oid") int oid);
}
