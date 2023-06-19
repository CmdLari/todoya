package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ControllCentre {
    // THE UNICORN IMAGES
    String[] einhornImages = {
            "src\\main\\resources\\assets\\Einhorn1.png",
            "src\\main\\resources\\assets\\Einhorn2.png",
            "src\\main\\resources\\assets\\Einhorn3.png",
            "src\\main\\resources\\assets\\Einhorn4.png",
            "src\\main\\resources\\assets\\Einhorn5.png",
            "src\\main\\resources\\assets\\Einhorn6.png"};

    //THE TASK CONTROLLER

    //List of tasks entered & done
    public ObservableList<String> tasks = FXCollections.observableArrayList();
    public ObservableList<String> tasksDoneItems = FXCollections.observableArrayList();

    //Various input options are declared
    public TextField taskEnter = new TextField();
    public ListView<String> taskList = new ListView<>(tasks);

    public ObservableList<String> comboBoxOptions = FXCollections.observableArrayList(
            "Complete",
            "Cancel");
    public ChoiceBox<String> statemenu = new ChoiceBox<>(comboBoxOptions);
    Path hoernchen = Paths.get(einhornImages[Math.min(tasksDoneItems.size(),einhornImages.length-1)]);

    public Image einhoernchen;
    {
        try {
            einhoernchen = new Image(new URL("file:///"+hoernchen.toAbsolutePath()).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Button ok = new Button();

    //Various Stats showing
    public Text tasksEntered = new Text();
    public Text tasksDone = new Text();

    public ImageView einhornState;

    public Image updateEinhorn(){
        Path hoernchen = Paths.get(einhornImages[Math.min(tasksDoneItems.size(),einhornImages.length-1)]);

        Image einhoernchen;
        {
            try {
                einhoernchen = new Image(new URL("file:///"+hoernchen.toAbsolutePath()).openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return einhoernchen;
    }

    public ControllCentre() throws IOException {
    }

    public void initialize(){
        statemenu.setItems(comboBoxOptions);
        einhornState.setImage(einhoernchen);
        statemenu.setOnAction(event -> {onDropbox();
            statemenu.getSelectionModel().clearSelection();});
    }

    protected void updateGUI(){
        taskList.setItems(tasks);
        tasksEntered.setText(Integer.toString(tasks.size()));
        tasksDone.setText(Integer.toString(tasksDoneItems.size()));
        einhornState.setImage(updateEinhorn());

    }

    @FXML
    protected void onAddPressed(){
        if (!(taskEnter.getText().trim().length()==0)) {
            tasks.add(taskEnter.getText());
            taskEnter.clear();
            updateGUI();
        }
    }
    @FXML
    protected void onDropbox(){

        String selectedItem = taskList.getSelectionModel().getSelectedItem();

        if ("Complete".equals(statemenu.getSelectionModel().getSelectedItem())) {
            tasksDoneItems.add(selectedItem);
            tasks.remove(selectedItem);
            updateGUI();

        } else if ("Cancel".equals(statemenu.getSelectionModel().getSelectedItem())) {
            tasks.remove(selectedItem);
            tasksDoneItems.clear();
            System.out.println(tasksDoneItems.size());
            updateGUI();
        }

    }

    //THE STATS CONTROLLER


}