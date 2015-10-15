package controllers;

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
import org.controlsfx.control.MasterDetailPane;
import org.controlsfx.control.PopOver;




import java.net.URL;
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
    private TableColumn<Message,String> sentDateCol;
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
    private ComboBox<SubDepartment> subDepartmentComboBox;
    @FXML
    private ComboBox<Department> departmentComboBox;
    private ObservableList<Department> departments;

    @FXML
    private TextField titleText;

    @FXML
    private TextArea messageTxt;

    private Image imageNewMsg;


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
                sendMessage();
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

        setDetailsValue(null);
        masterTable.getSelectionModel().selectedItemProperty().addListener((
                (observable, oldValue, newValue) -> setDetailsValue(newValue)
                ));
        masterDetailPane.setMasterNode(masterTable);
        masterDetailPane.setDetailNode(box);
          }

    private void sendMessage() {

        if (titleText.getText().isEmpty()||messageTxt.getText().isEmpty()){
            failAlert("Can Not Send (Mpunga Kitenesi)");
        }
        else {
            successAlert();
        }

    }


    public void setDepartments(){
        departments= FXCollections.observableArrayList();
        departments.addAll(DepartmentsManager.getInstance().getDepartments());
        departmentComboBox.setItems(departments);
        departmentComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Department>() {
            @Override
            public void changed(ObservableValue<? extends Department> observable, Department oldValue, Department newValue) {
                System.out.println(newValue.getDepartmentName());

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
