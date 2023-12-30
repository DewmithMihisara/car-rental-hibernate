package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.CategoryBO;
import lk.ijse.controller.util.AlertTypes;
import lk.ijse.controller.util.PopUpAlerts;
import lk.ijse.controller.util.Validation;
import lk.ijse.dto.CategoryDto;
import lk.ijse.dto.tm.CategoryTM;

public class CategoryFormController {
    @FXML
    private Button addBtn1;

    @FXML
    private TableColumn<?, ?> categoryClm;

    @FXML
    private TextField categoryNameTxt;

    @FXML
    private TableView<CategoryTM> categoryTbl;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<?, ?> idClm;

    @FXML
    private Label idLbl;

    @FXML
    private Button searchBtn;
    @FXML
    private Line catNameLine;

    @FXML
    private TextField searchTxt;

    @FXML
    private Button svBtn;

    @FXML
    private Button upBtn;
    private final CategoryBO categoryBO= (CategoryBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.CATEGORY);

    boolean catName;

    @FXML
    void addNewBtnOnAction(ActionEvent event) {
        svBtn.setDisable(false);
        categoryNameTxt.setDisable(false);
        categoryNameTxt.requestFocus();
        setCatId();
    }

    private void setCatId() {
        idLbl.setText(categoryBO.getNewCategoryId());
    }

    @FXML
    void categoryNameTxtOnAction(ActionEvent event) {
        svBtn.fire();
    }

    @FXML
    void deleteBtnOnAction(ActionEvent event) {
        if (categoryBO.deleteCategory(new CategoryDto(idLbl.getText(),categoryNameTxt.getText()))){
            PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Delete", "Category Delete Successfully!");
            fillTbl();
            initUi();
        }else {
            PopUpAlerts.popUps(AlertTypes.ERROR, "Delete", "Category Not Delete Successfully!");
        }
    }

    @FXML
    void searchBtnOnAction(ActionEvent event) {
        CategoryDto categoryDto=categoryBO.getCategory(searchTxt.getText());
        if(categoryDto!=null){
            idLbl.setText(categoryDto.getId());
            categoryNameTxt.setText(categoryDto.getName());
            upBtn.setDisable(false);
            deleteBtn.setDisable(false);
            categoryNameTxt.setDisable(false);

            addBtn1.setDisable(true);
        }else {
            PopUpAlerts.popUps(AlertTypes.ERROR, "Search", "Category Not Found!");
        }
        searchTxt.clear();
    }

    @FXML
    void searchTxtOnAction(ActionEvent event) {
        searchBtn.fire();
    }

    @FXML
    void svBtnOnAction(ActionEvent event) {
        if(validation()){
            if (categoryBO.saveCategory(new CategoryDto(idLbl.getText(),categoryNameTxt.getText()))){
                PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Save", "Category Saved Successfully!");
                fillTbl();
                initUi();
            }else {
                PopUpAlerts.popUps(AlertTypes.ERROR, "Save", "Category Not Saved Successfully!");
            }
        }

    }
    @FXML
    void upBtnOnAction(ActionEvent event) {
        if(validation()){
            if (categoryBO.updateCategory(new CategoryDto(idLbl.getText(),categoryNameTxt.getText()))){
                PopUpAlerts.popUps(AlertTypes.CONFORMATION, "Update", "Category Updated Successfully!");
                fillTbl();
                initUi();
            }else {
                PopUpAlerts.popUps(AlertTypes.ERROR, "Update", "Category Not Updated Successfully!");
            }
        }
    }
    @FXML
    void initialize(){
        initUi();
        setCellValueFactory();
        fillTbl();
    }
    private boolean validation() {
        catName=false;
        catName= Validation.txtValidation(categoryNameTxt, catNameLine);
        return catName;
    }
    private void fillTbl() {
        ObservableList<CategoryTM>categoryTMS= FXCollections.observableArrayList();
        for(CategoryDto categoryDto: categoryBO.getAllCategories()){
            categoryTMS.add(new CategoryTM(
                    categoryDto.getId(),
                    categoryDto.getName())
            );
        }
        categoryTbl.setItems(categoryTMS);
    }

    private void setCellValueFactory() {
        idClm.setCellValueFactory(new PropertyValueFactory<>("id"));
        categoryClm.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    private void initUi() {
        idLbl.setText("");
        categoryNameTxt.clear();

        categoryNameTxt.setDisable(true);
        svBtn.setDisable(true);
        upBtn.setDisable(true);
        deleteBtn.setDisable(true);

        addBtn1.setDisable(false);
    }
}
