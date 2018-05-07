/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author biidz
 */
public class ComputerManagerController implements Initializable {

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField producer;

    @FXML
    private TextField yearmaking;

    @FXML
    private TextField price;

    @FXML
    private Button save;
    
    @FXML
    private TableView tableComputer;

    @FXML
    TableView table = new TableView<Computer>();

    @FXML
    public void checkValue() {
        if (!id.getText().trim().isEmpty() && !name.getText().trim().isEmpty() && !producer.getText().trim().isEmpty() && !yearmaking.getText().trim().isEmpty() && !price.getText().trim().isEmpty()) {
            save.setDisable(false);
        } else if (id.getText().trim().isEmpty() || name.getText().trim().isEmpty() || producer.getText().trim().isEmpty() || yearmaking.getText().trim().isEmpty() || price.getText().trim().isEmpty()) {
            save.setDisable(true);
        }
    }

    @FXML
    ComputerModel cm = new ComputerModel();

    @FXML
    public void saveToDb() {
        int idCp = Integer.parseInt(id.getText());
        String nameCp = name.getText();
        String producerCp = producer.getText();
        int yearmakingCp = Integer.parseInt(yearmaking.getText());
        int priceCp = Integer.parseInt(price.getText());
        Computer computer = new Computer(idCp, nameCp, producerCp, yearmakingCp, priceCp);
        cm.saveToDatabase(computer);
    }

    @FXML
    public void getTable() {
        ArrayList<Computer> cp = cm.getTableDb();
        TableColumn idComputer = new TableColumn("Computer ID");
        TableColumn nameComputer = new TableColumn("Computer Name");
        TableColumn producerComputer = new TableColumn("Producer");
        TableColumn ymComputer = new TableColumn("Year Making");
        TableColumn priceComputer = new TableColumn("Price");
        tableComputer.getColumns().addAll(idComputer, nameComputer, producerComputer, ymComputer, priceComputer);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
