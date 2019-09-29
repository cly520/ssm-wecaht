package cy.ly.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AccessTokenListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        new AccessTokenThread().start();
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
