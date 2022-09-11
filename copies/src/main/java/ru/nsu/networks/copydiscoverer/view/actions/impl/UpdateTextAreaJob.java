package ru.nsu.networks.copydiscoverer.view.actions.impl;

import ru.nsu.networks.copydiscoverer.view.actions.ChangeTextArea;

import java.awt.*;
import java.util.Arrays;

public class UpdateTextAreaJob extends ChangeTextArea {

    public UpdateTextAreaJob(TextArea area, String data) {
        super(area,data);
    }

    @Override
    protected void execute() {
        textService.add(data);
        area.setText(Arrays.toString(textService.filter()));
    }

    @Override
    public void run() {
        execute();
    }
}
