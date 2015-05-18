package com.thoughtworks.repository;

public interface Repository {
    void put(String input);

    String get(String key);
}
