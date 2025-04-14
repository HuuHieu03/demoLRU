module org.example.demotuan8_lru {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.demotuan8_lru to javafx.fxml;
    opens org.example.demotuan8_lru.controller to javafx.fxml;

    exports org.example.demotuan8_lru;
    exports org.example.demotuan8_lru.controller;
    exports org.example.demotuan8_lru.logic;

}