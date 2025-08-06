package com.mycompany.trabalho03oo.dao;

import java.util.List;

/**
 * Interface genérica para operações CRUD
 * @author 84398
 * @param <T> Tipo da entidade
 */
public interface GenericDAO<T> {
    
    /**
     * Salva uma entidade
     */
    void salvar(T entidade) throws Exception;
    
    /**
     * Busca uma entidade por ID
     */
    T buscarPorId(int id) throws Exception;
    
    /**
     * Lista todas as entidades
     */
    List<T> listarTodos() throws Exception;
    
    /**
     * Atualiza uma entidade
     */
    void atualizar(T entidade) throws Exception;
    
    /**
     * Remove uma entidade por ID
     */
    void remover(int id) throws Exception;
    
    /**
     * Verifica se existe uma entidade com o ID especificado
     */
    boolean existe(int id) throws Exception;
    
    /**
     * Obtém o próximo ID disponível
     */
    int obterProximoId() throws Exception;
}
