package com.mycompany.trabalho03oo.util;

import com.mycompany.trabalho03oo.exceptions.*;

/**
 * Classe para demonstrar como usar as exceptions nos testes unitários
 * Esta classe mostra exemplos de como usar assertThrows nos testes
 * @author 84398
 */
public class ExemplosTestesException {
    
    /**
     * Exemplo de como testar exceptions específicas com JUnit 5
     * 
     * // Para testar campo obrigatório:
     * @Test
     * void testValidarStringObrigatoria_QuandoNula_DeveLancarCampoObrigatorioException() {
     *     CampoObrigatorioException exception = assertThrows(
     *         CampoObrigatorioException.class, 
     *         () -> ValidadorUtil.validarStringObrigatoria(null, "nome")
     *     );
     *     
     *     assertEquals("REQUIRED_FIELD", exception.getCodigoErro());
     *     assertTrue(exception.getMessage().contains("nome"));
     * }
     * 
     * // Para testar formato inválido:
     * @Test
     * void testValidarEmail_QuandoFormatoInvalido_DeveLancarFormatoInvalidoException() {
     *     FormatoInvalidoException exception = assertThrows(
     *         FormatoInvalidoException.class,
     *         () -> ValidadorUtil.validarEmail("email-invalido")
     *     );
     *     
     *     assertEquals("INVALID_FORMAT", exception.getCodigoErro());
     *     assertTrue(exception.getMessage().contains("email"));
     * }
     * 
     * // Para testar validação de negócio:
     * @Test
     * void testValidarEstoque_QuandoInsuficiente_DeveLancarRegraNegocioException() {
     *     Produto produto = new Produto("Pizza", "Descrição", 25.90, 5);
     *     
     *     RegraNegocioException exception = assertThrows(
     *         RegraNegocioException.class,
     *         () -> ValidadorUtil.validarEstoque(produto, 10)
     *     );
     *     
     *     assertEquals("BUSINESS_RULE_VIOLATION", exception.getCodigoErro());
     *     assertTrue(exception.getMessage().contains("Estoque insuficiente"));
     * }
     * 
     * // Para testar múltiplos tipos de exception na mesma hierarquia:
     * @Test
     * void testValidarUsuario_QuandoEmailInvalido_DeveLancarValidacaoException() {
     *     Usuario usuario = new Dono("Nome", "email-invalido", "12345678901");
     *     
     *     // Pode capturar a exception base se não souber qual específica será lançada
     *     ValidacaoException exception = assertThrows(
     *         ValidacaoException.class,
     *         () -> ValidadorUtil.validarUsuario(usuario)
     *     );
     *     
     *     // Ou verificar o tipo específico
     *     assertTrue(exception instanceof FormatoInvalidoException);
     *     assertEquals("INVALID_FORMAT", exception.getCodigoErro());
     * }
     * 
     * // Para testar que NÃO deve lançar exception:
     * @Test
     * void testValidarEmail_QuandoValido_NaoDeveLancarException() {
     *     assertDoesNotThrow(() -> ValidadorUtil.validarEmail("joao@email.com"));
     * }
     * 
     * // Para testar exceptions de DAO:
     * @Test
     * void testBuscarUsuario_QuandoNaoExiste_DeveLancarRecursoNaoEncontradoException() {
     *     RecursoNaoEncontradoException exception = assertThrows(
     *         RecursoNaoEncontradoException.class,
     *         () -> usuarioDAO.buscarPorId(999)
     *     );
     *     
     *     assertEquals("RESOURCE_NOT_FOUND", exception.getCodigoErro());
     *     assertTrue(exception.getMessage().contains("999"));
     * }
     * 
     * // Para testar exceptions de autenticação:
     * @Test
     * void testAutenticar_ComCredenciaisInvalidas_DeveLancarCredenciaisInvalidasException() {
     *     CredenciaisInvalidasException exception = assertThrows(
     *         CredenciaisInvalidasException.class,
     *         () -> authController.autenticar("email@test.com", "senhaErrada")
     *     );
     *     
     *     assertEquals("INVALID_CREDENTIALS", exception.getCodigoErro());
     * }
     * 
     * // Para testar exceptions de permissão:
     * @Test
     * void testCadastrarFranquia_SemPermissao_DeveLancarPermissaoException() {
     *     // Simular usuário logado que não é Dono
     *     authController.setUsuarioLogado(new Gerente("Gerente", "gerente@test.com", "12345678901"));
     *     
     *     PermissaoException exception = assertThrows(
     *         PermissaoException.class,
     *         () -> sistemaController.cadastrarFranquia("Nova Franquia", endereco, 1)
     *     );
     *     
     *     assertEquals("PERMISSION_DENIED", exception.getCodigoErro());
     * }
     */
    
    // Métodos de exemplo para demonstrar uso das exceptions
    public static void exemploUsoValidacoes() {
        try {
            // Exemplo de validação básica
            ValidadorUtil.validarStringObrigatoria("", "nome");
        } catch (CampoObrigatorioException e) {
            System.out.println("Erro específico - Campo obrigatório: " + e.getMessage());
            System.out.println("Código do erro: " + e.getCodigoErro());
        }
        
        try {
            // Exemplo de validação de formato
            ValidadorUtil.validarEmail("email-inválido");
        } catch (CampoObrigatorioException e) {
            System.out.println("Erro - Campo obrigatório: " + e.getMessage());
        } catch (FormatoInvalidoException e) {
            System.out.println("Erro específico - Formato inválido: " + e.getMessage());
            System.out.println("Código do erro: " + e.getCodigoErro());
        }
        
        try {
            // Exemplo de validação de faixa
            ValidadorUtil.validarFaixaNumerica(-10, "preço", 0, 1000);
        } catch (FaixaInvalidaException e) {
            System.out.println("Erro específico - Faixa inválida: " + e.getMessage());
            System.out.println("Código do erro: " + e.getCodigoErro());
        }
    }
    
    /**
     * Exemplo de como capturar exceptions na hierarquia correta em um método genérico
     */
    public static void exemploCapturarExceptionsHierarquia(String operacao, Object parametro) {
        try {
            // Simulação de diferentes operações que podem lançar diferentes tipos de exception
            switch (operacao) {
                case "validarEmail":
                    ValidadorUtil.validarEmail((String) parametro);
                    break;
                case "validarString":
                    ValidadorUtil.validarStringObrigatoria((String) parametro, "campo");
                    break;
                case "validarFaixa":
                    ValidadorUtil.validarFaixaNumerica((Double) parametro, "valor", 0, 100);
                    break;
                default:
                    throw new NegocioException("Operação desconhecida: " + operacao);
            }
            
        } catch (FormatoInvalidoException e) {
            // Captura exceptions específicas de formato
            System.out.println("Formato inválido: " + e.getMessage());
            
        } catch (CampoObrigatorioException e) {
            // Captura exceptions específicas de campo obrigatório
            System.out.println("Campo obrigatório: " + e.getMessage());
            
        } catch (FaixaInvalidaException e) {
            // Captura exceptions específicas de faixa inválida
            System.out.println("Faixa inválida: " + e.getMessage());
            
        } catch (NegocioException e) {
            // Captura exceptions de negócio
            System.out.println("Erro de negócio: " + e.getMessage());
            
        } catch (Exception e) {
            // Captura qualquer outra exception não esperada
            System.out.println("Erro inesperado: " + e.getMessage());
        }
    }
}
