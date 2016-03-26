import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MarkovGUI extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage)
	{
		primaryStage.setTitle("Markov Chain GUI");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 425, 350);
		primaryStage.setScene(scene);

		Text scenetitle = new Text("Markov Chain GUI");
		scenetitle.setFont(Font.font("Helvetica", FontWeight.NORMAL, 24));
		grid.add(scenetitle, 0, 0, 2, 1);

		Text sceneAuthor = new Text("By Nomi Ringach");
		sceneAuthor.setFont(Font.font("Helvetica", FontWeight.NORMAL, 10));
		grid.add(sceneAuthor, 0, 1);

		FileChooser inputGUI = new FileChooser();
		inputGUI.getExtensionFilters().add(new ExtensionFilter("Plaintext (*.txt)", "*.txt"));
		Button inputBtn = new Button("Choose Input File");
		HBox inputBox = new HBox(10);
		inputBox.setAlignment(Pos.CENTER);
		inputBox.getChildren().add(inputBtn);
		grid.add(inputBox, 0, 2, 2, 1);
		StringBuffer inputName = new StringBuffer();;
		inputBtn.setOnAction((event) ->
		{
			File inputFile = inputGUI.showOpenDialog(primaryStage);
			inputName.append(inputFile.getAbsolutePath());
		}
				);

		Label order = new Label("Markov chain order:");
		grid.add(order, 0, 3);

		TextField orderField = new TextField();
		grid.add(orderField, 1, 3);

		Label outputFile = new Label("Output filename:");
		grid.add(outputFile, 0, 4);

		TextField outputFileField = new TextField();
		grid.add(outputFileField, 1, 4);

		Label numChars = new Label("Number of characters to write:");
		grid.add(numChars, 0, 5);

		TextField numCharsField = new TextField();
		grid.add(numCharsField, 1, 5);

		Button btn = new Button("Create Story");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, 7);

		final Text chainTarget = new Text();
		grid.add(chainTarget, 1, 8);

		final Text storyTarget = new Text();
		grid.add(storyTarget, 1, 9);

		btn.setOnAction((e) -> {
			int chainOrder = Integer.parseInt(orderField.getText());
			String outputName = makeOutputString(inputName, outputFileField.getText());
			int outputChars = Integer.parseInt(numCharsField.getText());

			try {
				MarkovChainHandler mChain = new MarkovChainHandler(inputName.toString(), outputName, chainOrder);

				chainTarget.setFill(Color.FORESTGREEN);
				chainTarget.setText("Chain created successfully!");

				mChain.createStory(outputChars);

				storyTarget.setFill(Color.FORESTGREEN);
				storyTarget.setText("Story created Successfully!");
			} catch (IOException e1) {
				chainTarget.setFill(Color.RED);
				chainTarget.setText("An error occurred!");

				storyTarget.setFill(Color.RED);
				storyTarget.setText(e1.getMessage());
			}
		});

		primaryStage.show();
	}

	private String makeOutputString(StringBuffer input, String output)
	{
		return input.toString().substring(0, input.toString().lastIndexOf('/')+1) + output;
	}
}
