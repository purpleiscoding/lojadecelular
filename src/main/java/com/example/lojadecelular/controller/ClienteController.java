package com.example.lojadecelular.controller;

import com.example.lojadecelular.dao.ClienteDao;
import com.example.lojadecelular.model.Cliente;
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

public class ClienteController implements Initializable {

    private ClienteDao clienteDao = new ClienteDao();

    @FXML
    private ListView<Cliente> LstCliente;

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
    private TextField TxtCpf;



    private void habilitarInterface(boolean valor){
        TxtNome.setDisable(!valor);
        TxtCpf.setDisable(!valor);
        BtnInserir.setDisable(valor);
        BtnCancelar.setDisable(!valor);
        BtnGravar.setDisable(!valor);
        LstCliente.setDisable(valor);
    }

    private void limparInterface(){
        TxtCodigo.setText("");
        TxtNome.setText("");
        TxtCpf.setText("");
    }

    private void atualizarLista(){
        List<Cliente> clientes;
        try{
            clientes = clienteDao.listar();
        } catch(Exception e) {
            clientes = new ArrayList<Cliente>();
        }
        ObservableList<Cliente> clientesOb = FXCollections.observableArrayList(clientes);
        LstCliente.setItems(clientesOb);
    }



    private void exiblirClientes(){
        Cliente clientes = LstCliente.getSelectionModel().getSelectedItem();
        if (clientes == null) return;
        TxtNome.setText(clientes.getNome());
        TxtCodigo.setText(clientes.getCodigoCliente().toString());
        TxtCpf.setText(clientes.getCpf());
    }

    @FXML
    private void LstCliente_MouseClicked(MouseEvent evento){
        exiblirClientes();
    }

    @FXML
    private void LstCliente_KeyPressed(KeyEvent evento){
        exiblirClientes();
    }



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
        Cliente cliente = new Cliente();
        cliente.setNome(TxtNome.getText());
        cliente.setCpf(TxtCpf.getText());

        try {
            clienteDao.gravar(cliente);
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
