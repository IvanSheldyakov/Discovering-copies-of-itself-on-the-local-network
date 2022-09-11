package ru.nsu.networks.copydiscoverer.view.actions.impl;

import ru.nsu.networks.copydiscoverer.view.actions.ChangeTextArea;

import java.awt.*;
import java.util.Arrays;

public class RemoveFromTextAreaJob extends ChangeTextArea {

    public RemoveFromTextAreaJob(TextArea area, String data) {
        super(area, data);
    }

    @Override
    protected void execute() {
        textService.remove(data);
        area.setText(Arrays.toString(textService.getText()));
    }

    @Override
    public void run() {
        execute();
    }
}
