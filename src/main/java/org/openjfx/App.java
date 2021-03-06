package org.openjfx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;
    private static AnchorPane mainLayout;
    private static ObservableList<ComponentAndAntall> componentList = FXCollections.observableArrayList();
    private static Integer amount;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        scene = new Scene(loadFXML("login"));
        scene.getStylesheets().add("Primary.css");
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void switchToHandlekurv(ObservableList<ComponentAndAntall> ol) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("handlekurv.fxml"));
        mainLayout = loader.load();

        HandlekurvController hc = loader.getController();
        componentList.addAll(ol);
        hc.saveHandlekurvArray(componentList);

        scene.getStylesheets().add("Primary.css");
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void switchToUserIndex(Integer i) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource("userIndex.fxml"));
        mainLayout = loader.load();

        UserIndexController uic = loader.getController();
        amount = i;
        uic.addToHandlekurvNumber(amount);

        scene.getStylesheets().add("Primary.css");
        scene.setRoot(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}