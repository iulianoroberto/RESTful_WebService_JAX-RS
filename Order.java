package it.unisannio.eshop;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Classe di Java bean. Si accetta la serializzazione di default per tutti i campi, in quanto si usa solo la prima
 * annotazione.
 */
@XmlRootElement
public class Order {
	
	/*
	 * Variabili di istanza.
	 */
	private String item;
	private int id;
	
	/*
	 * Costruttore di default.
	 */
	public Order() {}
	
	/*
	 *  Secondo costruttore.
	 */
	public Order(String c, int i) {
		item=c; id=i; 
	}
	
	/*
	 * Metodi accessor e mutator.
	 */
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Metodo toString.
	 */
	public String toString() {
		return "Order [item=" + item + ", id=" + id + "]";
	} 
	
	
	

}