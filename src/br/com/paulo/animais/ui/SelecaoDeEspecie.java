/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.paulo.animais.ui;

import br.com.paulo.animais.enuns.Especie;
import br.com.paulo.animais.model.Animal;
import br.com.paulo.animais.utils.AlertasUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author paulo
 */
public class SelecaoDeEspecie extends InterfaceGrafica {

    private Button btnAvancar;
    private ComboBox cbxEspecie;
    private final Label lblEspecie = gerarLabel("LABEL_ESPECIE");

    public SelecaoDeEspecie() {
        super();
    }

    @Override
    protected void inicializarElementos(GridPane grid, Stage stage) {
        inicializarComboboxCurso();
        inicializarButtonAvancar(stage);

        grid.add(lblEspecie, 0, 0, 1, 1);
        grid.add(cbxEspecie, 1, 0, 1, 1);
        grid.add(obrigatorio(), 2, 0);
        grid.add(btnAvancar, 1, 2);
    }

    private void inicializarComboboxCurso() {
        ObservableList<Especie> cursos = FXCollections.observableArrayList();

        cbxEspecie = new ComboBox();
        cursos.addAll(Especie.values());
        cbxEspecie.setItems(cursos);
    }

    private void inicializarButtonAvancar(Stage primaryStage) {
        btnAvancar = gerarButtonBase("BOTAO_AVANCAR");
        btnAvancar.setOnAction((ActionEvent event) -> {
            String erros = validarDadosInformados();

            if (erros == null) {
                try {
                    Animal animal = new Animal(Especie.valueOf(cbxEspecie.getValue().toString()));

                    Stage stage = new Stage();
                    InterfaceGrafica cadastroDeAnimais = new CadastroDeAnimais(animal);
                    cadastroDeAnimais.montarTela(stage);
                    primaryStage.close();
                } catch (Exception ex) {
                    AlertasUtils.exibirErro(ex);
                }
            } else {
                AlertasUtils.exibirErro(erros);
            }
        });
    }

    private String validarDadosInformados() {
        if (cbxEspecie.getValue() == null) {
            return "ERRO_ESPECIE_OBRIGATORIO";
        }

        return null;
    }

}
