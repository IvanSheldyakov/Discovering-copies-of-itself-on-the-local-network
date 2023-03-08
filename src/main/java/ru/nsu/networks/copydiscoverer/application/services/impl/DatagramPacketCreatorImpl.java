package ru.nsu.networks.copydiscoverer.application.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.nsu.networks.copydiscoverer.application.services.DatagramPacketCreator;


import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class DatagramPacketCreatorImpl implements DatagramPacketCreator {

    public DatagramPacket create(String ip, int port, Object message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            byte[] buf = objectMapper.writeValueAsBytes(message);
            return new DatagramPacket(buf, buf.length,
                    InetAddress.getByName(ip),port);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

    }
}
