package weddingsite.persist;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		DatabaseProvider.setInstance(new DerbyDatabase()); // TODO: eventually use real database
		System.out.println("Database initialized!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		
		
	}

}
