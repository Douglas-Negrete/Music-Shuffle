import java.io.File;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GUI extends Application {

	String[] addresses;
	Shuffler s = new Shuffler();

	File song = s.getSong();
	Media m;
	MediaPlayer mP;
	Label songName, albumName;

	@Override
	public void start(Stage primaryStage) {
		
		StringProperty sSN, sAN;

		primaryStage.setTitle("Music Shuffler");

		GridPane grid = new GridPane(), buttonGrid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Button playBtn = new Button("Play"),
				pauseBtn = new Button("Pause");

		sSN = new SimpleStringProperty(s.getSongName(song));
		sAN = new SimpleStringProperty(s.getAlbumName(song));
		
		songName = new Label(sSN.toString());
		albumName = new Label(sAN.toString());
		
		songName.textProperty().bind(sSN);
		albumName.textProperty().bind(sAN);

		m = new Media(song.toURI().toString());
		mP = new MediaPlayer(m);

		new Thread() {

			public void run() {

				while(true) {

					mP.setOnEndOfMedia(new Runnable() {
						@Override
						public void run() {
							mP.stop();
							song = s.getSong();
							sSN.set(s.getSongName(song));
							sAN.set(s.getAlbumName(song));
							m = new Media(song.toURI().toString());
							mP = new MediaPlayer(m);
							mP.play();
							primaryStage.show();
							return;
						}
					});

				}

			}

		}.start();

		MediaView mediaView = new MediaView(mP);
		mediaView.setMediaPlayer(mP);

		playBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				mP.play();

			}//end handle

		});//end setOnAction

		pauseBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				mP.pause();

			}//end handle

		});//end setOnAction

		buttonGrid.add(playBtn, 0, 0);
		buttonGrid.add(pauseBtn, 1, 0);

		grid.add(songName, 0, 0);
		grid.add(albumName, 0, 1);
		grid.add(buttonGrid, 0, 2);
		grid.add(mediaView, 0, 0);

		primaryStage.setScene(new Scene(grid, 300, 300));
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent we) {

				System.out.println("window is closing");

			}//end handle

		});//end setoncloserequest

	}//end start



}//end class main 