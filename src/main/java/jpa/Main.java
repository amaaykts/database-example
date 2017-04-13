package jpa;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("UserPersist");   //Название persistence-unit из файла persistence
        EntityManager entityManager = factory.createEntityManager();

        Query query = entityManager.createQuery("select u from User u");    //Запрос на получение пользователей из таблицы User (jpql?)
        List<User> users = query.getResultList();   //получить список пользователей
        for (User user : users) {
            System.out.println(user);
        }

        User user = new User();
        user.setUserId(1);
        user.setFirstName("Pavel");
        user.setLastName("Pavlov");
        user.setCreatedOn(new Date(1997, 03, 19));

        entityManager.getTransaction().begin(); //Начинаем транзакцию
        entityManager.persist(user);    //сохраняем
        entityManager.getTransaction().commit();    //Коммитим наши действия
        entityManager.close();
    }
}
