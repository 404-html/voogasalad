package author.view.pages.menu;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Display a html formatted help page
 * 
 * @author Jordan Frazier
 *
 */
class HelpDialog {
	
	private Pane pane;
	private WebView webView = new WebView();
	private WebEngine webEngine = webView.getEngine();
	private Stage stage;

	HelpDialog() {
		pane = new Pane();
		URI uri = new File("data/gui/helpPage.html").toURI();
		webEngine.load(uri.toString());
		pane.getChildren().add(webView);
		buildWindow();
	}

	private void buildWindow() {
		stage = new Stage();
		Scene scene = new Scene(pane, 800, 600);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setScene(scene);
	}

	protected void display() {
		stage.show();
	}
	

}
