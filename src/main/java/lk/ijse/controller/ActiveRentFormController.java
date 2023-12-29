package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.ActiveRentBO;
import lk.ijse.bo.custom.CustomerBO;
import lk.ijse.bo.custom.RentBO;
import lk.ijse.controller.util.CustomAlert;
import lk.ijse.dao.custom.RentDAO;
import lk.ijse.dto.ActiveRentDto;
import lk.ijse.dto.CategoryDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.RentDto;
import lk.ijse.dto.tm.ActiveRentTM;
import lk.ijse.dto.tm.CategoryTM;

import java.time.LocalDate;
import java.util.List;

public class ActiveRentFormController {
    @FXML
    private TableView<ActiveRentTM> activeRentTbl;

    @FXML
    private TableColumn<?, ?> balanceClm;

    @FXML
    private Label balanceLbl;

    @FXML
    private Button closeBtn;

    @FXML
    private Label idLbl;

    @FXML
    private TableColumn<?, ?> nameClm;

    @FXML
    private Label nameLbl;

    @FXML
    private TableColumn<?, ?> numClm;

    @FXML
    private Label numLbl;

    @FXML
    private TableColumn<?, ?> overdueClm;

    @FXML
    private Label overdueLbl;

    @FXML
    private TableColumn<?, ?> rentDateClm;

    @FXML
    private Label rentDateLbl;

    @FXML
    private TableColumn<?, ?> rentIdClm;

    @FXML
    private TableColumn<?, ?> returnClm;

    @FXML
    private Label returnLbl;
    private final ActiveRentBO activeRentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.ACTIVE_RENT);
    private final CustomerBO customerBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.CUSTOMER);
    private final RentBO rentBO = BOFactory.getInstance().getBO(BOFactory.BOTypes.RENT);

    @FXML
    void closeBtnOnAction(ActionEvent event) {
        try {
            RentDto rentDto = rentBO.getRent(idLbl.getText());
            String resp = rentBO.closeRent(rentDto);
            tableListener();
            loadReservations();
            new CustomAlert(Alert.AlertType.CONFIRMATION,"Close","Close Rent","Close Rent Successfully!").show();
        } catch (Exception e) {
            new CustomAlert(Alert.AlertType.ERROR,"Close","Close Rent","Close Rent Failed!").show();
        }
    }

    @FXML
    void initialize() {
        setCellValueFactory();
        loadReservations();
        tableListener();
    }

    private void loadReservations() {
        ObservableList<ActiveRentTM> activeRentTMS = FXCollections.observableArrayList();
        for (RentDto rentDto : activeRentBO.getAllActive()) {
            CustomerDto customerDto = customerBO.getCustomer(rentDto.getCustomerId());
            String overDue = checkOverDue(rentDto.getEndDate());
            double balance = rentDto.getDepositAmount() - rentDto.getAdvancePayment();
            activeRentTMS.add(new ActiveRentTM(
                    rentDto.getId(),
                    customerDto.getFirstName() + " " + customerDto.getLastName(),
                    rentDto.getCarNumber(),
                    String.valueOf(rentDto.getStartDate()),
                    String.valueOf(rentDto.getEndDate()),
                    overDue,
                    String.valueOf(balance)
                    ));
        }
        activeRentTbl.setItems(activeRentTMS);
    }

    private String checkOverDue(LocalDate endDate) {
        LocalDate today = LocalDate.now();
        if (today.isAfter(endDate)) {
            return "Yes";
        } else {
            return "No";
        }
    }
    private void tableListener() {
        activeRentTbl.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setData((ActiveRentTM)newValue);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    private void setData(ActiveRentTM activeRentTM) {
        try {
            idLbl.setText(activeRentTM.getRentId());
            nameLbl.setText(activeRentTM.getCusName());
            numLbl.setText(activeRentTM.getCarNumber());
            rentDateLbl.setText(activeRentTM.getRentDate());
            returnLbl.setText(activeRentTM.getRentDate());
            overdueLbl.setText(activeRentTM.getOverView());
            balanceLbl.setText(activeRentTM.getBalanceToPay());

        } catch (Exception e) {

        }
    }
    private void setCellValueFactory() {
        rentIdClm.setCellValueFactory(new PropertyValueFactory<>("rentId"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        numClm.setCellValueFactory(new PropertyValueFactory<>("carNumber"));
        rentDateClm.setCellValueFactory(new PropertyValueFactory<>("rentDate"));
        overdueClm.setCellValueFactory(new PropertyValueFactory<>("overView"));
        balanceClm.setCellValueFactory(new PropertyValueFactory<>("balanceToPay"));
        returnClm.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

    }

}
