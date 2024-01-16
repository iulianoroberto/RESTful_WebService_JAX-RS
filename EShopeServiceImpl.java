package it.unisannio.eshop;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Response;

public class EShopeServiceImpl implements EShopService {
	/*
	 * Prevediamo di utilizzare due collezioni, una per mantenere gli item e l'altra per manetnere gli ordini che fanno
	 * riferimento a quegli item.
	 * itemCollection è una collezione che associa a delle stringhe (codice degli item) i relativi item.
	 */
	private HashMap<String, Item> itemCollection = new HashMap<String, Item>();
	private HashMap<String, List<Order>> orderCollection = new HashMap<String, List<Order>>();

	public EShopeServiceImpl() {
		itemCollection.put("0001", new Item("0001", "IPhone 13 Pro Plus", 12, 1240.00));
		itemCollection.put("0002", new Item("0002", "Samsung S21", 9, 1000.00));
		itemCollection.put("0003", new Item("0003", "Huawei P40", 13, 720.00));
	}

	/*
	 * Recupero gli elementi dalla collezione. Si estraggono le chiavi, vengono incapsulate in un ArrayList e il tutto
	 * viene wrappato in un oggetto di tipo Items. 
	 * Si restituiscono i codici dei prodotti gestiti dal servizio.
	 */
	public Items getItemList() {
		return new Items(new ArrayList<String>(itemCollection.keySet()));
	}

	/*
	 * Creazione di un prodotto. 
	 * Il parametro item da dove vien estratto? Dal body della richiesta di tipo POST (contiene la risorsa).
	 */
	public Response createItem(Item item) {
		itemCollection.put(item.getCode(), item);
		URI uri = null;
		try {
			uri = new URI("/items/"+item.getCode());
		} catch(URISyntaxException e) { }
		/*
		 * Per dare la risposta dell'avvenuta creazione (codice 201 - avvenuta creazione) e inserire nella risposta l'URI 
		 * della risorsa creata viene creato un oggetto di tipo Response. 
		 */
		return Response.created(uri).build();
	}

	public Item getItemDetails(String code) {
		return itemCollection.get(code);
	}
	
	public void deleteItem(String code) {
		itemCollection.remove(code);
	}

	/*
	 * Product ID (String)  |   ArrayList<Order>
	 */
	public Response orderItem(String code) {
		List<Order> list = orderCollection.get(code);
		if (list == null) {
			list = new ArrayList<Order>();
			orderCollection.put(code, list);
		}
		int oid = orderCollection.get(code).size();
		
		Order order = new Order(code, oid);
		list.add(oid, order);
		
		URI uri = null;
		try {
			uri = new URI("/items/"+code+"/orders/"+oid);
		} catch (URISyntaxException e) { }
		
		return Response.created(uri).build();
	}

	public Order getOrder(String code, int oid) {
		List<Order> list = orderCollection.get(code);
		if (list!=null) return list.get(oid);
		return null;
	}

}
