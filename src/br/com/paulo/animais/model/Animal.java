package br.com.paulo.animais.model;

import br.com.paulo.animais.enuns.Especie;
import br.com.paulo.animais.properties.PropertiesBundle;
import br.com.paulo.animais.utils.StringUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author paulo
 */
public class Animal {
    
    private String nome;
    private Especie especie;
    private Long idade;
    private Double peso;
    private Double valor;
    private String dono;

    public Animal(Especie especie) {
        this.especie = especie;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String imprimirConfirmacao(File destino) throws FileNotFoundException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        
        String nomeArquivo = StringUtils.removerAcentos(this.nome).concat("_").concat(LocalDateTime.now().format(formatter)).concat(".txt").replace(' ', '-').toLowerCase();

        OutputStream os = new FileOutputStream(destino.getAbsolutePath().concat(File.separator).concat(nomeArquivo));
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        
        bw.write(configurarLinhaRelatorio("LABEL_NOME", nome));
        bw.newLine();
        bw.write(configurarLinhaRelatorio("LABEL_ESPECIE", especie.getDescricao()));
        bw.newLine();
        bw.write(configurarLinhaImpressao("LABEL_IDADE", idade.toString(), "UNIDADE_IDADE"));
        bw.newLine();
        bw.write(configurarLinhaImpressao("LABEL_PESO", peso.toString(), "UNIDADE_PESO"));
        bw.newLine();
        bw.write(configurarLinhaImpressao("LABEL_VALOR", (valor != null) ? valor.toString() : "", "UNIDADE_MOEDA"));
        bw.newLine();
        bw.write(configurarLinhaRelatorio("LABEL_DONO", dono));
        
        bw.close();
        osw.close();
        os.close();
        
        return nomeArquivo;
    }

    private String configurarLinhaRelatorio(String chave, String valor) {
        return PropertiesBundle.getProperty(chave).concat(": ").concat(valor);
    }
    
    private String configurarLinhaImpressao(String chave, String valor, String unidade) {
        return PropertiesBundle.getProperty(chave).concat("(" + PropertiesBundle.getProperty(unidade) + "): ").concat(valor);
    }

}
