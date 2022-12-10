module com.roboskeletron.task13 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;


    opens com.roboskeletron.task13 to javafx.fxml;
    exports com.roboskeletron.task13;
    exports com.roboskeletron.task13.controllers;
    opens com.roboskeletron.task13.controllers to javafx.fxml;
}