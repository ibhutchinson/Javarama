package Javarama_local;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * Controller.java
 *
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
public class Controller {

    @FXML private Button loginButton;
    @FXML private Button loginButtonSubmit;
    @FXML private Button next;
    @FXML private Button start;
    @FXML private Button previous;
    @FXML private Button check;
    @FXML private Button primitiveDataStart;
    @FXML private Button registerButton;
    @FXML private Label lessonTitle;
    @FXML private Label questionLabel;
    @FXML private Label correctIncorrect;
    @FXML private Label usernameOrPassIncorrect;
    @FXML private Label usernameTaken;
    @FXML private Label badCode;
    @FXML private Label badPassword;
    @FXML private Label lessonOne;
    @FXML private Label lessonTwo;
    @FXML private Label lessonThree;
    @FXML private Label lessonFour;
    @FXML private Label lessonFive;
    @FXML private Label lessonSix;
    @FXML private Label lessonSeven;
    @FXML private Label lessonEight;
    @FXML private TextField answerField;
    @FXML private TextField uname;
    @FXML private TextField password;
    @FXML private TextField newUsername;
    @FXML private TextField newPassword;
    @FXML private TextField teacherCode;
    private ArrayList<lessonQuestion> lessonQuestions;
    private int questionCount;
    private SHA sha;
    private DatabaseConnect dbc;
    private Stage prevStage;
    private PrimaryWindow primaryWindow;
    private boolean isStartClicked;

    public Controller() throws Exception {
        isStartClicked = false;
        loginButton = new Button();
        loginButtonSubmit = new Button();
        check = new Button();
        next = new Button();
        previous = new Button();
        questionLabel = new Label();
        correctIncorrect = new Label();
        lessonQuestions = new ArrayList<>();
        sha = new SHA();
        primaryWindow = new PrimaryWindow();
        questionCount = 0;
        dbc = new DatabaseConnect();
        lessonTitle = new Label();

    }

    /**
     * The method setPrevStage sets the stage.
     *
     * @param stage Javarama application stage
     */
    public void setPrevStage(Stage stage) {
        this.prevStage = stage;
    }

    /**
     * The loginButton method changes the scene to the login scene from the home scene. The method grabs the
     * primaryWindow and changes the scene.
     * <p>
     * Modified from: http://stackoverflow.com/questions/12804664/how-to-swap-screens-in-a-javafx-application-in-the-controller-class
     *
     * @param event scene change
     */
    @FXML
    public void loginButton(ActionEvent event) {

        try {
            Pane newLoginPane;
            newLoginPane = FXMLLoader.load(getClass().getResource("LoginScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(newLoginPane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }


    }

    /**
     * The register method changes the scene to the register scene from the home scene. The method grabs the
     * primaryWindow and changes the scene.
     * <p>
     * Modified from: http://stackoverflow.com/questions/12804664/how-to-swap-screens-in-a-javafx-application-in-the-controller-class
     *
     * @param event scene change
     */
    @FXML
    public void register(ActionEvent event) {
        try {
            Pane newRegisterPane;
            newRegisterPane = FXMLLoader.load(getClass().getResource("registerScreen.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(newRegisterPane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The registerSubmit method validates the input of username and password and against the database. If the database
     * returns false then the new user is inserted into the database and the scene will be changed to the login screen.
     * If username does not match or the use already exists then error messages are displayed.
     *
     * @param event scene change
     */
    @FXML
    public void registerSubmit(ActionEvent event) {
        usernameTaken.setText(null);
        badCode.setText(null);
        badPassword.setText(null);
        try {
            if ((newUsername.getText() != null && newPassword.getText() != null) && ((dbc.userAlreadyExists(newUsername.getText())) != true)
                    && Pattern.matches("[0-9]+", teacherCode.getText()) && Pattern.matches("[a-zA-Z]+", newUsername.getText()) && (dbc.codeAlreadyExists(teacherCode.getText()) != true)) {
                dbc.newUser(sha.SHAHash(newPassword.getText()), newUsername.getText(), Integer.parseInt(teacherCode.getText()));
                Pane newLoginPane;
                newLoginPane = FXMLLoader.load(getClass().getResource("LoginScreenLayout.fxml"));
                Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryWindow.setScene(new Scene(newLoginPane));
            } else {
                if (!(Pattern.matches("[a-zA-Z]+", newUsername.getText())) || (dbc.userAlreadyExists(newUsername.getText())) == true) {
                    usernameTaken.setText("Username taken or contains non A to Z character");
                }
                if (!(Pattern.matches("[0-9]+", teacherCode.getText()))) {
                    badCode.setText("Invalid Code");
                }
                if (dbc.codeAlreadyExists(teacherCode.getText()) == true) {
                    badCode.setText("Code already used");
                }
                if (newPassword.getText().isEmpty()) {
                    badPassword.setText("Invalid Password");
                }

            }
        } catch (MySQLSyntaxErrorException sql) {

        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The loginButtonSubmit method handles the validation of the username and password. If the username and password
     * exists then the scene is changed. If the username and password do not exist and error is displayed to the
     * user. If a network error is produced the scene will
     * change to an error scene.
     *
     * @param event scene change
     */
    @FXML
    public void loginButtonSubmit(ActionEvent event) {
        try {
            if (dbc.isUserFound(sha.SHAHash(password.getText()), uname.getText())) {
                Pane exercisePane;
                exercisePane = FXMLLoader.load(getClass().getResource("ExerciseScreenLayout.fxml"));
                Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryWindow.setScene(new Scene(exercisePane));

            } else {
                usernameOrPassIncorrect.setText("Incorrect username or password");
            }
        } catch (MySQLSyntaxErrorException sql) {

        } catch (Exception e) {
            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }

    }

    /**
     * The logoutButton method changes the scene back to the home screen. If a network error is produced the scene will
     * change to an error scene.
     *
     * @param event scene change
     */
    @FXML
    public void logoutButton(ActionEvent event) {
        try {
            Pane logoutPane;
            logoutPane = FXMLLoader.load(getClass().getResource("LogoutScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(logoutPane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The method home changes the scene to the home scene. If a network error is produced the scene will
     * change to an error scene.
     *
     * @param event scene change
     */
    @FXML
    public void home(ActionEvent event) {
        try {
            Pane homePane;
            homePane = FXMLLoader.load(getClass().getResource("HomeScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(homePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The inExerciseOne method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseOne(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(1);
            lessonBuilder.lessonTitleData = lessonOne.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {
            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }

    }

    /**
     * The inExerciseTwo method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseTwo(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(2);
            lessonBuilder.lessonTitleData = lessonTwo.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }

    }

    /**
     * The inExerciseThree method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseThree(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(3);
            lessonBuilder.lessonTitleData = lessonThree.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }

    }

    /**
     * The inExerciseFour method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseFour(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(4);
            lessonBuilder.lessonTitleData = lessonFour.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }

    }

    /**
     * The inExerciseFive method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseFive(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(5);
            lessonBuilder.lessonTitleData = lessonFive.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The inExerciseSix method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseSix(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(6);
            lessonBuilder.lessonTitleData = lessonSix.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The inExerciseSeven method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseSeven(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(7);
            lessonBuilder.lessonTitleData = lessonSeven.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The inExerciseEight method once being clicked will attempt to pull the lesson from the from the database. If there
     * is an network communication issue and error will be displayed.
     *
     * @param event scene change
     */
    @FXML
    public void inExerciseEight(ActionEvent event) {
        try {
            lessonBuilder.currentLesson = dbc.pullLesson(8);
            lessonBuilder.lessonTitleData = lessonEight.getText();
            Pane inExercisePane;
            inExercisePane = FXMLLoader.load(getClass().getResource("InExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(inExercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }

    }

    /**
     * The exerciseScreen method changes the scene to the exercise screen. If a network error is produced the scene will
     * change to an error scene.
     *
     * @param event scene change
     * @throws Exception
     */
    @FXML
    public void exerciseScreen(ActionEvent event) {
        try {
            Pane exercisePane;
            exercisePane = FXMLLoader.load(getClass().getResource("ExerciseScreenLayout.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(exercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The exerciseScreenPTwo method changes the scene to the second exercise screen.
     * If a network error is produced the scene will change to an error scene.
     *
     * @param event scene change
     * @throws Exception
     */
    @FXML
    public void exerciseScreenPTwo(ActionEvent event) throws Exception {
        try {
            Pane exercisePane;
            exercisePane = FXMLLoader.load(getClass().getResource("ExerciseScreenLayoutP2.fxml"));
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(exercisePane));
        } catch (Exception e) {

            StackPane stackPane = new StackPane();
            Image neterror = new Image("Javarama_local/Images/nerror.jpeg");
            ImageView imageView = new ImageView(neterror);
            stackPane.getChildren().add(imageView);
            Stage primaryWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryWindow.setScene(new Scene(stackPane, 1280, 720));
        }
    }

    /**
     * The start method once clicked will load the first question and the title of the lesson to the lesson scene.
     */
    @FXML
    public void start() {

        questionLabel.setText(lessonBuilder.currentLesson.get(0).getQuestion());
        lessonTitle.setText(lessonBuilder.lessonTitleData);
        start.setVisible(false);
        start.setDisable(true);
        isStartClicked = true;

    }

    /**
     * The next method will increment the question count and load the next question to the scene.
     */
    @FXML
    public void next() {
        if ((questionCount < lessonBuilder.currentLesson.size()) && (questionCount != lessonBuilder.currentLesson.size() - 1) && isStartClicked == true) {
            questionCount++;
            questionLabel.setText(lessonBuilder.currentLesson.get(questionCount).getQuestion());
            answerField.clear();
            correctIncorrect.setText("");
        }
    }

    /**
     * The previous method will decrement the question count and load the previous question to the scene
     */
    @FXML
    public void previous() {
        if ((questionCount < lessonBuilder.currentLesson.size()) && (questionCount > 0) && isStartClicked == true) {
            questionCount--;
            questionLabel.setText(lessonBuilder.currentLesson.get(questionCount).getQuestion());
            answerField.clear();
            correctIncorrect.setText("");
        }
    }

    /**
     * The check method gets the information from the answer textfield and compares the input against the answer from the
     * current question in the currentLesson.
     */
    @FXML
    public void check() {
        answerField.setText(answerField.getText().replaceAll("\\s+", ""));
        if ((isStartClicked == true) && answerField.getText().toUpperCase().equals(lessonBuilder.currentLesson.get(questionCount).getAnswer())) {
            correctIncorrect.setText("Correct!");
        } else {
            if (isStartClicked)
                correctIncorrect.setText("Incorrect!");
        }

    }
}
