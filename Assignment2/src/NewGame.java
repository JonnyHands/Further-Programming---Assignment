
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author candidate number 198397
 */
public class NewGame {

    private int xsize;
    private int ysize;
    private int mines;
    MinesweeperBy198397 game = new MinesweeperBy198397();

    /**
     * creates stage, creates contents and allocates/formats its contents
     * layout then opens the GUI
     *
     * @return a new game with user's input of parameters
     */
    void showStage() {
        Stage ngStage = new Stage();
        ngStage.initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        VBox root = new VBox();
        Scene scene = new Scene(root,Color.GREY);
        Button submit = new Button("Submit");
        Button cancel = new Button("Cancel");
        TextField x_size = new TextField();
        x_size.setPromptText("Column size");
        TextField y_size = new TextField();
        y_size.setPromptText("Row size");
        TextField noMines = new TextField();
        noMines.setPromptText("Number of mines");
        grid.setAlignment(Pos.CENTER);
        grid.add(new Label("Column size:"), 0, 0);
        grid.add(x_size, 1, 0);
        grid.add(new Label("Row size:"), 0, 1);
        grid.add(y_size, 1, 1);
        grid.add(new Label("Number of mines:"), 0, 2);
        grid.add(noMines, 1, 2);

        TilePane newGameButtons = new TilePane(Orientation.HORIZONTAL);
        newGameButtons.setPadding(new Insets(20, 10, 20, 0));
        newGameButtons.setHgap(10.0);
        newGameButtons.getChildren().addAll(submit, cancel);

        newGameButtons.setAlignment(Pos.BOTTOM_CENTER);
        GridPane innergrid = new GridPane();
        innergrid.setAlignment(Pos.CENTER);
        innergrid.add(newGameButtons, 0, 0);
        grid.add(innergrid, 0, 2, 2, 1);

        submit.setOnAction(e -> {
            // sets and converts variables to ints
            xsize = Integer.parseInt(x_size.getText());
            ysize = Integer.parseInt(y_size.getText());
            mines = Integer.parseInt(noMines.getText());   
            
            ngStage.close();
            
        });

        cancel.setOnAction(e -> {
            ngStage.close();
        });

        root.getChildren().addAll(grid, newGameButtons);
        ngStage.getIcons().add(new Image("minesweeper.jpg"));
        ngStage.setScene(scene);
        ngStage.showAndWait();

    }

    /**
     *
     * @return xsize
     */
    public int getXsize() {
        return xsize;
    }

    /**
     *
     * @return ysize
     */
    public int getYsize() {
        return ysize;
    }

    /**
     *
     * @return mines
     */
    public int getMines() {
        return mines;
    }

}
