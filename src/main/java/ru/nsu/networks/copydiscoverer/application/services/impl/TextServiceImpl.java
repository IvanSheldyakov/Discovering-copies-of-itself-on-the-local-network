package ru.nsu.networks.copydiscoverer.application.services.impl;

import ru.nsu.networks.copydiscoverer.application.services.TextService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class TextServiceImpl  implements TextService {

    private LinkedHashSet<String> text;

    public TextServiceImpl(LinkedHashSet<String> text) {
        this.text = text;
    }

    @Override
    public void add(String data) {
        text.add(data);
    }

    @Override
    public void remove(String data) {
        text.remove(data);
    }

    public String getText() {

        return text.toString();

    }

    @Override
    public String filter() {
       return getText();
    }


}
