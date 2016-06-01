package br.com.paulo.animais.ui;

import br.com.paulo.animais.model.Animal;
import br.com.paulo.animais.properties.PropertiesBundle;
import br.com.paulo.animais.utils.AlertasUtils;
import br.com.paulo.animais.utils.MaskFieldUtil;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author paulo
 */
public class CadastroDeAnimais extends InterfaceGrafica {

    private final Animal animal;
    private Button btnImprimir;
    private Button btnVoltar;
    private final Label lblNome = gerarLabel("LABEL_NOME");
    private final Label lblEspecie = gerarLabel("LABEL_ESPECIE");
    private final Label lblIdade = gerarLabel("LABEL_IDADE");
    private final Label lblPeso = gerarLabel("LABEL_PESO");
    private final Label lblValor = gerarLabel("LABEL_VALOR");
    private final Label lblDono = gerarLabel("LABEL_DONO");

    private final TextField txtNome = new TextField();
    private final Label txtEspecie;
    private final TextField txtIdade = new TextField();
    private final TextField txtPeso = new TextField();
    private final TextField txtValor = new TextField();
    private final TextField txtDono = new TextField();

    public CadastroDeAnimais(Animal animal) {
        this.animal = animal;
        txtEspecie = new Label(this.animal.getEspecie().getDescricao());
    }

    @Override
    protected void inicializarElementos(GridPane grid, Stage stage) {
        inicializarButtonImprimir(stage);
        inicializarButtonVoltar(stage);

        MaskFieldUtil.numericField(txtIdade);
        MaskFieldUtil.numericField(txtPeso);
        MaskFieldUtil.monetaryField(txtValor);

        grid.add(lblNome, 0, 0, 1, 1);
        grid.add(txtNome, 1, 0, 2, 1);
        grid.add(obrigatorio(), 3, 0);

        grid.add(lblEspecie, 0, 1, 1, 1);
        grid.add(txtEspecie, 1, 1, 2, 1);

        grid.add(lblIdade, 0, 2, 1, 1);
        grid.add(txtIdade, 1, 2, 2, 1);
        grid.add(obrigatorio(), 3, 2);

        grid.add(lblPeso, 0, 3, 1, 1);
        grid.add(txtPeso, 1, 3, 2, 1);
        grid.add(obrigatorio(), 3, 3);

        grid.add(lblValor, 0, 4, 1, 1);
        grid.add(txtValor, 1, 4, 2, 1);

        grid.add(lblDono, 0, 5, 1, 1);
        grid.add(txtDono, 1, 5, 2, 1);

        grid.add(btnImprimir, 1, 6);
        grid.add(btnVoltar, 2, 6);
    }

    private void inicializarButtonImprimir(Stage stage) {
        btnImprimir = gerarButtonBase("BOTAO_IMPRIMIR");
        btnImprimir.setOnAction((ActionEvent event) -> {
            String erros = validarDadosInformados();

            if (erros == null) {
                try {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    directoryChooser.setTitle(PropertiesBundle.getProperty("TITULO_DESTINO"));
                    
                    animal.setNome(txtNome.getText());
                    animal.setIdade(Long.parseLong(txtIdade.getText()));
                    animal.setPeso(Double.parseDouble(txtPeso.getText()));
                    animal.setValor((txtValor.getText().isEmpty()) ? null : Double.parseDouble(txtValor.getText().replace(".", "").replace(",", ".")));
                    animal.setDono(txtDono.getText());
                    
                    String nomeArquivo = animal.imprimirConfirmacao(directoryChooser.showDialog(stage));
                    
                    nomeArquivo = PropertiesBundle.getProperty("INFO_EXPORTACAO_SUCESSO").replace("{0}", nomeArquivo);
                    
                    AlertasUtils.exibirInformacao(nomeArquivo);
                    
                    InterfaceGrafica interfaceGrafica = new SelecaoDeEspecie();
                    interfaceGrafica.montarTela(stage);
                } catch (NumberFormatException | IOException npex) {
                    AlertasUtils.exibirErro(npex);
                } 
            } else {
                AlertasUtils.exibirErro(erros);
            }
        });
    }

    private void inicializarButtonVoltar(Stage stage) {
        btnVoltar = gerarButtonBase("BOTAO_VOLTAR");
        btnVoltar.setOnAction((ActionEvent event) -> {
            InterfaceGrafica interfaceGrafica = new SelecaoDeEspecie();
            interfaceGrafica.montarTela(stage);
        });
    }

    private String validarDadosInformados() {
        if (txtNome.getText() == null || txtNome.getText().isEmpty()) {
            return "ERRO_NOME_OBRIGATORIO";
        }
        
        if (txtIdade.getText() == null || txtIdade.getText().isEmpty()) {
            return "ERRO_IDADE_OBRIGATORIA";
        }
        
        if (txtPeso.getText() == null || txtPeso.getText().isEmpty()) {
            return "ERRO_PESO_OBRIGATORIO";
        }
        
        return null;
    }

}
