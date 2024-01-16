package client;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import it.unisannio.eshop.Item;
import it.unisannio.eshop.Items;
import it.unisannio.eshop.Order;

/*
 * Si possono usare diversi tipi di client. In PSR abbiamo visto POSTMAN (client per testare un servizio Web).
 * Oppure, si pu� far ricorso alle classi che JAX-RS mette a disposizione. Il client non deve essere per forza Java,
 * si pu� accedere al servizio anche da codice Javascript all'interno di un browser.
 */

public class EShopClient {

	public static void main(String[] args) {
		try {
			/*
			 * Creiamo un oggetto Client attraverso cui invocare i meotdi.
			 */
			Client client = ClientBuilder.newClient();
			
			/*
			 * Specifico il target per quel client, ossia il percorso di base.
			 * L'indirizzo di base � localhost:8080 indirizzo sul quale � in esecuzione TomCat.
			 */
			WebTarget endpoint = client.target("http://localhost:8080/Exercise5.1/eshop");
			
			/*
			 * Specifico il percorso che mi consente di realizzare il mapping con uno 
			 * dei metodi previsti nell'interfaccia.
			 */
			// GET /items/0001
			WebTarget resource = endpoint.path("/items/0001");
			/*
			 * Con accept invocando questo metodo sto dicendo che all'interno del messaggio di richiesta ci
			 * sarà un campo accept. Nel campo accept ci sarà il mediatype "application/json" che indica
			 * che il client accetta come rappresentazione di ritorno da parte del server un mediatype (rappresentazione)
			 * di tipo application/json. Se c'è missmatch tra ciò che accetta il client e ciò che
			 * produce il server non è possibile realizzare la comunicazione.
			 * Invocando get usiamo un oggetto di tipo class per evitare di realizzare l'operazione
			 * di conversione dell'oggetto restituito. L'oggetto restituito sarà di tipo item
			 * e potrà essere assegnato alla variabile i. La risorsa di ritorno sarà un json contenente l'item e
			 * quel json verrà deserializzato in un oggetto di tipo Item di cui stampiamo il contenuto.
			 */
			Item i = resource.request().accept("application/json").get(Item.class);
			
			System.out.println("Item:" + i);
			System.out.println();
			
			
			/*
			 * Creazione di un nuovo item nell'eshop. 
			 * 
			 * 1 - Si definisce il percorso, quindi costruiamo la risorsa che punta al percorso "/items". 
			 * 
			 * 2 - Si crea in Java l'oggetto che rappresenta la risorsa che si vuole trasferire sul server. 
			 * 
			 * 3 - Si invocano i metodi che permettono di creare il messaggio di richiesta utile per la creazione della 
			 * risorsa remota. 
			 * 
			 * Dalla risorsa si crea una richiesta, prevedendo il formato "mime" ("application/json") della risorsa 
			 * accettata in risposta e quindi si invoca POST. 
			 * In questo caso usando post si deve prvedere un body nel messaggio di richiesta. Si tratta di un item che 
			 * si trasferisce usando la rappresentazione json. Per creare il body si usa il metodo entity della classe 
			 * Entity. Si usano i dati presenti nell'oggetto item che dovr� essere serializzato usando json.
			 */
			
			// POST /items
			// 1
			resource = endpoint.path("/items");
			// 2
			Item item = new Item("0004", "Xiaomi Mi 10", 30, 450.50);
			// 3
			Response res = resource.request().accept("application/json").post(Entity.entity(item, MediaType.APPLICATION_JSON));
			
			/*
			 * Il messaggio di risposta restituito contiene il campo location che si pu� recuperare attraverso il metodo
			 * getLocation(). Si ottiene con getLocation() l'id (URI) della risorsa appena creata.
			 */
			System.out.println("Created item" + item + "\n" + res.getLocation());
			System.out.println();
			
			/*
			 * Recuperiamo item con il codice 004, ovvero la risorsa appena creata. Se non prevediamo di utilizzate il
			 * singleton la risorsa creata non la troveremo perchè ad ogni richiesta viene reistanziato il servizio, quindi,
			 * lo stato modificato in precedenza sarà perso.
			 */
			
			// GET /items/0004
			resource = endpoint.path("/items/0004");
			// accept("application/json") � il mediatype atteso
			// get(Item.class) � l'operazione che si vuole eseguire
			item = resource.request().accept("application/json").get(Item.class);
			
			System.out.println("Item:" + item);
			System.out.println();
			
			/*
			 * Implementiamo l'operazione che ci consente di recuperare l'elenco tutti gli items
			 * disponibili nello shop. In particolar modo, l'elenco di tutti i codici gestiti dalo shop.
			 * Il tipo di ritorno sarà il wrapper della collezione contenente i codici degli item previsti nello shop.
			 */
			// GET /items
			resource = endpoint.path("/items");
			Items items = resource.request().accept("application/json").get(Items.class);
			
			System.out.println("whole items collection" + items);
			System.out.println();
			
			/*
			 * Con questa operazione vogliamo creare un ordine con riferimento all'item con codice 0004.
			 * L'operazione è simile a quella vista in precedenza.
			 * L'ordine è trasformato in json e il json è inserito nel body del messaggio di richiesta HTTP.
			 * Effettuato l'ordine recuperiamo l'uri con getLocatio().
			 */
			// POST /items/0004/orders
			resource = endpoint.path("/items/0004/orders");
			res = resource.request().accept("application/json").post(Entity.entity(new Order(), MediaType.APPLICATION_JSON));
			
			/*
			 * L'URI contiene anche le informazioni relative al percorso che estraiamo con getPath().
			 */
			String uriPath = res.getLocation().getPath();
			System.out.println("Order id: " + uriPath);
			System.out.println();
			
			// GET /items/0004/orders/{oid}
			/*
			 * Con questa operazione voglio recuperare l'ordine il cui id è 0.
			 */
			resource = endpoint.path("items/0004/orders/0");
			Order order = resource.request().accept("application/json").get(Order.class);
			
			System.out.println(order);
			System.out.println();
			
			// DELETE /items/0004
			/*
			 * Cancellazione item con id 0004.
			 * Recuperiamo la richiesta, la componiamo con accept() (potremmo evitarlo perchè non c'è una risorsa
			 * restituita) e quindi cancelliamo.
			 */
			resource = endpoint.path("items/0004");
			resource.request().accept("application/json").delete();
			
			System.out.println("item 0004 deleted");
			System.out.println();
			
			// GET /items/0004
			/*
			 * Verifichiamo se la risorsa è stata correttamente cancellata.
			 */
			resource = endpoint.path("items/0004");
			resource.request().accept("application/json").get(Item.class);
			
			if (item == null) System.out.println("item/0004 doesn't exist");
			System.out.println();
			
		} catch(NotFoundException e) {
			System.err.println("Resource not found" + e);
		}

	}

}
