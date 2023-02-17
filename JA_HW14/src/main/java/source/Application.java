package source;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Application {

	public static void main(String[] args) {

		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

		Session session = factory.openSession();

		Transaction transaction = session.beginTransaction();

		Cart cart1 = new Cart(11, "Audi");
		Cart cart2 = new Cart(9, "BMW");
		Cart cart3 = new Cart(6, "Mercedes");
		Cart cart4 = new Cart(22, "Volkswagen");
		Cart cart5 = new Cart(1, "Honda");
		Item item1 = new Item(2);
		Item item2 = new Item(1);
		Item item3 = new Item(3);
		Item item4 = new Item(2);
		cart1.setItems(new HashSet<>(Arrays.asList(item1, item2, item3)));
		cart2.setItems(new HashSet<>(Arrays.asList(item2, item4)));
		cart4.setItems(new HashSet<>(Arrays.asList(item3)));
		session.persist(cart1);
		session.persist(cart2);
		session.persist(cart3);
		session.persist(cart4);
		session.persist(cart5);

		transaction.commit();
		session.close();
	}

}
