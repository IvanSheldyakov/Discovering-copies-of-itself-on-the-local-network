package ru.nsu.networks.copydiscoverer.application.services.impl;

import ru.nsu.networks.copydiscoverer.application.services.TextService;

import java.util.LinkedHashSet;

public class TextServiceImpl  implements TextService {

    private final LinkedHashSet<String> text = new LinkedHashSet<>();


    @Override
    public void add(String data) {
        text.add(data);
    }

    @Override
    public void remove(String data) {
        text.remove(data);
    }

    public String[] getText() {
        return text.toArray(new String[0]);
    }

    @Override
    public String[] filter() {
       return getText();
    }


}
