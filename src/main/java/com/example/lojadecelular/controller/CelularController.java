package com.example.lojadecelular.controller;

import com.example.lojadecelular.dao.CelularDao;
import com.example.lojadecelular.model.Celular;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CelularController implements Initializable {

    private CelularDao celularDao = new CelularDao();

    @FXML
    private ListView<Celular> LstCelular;

    @FXML
    private Button BtnInserir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtCodigo;

    @FXML
    private TextField TxtQuantidade;

    @FXML
    private TextField TxtValor;

    @FXML
    private TextField TxtMarca;



    private void habilitarInterface(boolean valor){
        TxtValor.setDisable(!valor);
        TxtMarca.setDisable(!valor);

        BtnInserir.setDisable(valor);
        BtnCancelar.setDisable(!valor);
        BtnGravar.setDisable(!valor);
        LstCelular.setDisable(valor);
    }

    private void limparInterface(){
        TxtCodigo.setText("");
        TxtQuantidade.setText("");
        TxtValor.setText("");
        TxtMarca.setText("");
    }

    private void atualizarLista(){
        List<Celular> celulars;
        try{
            celulars = celularDao.listar();
        } catch(Exception e) {
            celulars = new ArrayList<Celular>();
            e.printStackTrace();
        }
        ObservableList<Celular> celularsOb = FXCollections.observableArrayList(celulars);
        LstCelular.setItems(celularsOb);
    }


    private void exibirCelulars(){
        Celular celulars = LstCelular.getSelectionModel().getSelectedItem();
        if (celulars == null) return;

        TxtCodigo.setText(celulars.getCodigo().toString());
        TxtQuantidade.setText(celulars.getEstoque().toString());
        TxtValor.setText(celulars.getValor().toString());
        TxtMarca.setText(celulars.getMarca());
    }

    @FXML
    private void LstCelular_MouseClicked(MouseEvent evento){
        exibirCelulars();
    }

    @FXML
    private void LstCelular_KeyPressed(KeyEvent evento){ exibirCelulars(); }



    @FXML
    protected void BtnInserir_Action(ActionEvent evento){
        TxtQuantidade.requestFocus();
        habilitarInterface(true);
        limparInterface();
    }

    @FXML
    protected void BtnCancelar_Action(ActionEvent evento){
        limparInterface();
        habilitarInterface(false);
    }

    @FXML
    protected void BtnGravar_Action(ActionEvent evento){
        Celular celular = new Celular();

        celular.setValor(Double.parseDouble(TxtValor.getText()));
        celular.setMarca(TxtMarca.getText());
        celular.setEstoque(0.0);

        try {
            celularDao.gravar(celular);
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarLista();
        habilitarInterface(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();
    }

}
