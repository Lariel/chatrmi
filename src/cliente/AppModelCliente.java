package cliente;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AppModelCliente {

    private final StringProperty text = new SimpleStringProperty();

    public StringProperty textProperty() {
        return text ;
    }

    public final String getText() {
        return textProperty().get();
    }

    public final void setText(String text) {
        textProperty().set(text);
    }
}