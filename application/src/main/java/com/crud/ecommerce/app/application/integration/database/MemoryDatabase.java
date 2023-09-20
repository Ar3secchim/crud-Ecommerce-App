package com.crud.ecommerce.app.application.integration.database;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemoryDatabase {

  private static final MemoryDatabase INSTANCE = new MemoryDatabase();
  private final Map<String, Set> database = new HashMap<>();
  private Long sequence = 100l;

  private MemoryDatabase() {
  }

  public static MemoryDatabase getInstance() {
    return INSTANCE;
  }

  public <T> T saveOrUpdate(T object) {
    String key = makeKey(object);
    Set<T> objects = database.getOrDefault(key, new TreeSet());
    objects.add(object);
    database.put(key, objects);
    return object;
  }

  public <T> List<T> listAll(Class<T> clazz) {
    String key = makeKey(clazz);
    return new ArrayList<>(database.getOrDefault(key, new HashSet<>()));
  }

  public <T> List<T> find(Class<T> clazz, Predicate<T> predicate) {
    String key = makeKey(clazz);
    Set<T> objects = database.getOrDefault(key, new TreeSet());
    List<T> found = objects.stream().filter(predicate)
            .collect(Collectors.toList());
    return found;
  }

  public <T> T delete(T object) {
    String key = makeKey(object);
    Set<T> objects = database.getOrDefault(key, new TreeSet());
    objects.remove(object);
    return object;
  }

  public Long nextId() {
    return ++sequence;
  }

  private String makeKey(Object object) {
    return object.getClass().getName();
  }

  private String makeKey(Class clazz) {
    return clazz.getName();
  }

}
