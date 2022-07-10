package com.example.lojadecelular.controller;

import com.example.lojadecelular.dao.*;
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

public class VendaController implements Initializable {

    private VendaDao vendaDao = new VendaDao();
    private CelularDao celularDao = new CelularDao();
    private ClienteDao clienteDao = new ClienteDao();

    @FXML
    private ListView<Venda> LstVenda;

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
    private ComboBox<Cliente> CboCliente;


    private void habilitarInterface(boolean valor) {
        CboCelular.setDisable(!valor);
        CboCliente.setDisable(!valor);
        TxtQuantidade.setDisable(!valor);
        BtnInserir.setDisable(valor);
        BtnCancelar.setDisable(!valor);
        BtnGravar.setDisable(!valor);
        LstVenda.setDisable(valor);
    }

    private void limparInterface() {
        TxtCodigo.setText("");
        TxtData.setText("");
        TxtQuantidade.setText("");
        CboCelular.setValue(null);
        CboCliente.setValue(null);
    }

    private void atualizarLista() {
        List<Venda> vendas;
        try {
            vendas = vendaDao.listar();
        } catch (Exception e) {
            vendas = new ArrayList<Venda>();
        }
        ObservableList<Venda> vendasOb = FXCollections.observableArrayList(vendas);
        LstVenda.setItems(vendasOb);
    }

    private void exibirVendas() {
        Venda vendass = LstVenda.getSelectionModel().getSelectedItem();
        if (vendass == null) return;
        TxtData.setText(vendass.getDataVenda().toString());
        TxtCodigo.setText(vendass.getCodigoVenda().toString());
        CboCelular.setValue(vendass.getCelular());
        CboCliente.setValue(vendass.getCliente());

    }

    @FXML
    private void LstVenda_MouseClicked(MouseEvent evento) {
        exibirVendas();
    }

    @FXML
    private void LstVenda_KeyPressed(KeyEvent evento) {
        exibirVendas();
    }


    @FXML
    protected void BtnInserir_Action(ActionEvent evento) {
        CboCelular.requestFocus();
        habilitarInterface(true);
        limparInterface();
    }

    @FXML
    protected void BtnCancelar_Action(ActionEvent evento) {
        limparInterface();
        habilitarInterface(false);
    }


    @FXML
    protected void BtnGravar_Action(ActionEvent evento) {
        Venda venda = new Venda();
        Celular celular = CboCelular.getValue();
        DetalheVenda detalheVenda = new DetalheVenda();

        detalheVenda.setCelular(CboCelular.getValue());
        detalheVenda.setQuantidade(Double.parseDouble(TxtQuantidade.getText()));
        detalheVenda.setValorpedido(detalheVenda.getQuantidade() * celular.getValor());

        venda.setCelular(celular);
        venda.setDataVenda(LocalDate.now());
        venda.setCliente(CboCliente.getValue());
        venda.setDetalheVenda(detalheVenda);

        try {
            vendaDao.gravar(venda);
        } catch (Exception e) {
            e.printStackTrace();
        }
        atualizarLista();
        habilitarInterface(false);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarLista();

        List<Celular> celulars;
        List<Cliente> clientes;

        try {
            celulars = celularDao.listar();
            clientes = clienteDao.listar();
        } catch (Exception e) {
            celulars = new ArrayList<Celular>();
            clientes = new ArrayList<>();
        }

        ObservableList<Celular> camisaOb = FXCollections.observableArrayList(celulars);
        CboCelular.setItems(camisaOb);

        ObservableList<Cliente> clienteOb = FXCollections.observableArrayList(clientes);
        CboCliente.setItems(clienteOb);
    }
}

