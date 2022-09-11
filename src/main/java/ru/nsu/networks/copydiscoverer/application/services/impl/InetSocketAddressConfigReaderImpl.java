package ru.nsu.networks.copydiscoverer.application.services.impl;

import ru.nsu.networks.copydiscoverer.application.model.InetSocketAddressConfig;
import ru.nsu.networks.copydiscoverer.application.services.InetSocketAddressConfigReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;

public class InetSocketAddressConfigReaderImpl implements InetSocketAddressConfigReader {


    private final InputStream in =
            InetSocketAddressConfigReaderImpl.class.getResourceAsStream("/app.properties");

    public InetSocketAddressConfig read() {
        try {
            Properties properties = new Properties();
            properties.load(in);
            InetSocketAddressConfig conf =
                    new InetSocketAddressConfig(properties.getProperty("ip"),properties.getProperty("port"));
            return conf;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
