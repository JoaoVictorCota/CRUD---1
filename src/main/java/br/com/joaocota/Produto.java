package br.com.joaocota;


public record Produto(Long id, String nome, Integer quantidade, double valor) {
    public Produto(String nome, Integer quantidade, double valor) {
        this(null, nome, quantidade, valor);
    }
}
