/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appagenda;

import entidades.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AgendaApp {

    public static void main(String[] args) {

        Map<String, String> emfProperties = new HashMap<String, String>();
        //emfProperties.put("javax.persistence.jdbc.user", "root");
        //emfProperties.put("javax.persistence.jdbc.password", "usuario");
        
        emfProperties.put("javax.persistence.schema-generation.database.action", "create");
        emfProperties.put("javax.persistence.jdbc.url", "jdbc:derby:AgendaDB;create=true");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaAppPU"); // Comprobar nombre de la PU
        EntityManager em = emf.createEntityManager();

        Provincia provinciaCadiz = new Provincia(0, "Cádiz");

        Provincia provinciaSevilla = new Provincia();
        provinciaSevilla.setNombre("Sevilla");

        em.getTransaction().begin();
        em.persist(provinciaCadiz);
        em.persist(provinciaSevilla);
        em.getTransaction().commit();

        em.close();
        emf.close();
        try {
            DriverManager.getConnection("jdbc:derby:AgendaDB;shutdown=true");
        } catch (SQLException ex) {
        }
    }

}
