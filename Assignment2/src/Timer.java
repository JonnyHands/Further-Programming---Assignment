
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author candidate number 198397
 */
public class Timer extends Pane {

    
    private final Timeline animation;
    private int tmp = 0;
    private String s = "";

    Label label = new Label ("0");
    
    /**
     * Timer method to create a functioning timer
     */
    public Timer() {
        label.setFont(javafx.scene.text.Font.font(28));
        label.setStyle("-fx-font-weight: bold");
        label.setTextFill(Color.BLACK);
        
        getChildren().add(label);
        //sets keyframe to increase every second
        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();
    }
    
    private void timelabel(){
        if(tmp >= 0){
        tmp++;
        
    }
        s = tmp + " ";
        label.setText(s);
    }

    /**
     *
     * @return tmp
     */
    public int getTmp() {
        return tmp;
    }

    void reset() {
        tmp = 0;
    }
    
}
