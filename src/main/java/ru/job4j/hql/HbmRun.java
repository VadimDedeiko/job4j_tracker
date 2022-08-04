package ru.job4j.hql;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Student one = Student.of("Alex", 21, "Moscow");
            Student two = Student.of("Nikolay", 28, "Saint-Petersburg");
            Student three = Student.of("Nikita", 25, "Kaliningrad");

            session.save(one);
            session.save(two);
            session.save(three);

            Query selection = session.createQuery("from Student s where s.id = 1");
            System.out.println(selection.uniqueResult());
            Query selection1 = session.createQuery("from Student s where s.id = :fId");
            selection1.setParameter("fId", 1);
            System.out.println(selection1.uniqueResult());

            session.createQuery("update Student s set s.age = :newAge, "
                    + "s.city = :newCity where s.id = :fId")
                    .setParameter("newAge", 22)
                    .setParameter("newCity", "London")
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.createQuery("delete Student s where s.id = :fId")
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.createQuery("insert into Student(name, age, city) select concat(s.name, 'NEW'), s.age + 5, s.city"
                    + " from Student s where s.id = :fId")
                    .setParameter("fId", 1)
                    .executeUpdate();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}