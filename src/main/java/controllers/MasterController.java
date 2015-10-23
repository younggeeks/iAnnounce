package controllers;


//this is awesome for shizzle
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;
import model.*;
import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PopOver;
import org.json.JSONException;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/**
 * Created by samjunior on 10/7/15.
 */
public class MasterController implements Initializable {

    @FXML
    public MasterDetailPane masterDetailPane;

    private TableView<Message> masterTable;
    private TableColumn<Message,Number> indexCol;
    private TableColumn<Message,String> titleCol;
    private TableColumn<Message,LocalDate> sentDateCol;
    private TableColumn<Message,String> messageCol;
    private TableColumn<Message,String> sentByCol;
    private TableColumn<Message,String> sentToCol;
    private Label title=new Label();
    private Label date=new Label();
    private Label messagelbl=new Label();
    private Label sentBy=new Label();
    private VBox box;
    @FXML
    private Tab announcementTab;
    @FXML
    private Tab departmentTab;


    private Image image;

    @FXML
    private Button addMessage;

    private ImageView imageView;
    private ImageView dptImageview;

    @FXML
    private Button resetBtn;
    @FXML
    private Button sendBtn;

    @FXML
    private VBox popBox;


    @FXML
    private ComboBox<Intake>  intakeComboBox;
    @FXML
    private ComboBox<Course> courseComboBox;
    @FXML
    private ComboBox<Department> departmentComboBox;
    private ObservableList<Department> departments;

    @FXML
    private CheckComboBox studentsCheckCombo;



    @FXML
    private TextField titleText;

    @FXML
    private TextArea messageTxt;

    private Image imageNewMsg;

    private String intake;
    private String message;
    private String course;
    String taito;


    public void initialize(URL location, ResourceBundle resources) {
        setDepartments();

        resetBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                clearFields();
            }
        });

        sendBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    sendMessage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        popBox.setVisible(false);
        image=new Image(String.valueOf(getClass().getClassLoader().getResource("images/announce.png")));
        imageView=new ImageView(image);
        announcementTab.setGraphic(imageView);

        departmentTab.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResource("images/department.png").toString())));

imageNewMsg=new Image(getClass().getClassLoader().getResourceAsStream("images/newMessage.png"));


        addMessage.setGraphic(new ImageView(imageNewMsg));

        addMessage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                //for VBOX controlllers from fxml
                popBox.setVisible(true);
                popBox.setMinWidth(500);
                popBox.setMinHeight(400);
                popBox.setPadding(new Insets(29));


                PopOver newMessagePop=new PopOver();
                newMessagePop.setDetachedTitle("Send New Announcement");
                newMessagePop.setAnchorLocation(PopupWindow.AnchorLocation.WINDOW_TOP_LEFT);
                newMessagePop.setOnHiding(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent event) {
                        newMessagePop.setDetached(false);
                    }
                });


                newMessagePop.setContentNode(popBox);
                newMessagePop.show(masterDetailPane);
            }
        });

        masterTable=new TableView();
        indexCol=new TableColumn("#");
        titleCol=new TableColumn("Title");
        messageCol=new TableColumn("Message");
        sentDateCol=new TableColumn("Date");
        sentByCol=new TableColumn("Sent By");
        sentToCol=new TableColumn("To");
        indexCol.setSortable(false);


        box=new VBox(10);
        box.getChildren().addAll(title,date,messagelbl,sentBy);
        indexCol.setCellValueFactory(column -> new ReadOnlyObjectWrapper<Number>(masterTable.getItems().indexOf(column.getValue())));
        masterTable.getColumns().addAll(indexCol, titleCol, messageCol, sentByCol, sentToCol, sentDateCol);
        masterTable.setItems(MessagesManager.getInstance().getMessages());
        titleCol.setCellValueFactory(data->data.getValue().titleProperty());
        messageCol.setCellValueFactory(data -> data.getValue().bodyProperty());
        sentToCol.setCellValueFactory(data -> data.getValue().recepientProperty());
        sentByCol.setCellValueFactory(data -> data.getValue().senderProperty());
        sentDateCol.setCellValueFactory(data->data.getValue().dateProperty());

        setDetailsValue(null);
        masterTable.getSelectionModel().selectedItemProperty().addListener((
                (observable, oldValue, newValue) -> setDetailsValue(newValue)
                ));
        masterDetailPane.setMasterNode(masterTable);
        masterDetailPane.setDetailNode(box);
          }

    private void sendMessage() throws Exception {

        if (titleText.getText().isEmpty()||messageTxt.getText().isEmpty()){
            failAlert("Please fill in all the details");
        }
        else {
            course=courseComboBox.getSelectionModel().getSelectedItem().getName();
            intake=intakeComboBox.getSelectionModel().getSelectedItem().getName();
            message=messageTxt.getText();
           taito=titleText.getText();


            String sendingResponse=null;

            String uri=MessagesManager.getInstance().sendSms(course,intake,"Geeks",message);

           // String url="http://localhost:8888/kiuMessagesApi/public/api/messages/send/"+intake+"/"+course+"/"+taito+"/"+message;


        }

    }

    public void setDepartments(){
        departments= FXCollections.observableArrayList();
        departments.addAll(DepartmentsManager.getInstance().getDepartments());
        departmentComboBox.setPromptText("---Select Department----");
        departmentComboBox.setItems(departments);
        departmentComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {


                switch (newValue.getDepartmentName()){
                    case "Computer":
                        courseComboBox.setItems(CoursesManager.getInstance().getComputerCourses());
                        courseComboBox.setPromptText("---Select Course----");
                        intakeComboBox.setItems(CoursesManager.getInstance().getIntakes());
                        studentsCheckCombo=new CheckComboBox<>(StudentsManager.getInstance().getStudents());
                        intakeComboBox.setPromptText("----Select Intake----");
                        break;
                    case "Health Science":
                        courseComboBox.setItems(CoursesManager.getInstance().getShsCourses());
                        courseComboBox.setPromptText("---Select Course----");
                        intakeComboBox.setItems(CoursesManager.getInstance().getIntakes());

                        intakeComboBox.setPromptText("----Select Intake----");
                        break;
                }

            }
        });
        courseComboBox.setCellFactory(
                (comboBox)->{
                    return  new ListCell<Course>(){

                        @Override
                        protected void updateItem(Course item, boolean empty) {
                            super.updateItem(item, empty);

                            if (item==null || empty){
                                setText(null);
                            }else {
                                setText(item.getName());
                            }
                        }

                    };
                }
        );
        intakeComboBox.setCellFactory( (comboBox)->{
            return  new ListCell<Intake>(){

                @Override
                protected void updateItem(Intake item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item==null || empty){
                        setText(null);
                    }else {
                        setText(item.getName());
                    }
                }

            };
        });
        intakeComboBox.setConverter(new StringConverter<Intake>() {
            @Override
            public String toString(Intake object) {
                return object.getName();
            }

            @Override
            public Intake fromString(String string) {
                return null;
            }
        });
        courseComboBox.setConverter(new StringConverter<Course>() {
            @Override
            public String toString(Course object) {
                return object.getName();
            }

            @Override
            public Course fromString(String string) {
                return null;
            }
        });
        departmentComboBox.setCellFactory((comboBox)->{
            return  new ListCell<Department>(){

                @Override
                protected void updateItem(Department item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item==null || empty){
                        setText(null);
                    }else {
                        setText(item.getDepartmentName());
                    }
                }

            };
        });
        departmentComboBox.setConverter(new StringConverter<Department>() {
            @Override
            public String toString(Department object) {
                if (object==null){
                    return  null;
                }else {
                    return object.getDepartmentName();
                }
            }

            @Override
            public Department fromString(String string) {
                return null;
            }
        });
    }

    public void successAlert(){
        Alert success=new Alert(Alert.AlertType.INFORMATION);
        success.setTitle("Message Sent !");
        success.setHeaderText("Message Has Been Sent Successfully");
        success.setContentText("The messsage Has been sent to Their Respective Recepient");
        success.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/sentOk.png"))));
        success.showAndWait();
    }
    public void failAlert(String s){
        Alert success=new Alert(Alert.AlertType.ERROR);
        success.setTitle("Message Not Sent !");
        success.setHeaderText("There Was An Error");
        success.setContentText(s);
       // success.setGraphic(new ImageView(new Image(getClass().getClassLoader().getResourceAsStream("images/sentOk.png"))));
        success.showAndWait();
    }

    private void clearFields() {

        titleText.setText("");
        messageTxt.setText("");

    }

    public void setDetailsValue(Message message){
        if (message!=null){
            title.setText(message.getTitle());
            messagelbl.setText(message.getBody());
            date.setText(message.getDate().toString());
            sentBy.setText(message.getSender());
        }else {
            title.setText("");
            messagelbl.setText("");
            date.setText("");
            sentBy.setText("");
        }

    }

}
