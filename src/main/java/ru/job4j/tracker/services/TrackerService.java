package ru.job4j.tracker.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.persistence.HbmTracker;

import java.util.List;

@AllArgsConstructor
@Service
public class TrackerService {
    private final HbmTracker hbmTracker;

    public Item add(Item item) {
        return hbmTracker.add(item);
    }

    public boolean replace(int id, Item item) {
        return hbmTracker.replace(id, item);
    }

    public boolean delete(int id) {
        return hbmTracker.delete(id);
    }

    public List<Item> findAll() {
        return hbmTracker.findAll();
    }

    public List<Item> findByName(String name) {
        return hbmTracker.findByName(name);
    }

    public Item findById(int id) {
        return hbmTracker.findById(id);
    }

}
