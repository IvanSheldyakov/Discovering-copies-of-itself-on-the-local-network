package ru.nsu.networks.copydiscoverer.application.services;

public interface TextService extends DuplicationTextFilter {
    void add(String data);
    void remove(String data);
    String[] getText();
}
