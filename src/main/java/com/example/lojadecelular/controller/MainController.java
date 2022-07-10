package com.example.lojadecelular.controller;

import com.example.lojadecelular.view.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button BtnFornecedor;

    @FXML
    private Button BtnCliente;

    @FXML
    private Button BtnTimes;

    @FXML
    private Button BtnCelulars;

    @FXML
    private Button BtnCompra;

    @FXML
    private Button BtnVenda;
    //==//
    @FXML
    protected void BtnFornecedor_Action(ActionEvent evento){
        abrirTela("Fornecedor","Registro Fornecedores");
    }

    @FXML
    protected void BtnCliente_Action(ActionEvent evento){
        abrirTela("Cliente","Registro Clientes");
    }

    @FXML
    protected void BtnTimes_Action(ActionEvent evento){
        abrirTela("Timee","Registro Times");
    }

    @FXML
    protected void BtnCelulars_Action(ActionEvent evento){
        abrirTela("Celular","Registro Celulares");
    }

    @FXML
    protected void BtnCompra_Action(ActionEvent evento){
        abrirTela("Compra","Realizar Compras");
    }

    @FXML
    protected void BtnVenda_Action(ActionEvent evento){
        abrirTela("Venda","Realizar Vendas");
    }

    private void abrirTela(String nome, String titulo){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/fxml/" + nome + ".fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 403, 412);
            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
