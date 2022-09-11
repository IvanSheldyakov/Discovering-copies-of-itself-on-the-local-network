package ru.nsu.networks.copydiscoverer.application.services.impl;

import ru.nsu.networks.copydiscoverer.application.model.InetSocketAddressConfig;
import ru.nsu.networks.copydiscoverer.application.services.MulticastPublisher;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MulticastPublisherImpl implements MulticastPublisher {

    private DatagramSocket socket;


    @Override
    public void send(DatagramPacket packet) {
        try {
            socket = new DatagramSocket();
            socket.send(packet);
        } catch (IOException e) {
            socket.close();
            throw new UncheckedIOException(e);
        }

    }
}
