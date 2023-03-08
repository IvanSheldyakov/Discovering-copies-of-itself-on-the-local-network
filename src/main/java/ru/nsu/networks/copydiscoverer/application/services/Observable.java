package ru.nsu.networks.copydiscoverer.application.services;

public interface Observable {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyAllObservers(Object obj);
}
