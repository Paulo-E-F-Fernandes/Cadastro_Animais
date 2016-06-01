/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.paulo.animais;

import br.com.paulo.animais.ui.InterfaceGrafica;
import br.com.paulo.animais.ui.SelecaoDeEspecie;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author paulo
 */
public class Animais extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        InterfaceGrafica ui = new SelecaoDeEspecie();
        
        ui.montarTela(primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
