package ru.netology.service;

import java.util.List;

public interface TodoService {
    boolean add(String text);

    List<String> list();

    boolean deleteByNumber(int oneBasedIndex);

    boolean deleteByExactText(String text);
}