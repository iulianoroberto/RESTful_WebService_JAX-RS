package it.unisannio.eshop;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * PARTE IMPORTANTE
 * Annotazione della specifica JAX-bind, che consente di indicare il mapping che vogliamo realizzare
 * tra gli elementi che caratterizzano la classe di oggetti che vogliamo serializzare e ciò che
 * viene prodotto in serializzazione. Mapping tra elementi Java ed elementi rappresentazione esterna (elementi
 * Json o XML).
 * Queste annotazioni sono utilizzate sia dal serializzatore Java verso XML che da quello Java verso Json.
 * In questo caso serializziamo in Json, il serializzatore trasforma le istanze di item in documenti Json.
 */
@XmlRootElement
public class Item {
	private String code;
	private String description;
	private int quantity;
	private double price;

	public Item(String c, String descr, int qty, double p) {
		code = c;
		description = descr;
		quantity = qty;
		price = p;
	}
	
	/*
	 * Costruttore senza argomenti, necessario quando realizziamo classi come queste,
	 * note come classi di java bean. Sono calssi i cui oggetti rappresentano dati.
	 * I metodi che prevediamo in queste classi sono semplici, noti come metodi accessor/mutator.
	 */
	public Item() {}

	
	/*
	 * Metodi di accesso in lettura
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	/*
	 * Metodi di accesso in scrittura
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/*
	 * Metodo toString() ridefinito
	 */
	@Override
	public String toString() {
		return "Item [code=" + code + ", description=" + description + ", quantity=" + quantity + ", price=" + price
				+ "]";
	}

}
