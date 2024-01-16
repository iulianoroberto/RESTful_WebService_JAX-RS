package it.unisannio.eshop;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * La classe è annotata con questa annotazione di JAX-rs, che ci permette di estendere
 * il percorso di base (indirizzo di trasporto/nome del progetto/eshop/services/items).
 */
@ApplicationPath("/eshop")
public class EShopApplication extends Application{
	
	/*
	 * All'interno di questa classe dobbiamo prevedere uno dei due metodi. Il primo nel caso in cui l'implementazione
	 * del servizio sia creata ogni qualvolta viene effettuata una richiesta. Il secondo nel caso in cui vogliamo che
	 * l'implemntazione del serizio sia un singleton, cioè una volta che abbiamo istanziato quel servizio rimane quella
	 * istanza. 
	 * 
	 * Perch� usiamo il singleton?
	 * Utilizziamo il singleton perchè stiamo prevedendo la gestione dello stato. Per evitare che modifiche di
	 * stato apportate attraverso le operazioni di creazione (ad esempio) venissero perse nelle interazioni successive.
	 * Per far si che una creazione sia visibile nelle interazioni successive dobbiamo usare il singleton.
	 */ 
	
	/*
	 * Con questa soluzione non specifichiamo l'oggetto ma la classe, ogni volta che vi � una richiesta
	 * verrà creato l'oggetto. Quindi il servizio viene creato ogni volta che viene fatta una richiesta.
	 */
	/*
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(it.unisannio.eshop.EShopeServiceImpl.class);
		return s;
	}
	*/

	/*
	 * Questo metodo se si vuole che l'implementazione del servizio sia un singleton, una volta istanziato il servizio
	 * rimane quell'istanza.
	 * In questo metodo specifichiamo tutti i servizi che vogliamo rendere accessibili attraverso questa
	 * applicazione. Attraverso il server che ospiterà queste componenti.
	 * Creiamo un HashSet di oggetti e andiamo ad aggiungere l'ogetto che è istanza della classe EShopeServiceImpl.
	 * L'applicazione è composta da un servizio la cui implemnetazione è compresa nell'oggetto che 
	 * istanziamo (new it.unisannio.eshop.EShopeServiceImpl()).
	 * 
	 */
	public Set<Object> getSingletons() {
		Set<Object> s = new HashSet<Object>();
		s.add(new it.unisannio.eshop.EShopeServiceImpl());
		return s;
	}

}
