package ar.edu.unnoba.poo2021.sistcausapenales.service;

import java.util.List;

public interface PagingService {
    public List<Integer> getPagingRange(int current, int total, int lenght);
}
