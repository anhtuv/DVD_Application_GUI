import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class DVDCollectionApp1  extends Application {
    private DVDCollection model;

    public DVDCollectionApp1() {
      model = DVDCollection.example1();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        DVDCollectionAppView1  view = new DVDCollectionAppView1();
        //DVDCollectionAppView2  view = new DVDCollectionAppView2();
        aPane.getChildren().add(view);
        view.update(model, 1);

        // Event handler for Add button
        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String title = JOptionPane.showInputDialog("Please enter the title of the movie: ");
                int year = Integer.parseInt(JOptionPane.showInputDialog("Please enter the year when the movie was released: "));
                int length = Integer.parseInt(JOptionPane.showInputDialog("Please enter the length of the movie in minutes: "));

                model.add(new DVD(title, year, length));
                view.update(model, 0);
            }
        });

        // Event handler for Delete button
        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.remove(view.getTitleList().getSelectionModel().getSelectedItem());
                view.update(model, 0);
            }
        });

        // Event handlers for synchronized rows
        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                int title = view.getTitleList().getSelectionModel().getSelectedIndex();
                view.update(model, title);
            }
        });

        view.getYearList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                int year = view.getYearList().getSelectionModel().getSelectedIndex();
                view.update(model, year);
            }
        });

        view.getLengthList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                int length = view.getLengthList().getSelectionModel().getSelectedIndex();
                view.update(model, length);
            }
        });

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}