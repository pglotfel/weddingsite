package weddingsite.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DatabaseInitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent e) {
		DatabaseProvider.setInstance(new FakeDatabase()); // TODO: eventually use real database
		System.out.println("Database initialized!");
	}

	@Override
	public void contextDestroyed(ServletContextEvent e) {
		
		
	}

}
