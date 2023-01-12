module com.mycompany.mavenproject1 {
    requires javafx.controls;
    requires javafx.fxml;   
    requires java.net.http;
    requires org.json;
    requires org.jsoup;

    opens com.mycompany.mavenproject1 to javafx.fxml;
    exports com.mycompany.mavenproject1;
}
