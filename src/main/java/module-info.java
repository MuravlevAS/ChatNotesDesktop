module ru.sgk.chatnotesdesktop {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens ru.sgk.chatnotesdesktop to javafx.fxml;
    exports ru.sgk.chatnotesdesktop;
}