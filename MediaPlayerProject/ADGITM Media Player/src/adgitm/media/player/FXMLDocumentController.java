package adgitm.media.player;

import java.io.File;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ResourceBundle;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;


public class FXMLDocumentController implements Initializable {
    
    private MediaPlayer mediaPlayer;
    
    @FXML
    private MediaView mediaView;
    
    private String filePath;
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    
    @FXML
    private Slider volslider;
    
    @FXML
    private Slider seekSlider;
    
    @FXML
    private VBox Tvbox;
    
    @FXML
    private Button Translatebtn;
    
    @FXML
    private TextField InputText,OutputText;
    
    @FXML
    private MenuItem Langchoice;
    
    @FXML
    private MenuButton inputTrans,outputTrans;
    
    private String inText,outText;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a File (*.mp4)","*.mp4");
                fileChooser.getExtensionFilters().add(filter);
                File file = fileChooser.showOpenDialog(null);
                filePath = file.toURI().toString();
                
                if(filePath!=null)
                {
                     Media media = new Media(filePath);
                     mediaPlayer = new MediaPlayer(media);
                     mediaView.setMediaPlayer(mediaPlayer);
                     
                     DoubleProperty width = mediaView.fitWidthProperty();
                     DoubleProperty hight = mediaView.fitHeightProperty();
                     width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
                     hight.bind(Bindings.selectDouble(mediaView.sceneProperty(), "hight"));
                     
                     volslider.setValue(mediaPlayer.getVolume()*100);
                     volslider.valueProperty().addListener(new InvalidationListener(){
                        @Override
                        public void invalidated(Observable observable)
                        {
                            mediaPlayer.setVolume(volslider.getValue()/100);
                        }
                         
                     });
                     
                     mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                         @Override
                         public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                             seekSlider.setValue(newValue.toSeconds());
                         }
                     });
                     
                     seekSlider.setOnMouseClicked(new EventHandler<MouseEvent>(){
                         @Override
                         public void handle(MouseEvent event) {
                            mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                         }
                         
                     });
                     
                     seekSlider.setOnMousePressed(new EventHandler() {
                         @Override
                         public void handle(Event event) {
                             mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                         }
                     });
                     
                     seekSlider.setOnMouseDragged(new EventHandler() {
                         @Override
                         public void handle(Event event) {
                             mediaPlayer.seek(Duration.seconds(seekSlider.getValue()));
                         }
                     });
                     
                     mediaPlayer.setOnReady(new Runnable() {
                         @Override
                         public void run() {
                           Duration total = media.getDuration();
                           seekSlider.setMax(total.toSeconds());
                         }
                     });
                     mediaPlayer.play();

                }
    }
   
    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }
    
    @FXML
    private void playVideo(ActionEvent event){
        mediaPlayer.play();
        mediaPlayer.setRate(1);
        Tvbox.setVisible(false);
    }
    
    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();
    }
    
    @FXML
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);
    }
    
    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);
    }
    
    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(0.75);
    }
    
    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }
    
    @FXML
    private void exitVideo(ActionEvent event){
        System.exit(0);
    }
    
    private int indexofcomma;
    @FXML
    private void Translation(ActionEvent event)
    {
        mediaPlayer.pause();
        Tvbox.setVisible(true);
//        indexofcomma=event.toString().indexOf(",");
//        System.out.println(event.toString().substring(42, indexofcomma));
        
    }
    private String inlangId,outlangId;
    private String inputCode,outputCode;
    @FXML
    private void Inputtranslatetext(ActionEvent event)
    {
        System.out.println(event);
        indexofcomma = event.toString().indexOf(",");
        inlangId=event.toString().substring(44, indexofcomma-3);
        inputTrans.setText(inlangId);
        inputCode=event.toString().substring(indexofcomma-3, indexofcomma-1);
    }
    
    @FXML
    private void Outputtranslatetext(ActionEvent event)
    {
        indexofcomma = event.toString().indexOf(",");
        outlangId=event.toString().substring(44, indexofcomma-3);
        outputTrans.setText(outlangId);
        outputCode=event.toString().substring(indexofcomma-3, indexofcomma-1);
        System.out.println(outputCode);
    }
    
    @FXML
    private void Translateit(ActionEvent event) throws IOException
    {
        String text = InputText.getText();
        OutputText.setText(translate(inputCode,outputCode,text));
    }
    
    private static String translate(String langFrom, String langTo, String text) throws IOException {
	        // INSERT YOU URL HERE
	        String urlStr = "https://script.google.com/macros/s/AKfycbwECpgbPkVSO7PXGt3vNoE0NlrZrEw68sN7Fx6gfcLe4sKIOrZ_/exec" +
	                "?q=" + URLEncoder.encode(text, "UTF-8") +
	                "&target=" + langTo +
	                "&source=" + langFrom;
	        URL url = new URL(urlStr);
	        StringBuilder response = new StringBuilder();
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestProperty("User-Agent", "Mozilla/5.0");
	        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	        }
	        in.close();
	        return response.toString();
	    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
