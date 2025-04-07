module org.michaelmathews.membershipfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.michaelmathews.membershipfx to javafx.fxml;
    exports org.michaelmathews.membershipfx;
}