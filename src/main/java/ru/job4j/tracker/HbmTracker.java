package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.save(item);
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Session session = sf.openSession();
        boolean rsl = session.createQuery("update Item i set i.name = :fName,"
                        + "i.description = :fDesc,"
                        + " i.created = :fCreated where i.id = :fId")
                .setParameter("fName", item.getName())
                .setParameter("fDesc", item.getDescription())
                .setParameter("fCreated", item.getCreated())
                .setParameter("fId", id)
                .executeUpdate() > 0;
        session.close();
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        Session session = sf.openSession();
        boolean rsl = session.createQuery("delete from Item i where i.id = id").executeUpdate() > 0;
        session.close();
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Session session = sf.openSession();
        List rslList = session.createQuery("from Item").getResultList();
        session.close();
        return rslList;
    }

    @Override
    public List<Item> findByName(String name) {
        Session session = sf.openSession();
        List rslList = session.createQuery("from Item i where i.name = name").getResultList();
        session.close();
        return rslList;
    }

    @Override
    public Item findById(int id) {
        Session session = sf.openSession();
        Item item = (Item) session.createQuery("from Item i where i.id = id").uniqueResult();
        session.close();
        return item;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}