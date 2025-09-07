package ru.netology.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InMemoryTodoService implements TodoService {
    private final List<String> items = new ArrayList<>();

    @Override
    public boolean add(String text) {
        if (items.contains(text)) return false;
        items.add(text);
        return true;
    }

    @Override
    public List<String> list() {
        return Collections.unmodifiableList(items);
    }

    @Override
    public boolean deleteByNumber(int oneBasedIndex) {
        int idx = oneBasedIndex - 1;
        if (idx < 0 || idx >= items.size()) return false;
        items.remove(idx);
        return true;
    }

    @Override
    public boolean deleteByExactText(String text) {
        return items.remove(text);
    }
}