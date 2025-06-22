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

        try (SessionFactory sf = cfg.buildSessionFactory();
             Session session = sf.getCurrentSession()) {
                    session.beginTransaction();

                    System.out.println("Загружаю класс: " + Person.class);
                    Person person = session.get(Person.class, 1L);
                    System.out.println(BackgroundColors.BG_GREEN + ConsoleColors.BLACK + person+ ANSI_RESET);
                    session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(ANSI_GREEN + "Произошла какая то ошибка" + ANSI_RESET);
        }
    }
}
