package com.iyke.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, K> {
    public Optional<T> add(T value);
    public List<T> getAll();
    public Optional<T> findBy(K id);
    public Optional<T> remove(K id);
}
