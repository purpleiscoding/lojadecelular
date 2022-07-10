package com.example.lojadecelular.controller;

import com.example.lojadecelular.dao.FornecedorDao;
import com.example.lojadecelular.model.Fornecedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javafx.event.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FornecedorController implements Initializable {

    private FornecedorDao fornecedorDao = new FornecedorDao();

    @FXML
    private ListView<Fornecedor> LstFornecedor;

    @FXML
    private Button BtnInserir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtCodigo;

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtCnpj;



    private void habilitarInterface(boolean valor){
        TxtNome.setDisable(!valor);
        TxtCnpj.setDisable(!valor);
        BtnInserir.setDisable(valor);
        BtnCancelar.setDisable(!valor);
        BtnGravar.setDisable(!valor);
        LstFornecedor.setDisable(valor);
    }

    private void limparInterface(){
        TxtCodigo.setText("");
        TxtNome.setText("");
        TxtCnpj.setText("");
    }

    private void atualizarLista(){
        List<Fornecedor> fornecedores;
        try{
            fornecedores = fornecedorDao.listar();
        } catch(Exception e) {
            fornecedores = new ArrayList<Fornecedor>();
        }
        ObservableList<Fornecedor> fornecedoresOb = FXCollections.observableArrayList(fornecedores);
        LstFornecedor.setItems(fornecedoresOb);
    }


    private void exibirFornecedores(){
        Fornecedor fornecedorr = LstFornecedor.getSelectionModel().getSelectedItem();
        if (fornecedorr == null) return;
        TxtNome.setText(fornecedorr.getNome());
        TxtCodigo.setText(fornecedorr.getCodigoFornecedor().toString());
        TxtCnpj.setText(fornecedorr.getCnpj());
    }

    @FXML
    private void LstFornecedor_MouseClicked(MouseEvent evento){
        exibirFornecedores();
    }

    @FXML
    private void LstFornecedor_KeyPressed(KeyEvent evento){ exibirFornecedores(); }



    @FXML
    protected void BtnInserir_Action(ActionEvent evento){
        TxtNome.requestFocus();
        habilitarInterface(true);
        limparInterface();
        TxtNome.requestFocus();
    }

    @FXML
    protected void BtnCancelar_Action(ActionEvent evento){
        limparInterface();
        habilitarInterface(false);
    }

    @FXML
    protected void BtnGravar_Action(ActionEvent evento){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(TxtNome.getText());
        fornecedor.setCnpj(TxtCnpj.getText());

        try {
            fornecedorDao.gravar(fornecedor);
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
