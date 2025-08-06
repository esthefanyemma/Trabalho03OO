package com.mycompany.trabalho03oo.util;

/**
 * Documentação do Sistema de Exceptions
 * 
 * ESTRUTURA HIERÁRQUICA DAS EXCEPTIONS:
 * 
 * SistemaException (abstract)
 * ├── DaoException
 * │   ├── RecursoNaoEncontradoException
 * │   ├── ConflitoDadosException
 * │   └── ArquivoException
 * ├── ValidacaoException
 * │   ├── CampoObrigatorioException
 * │   ├── FormatoInvalidoException
 * │   ├── FaixaInvalidaException
 * │   └── TamanhoInvalidoException
 * ├── AutenticacaoException
 * │   ├── CredenciaisInvalidasException
 * │   ├── UsuarioNaoAutenticadoException
 * │   └── ContaBloqueadaException
 * └── NegocioException
 *     ├── OperacaoNaoPermitidaException
 *     ├── RegraNegocioException
 *     ├── PermissaoException
 *     └── EstadoInvalidoException
 * 
 * CÓDIGOS DE ERRO:
 * 
 * VALIDAÇÃO:
 * - VALIDATION_ERROR: Erro genérico de validação
 * - REQUIRED_FIELD: Campo obrigatório não preenchido
 * - INVALID_FORMAT: Formato inválido (email, CPF, telefone, etc.)
 * - INVALID_RANGE: Valor fora da faixa permitida
 * - INVALID_LENGTH: Tamanho de string inválido
 * 
 * AUTENTICAÇÃO:
 * - AUTH_ERROR: Erro genérico de autenticação
 * - INVALID_CREDENTIALS: Credenciais inválidas
 * - USER_NOT_AUTHENTICATED: Usuário não autenticado
 * - ACCOUNT_BLOCKED: Conta bloqueada
 * 
 * DAO:
 * - DAO_ERROR: Erro genérico de acesso a dados
 * - RESOURCE_NOT_FOUND: Recurso não encontrado
 * - DATA_CONFLICT: Conflito de dados únicos
 * - FILE_ERROR: Erro de I/O com arquivos
 * 
 * NEGÓCIO:
 * - BUSINESS_ERROR: Erro genérico de negócio
 * - OPERATION_NOT_ALLOWED: Operação não permitida
 * - BUSINESS_RULE_VIOLATION: Violação de regra de negócio
 * - PERMISSION_DENIED: Permissão negada
 * - INVALID_STATE: Estado inválido do sistema
 * 
 * EXEMPLOS DE USO NOS TESTES UNITÁRIOS:
 * 
 * // Testando exception específica
 * @Test
 * void testValidarEmail_QuandoFormatoInvalido() {
 *     FormatoInvalidoException exception = assertThrows(
 *         FormatoInvalidoException.class,
 *         () -> ValidadorUtil.validarEmail("email-invalido")
 *     );
 *     
 *     assertEquals("INVALID_FORMAT", exception.getCodigoErro());
 *     assertTrue(exception.getMessage().contains("email"));
 * }
 * 
 * // Testando exception da hierarquia
 * @Test
 * void testValidarUsuario_QuandoDadosInvalidos() {
 *     ValidacaoException exception = assertThrows(
 *         ValidacaoException.class,
 *         () -> ValidadorUtil.validarUsuario(usuarioInvalido)
 *     );
 *     
 *     // Pode verificar o tipo específico
 *     assertTrue(exception instanceof CampoObrigatorioException);
 * }
 * 
 * // Testando que não deve lançar exception
 * @Test
 * void testValidarEmail_QuandoValido() {
 *     assertDoesNotThrow(() -> ValidadorUtil.validarEmail("joao@email.com"));
 * }
 * 
 * BOAS PRÁTICAS:
 * 
 * 1. Sempre capturar a exception mais específica primeiro
 * 2. Usar o código de erro para identificação programática
 * 3. Usar a mensagem para exibição ao usuário
 * 4. Nos testes, sempre verificar o tipo exato da exception esperada
 * 5. Usar assertThrows para verificar se exceptions são lançadas corretamente
 * 6. Usar assertDoesNotThrow para verificar casos válidos
 * 
 * VALIDAÇÕES IMPLEMENTADAS:
 * 
 * Básicas:
 * - String obrigatória
 * - Tamanho de string (min/max)
 * - Faixa numérica
 * - Valores positivos/não negativos
 * 
 * Formato:
 * - Email
 * - CPF (com algoritmo de validação)
 * - Telefone
 * - CEP
 * - Nome (apenas letras, espaços e acentos)
 * - Senha (força mínima)
 * 
 * Domínio:
 * - Usuario
 * - Endereco
 * - Franquia
 * - Produto
 * - Pedido
 * - Item de Pedido
 * 
 * Negócio:
 * - Transições de status de pedido
 * - Validação de estoque
 * - Permissões de usuário
 * 
 * INTEGRAÇÃO COM O SISTEMA:
 * 
 * Todas as validações devem ser chamadas nos pontos apropriados:
 * - Controllers: antes de processar dados
 * - DAOs: antes de salvar dados
 * - Views: na validação de formulários
 * - Services: em regras de negócio
 * 
 * @author 84398
 */
public class DocumentacaoExceptions {
    
    // Esta classe serve apenas para documentação
    // Não contém código executável
    
}
