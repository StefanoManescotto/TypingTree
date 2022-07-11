module com.example.typingtree_java {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires richtextfx.fat;

    opens com.example.typingtree_java to javafx.fxml;
    exports com.example.typingtree_java;
}