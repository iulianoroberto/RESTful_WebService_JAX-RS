package it.unisannio.eshop;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Questa classe è stata prevista per gestire la serializzazione. Classe mapper, che incapsula le collezioni.
 * Questa annotazione serve a specificare che quella classe deve essere serializzata.
 */

@XmlRootElement
public class Items {
	
	private List<String> collection;
	
	public Items() {}

	public Items(ArrayList<String> arrayList) {
		collection = arrayList;
	}

	public List<String> getCollection() {
		return collection;
	}

	public void setCollection(List<String> collection) {
		this.collection = collection;
	}

	public String toString() {
		return collection.toString();
	}
	
}
