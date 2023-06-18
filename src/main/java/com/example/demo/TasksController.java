package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.List;

class tasksController {

    private TextField taskEnter = new TextField();
    private ListView taskList = new ListView<>();


    public void addToTaskList(List<String> tasks) throws IOException {

        tasks.add("");
    }

}
