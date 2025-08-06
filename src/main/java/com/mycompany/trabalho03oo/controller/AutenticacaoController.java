package com.mycompany.trabalho03oo.controller;

import com.mycompany.trabalho03oo.dao.UsuarioDAO;
import com.mycompany.trabalho03oo.exceptions.AutenticacaoException;
import com.mycompany.trabalho03oo.model.Usuario;

/**
 * Controller responsável pela autenticação de usuários
 * @author 84398
 */
public class AutenticacaoController {
    
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioLogado;
    
    public AutenticacaoController() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    /**
     * Autentica um usuário no sistema
     */
    public Usuario autenticar(String email, String senha) throws AutenticacaoException {
        try {
            Usuario usuario = usuarioDAO.buscarPorEmail(email);
            
            if (usuario == null) {
                throw new AutenticacaoException("Email não encontrado");
            }
            
            if (!usuario.getSenha().equals(senha)) {
                throw new AutenticacaoException("Senha incorreta");
            }
            
            this.usuarioLogado = usuario;
            return usuario;
            
        } catch (Exception e) {
            throw new AutenticacaoException("Erro ao autenticar usuário: " + e.getMessage());
        }
    }
    
    /**
     * Faz logout do sistema
     */
    public void logout() {
        this.usuarioLogado = null;
    }
    
    /**
     * Retorna o usuário atualmente logado
     */
    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    /**
     * Verifica se há um usuário logado
     */
    public boolean isLogado() {
        return usuarioLogado != null;
    }
    
    /**
     * Verifica se o usuário logado é do tipo especificado
     */
    public boolean isUsuarioTipo(Class<?> tipoUsuario) {
        return isLogado() && tipoUsuario.isInstance(usuarioLogado);
    }
}
