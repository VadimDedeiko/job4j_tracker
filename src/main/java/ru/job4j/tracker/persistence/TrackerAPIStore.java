package ru.job4j.tracker.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ru.job4j.tracker.model.Item;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TrackerAPIStore implements Store {
    @Value("${api-url}")
    private String url;
    private final RestTemplate client;

    @Override
    public Item add(Item item) {
        System.out.println(url);
        return client.postForEntity(
                url, item, Item.class
        ).getBody();
    }

    @Override
    public boolean replace(int id, Item item) {
        return client.exchange(
                String.format("%s?id=%d", url, id),
                HttpMethod.PUT,
                new HttpEntity<>(item),
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    @Override
    public boolean delete(int id) {
        return client.exchange(
                String.format("%s?id=%d", url, id),
                HttpMethod.DELETE,
                HttpEntity.EMPTY,
                Void.class
        ).getStatusCode() != HttpStatus.NOT_FOUND;
    }

    @Override
    public List<Item> findAll() {
        return getList(String.format(
                "%s/getAll", url
        ));
    }

    @Override
    public List<Item> findByName(String name) {
        return getList(String.format(
                "%s/getByName?name=%s", url, name
        ));
    }

    private List<Item> getList(String url) {
        List<Item> body = client.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Item>>() {
                }
        ).getBody();
        return body == null ? Collections.emptyList() : body;
    }

    @Override
    public Item findById(int id) {
        return client.getForEntity(
                String.format("%s/getById?id=%d", url, id),
                Item.class
        ).getBody();
    }

    @Override
    @PostConstruct
    public void init() {
    }

    @PreDestroy
    @Override
    public void close() {
    }
}
