
package appagenda;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sheyla
 */
public class Main extends Application {

    private EntityManagerFactory emf;
    private EntityManager em;

    @Override
    public void start(Stage primaryStage) throws IOException {
        StackPane rootMain = new StackPane(); 
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AgendaView.fxml")); 
        Pane rootAgendaView = fxmlLoader.load(); 
        rootMain.getChildren().add(rootAgendaView); 
        //Carga del EntityManager, etc… 
        Scene scene = new Scene(rootMain,600,400);
        
        
        AgendaViewController agendaViewController = (AgendaViewController)fxmlLoader.getController();
       

        emf = Persistence.createEntityManagerFactory("AgendaAppPU");
        em = emf.createEntityManager();
        agendaViewController.setEntityManager(em);
        agendaViewController.cargarTodasPersonas();

        primaryStage.setTitle("App Agenda");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        em.close();
        emf.close();
        try {
            DriverManager.getConnection("jdbc:derby:AgendaDB;shutdown=true");
        } catch (SQLException ex) {
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
