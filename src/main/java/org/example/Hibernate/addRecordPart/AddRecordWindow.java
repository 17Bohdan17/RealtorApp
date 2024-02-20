package org.example.Hibernate.addRecordPart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class AddRecordWindow {

    FXMLLoader loader;
    public int addRecordStatus;

    public void init() throws Exception{

       switch (addRecordStatus){
           case 1:
                loader = new FXMLLoader(getClass().getResource("/recordFxml/AgreementRecord.fxml"));
               break;




       }

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);

        ((AddRecordController)loader.getController()).init(stage);
        stage.show();

    }
}




