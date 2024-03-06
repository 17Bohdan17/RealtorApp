package RealtorApp.view;

import RealtorApp.controller.RecordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import RealtorApp.enums.RecordStatusEnum;

import java.io.IOException;


public class RecordWindow {

    FXMLLoader loader;
    public RecordStatusEnum recordStatusEnum;
    public int addRecordStatus;


    public void init() throws IOException {
       switch (recordStatusEnum){
           case ADD_OBJECT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/AddObject.fxml"));
               break;

           case ADD_AGREEMENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddAgreement.fxml"));
               break;

           case ADD_CLIENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/AddClient.fxml"));
               break;

           case ADD_CONSULTATION:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/AddConsultation.fxml"));
               break;

           case ADD_FACILITY:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/AddFacility.fxml"));
               break;

           case ADD_REQUIREMENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/AddRequirement.fxml"));
               break;

           case UPDATE_OBJECT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/UpdateObject.fxml"));
               break;

           case UPDATE_AGREEMENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/UpdateAgreement.fxml"));
               break;

           case UPDATE_CLIENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/UpdateClient.fxml"));
               break;

           case UPDATE_CONSULTATION:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/UpdateConsultation.fxml"));
               break;

           case UPDATE_FACILITY:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/UpdateFacility.fxml"));
               break;

           case UPDATE_REQUIREMENT:
               loader = new FXMLLoader(getClass()
                       .getResource("/fxml/UpdateRequirement.fxml"));
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




