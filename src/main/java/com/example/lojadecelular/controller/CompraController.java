package com.example.lojadecelular.controller;

import com.example.lojadecelular.dao.CelularDao;
import com.example.lojadecelular.dao.CompraDao;
import com.example.lojadecelular.dao.FornecedorDao;
import com.example.lojadecelular.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CompraController implements Initializable {

    private CompraDao compraDao = new CompraDao();
    private CelularDao celularDao = new CelularDao();
    private FornecedorDao fornecedorDao = new FornecedorDao();

    @FXML
    private ListView<Compra> LstCompra;

    @FXML
    private Button BtnInserir;

    @FXML
    private Button BtnGravar;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtCodigo;

    @FXML
    private TextField TxtData;

    @FXML
    private TextField TxtQuantidade;

    @FXML
    private ComboBox<Celular> CboCelular;

    @FXML
    private ComboBox<Fornecedor> CboFornecedor;

    //= Métodos auxiliares =//

    private void habilitarInterface(boolean valor){
        CboCelular.setDisable(!valor);
        CboFornecedor.setDisable(!valor);
        TxtQuantidade.setDisable(!valor);

        BtnInserir.setDisable(valor);
        BtnCancelar.setDisable(!valor);
        BtnGravar.setDisable(!valor);
        LstCompra.setDisable(valor);
    }

    private void limparInterface(){
        TxtCodigo.setText("");
        TxtData.setText("");
        TxtQuantidade.setText("");
        CboCelular.setValue(null);
        CboFornecedor.setValue(null);
    }

    private void atualizarLista(){
        List<Compra> compras;
        try{
            compras = compraDao.listar();
        } catch(Exception e) {
            compras = new ArrayList<Compra>();
            e.printStackTrace();
        }
        ObservableList<Compra> comprasOb = FXCollections.observableArrayList(compras);
        LstCompra.setItems(comprasOb);
    }



    private void exibirCompras(){
        Compra comprass = LstCompra.getSelectionModel().getSelectedItem();
        if (comprass == null) return;
        TxtData.setText(comprass.getDataCompra().toString());
        TxtCodigo.setText(comprass.getCodigoCompra().toString());
        CboCelular.setValue(comprass.getCelular());
        CboFornecedor.setValue(comprass.getFornecedor());


    }

    @FXML
    private void LstCompra_MouseClicked(MouseEvent evento){
        exibirCompras();
    }

    @FXML
    private void LstCompra_KeyPressed(KeyEvent evento){
        exibirCompras();
    }



    @FXML
    protected void BtnInserir_Action(ActionEvent evento){
        CboCelular.requestFocus();
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
        Compra compra = new Compra();
        Celular celular = CboCelular.getValue();
        DetalheCompra detalheCompra = new DetalheCompra();

        detalheCompra.setCelular(CboCelular.getValue());
        detalheCompra.setQuantidade(Double.parseDouble(TxtQuantidade.getText()));
        detalheCompra.setValorpedido(detalheCompra.getQuantidade()*celular.getValor()*0.75);

        compra.setCelular(celular);
        compra.setDataCompra(LocalDate.now());
        compra.setFornecedor(CboFornecedor.getValue());
        compra.setDetalheCompra(detalheCompra);

        try {
            compraDao.gravar(compra);
        } catch (Exception e){
            e.printStackTrace();
        }
        atualizarLista();
        habilitarInterface(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();

        List<Celular> celulars;
        List<Fornecedor> fornecedors;

        try {
            celulars = celularDao.listar();
            fornecedors = fornecedorDao.listar();
        } catch (Exception e) {
            celulars = new ArrayList<Celular>();
            fornecedors = new ArrayList<>();
        }

        ObservableList<Celular> celularOb = FXCollections.observableArrayList(celulars);
        CboCelular.setItems(celularOb);

        ObservableList<Fornecedor> fornecedorsOb = FXCollections.observableArrayList(fornecedors);
        CboFornecedor.setItems(fornecedorsOb);
    }
}
