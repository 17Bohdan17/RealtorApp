package project.Hibernate.addRecordPart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.Hibernate.AddRecordStatusEnum;

import java.io.IOException;


public class AddRecordWindow {

    FXMLLoader loader;
    public AddRecordStatusEnum addRecordStatusEnum;
    public int addRecordStatus;


    public void init() throws IOException {
       switch (addRecordStatusEnum){
           case ADD_OBJECT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/ObjectRecord.fxml"));
               break;

           case ADD_AGREEMENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/recordFxml/AgreementRecord.fxml"));
               break;

           case ADD_CLIENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/ClientRecord.fxml"));
               break;

           case ADD_CONSULTATION:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/ConsultationRecord.fxml"));
               break;

           case ADD_FACILITY:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/FacilityRecord.fxml"));
               break;

           case ADD_REQUIREMENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/RequirementRecord.fxml"));
               break;


           default:
               throw new IllegalStateException("Unexpected value: " +
                       addRecordStatusEnum);
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




