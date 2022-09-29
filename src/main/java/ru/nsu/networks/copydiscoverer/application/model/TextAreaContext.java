package ru.nsu.networks.copydiscoverer.application.model;

import java.awt.*;
import java.util.LinkedHashSet;

public class TextAreaContext {
    private TextArea area;
    private LinkedHashSet<String> text;

    public TextAreaContext(TextArea area, LinkedHashSet<String> text) {
        this.area = area;
        this.text = text;
    }

    public TextArea getArea() {
        return area;
    }

    public void setArea(TextArea area) {
        this.area = area;
    }

    public LinkedHashSet<String> getText() {
        return text;
    }

    public void setText(LinkedHashSet<String> text) {
        this.text = text;
    }
}
