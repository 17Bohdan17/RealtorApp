package project.Hibernate.RecordPart;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import project.Hibernate.RecordStatusEnum;

import java.io.IOException;


public class RecordWindow {

    FXMLLoader loader;
    public RecordStatusEnum recordStatusEnum;
    public int addRecordStatus;


    public void init() throws IOException {
       switch (recordStatusEnum){
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

           case UPDATE_OBJECT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/UpdateObjectRecord.fxml"));
               break;

           case UPDATE_AGREEMENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/UpdateAgreementRecord.fxml"));
               break;

           case UPDATE_CLIENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/UpdateClientRecord.fxml"));
               break;

           case UPDATE_CONSULTATION:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/UpdateConsultationRecord.fxml"));
               break;

           case UPDATE_FACILITY:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/UpdateFacilityRecord.fxml"));
               break;

           case UPDATE_REQUIREMENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/recordFxml/UpdateRequirementRecord.fxml"));
               break;

           default:
               throw new IllegalStateException("Unexpected value: " +
                       recordStatusEnum);
       }

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);

        ((RecordController)loader.getController()).init(stage);
        stage.show();

    }
}




