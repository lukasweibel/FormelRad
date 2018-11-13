package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;

import static javafx.scene.control.Alert.AlertType.WARNING;

/**
 * Formelrad Application
 * @author Peter Rutschmann
 * @version 13.09.2018
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();

			// Creating an image
			Image image = new Image(new FileInputStream("src/application/formelradelektronik.gif"));
			ImageView imageView = new ImageView(image);
			imageView.setX(10);
			imageView.setY(10);
			imageView.setFitHeight(300);
			imageView.setFitWidth(300);
			imageView.setPreserveRatio(true);
			root.getChildren().add(imageView);

			Label lbleistung = new Label("Leistung:");
			lbleistung.relocate(10, 285);
			lbleistung.setFont(Font.font(15));
			root.getChildren().add(lbleistung);

			TextField txLeistung = new TextField();
			txLeistung.relocate(100, 285);
			txLeistung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txLeistung);

			Label lblSpannung = new Label("Spannung:");
			lblSpannung.relocate(10, 325);
			lblSpannung.setFont(Font.font(15));
			root.getChildren().add(lblSpannung);

			TextField txSpannung = new TextField();
			txSpannung.relocate(100, 325);
			txSpannung.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txSpannung);

			Label lblStrom = new Label("Strom:");
			lblStrom.relocate(10, 365);
			lblStrom.setFont(Font.font(15));
			root.getChildren().add(lblStrom);

			TextField txStrom = new TextField();
			txStrom.relocate(100, 365);
			txStrom.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txStrom);

			Label lblWiderstand = new Label("Widerstand:");
			lblWiderstand.relocate(10, 405);
			lblWiderstand.setFont(Font.font(15));
			root.getChildren().add(lblWiderstand);

			TextField txWiderstand = new TextField();
			txWiderstand.relocate(100, 405);
			txWiderstand.setFont(Font.font("Verdana", 15));
			root.getChildren().add(txWiderstand);

			Button btnBerechnen = new Button();
			btnBerechnen.relocate(100, 445);
			btnBerechnen.setText("Berechnen");
			root.getChildren().add(btnBerechnen);
			
			btnBerechnen.setOnAction(e -> {
				Calculator myCalculator = new Calculator(
						evaluateDouble(txLeistung.getText()),
						evaluateDouble(txSpannung.getText()),
						evaluateDouble(txStrom.getText()),
						evaluateDouble(txWiderstand.getText()));
				boolean isLeistungNull = myCalculator.getLeistung() == null;
				boolean isSpannungNull = myCalculator.getSpannung() == null;
				boolean isStromNull = myCalculator.getStrom() == null;
				boolean isWiderstandNull = myCalculator.getWiderstand() == null;
				System.out.print("Vorher:  ");
				System.out.println(myCalculator.toString());
				boolean validInputs = myCalculator.calculate();
				if (!validInputs) {
                    Alert alert = new Alert(WARNING);
                    alert.setTitle("Warning Dialog");
                    alert.setHeaderText("More than two values");
                    alert.setContentText("You filled out more than two fields... This is no problem, but take care..");
                    alert.showAndWait();
                }
				System.out.print("Nachher: ");
				System.out.println(myCalculator.toString());
				
				txLeistung.setStyle("-fx-text-inner-color: black;");
				txSpannung.setStyle("-fx-text-inner-color: black;");
				txStrom.setStyle("-fx-text-inner-color: black;");
				txWiderstand.setStyle("-fx-text-inner-color: black;");

				txLeistung.setText(Double.toString(myCalculator.getLeistung()));
				if(isLeistungNull) {
					txLeistung.setStyle("-fx-text-inner-color: red;");
				}
				txSpannung.setText(Double.toString(myCalculator.getSpannung()));
				if(isSpannungNull) {
					txSpannung.setStyle("-fx-text-inner-color: red;");
				}
				txStrom.setText(Double.toString(myCalculator.getStrom()));
				if(isStromNull) {
					txStrom.setStyle("-fx-text-inner-color: red;");
				}
				txWiderstand.setText(Double.toString(myCalculator.getWiderstand()));
				if(isWiderstandNull) {
					txWiderstand.setStyle("-fx-text-inner-color: red;");
				}
			});

			Scene scene = new Scene(root, 330, 490);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Formelrad");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Double evaluateDouble(String value) {
		if (value == null || value.isEmpty())
			return null;
		return Double.parseDouble(value);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
