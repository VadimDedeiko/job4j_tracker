package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.persistence.HbmTracker;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HbmTrackerTest {
    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item("item");
            tracker.add(item);
            assertEquals(tracker.findById(item.getId()), item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSaveItemAndReplaceAndFindThenMustHaveNewName() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item("item");
            Item item2 = new Item("item2");
            tracker.add(item);
            tracker.replace(item.getId(), item2);
            assertEquals(tracker.findById(item.getId()).getName(), item2.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddItemsAndFindAllThenMustBeTheSame() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item("item");
            Item item2 = new Item("item2");
            tracker.add(item);
            tracker.add(item2);
            List<Item> expected = List.of(item, item2);
            assertEquals(tracker.findAll(), expected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSaveItemAndFindByNameThenMustBeTheSame() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item1 = new Item("item");
            Item item2 = new Item("item");
            Item item3 = new Item("item3");
            tracker.add(item1);
            tracker.add(item2);
            tracker.add(item3);
            List<Item> expected = List.of(item1, item2);
            assertEquals(expected, tracker.findByName(item1.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSaveItemAndDeleteFindAllThenMustBeEmpty() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item("item");
            tracker.add(item);
            tracker.delete(item.getId());
            assertEquals(tracker.findAll(), new ArrayList<>());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenSaveItemSAndDeleteFindAllThenMustBeOne() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item("item");
            Item item2 = new Item("item2");
            tracker.add(item);
            tracker.add(item2);
            tracker.delete(item.getId());
            List<Item> all = tracker.findAll();
            assertEquals(all, List.of(item2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}