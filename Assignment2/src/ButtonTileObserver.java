
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Button;


/**
 *
 * @author candidate number 198397
 */
public class ButtonTileObserver implements Observer {

    private final Button btn;

    /**
     *
     * @param btn
     * @return btn
     */
    public ButtonTileObserver(Button btn) {
        this.btn = btn;
        
    }

    /**
     * updates buttons observing
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        btn.setStyle("-fx-border-radius: 5;-fx-border-width: 2 2 2 2;-fx-border-color:lightgray; -fx-background-color: darkgray;");
        btn.setText(arg.toString());
        
    }

}
