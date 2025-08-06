package com.mycompany.trabalho03oo.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testes unitários para a classe Produto
 * @author 84398
 */
@DisplayName("Testes da classe Produto")
class ProdutoTest {
    
    private Produto produto;
    
    @BeforeEach
    void setUp() {
        produto = new Produto(1, "Pizza Margherita", "Pizza com molho de tomate e queijo", 25.90, 50, 1);
    }
    
    @Nested
    @DisplayName("Testes de construtor")
    class ConstrutorTest {
        
        @Test
        @DisplayName("Deve criar produto com construtor completo")
        void testConstrutorCompleto_DeveCriarProduto() {
            Produto prod = new Produto(2, "Pizza Calabresa", "Pizza com calabresa e cebola", 28.50, 30, 2);
            
            assertEquals(2, prod.getId());
            assertEquals("Pizza Calabresa", prod.getNome());
            assertEquals("Pizza com calabresa e cebola", prod.getDescricao());
            assertEquals(28.50, prod.getPreco());
            assertEquals(30, prod.getQuantidadeEstoque());
            assertEquals(2, prod.getFranquiaId());
            assertEquals(5, prod.getEstoqueMinimo()); // Valor padrão
        }
        
        @Test
        @DisplayName("Deve criar produto com construtor vazio")
        void testConstrutorVazio_DeveCriarProdutoVazio() {
            Produto produtoVazio = new Produto();
            
            assertEquals(0, produtoVazio.getId());
            assertNull(produtoVazio.getNome());
            assertNull(produtoVazio.getDescricao());
            assertEquals(0.0, produtoVazio.getPreco());
            assertEquals(0, produtoVazio.getQuantidadeEstoque());
            assertEquals(0, produtoVazio.getFranquiaId());
            assertEquals(0, produtoVazio.getEstoqueMinimo());
        }
    }
    
    @Nested
    @DisplayName("Testes de getters e setters")
    class GettersSettersTest {
        
        @Test
        @DisplayName("Deve permitir alteração de ID")
        void testSetId_DeveAlterarId() {
            produto.setId(999);
            
            assertEquals(999, produto.getId());
        }
        
        @Test
        @DisplayName("Deve permitir alteração de nome")
        void testSetNome_DeveAlterarNome() {
            produto.setNome("Pizza Portuguesa");
            
            assertEquals("Pizza Portuguesa", produto.getNome());
        }
        
        @Test
        @DisplayName("Deve permitir alteração de descrição")
        void testSetDescricao_DeveAlterarDescricao() {
            produto.setDescricao("Pizza com presunto, ovos e ervilhas");
            
            assertEquals("Pizza com presunto, ovos e ervilhas", produto.getDescricao());
        }
        
        @Test
        @DisplayName("Deve permitir alteração de preço")
        void testSetPreco_DeveAlterarPreco() {
            produto.setPreco(30.90);
            
            assertEquals(30.90, produto.getPreco());
        }
        
        @Test
        @DisplayName("Deve permitir alteração de quantidade em estoque")
        void testSetQuantidadeEstoque_DeveAlterarQuantidade() {
            produto.setQuantidadeEstoque(100);
            
            assertEquals(100, produto.getQuantidadeEstoque());
        }
        
        @Test
        @DisplayName("Deve permitir alteração de franquia ID")
        void testSetFranquiaId_DeveAlterarFranquiaId() {
            produto.setFranquiaId(5);
            
            assertEquals(5, produto.getFranquiaId());
        }
        
        @Test
        @DisplayName("Deve permitir alteração de estoque mínimo")
        void testSetEstoqueMinimo_DeveAlterarEstoqueMinimo() {
            produto.setEstoqueMinimo(10);
            
            assertEquals(10, produto.getEstoqueMinimo());
        }
        
        @Test
        @DisplayName("Deve aceitar valores nulos onde permitido")
        void testSetters_ComValoresNulos() {
            assertDoesNotThrow(() -> {
                produto.setNome(null);
                produto.setDescricao(null);
            });
            
            assertNull(produto.getNome());
            assertNull(produto.getDescricao());
        }
        
        @Test
        @DisplayName("Deve aceitar preço zero")
        void testSetPreco_ComValorZero() {
            produto.setPreco(0.0);
            
            assertEquals(0.0, produto.getPreco());
        }
        
        @Test
        @DisplayName("Deve aceitar valores negativos para IDs e quantidades")
        void testSetters_ComValoresNegativos() {
            produto.setId(-1);
            produto.setQuantidadeEstoque(-5);
            produto.setFranquiaId(-10);
            produto.setEstoqueMinimo(-2);
            
            assertEquals(-1, produto.getId());
            assertEquals(-5, produto.getQuantidadeEstoque());
            assertEquals(-10, produto.getFranquiaId());
            assertEquals(-2, produto.getEstoqueMinimo());
        }
    }
    
    @Nested
    @DisplayName("Testes de métodos de negócio")
    class MetodosNegocioTest {
        
        @Test
        @DisplayName("Deve identificar estoque baixo corretamente")
        void testIsEstoqueBaixo_DeveIdentificarCorretamente() {
            // Estoque acima do mínimo (50 > 5)
            assertFalse(produto.isEstoqueBaixo());
            
            // Estoque igual ao mínimo
            produto.setQuantidadeEstoque(5);
            assertTrue(produto.isEstoqueBaixo());
            
            // Estoque abaixo do mínimo
            produto.setQuantidadeEstoque(3);
            assertTrue(produto.isEstoqueBaixo());
        }
        
        @Test
        @DisplayName("Deve diminuir estoque corretamente")
        void testDiminuirEstoque_DeveDiminuirCorretamente() {
            int estoqueInicial = produto.getQuantidadeEstoque(); // 50
            
            produto.diminuirEstoque(10);
            
            assertEquals(estoqueInicial - 10, produto.getQuantidadeEstoque());
        }
        
        @Test
        @DisplayName("Deve lançar exception ao diminuir estoque com quantidade inválida")
        void testDiminuirEstoque_ComQuantidadeInvalida_DeveLancarException() {
            // Quantidade zero
            IllegalArgumentException exception1 = assertThrows(
                IllegalArgumentException.class,
                () -> produto.diminuirEstoque(0)
            );
            assertNotNull(exception1.getMessage());
            assertEquals(IllegalArgumentException.class, exception1.getClass());
            
            // Quantidade negativa
            IllegalArgumentException exception2 = assertThrows(
                IllegalArgumentException.class,
                () -> produto.diminuirEstoque(-5)
            );
            assertNotNull(exception2.getMessage());
            
            // Quantidade maior que estoque
            IllegalArgumentException exception3 = assertThrows(
                IllegalArgumentException.class,
                () -> produto.diminuirEstoque(100) // estoque é 50
            );
            assertNotNull(exception3.getMessage());
            assertEquals(IllegalArgumentException.class, exception3.getClass());
        }
        
        @Test
        @DisplayName("Deve aumentar estoque corretamente")
        void testAumentarEstoque_DeveAumentarCorretamente() {
            int estoqueInicial = produto.getQuantidadeEstoque(); // 50
            
            produto.aumentarEstoque(20);
            
            assertEquals(estoqueInicial + 20, produto.getQuantidadeEstoque());
        }
        
        @Test
        @DisplayName("Deve lançar exception ao aumentar estoque com quantidade inválida")
        void testAumentarEstoque_ComQuantidadeInvalida_DeveLancarException() {
            // Quantidade zero
            IllegalArgumentException exception1 = assertThrows(
                IllegalArgumentException.class,
                () -> produto.aumentarEstoque(0)
            );
            assertNotNull(exception1.getMessage());
            assertEquals(IllegalArgumentException.class, exception1.getClass());
            
            // Quantidade negativa
            IllegalArgumentException exception2 = assertThrows(
                IllegalArgumentException.class,
                () -> produto.aumentarEstoque(-10)
            );
            assertNotNull(exception2.getMessage());
            assertEquals(IllegalArgumentException.class, exception2.getClass());
        }
        
        @Test
        @DisplayName("Deve verificar disponibilidade de estoque corretamente")
        void testTemEstoqueDisponivel_DeveVerificarCorretamente() {
            // Quantidade disponível
            assertTrue(produto.temEstoqueDisponivel(30));
            
            // Quantidade igual ao estoque
            assertTrue(produto.temEstoqueDisponivel(50));
            
            // Quantidade maior que estoque
            assertFalse(produto.temEstoqueDisponivel(60));
            
            // Quantidade zero
            assertTrue(produto.temEstoqueDisponivel(0));
        }
        
        @Test
        @DisplayName("Deve reduzir estoque corretamente")
        void testReduzirEstoque_DeveReduzirCorretamente() {
            int estoqueInicial = produto.getQuantidadeEstoque(); // 50
            
            assertDoesNotThrow(() -> produto.reduzirEstoque(15));
            
            assertEquals(estoqueInicial - 15, produto.getQuantidadeEstoque());
        }
        
        @Test
        @DisplayName("Deve lançar exception ao reduzir estoque com quantidade maior que disponível")
        void testReduzirEstoque_ComQuantidadeMaiorQueDisponivel_DeveLancarException() {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> produto.reduzirEstoque(100) // estoque é 50
            );
            
            assertNotNull(exception.getMessage());
            assertEquals(IllegalArgumentException.class, exception.getClass());
            
            // Verifica que o estoque não foi alterado
            assertEquals(50, produto.getQuantidadeEstoque());
        }
    }
    
    @Nested
    @DisplayName("Testes de comportamentos especiais")
    class ComportamentosEspeciais {
        
        @Test
        @DisplayName("Deve implementar toString com informações do produto")
        void testToString_DeveConterInformacoesProduto() {
            String resultado = produto.toString();
            
            // Testa que toString não retorna null nem vazio
            assertNotNull(resultado);
            assertFalse(resultado.trim().isEmpty());
            
            // Verifica que os dados estão corretos através dos getters
            assertEquals("Pizza Margherita", produto.getNome());
            assertEquals(25.90, produto.getPreco(), 0.01);
            assertEquals(50, produto.getQuantidadeEstoque());
            assertEquals(5, produto.getEstoqueMinimo());
            
            // Verifica que o estoque não está baixo
            assertFalse(produto.isEstoqueBaixo());
        }
        
        @Test
        @DisplayName("Deve mostrar alerta de estoque baixo no toString")
        void testToString_ComEstoqueBaixo() {
            produto.setQuantidadeEstoque(3); // Abaixo do mínimo (5)
            
            String resultado = produto.toString();
            
            // Testa que toString não retorna null nem vazio
            assertNotNull(resultado);
            assertFalse(resultado.trim().isEmpty());
            
            // Verifica que o estoque realmente está baixo
            assertTrue(produto.isEstoqueBaixo());
            assertEquals(3, produto.getQuantidadeEstoque());
            assertEquals(5, produto.getEstoqueMinimo());
            assertTrue(produto.getQuantidadeEstoque() < produto.getEstoqueMinimo());
        }
        
        @Test
        @DisplayName("Deve tratar toString com campos nulos")
        void testToString_ComCamposNulos() {
            Produto produtoComNulos = new Produto();
            produtoComNulos.setNome(null);
            
            // Não deve lançar exception
            assertDoesNotThrow(() -> produtoComNulos.toString());
            
            String resultado = produtoComNulos.toString();
            assertNotNull(resultado);
        }
        
        @Test
        @DisplayName("Deve implementar equals e hashCode baseado no ID")
        void testEqualsHashCode_BaseadoNoId() {
            Produto produto1 = new Produto(1, "Pizza A", "Descrição A", 20.0, 10, 1);
            Produto produto2 = new Produto(1, "Pizza B", "Descrição B", 30.0, 20, 2);
            Produto produto3 = new Produto(2, "Pizza A", "Descrição A", 20.0, 10, 1);
            
            // Mesmo ID, devem ser iguais
            assertEquals(produto1, produto2);
            assertEquals(produto1.hashCode(), produto2.hashCode());
            
            // IDs diferentes, devem ser diferentes
            assertNotEquals(produto1, produto3);
            
            // Comparação com null
            assertNotEquals(produto1, null);
            
            // Comparação com objeto de classe diferente
            assertNotEquals(produto1, "string");
            
            // Reflexividade
            assertEquals(produto1, produto1);
        }
        
        @Test
        @DisplayName("Deve manter integridade após múltiplas alterações")
        void testIntegridade_MultiplasAlteracoes() {
            int idOriginal = produto.getId();
            
            // Múltiplas alterações
            produto.setNome("Nome Alterado");
            produto.setDescricao("Descrição Alterada");
            produto.setPreco(99.99);
            produto.setQuantidadeEstoque(200);
            produto.setFranquiaId(10);
            produto.setEstoqueMinimo(15);
            
            // ID deve permanecer o mesmo
            assertEquals(idOriginal, produto.getId());
            
            // Outras propriedades devem estar corretas
            assertEquals("Nome Alterado", produto.getNome());
            assertEquals("Descrição Alterada", produto.getDescricao());
            assertEquals(99.99, produto.getPreco());
            assertEquals(200, produto.getQuantidadeEstoque());
            assertEquals(10, produto.getFranquiaId());
            assertEquals(15, produto.getEstoqueMinimo());
        }
        
        @Test
        @DisplayName("Deve permitir estoque zero")
        void testEstoqueZero_DeveSerPermitido() {
            produto.setQuantidadeEstoque(0);
            
            assertEquals(0, produto.getQuantidadeEstoque());
            assertTrue(produto.isEstoqueBaixo()); // 0 <= 5
            assertFalse(produto.temEstoqueDisponivel(1));
            assertTrue(produto.temEstoqueDisponivel(0));
        }
        
        @Test
        @DisplayName("Deve manter comportamento consistente com estoque mínimo zero")
        void testEstoqueMinimoZero_ComportamentoConsistente() {
            produto.setEstoqueMinimo(0);
            produto.setQuantidadeEstoque(0);
            
            assertTrue(produto.isEstoqueBaixo()); // 0 <= 0
            
            produto.setQuantidadeEstoque(1);
            assertFalse(produto.isEstoqueBaixo()); // 1 > 0
        }
    }
}
