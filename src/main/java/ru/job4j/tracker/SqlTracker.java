package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {
    private Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps =
                     cn.prepareStatement("insert into items(name,created) values(?,?);",
                             Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                item.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (PreparedStatement ps =
                     cn.prepareStatement("update items set name=?, created=? where id = ?;")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            ps.setInt(3, id);
            rsl = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (PreparedStatement ps =
                     cn.prepareStatement("DELETE FROM items WHERE id=?;")) {
            ps.setInt(1, id);
            rsl = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        Item item = null;
        List<Item> list = new ArrayList<>();
        try (PreparedStatement ps =
                     cn.prepareStatement("SELECT * FROM items;")) {
            ResultSet resultSet = ps.executeQuery();
            setItem(resultSet, item, list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        Item item = null;
        try (PreparedStatement ps =
                     cn.prepareStatement("SELECT * FROM items where name=?;")) {
            ps.setString(1, key);
            ResultSet resultSet = ps.executeQuery();
            item = setItem(resultSet, item, list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        List<Item> list = new ArrayList<>();
        try (PreparedStatement ps =
                     cn.prepareStatement("select * from items where id=?;")) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            item = setItem(resultSet, item, list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    private Item setItem(ResultSet resultSet, Item item, List<Item> list) throws SQLException {
        while (resultSet.next()) {
            item = new Item(resultSet.getInt("id"),
                    resultSet.getString("name"));
            var ldt = resultSet.getTimestamp(3).toLocalDateTime();
            item.setCreated(ldt);
            list.add(item);
        }
        return item;
    }
}