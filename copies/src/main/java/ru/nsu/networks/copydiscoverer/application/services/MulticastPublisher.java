package ru.nsu.networks.copydiscoverer.application.services;

import java.net.DatagramPacket;

public interface MulticastPublisher {
    void send(DatagramPacket packet);
}
