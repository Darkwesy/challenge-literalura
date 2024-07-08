package br.alura.com.literalura.service.conversor;

public interface IConversorDados {
    <T> T obterDados(String json, Class<T> classe);
}