package ru.job4j.tracker.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.services.TrackerService;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
public class TrackerController {
    private final TrackerService trackerService;

    @PostMapping
    public Item save(@RequestBody Item item) {
        return trackerService.add(item);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestParam int id, @RequestBody Item item) {
        boolean status = trackerService.replace(id, item);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam int id) {
        boolean status = trackerService.delete(id);
        return ResponseEntity
                .status(status ? HttpStatus.OK : HttpStatus.NOT_FOUND)
                .build();
    }

    @GetMapping("/getById")
    public Item getById(@RequestParam int id) {
        return trackerService.findById(id);
    }

    @GetMapping("/getByName")
    public List<Item> getByName(@RequestParam String name) {
        return trackerService.findByName(name);
    }

    @GetMapping("/getAll")
    public List<Item> getAll() {
        return trackerService.findAll();
    }
}