package br.com.paulo.animais.enuns;

/**
 *
 * @author paulo
 */
public enum Especie {
    
    CAVALO ("Cavalo"),
    CACHORRO ("Cachorro"),
    GATO ("Gato"),
    PATO ("Pato");
    
    private final String descricao;

    private Especie(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
};
