package com.example.app1;

import com.example.app1.customer_and_ordering.Order;
import com.example.app1.parts.CPU;
import com.example.app1.parts.GPU;
import com.example.app1.customer_and_ordering.User;
import com.example.app1.parts.Mobo;
import com.example.app1.parts.Part;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import static com.example.app1.util.InputValidator.*;

import java.util.List;

public class HelloController {

    @FXML
    private TextField userField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label loginResponse;
    @FXML
    private TextField userRegisterField;
    @FXML
    private TextField passwordRegisterField;
    @FXML
    private TextField emailRegisterField;
    @FXML
    private Label registerResponse1;
    @FXML
    private Label registerResponse2;
    @FXML
    private Label registerResponse3;
    @FXML
    private Button registerButton;
    @FXML
    private TableColumn<Order,String> customerNameColumn;
    @FXML
    private TableColumn<Order,Integer> customerIdPartColumn;
    @FXML
    private TableColumn<Order,String> customerNamePartColumn;
    @FXML
    private TableColumn<Order,Integer> customerQuantityPartColumn;
    @FXML
    private TableColumn<Order,String> customerStatusPartColumn;
    @FXML
    private TableView<Order> customerTable;

    @FXML
    private Button loginButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Spinner<Integer> amountToBuy;
    @FXML
    private Label buyResponse;
    @FXML
    private Label buyResponse2;
    @FXML
    private Button buyButton;

    @FXML
    private Button loadParts;
    @FXML
    private TableView<Part> partTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, String> partBrandColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;
    @FXML
    private TableColumn<Part, String> partCategoryColumn;


    @FXML
    private Button loadGpu;
    @FXML
    private TableView<GPU> gpuTable;
    @FXML
    private TableColumn<GPU, String> gpuNameColumn;
    @FXML
    private TableColumn<GPU, Integer> gpuVramColumn;
    @FXML
    private TableColumn<GPU, Integer> gpuCoreClockColumn;
    @FXML
    private TableColumn<GPU, Integer> gpuPowerColumn;
    @FXML
    private TableColumn<GPU, String> gpuInterfaceColumn;

    @FXML
    private Button loadCpu;
    @FXML
    private TableView<CPU> cpuTable;
    @FXML
    private TableColumn<CPU, String> cpuNameColumn;
    @FXML
    private TableColumn<CPU, Integer> cpuCoresColumn;
    @FXML
    private TableColumn<CPU, Integer> cpuThreadsColumn;
    @FXML
    private TableColumn<CPU, Double> cpuBaseClockColumn;
    @FXML
    private TableColumn<CPU, Double> cpuBoostClockColumn;
    @FXML
    private TableColumn<CPU, String> cpuSocketColumn;
    @FXML
    private TableColumn<CPU, Integer> cpuTdpColumn;

    @FXML
    private Button loadMobo;
    @FXML
    private TableView<Mobo> motherboardTable;
    @FXML
    private TableColumn<Mobo, String> moboNameColumn;
    @FXML
    private TableColumn<Mobo, String> moboSocketColumn;
    @FXML
    private TableColumn<Mobo, String> moboChipsetColumn;
    @FXML
    private TableColumn<Mobo, String> moboFormFactorColumn;
    @FXML
    private TableColumn<Mobo, Integer> moboRamSlotsColumn;
    @FXML
    private TableColumn<Mobo, Integer> moboMaxRamColumn;
    @FXML
    private TableColumn<Mobo, Boolean> moboWifiColumn;
    @FXML
    private ComboBox<Part> partCombo;
    @FXML
    private Tab loginTab;
    @FXML
    private Tab registerTab;
    @FXML
    private Tab historyTab;
    @FXML
    private Tab buyTab;
    @FXML
    private Tab detailsTab;
    @FXML
    private Tab inventoryTab;
    @FXML
    private Tab insertNewItemTab;
    @FXML
    private ComboBox<Part> addExistingStock;
    @FXML
    private Spinner<Integer> addExistingStockQuantity;
    @FXML
    private Button addToExistingStock;
    @FXML
    private Button seeAllParts;
    @FXML
    private Label addToExistingResponse1;
    @FXML
    private Label addToExistingResponse2;
    @FXML
    private TableView<Part> allPartTable;
    @FXML
    private TableColumn<Part, Integer> allPartIdColumn;
    @FXML
    private TableColumn<Part, String> allPartNameColumn;
    @FXML
    private TableColumn<Part, String> allPartBrandColumn;
    @FXML
    private TableColumn<Part, Double> allPartPriceColumn;
    @FXML
    private TableColumn<Part, Integer> allPartStockColumn;
    @FXML
    private TableColumn<Part, String> allPartCategoryColumn;

    @FXML
    private ComboBox<String> newItemCategory;
    @FXML
    private TextField newItemName;
    @FXML
    private TextField newItemBrand;
    @FXML
    private TextField newItemPrice;
    @FXML
    private TextField newItemAdditionalInfo1;
    @FXML
    private Label newItemAdditionalInfoLabel1;
    @FXML
    private TextField newItemAdditionalInfo2;
    @FXML
    private Label newItemAdditionalInfoLabel2;
    @FXML
    private TextField newItemAdditionalInfo3;
    @FXML
    private Label newItemAdditionalInfoLabel3;
    @FXML
    private TextField newItemAdditionalInfo4;
    @FXML
    private Label newItemAdditionalInfoLabel4;
    @FXML
    private TextField newItemAdditionalInfo5;
    @FXML
    private Label newItemAdditionalInfoLabel5;
    @FXML
    private TextField newItemAdditionalInfo6;
    @FXML
    private Label newItemAdditionalInfoLabel6;
    @FXML
    private Button newItemAdd;

    @FXML
    private void initialize(){
        amountToBuy.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));
        addExistingStockQuantity.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1));
        buyTab.setDisable(true);
        historyTab.setDisable(true);
        detailsTab.setDisable(true);
        inventoryTab.setDisable(true);
        logoutButton.setDisable(true);
        insertNewItemTab.setDisable(true);
        newItemCategory.getItems().addAll("GPU","CPU","Motherboard");
        newItemAdd.setDisable(true);
        setDeliveredButton.setVisible(false);
    }
    private String currentUser;
    private int currentLoggedInId=0;
    @FXML
    private void onLoginButtonClick() {
        String user = userField.getText();
        String pass = passwordField.getText();
        if(user.length()==0||pass.length()==0) {
            loginResponse.setText("Empty field/s!");
            loginResponse.setTextFill(Color.RED);
            return;
        }
        String r = User.login(user,pass);
        if(r==null){
            loginResponse.setText("Invalid name/password");
            loginResponse.setTextFill(Color.RED);
            buyTab.setDisable(true);
            historyTab.setDisable(true);
            detailsTab.setDisable(true);
            inventoryTab.setDisable((true));
            insertNewItemTab.setDisable(true);
            return;
        }
        loginResponse.setText("Welcome " + user + "!");
        loginResponse.setTextFill(Color.WHITE);
        currentLoggedInId = Integer.parseInt(r.substring(1));
        if(r.charAt(0)=='a'){
            buyTab.setDisable(false);
            insertNewItemTab.setDisable(false);
            setLoggedin(user, historyTab, detailsTab, inventoryTab);
            setDeliveredButton.setVisible(true);
        }
        else{
            setLoggedin(user, buyTab, historyTab, detailsTab);
        }

    }

    private void setLoggedin(String user, Tab buyTab, Tab historyTab, Tab detailsTab) {
        buyTab.setDisable(false);
        historyTab.setDisable(false);
        detailsTab.setDisable(false);
        logoutButton.setDisable(false);
        registerTab.setDisable(true);
        loginButton.setDisable(true);
        userField.setDisable(true);
        passwordField.setDisable(true);
        currentUser=user;
        loginResponse.setText("Welcome " + user + "!");
        loginResponse.setTextFill(Color.WHITE);
    }

    @FXML
    private void onRegisterButtonClick(){
        String name = userRegisterField.getText();
        String pass = passwordRegisterField.getText();
        String email = emailRegisterField.getText();
        System.out.println(name + " " + pass + " " + email);
        User user = new User();
        String r1,r2,r3;
        r1=user.setName(name);
        registerResponse1.setText(r1);
        registerResponse1.setTextFill(Color.RED);
        registerResponse2.setTextFill(Color.RED);
        registerResponse3.setTextFill(Color.RED);
        r2 =user.setPass(pass);
        registerResponse2.setText(r2);
        r3=user.setEmail(email);
        registerResponse3.setText(r3);
        if(r1==null&&r2==null&&r3==null){
            int a = user.newUser(name,email,pass);
            if(a==1){
                registerResponse1.setTextFill(Color.WHITE);
                registerResponse1.setText("Register successful!");
                return;
            }
            registerResponse1.setTextFill(Color.RED);
            if(a==-1){
                registerResponse1.setText("Username or email already registered");
                return;
            }
            registerResponse1.setText("Something went wrong, try again");
        }
    }

    @FXML
    private void onLogoutButtonClick() {
        if(!logoutButton.isDisabled()) {
            loginResponse.setText("Successfully logged out!");
            loginResponse.setTextFill(Color.RED);
            registerTab.setDisable(false);
            logoutButton.setDisable(true);
            buyTab.setDisable(true);
            historyTab.setDisable(true);
            detailsTab.setDisable(true);
            inventoryTab.setDisable((true));
            insertNewItemTab.setDisable(true);
            userField.setText("");
            passwordField.setText("");
            currentUser=null;
            currentLoggedInId=0;
            customerTable.getItems().clear();
            loginButton.setDisable(false);
            userField.setDisable(false);
            passwordField.setDisable(false);
            setDeliveredButton.setVisible(false);
        }
    }

    @FXML
    private void onRefreshButtonClick() {
        customerNameColumn.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getUsername())
        );
        customerIdPartColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );

        customerNamePartColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPart())
        );
        customerQuantityPartColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getQuantity()).asObject()
        );
        customerStatusPartColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getStatus())
        );
        loadOrders();
    }

    private void loadOrders() {
        List<Order> list = Order.getOrders(currentUser,!inventoryTab.isDisabled());
        customerTable.getItems().setAll(list);
    }

    @FXML
    private void onLoadPartsClick() {
        partIdColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );
        partNameColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getName())
        );
        partBrandColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getBrand())
        );
        partPriceColumn.setCellValueFactory(
                cellData-> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject()
        );
        partStockColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject()
        );
        partCategoryColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getCategory())
        );
        loadParts();
    }

    private void loadParts() {
        Part.reloadParts();
        List<Part> list = Part.getAvailableParts();
        partTable.getItems().setAll(list);

        partCombo.setItems(partTable.getItems());
    }

    private void onLoadAllParts(){
        Part.reloadAllParts();
        List<Part> list = Part.getAllParts();
        allPartTable.getItems().setAll(list);

        addExistingStock.setItems(allPartTable.getItems());
    }

    @FXML
    private void onLoadExistingStock(){
        allPartIdColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );
        allPartNameColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getName())
        );
        allPartBrandColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getBrand())
        );
        allPartPriceColumn.setCellValueFactory(
                cellData-> new SimpleDoubleProperty(cellData.getValue().getPrice()).asObject()
        );
        allPartStockColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getStock()).asObject()
        );
        allPartCategoryColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getCategory())
        );
        onLoadAllParts();
    }

    @FXML
    private void onAddToExistingStock(){
        Part part = new Part();
        if(addExistingStock.getItems().isEmpty()){
            addToExistingResponse1.setText("Load items first!");
            return;
        }
        if(addExistingStock.getValue()==null){
            addToExistingResponse1.setText("Select an item first!");
            return;
        }
        String name = addExistingStock.getValue().getName();
        int nr = addExistingStockQuantity.getValue();
        String msg1 = part.setStock(nr);
        String msg2 = part.setName(name);
        if(msg1==null&&msg2==null){
            String s = Part.addToStock(name,nr);
            if(s.isEmpty()){
                addToExistingResponse1.setText(s);
                addToExistingResponse1.setTextFill(Color.RED);
                addToExistingResponse2.setTextFill(Color.RED);
                addToExistingResponse2.setText("Try again");
            }
            else{
                addToExistingResponse1.setText(s);
                addToExistingResponse1.setTextFill(Color.WHITE);
                addToExistingResponse2.setTextFill(Color.WHITE);
                addToExistingResponse2.setText(nr + " " + name + " added!");
            }
            return;
        }
        addToExistingResponse1.setText(msg1);
        addToExistingResponse1.setTextFill(Color.RED);
        addToExistingResponse2.setTextFill(Color.RED);
        addToExistingResponse2.setText(msg2);
    }

    @FXML
    private void onLoadGPUClick(){
        gpuNameColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getName())
        );
        gpuVramColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getVram()).asObject()
        );
        gpuCoreClockColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getClock()).asObject()
        );
        gpuPowerColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getPower()).asObject()
        );
        gpuInterfaceColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getInterf())
        );
        loadGPU();
    }
    private void loadGPU(){
        GPU.reloadGPU();
        List<GPU> list = GPU.getGPU();
        gpuTable.getItems().setAll(list);
    }
    @FXML
    private void onLoadCPUClick(){
        cpuNameColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getName())
        );
        cpuCoresColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getCores()).asObject()
        );
        cpuThreadsColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getThreads()).asObject()
        );
        cpuBaseClockColumn.setCellValueFactory(
                cellData-> new SimpleDoubleProperty(cellData.getValue().getBase()).asObject()
        );
        cpuBoostClockColumn.setCellValueFactory(
                cellData-> new SimpleDoubleProperty(cellData.getValue().getBoost()).asObject()
        );
        cpuSocketColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getSocket())
        );
        cpuTdpColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getTdp()).asObject()
        );
        loadCPU();
    }
    private void loadCPU(){
        CPU.reloadCPU();
        List<CPU> list = CPU.getCPU();
        cpuTable.getItems().setAll(list);
    }
    @FXML
    private void onLoadMoboClick(){
        moboNameColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getName())
        );
        moboSocketColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getSocket())
        );
        moboChipsetColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getChipset())
        );
        moboFormFactorColumn.setCellValueFactory(
                cellData-> new SimpleStringProperty(cellData.getValue().getForm())
        );
        moboRamSlotsColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getSlots()).asObject()
        );
        moboMaxRamColumn.setCellValueFactory(
                cellData-> new SimpleIntegerProperty(cellData.getValue().getMax()).asObject()
        );
        moboWifiColumn.setCellValueFactory(
                cellData-> new SimpleBooleanProperty(cellData.getValue().getWifi()).asObject()
        );
        loadMobo();
    }
    private void loadMobo(){
        Mobo.reloadMobo();
        List<Mobo> list = Mobo.getMobo();
        motherboardTable.getItems().setAll(list);
    }

    @FXML
    private void onBuyButtonClick(){
        Part part = partCombo.getValue();
        int amount = amountToBuy.getValue();
        if (part == null) {
            buyResponse.setText("No Part selected");
            buyResponse.setTextFill(Color.RED);
            buyResponse2.setText("Error!");
            buyResponse2.setTextFill(Color.RED);
            return;
        }
        if(amount==0){
            buyResponse.setText("Can't buy 0 items");
            buyResponse.setTextFill(Color.RED);
            buyResponse2.setText("Error!");
            buyResponse2.setTextFill(Color.RED);
            return;
        }
        String s = Part.buyPart(part.getName(),amount);
        if (s.charAt(0) == 'P') {
            buyResponse.setText(part.getName() + " bought! " + s);
            buyResponse.setTextFill(Color.WHITE);
            buyResponse2.setText(s);
            buyResponse2.setTextFill(Color.WHITE);
            String response = Order.newOrder(currentLoggedInId,Part.getPartIdByName(part.getName()),amount);
            if("Fail".equals(response)){
                buyResponse2.setText("Error at saving in db");
            }
            return;
        }
        buyResponse.setText(s);
        buyResponse.setTextFill(Color.RED);
        buyResponse2.setText("Error!");
        buyResponse2.setTextFill(Color.RED);


    }

    @FXML
    private void onNewItemCategoryChanged(){
        String s = newItemCategory.getValue();
        newItemAdd.setDisable(false);
        switch(s){
            case "GPU":
                newItemAdditionalInfoLabel1.setText("Vram GB");
                newItemAdditionalInfoLabel2.setText("Core Clock MHZ");
                newItemAdditionalInfoLabel3.setText("Watts of Power");
                newItemAdditionalInfoLabel4.setText("Interface");
                newItemAdditionalInfoLabel5.setText("");
                newItemAdditionalInfoLabel6.setText("");
                newItemAdditionalInfo5.setDisable(true);
                newItemAdditionalInfo6.setDisable(true);
                break;
            case "CPU":
                newItemAdditionalInfoLabel1.setText("Cores");
                newItemAdditionalInfoLabel2.setText("Threads");
                newItemAdditionalInfoLabel3.setText("Base GHZ");
                newItemAdditionalInfoLabel4.setText("Boost GHZ");
                newItemAdditionalInfoLabel5.setText("Socket");
                newItemAdditionalInfoLabel6.setText("TDP Watts");
                newItemAdditionalInfo5.setDisable(false);
                newItemAdditionalInfo6.setDisable(false);
                break;
            case "Motherboard":
                newItemAdditionalInfoLabel1.setText("Socket");
                newItemAdditionalInfoLabel2.setText("Chipset");
                newItemAdditionalInfoLabel3.setText("Form Factor");
                newItemAdditionalInfoLabel4.setText("Ram slots");
                newItemAdditionalInfoLabel5.setText("Max GB of Ram");
                newItemAdditionalInfoLabel6.setText("Has wifi?");
                newItemAdditionalInfo5.setDisable(false);
                newItemAdditionalInfo6.setDisable(false);
                break;
            default:
                break;
        }
    }

    private void setError(TextField field, boolean error) {
        if (error) {
            field.setStyle(
                    "-fx-border-color: red;" +
                            "-fx-border-width: 2;" +
                            "-fx-background-color: #ff8888;"
            );
        } else {
            field.setStyle("");
        }
    }
    @FXML
    public Label newItemInsertLabel;

    @FXML
    public void onNewItemAdd(){
        if(newItemCategory.getItems().isEmpty()){
            addToExistingResponse1.setText("Load items first!");
            return;
        }
        if(newItemCategory.getValue()==null){
            addToExistingResponse1.setText("Select an item first!");
            return;
        }
        String name,brand,category;
        String textPrice;
        double price = 0;
        String i1,i2,i3,i4,i5,i6;
        name=newItemName.getText();
        if(Part.doesPartExistByName(name)){
            addToExistingResponse1.setText("Part already exists with same name");
            return;
        }
        brand=newItemBrand.getText();
        category = newItemCategory.getValue();
        textPrice=newItemPrice.getText();

        int error=0;
        if (tryParseNonEmptyString(name) == null) {
            error++;
            setError(newItemName,true);
        }
        else{
            setError(newItemName,false);
        }
        if (tryParseNonEmptyString(brand) == null) {
            error++;
            setError(newItemBrand,true);
        }
        else{
            setError(newItemBrand,false);
        }
        if (tryParsePositiveDouble(textPrice) == null) {
            error++;
            setError(newItemPrice,true);
        }
        else{
            setError(newItemPrice,false);
            try {
                price = Double.parseDouble(newItemPrice.getText());
            } catch (NumberFormatException e) {
                // handle invalid input
                System.out.println("Please enter a valid price.");
            }
        }
        i1 = newItemAdditionalInfo1.getText();
        i2 = newItemAdditionalInfo2.getText();
        i3 = newItemAdditionalInfo3.getText();
        i4 = newItemAdditionalInfo4.getText();
        i5 = newItemAdditionalInfo5.getText();
        i6 = newItemAdditionalInfo6.getText();

        switch(category){
            case "GPU":
                if (tryParsePositiveInt(i1) == null) {
                    error++;
                    setError(newItemAdditionalInfo1,true);
                } else {
                    setError(newItemAdditionalInfo1,false);
                }

                if (tryParsePositiveInt(i2) == null) {
                    error++;
                    setError(newItemAdditionalInfo2,true);
                } else {
                    setError(newItemAdditionalInfo2,false);
                }

                if (tryParsePositiveInt(i3) == null) {
                    error++;
                    setError(newItemAdditionalInfo3,true);
                } else {
                    setError(newItemAdditionalInfo3,false);
                }

                if (tryParseNonEmptyString(i4) == null) {
                    error++;
                    setError(newItemAdditionalInfo4,true);
                } else {
                    setError(newItemAdditionalInfo4,false);
                }
                break;

            case "CPU":
                if (tryParsePositiveInt(i1) == null) {
                    error++;
                    setError(newItemAdditionalInfo1,true);
                } else {
                    setError(newItemAdditionalInfo1,false);
                }

                if (tryParsePositiveInt(i2) == null) {
                    error++;
                    setError(newItemAdditionalInfo2,true);
                } else {
                    setError(newItemAdditionalInfo2,false);
                }

                if (tryParsePositiveDouble(i3) == null) {
                    error++;
                    setError(newItemAdditionalInfo3,true);
                } else {
                    setError(newItemAdditionalInfo3,false);
                }

                if (tryParsePositiveDouble(i4) == null) {
                    error++;
                    setError(newItemAdditionalInfo4,true);
                } else {
                    setError(newItemAdditionalInfo4,false);
                }

                if (tryParseNonEmptyString(i5) == null) {
                    error++;
                    setError(newItemAdditionalInfo5,true);
                } else {
                    setError(newItemAdditionalInfo5,false);
                }

                if (tryParsePositiveInt(i6) == null) {
                    error++;
                    setError(newItemAdditionalInfo6,true);
                } else {
                    setError(newItemAdditionalInfo6,false);
                }
                break;

            case "Motherboard":
                if (tryParseNonEmptyString(i1) == null) {
                    error++;
                    setError(newItemAdditionalInfo1,true);
                } else {
                    setError(newItemAdditionalInfo1,false);
                }

                if (tryParseNonEmptyString(i2) == null) {
                    error++;
                    setError(newItemAdditionalInfo2,true);
                } else {
                    setError(newItemAdditionalInfo2,false);
                }

                if (tryParseNonEmptyString(i3) == null) {
                    error++;
                    setError(newItemAdditionalInfo3,true);
                } else {
                    setError(newItemAdditionalInfo3,false);
                }

                if (tryParsePositiveInt(i4) == null) {
                    error++;
                    setError(newItemAdditionalInfo4,true);
                } else {
                    setError(newItemAdditionalInfo4,false);
                }

                if (tryParsePositiveInt(i5) == null) {
                    error++;
                    setError(newItemAdditionalInfo5,true);
                } else {
                    setError(newItemAdditionalInfo5,false);
                }

                if (tryParseBoolean(i6) == null) {
                    error++;
                    setError(newItemAdditionalInfo6,true);
                } else {
                    setError(newItemAdditionalInfo6,false);
                }
                break;

            default:
                break;
        }
        if(error!=0){
            return;
        }
        String s = Part.addNewPartToInventory(name,brand,price,0,category,i1,i2,i3,i4,i5,i6);
        newItemInsertLabel.setText(s);
        if(s.equals("Success")){
            newItemInsertLabel.setTextFill(Color.GREEN);
        }
        else{
            newItemInsertLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private Button setDeliveredButton;
    @FXML
    public void onSetDeliveredButton(){
        Order order = customerTable.getSelectionModel().getSelectedItem();
        if(order==null)
            return;
        if(Order.setDelivered(order)!=0){
            onRefreshButtonClick();
        }
    }
}
