module org.example.Hibernate{
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    exports org.example.Hibernate;
    opens org.example.Hibernate;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires java.naming;

}