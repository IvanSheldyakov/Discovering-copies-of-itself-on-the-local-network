package ru.nsu.networks.copydiscoverer.application.services;

import java.net.DatagramPacket;

public interface DatagramPacketCreator {
    DatagramPacket create(String ip, int port, Object message);
}
