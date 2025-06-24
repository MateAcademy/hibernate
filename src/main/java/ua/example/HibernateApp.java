package ua.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.example.colors.BackgroundColors;
import ua.example.colors.BrightConsoleColors;
import ua.example.colors.ConsoleColors;
import ua.example.model.Person;

/**
 * Hello world!
 */
public class HibernateApp {

    // ANSI-коды для цвета
    private static String ANSI_RESET = "\u001B[0m";   // Сброс цвета (возвращает к стандартному)
    private static String ANSI_GREEN = "\u001B[32m"; // Зеленый цвет


    public static void main(String[] args) {
        Configuration cfg = new Configuration().addAnnotatedClass(Person.class);

        Person newPerson = new Person("1", 50);
        Person person2 = new Person("Человек2", 10);


        try (SessionFactory sf = cfg.buildSessionFactory();
                Session session = sf.getCurrentSession()) {
                session.beginTransaction();

                System.out.println("Загружаю класс: " + Person.class);
//                session.persist(person1);
//                session.persist(person2);

                session.persist(newPerson);

                session.getTransaction().commit();
            System.out.println(BackgroundColors.BG_GREEN + ConsoleColors.BLACK + newPerson.getId() + ANSI_RESET);
        }
    }
}

// эта строка завершает транзакцию и сохраняет все изменения в базу данных.


//        try (SessionFactory sf = cfg.buildSessionFactory();
//             Session session = sf.getCurrentSession()) {
//                    session.beginTransaction();
//
//                    System.out.println("Загружаю класс: " + Person.class);
//                    Person person = session.get(Person.class, 1L);
//                    System.out.println(BackgroundColors.BG_GREEN + ConsoleColors.BLACK + person+ ANSI_RESET);
//            //session.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println(ANSI_GREEN + "Произошла какая-то ошибка: " + e.getMessage() + ANSI_RESET);
//
//            // Rollback если транзакция всё ещё активна
//            try {
//                Session currentSession = HibernateApp.getCurrentSession(); // см. ниже
//                if (currentSession.getTransaction().isActive()) {
//                    currentSession.getTransaction().rollback();
//                }
//            } catch (Exception rollbackEx) {
//                System.out.println(ANSI_RED + "Ошибка при rollback: " + rollbackEx.getMessage() + ANSI_RESET);
//            }
//        }
