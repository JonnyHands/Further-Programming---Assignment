/**
 *
 * @author candidate number 198397
 */

import java.io.File;
import java.io.FileWriter;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;

/**
 *
 * @author Jonny
 */
public class MinesweeperBy198397 extends Application {

    private GridPane root;
    private static Minesweeper game;
    private int xsize = 10;
    private int ysize = 10;
    private int mines = 10;
    private int lastX;
    private int lastY;
    private int lastM;
    private static VBox vbox = new VBox();
    private StackPane clock = new StackPane();
    private static Stage primaryStage;
    private GridPane mineBoard;
    private Label flagCount = new Label("Flag count:");
    private Label flagNo = new Label("");
    private int flags;
    private Label timelabel = new Label("Time:");
    private Timer timer = new Timer();
    private int endX;
    private int endY;

    @Override
    public void init() throws Exception {
        super.init();
        this.game = new Minesweeper(xsize, ysize, mines);
        game.populate();
    }

    /**
     * creates primaryStage, creates contents and allocates/formats its contents
     * layout then opens the GUI
     *
     * @param primaryStage
     * @return
     */
    @Override
    public void start(Stage primaryStage) {
        /*
        * create all needed variables
         */
        BorderPane border = new BorderPane();
        HBox topContainer = new HBox();
        MenuBar mb = new MenuBar();
        Group group = new Group();
        NewGame newGame = new NewGame();
        Region region1 = new Region();
        Region region2 = new Region();
        Region region3 = new Region();
        Region region4 = new Region();
        /*
        * formats ui as needed
         */

        topContainer.setHgrow(region1, Priority.ALWAYS);
        topContainer.setHgrow(region1, Priority.ALWAYS);
        //creates a background fill, a background and applys to the topContainer
        BackgroundFill background_fill = new BackgroundFill(Color.DARKGREY,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        topContainer.setBackground(background);
        clock.getChildren().add(timer);
        border.setTop(mb);
        // creates spacing inbetween topContainer components
        topContainer.setSpacing(10);
        topContainer.prefWidthProperty().bind(primaryStage.widthProperty());
        //sets labels details 
        flagCount.setText("Flag count:");
        flagNo.setText(String.valueOf(flags));
        flagNo.setTextFill(Color.BLACK);
        flagNo.setFont(javafx.scene.text.Font.font(28));
        flagNo.setStyle("-fx-font-weight: bold");
        flagCount.setTextFill(Color.BLACK);
        flagCount.setFont(javafx.scene.text.Font.font(28));
        flagCount.setStyle("-fx-border-width: 2;-fx-border-color: black;-fx-background-color: grey;");
        timelabel.setText("Time:");
        timelabel.setFont(javafx.scene.text.Font.font(28));
        timelabel.setTextFill(Color.BLACK);
        timelabel.setStyle("-fx-border-width: 2;-fx-border-color: black;-fx-background-color: grey;");
        //puts all contents needed into topContainer
        topContainer.getChildren().addAll(mb, flagCount, flagNo, region1, timelabel, clock, region2);

        topContainer.setStyle("-fx-font-size: 30;");
        //creates menu 'Options'
        Menu options = new Menu("Options");
        //creates item 'New Game' for 'Options'
        MenuItem newGameButton = new MenuItem("New Game");
        //creates response for when newGameButton is pressed
        newGameButton.setOnAction(e -> {
            newGame.showStage();
            xsize = newGame.getXsize();
            ysize = newGame.getYsize();
            mines = newGame.getMines();
            root.getChildren().clear();
            root.getChildren().add(createBoard(xsize, ysize, mines));
            primaryStage.sizeToScene();

        });
        MenuItem exit = new MenuItem("Exit");
        //creates response for when exit is pressed
        exit.setOnAction(e -> {
            Platform.exit();
        });
        options.getItems().add(newGameButton);
        options.getItems().add(exit);

        mb.getMenus().add(options);
        mb.getStylesheets().add(
                getClass().getResource("myDialogs.css").toExternalForm());
        mb.getStyleClass().add("menu");
        mb.setStyle("-fx-border-width: 2;-fx-border-color: black;-fx-background-color: darkgrey;");
        //stores newly created board into variable
        mineBoard = createBoard(xsize, ysize, mines);
        group.getChildren().addAll(border, topContainer, mineBoard);
        Scene scene = new Scene(group, Color.GREY);

        //sets stage up
        primaryStage.setTitle("Minesweeper by 198397");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
        primaryStage.getIcons().add(new Image("minesweeper.jpg"));

    }

    /**
     * Method used to create a game board with parameters input
     *
     * @param x_size
     * @param y_size
     * @param numMines
     * @return board with desired parameters
     */
    public GridPane createBoard(int x_size, int y_size, int numMines) {
        //resets timer
        timer.reset();
        //resets flag count
        flags = 0;
        this.game = new Minesweeper(x_size, y_size, numMines);
        this.game.populate();
        flags = 0;
        // formats the GridPane
        root = new GridPane();
        root.setPadding(new Insets(40, 80, 40, 40));
        root.setAlignment(Pos.CENTER);
        root.setLayoutY(60);
        root.setLayoutX(60);
        root.setHgap(1);
        root.setVgap(1);
        //stores the last used x, y and mine variables
        lastX = x_size;
        lastY = y_size;
        lastM = numMines;
        //creates an instance of class NewGame
        NewGame newGame = new NewGame();
        //creates board with two dimensional array of buttons
        Button[][] board = new Button[x_size][y_size];
        // iterates through every button and sets its contents
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                endX = board.length;
                endY = board.length;
                Button btn = new Button("");
                btn.setMinSize(30, 30);
                btn.setPrefSize(30, 30);
                btn.setMaxSize(30, 30);
                //uses class ButtonTileObserver to recursively check if tiles need to be revealed
                game.getMineTile(i, j).addObserver(new ButtonTileObserver(btn));
                // fixes lambda problem by storing i and j into final variables x and y
                final int x = i, y = j;
                btn.setOnMouseClicked(event
                        -> {
                    //checks if left mouse key is pressed, if so calls setUpLeftButtonClick
                    if (event.getButton() == MouseButton.PRIMARY) {
                        setUpLeftButtonClick(x, y, btn, lastX, lastY, lastM, newGame);
                        //checks if right mouse key is pressed, if so calls setUpLeftButtonClick
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        setUpRightMouseClick(x, y, btn, lastX, lastY, lastM, newGame);
                    }
                });
                //sets board co-ordinates to the buttons 
                board[i][j] = btn;
                //adds newly constructed grid of buttons to the board
                root.add(board[i][j], i, j);
            }
        }
        return root;
    }

    /**
     * Method for when right mouse button is pressed
     *
     * @param x
     * @param y
     * @param btn
     * @param lastX
     * @param lastY
     * @param lastM
     * @param newGame
     * @return appropriate action for right mouse click
     */
    private void setUpRightMouseClick(int x, int y, Button btn, int lastX, int lastY, int lastM, NewGame newGame) {
        game.mark(x, y);

        if (game.getMineTile(x, y).isMarked()) {
            btn.setText("?");
            flags++;
            flagNo.setText(String.valueOf(flags));
            if (game.areAllMinesRevealed()) {
                int time = timer.getTmp();
                Alert win = new Alert(AlertType.CONFIRMATION);
                DialogPane dialogPane = win.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("myDialogs.css").toExternalForm());
                dialogPane.getStyleClass().add("myDialog");
                Stage stage = (Stage) win.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("minesweeper.jpg"));
                win.setTitle("Minesweeper by 198397");
                win.setHeaderText("You have marked all the mines! " + "you did it in " + timer.getTmp() + " seconds.");
                win.setContentText("Choose your option.");
                win.setGraphic(new ImageView(this.getClass().getResource("happyface.png").toString()));

                ButtonType playagain = new ButtonType("Play again");
                ButtonType changeDifficulty = new ButtonType("Change Difficulty");
                ButtonType cancel = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);

                win.getButtonTypes().setAll(playagain, changeDifficulty, cancel);

                bindAlertButtons(win, playagain, lastX, lastY, lastM, changeDifficulty, newGame);

            }
        } else if (!game.getMineTile(x, y).isRevealed()) {
            btn.setText("");
            flags--;
            flagNo.setText(String.valueOf(flags));
        }

    }

    /**
     * Method for when left mouse button is pressed
     *
     * @param x
     * @param y
     * @param btn
     * @param lastX
     * @param lastY
     * @param lastM
     * @param newGame
     * @return appropriate action for left mouse click
     */
    private void setUpLeftButtonClick(int x, int y, Button btn, int lastX, int lastY, int lastM, NewGame newGame) {
        if (!game.getMineTile(x, y).isMarked()) {
            if (!game.step(x, y)) {
                Alert loss = new Alert(AlertType.CONFIRMATION);
                DialogPane dialogPane = loss.getDialogPane();
                dialogPane.getStylesheets().add(
                        getClass().getResource("myDialogs.css").toExternalForm());
                dialogPane.getStyleClass().add("myDialog");
                Stage stage = (Stage) loss.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("minesweeper.jpg"));
                loss.setTitle("Minesweeper by 198397");
                loss.setHeaderText("You hit a mine! GAME OVER" + " you lasted " + timer.getTmp() + " seconds.");
                loss.setContentText("Choose your option.");
                loss.setGraphic(new ImageView(this.getClass().getResource("sadface.png").toString()));

                ButtonType retry = new ButtonType("Retry");
                ButtonType changeDifficulty = new ButtonType("Change Difficulty");
                ButtonType cancel = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);

                loss.getButtonTypes().setAll(retry, changeDifficulty, cancel);

                bindAlertButtons(loss, retry, lastX, lastY, lastM, changeDifficulty, newGame);

            } else {
                btn.setText(game.getMineTile(x, y).toString());
                btn.setStyle("-fx-border-radius: 5;-fx-border-width: 2 2 2 2;-fx-border-color:lightgray; -fx-background-color: darkgray;");
            }

        }
    }

    /**
     * Universal method for specific buttons
     *
     * @param x
     * @param y
     * @param btn
     * @param lastX
     * @param lastY
     * @param lastM
     * @param newGame
     * @param changeDifficulty
     * @return appropriate action button clicked
     */
    private void bindAlertButtons(Alert win, ButtonType retry, int lastX, int lastY, int lastM, ButtonType changeDifficulty, NewGame newGame) {
        Optional<ButtonType> result = win.showAndWait();
        // user choses retry
        if (result.get() == retry) {
            root.getChildren().clear();
            root.getChildren().add(createBoard(lastX, lastY, lastM));
            primaryStage.sizeToScene();
            // user choses changeDifficulty
        } else if (result.get() == changeDifficulty) {
            newGame.showStage();
            xsize = newGame.getXsize();
            ysize = newGame.getYsize();
            mines = newGame.getMines();
            root.getChildren().clear();
            root.getChildren().add(createBoard(xsize, ysize, mines));
            primaryStage.sizeToScene();
            //  user choses CANCEL or closed the dialog
        } else {
            Platform.exit();

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
