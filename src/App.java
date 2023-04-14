import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class App extends Application {
    private ComboBox<String> unitFromComboBox;
    private ComboBox<String> unitToComboBox;
    private TextField valueTextField;
    private Label resultLabel;

    public void start(Stage stage) {
        // create UI components
        Label titleLabel = new Label("Metric Converter");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        Label valueLabel = new Label("Value:");
        valueTextField = new TextField();

        Label unitFromLabel = new Label("Unit from:");
        unitFromComboBox = new ComboBox<>();
        unitFromComboBox.getItems().addAll("km", "kg", "g", "mm");
        unitFromComboBox.setValue("km");

        Label unitToLabel = new Label("Unit to:");
        unitToComboBox = new ComboBox<>();
        unitToComboBox.getItems().addAll("mile", "lb", "oz", "inch");
        unitToComboBox.setValue("mile");

        Button convertButton = new Button("Convert");
        convertButton.setOnAction(e -> convert());

        resultLabel = new Label();
        resultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // arrange components in a grid
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));
        gridPane.add(titleLabel, 0, 0, 2, 1);
        gridPane.add(valueLabel, 0, 1);
        gridPane.add(valueTextField, 1, 1);
        gridPane.add(unitFromLabel, 0, 2);
        gridPane.add(unitFromComboBox, 1, 2);
        gridPane.add(unitToLabel, 0, 3);
        gridPane.add(unitToComboBox, 1, 3);
        gridPane.add(convertButton, 0, 4, 2, 1);
        gridPane.add(resultLabel, 0, 5, 2, 1);

        // create and show the scene
        Scene scene = new Scene(gridPane, 400, 300);
        stage.setScene(scene);
        stage.setTitle("Metric Converter");
        stage.show();
    }

    private void convert() {
        String unitFrom = unitFromComboBox.getValue();
        String unitTo = unitToComboBox.getValue();

        try {
            double value = Double.parseDouble(valueTextField.getText());

            double result = 0;
            switch (unitFrom) {
                case "km":
                    switch (unitTo) {
                        case "mile":
                            result = value * 0.621371;
                            break;
                    }
                    break;
                case "kg":
                    switch (unitTo) {
                        case "lb":
                            result = value * 2.20462;
                            break;
                    }
                    break;
                case "g":
                    switch (unitTo) {
                        case "oz":
                            result = value * 0.0353;
                            break;
                    }
                    break;
                case "mm":
                    switch (unitTo) {
                        case "inch":
                            result = value * 0.03937;
                            break;
                    }
                    break;
                default:
                    resultLabel.setText("Your input is invalid, try again please");
                    return;
            }
    
            resultLabel.setText(String.format("%.2f %s = %.2f %s", value, unitFrom, result, unitTo));
        } catch (NumberFormatException e) {
            resultLabel.setText("Invalid input, please enter a number");
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}