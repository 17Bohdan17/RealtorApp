package project.Hibernate.addRecordPart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class AddRecordWindow {

    FXMLLoader loader;
    public int addRecordStatus;

    public void init() throws IOException {
       switch (addRecordStatus){
           case 1:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/ObjectRecord.fxml"));
               break;

           case 2:
                loader = new FXMLLoader(getClass()
                        .getResource("/recordFxml/AgreementRecord.fxml"));
               break;

           case 3:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/ClientRecord.fxml"));
               break;

           case 4:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/ConsultationRecord.fxml"));
               break;

           case 5:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/FacilityRecord.fxml"));
               break;

           case 6:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/RequirementRecord.fxml"));
               break;

           default:
               throw new IllegalStateException("Unexpected value: " +
                       addRecordStatus);
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




