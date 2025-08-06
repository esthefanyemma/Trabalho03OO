# Instruções de Execução - Sistema de Gerenciamento de Franquias

## Como Executar o Sistema

### 1. Compilar o Projeto
```bash
mvn compile
```

### 2. Executar o Sistema Principal

#### Interface de Linha de Comando (padrão)
```bash
mvn exec:java
```

OU manualmente:
```bash
java -cp target/classes com.mycompany.trabalho03oo.Trabalho03OO
```

#### Interface Gráfica Swing
```bash
mvn exec:java -Dexec.args="--swing"
```

OU manualmente:
```bash
java -cp target/classes com.mycompany.trabalho03oo.Trabalho03OO --swing
```

### 3. Executar Testes Unitários
```bash
java -cp target/classes com.mycompany.trabalho03oo.test.ValidadorUtilTest
```

## Interfaces Disponíveis

### 🖥️ Interface Gráfica (Swing)
- Interface moderna e intuitiva
- Menus organizados por tipo de usuário
- Formulários de cadastro com validação
- Tabelas para listagem de dados
- Diálogos informativos

### 💻 Interface de Linha de Comando
- Interface tradicional de console
- Menus textuais interativos
- Funcionalidade completa
- Ideal para servidores ou ambientes sem GUI

## Usuários de Teste Pré-configurados

O sistema cria automaticamente os seguintes usuários para teste:

### DONO
- **Email:** `joao@franquia.com`
- **Senha:** `123456`
- **Funcionalidades:** Gerenciar franquias, gerenciar gerentes, visualizar relatórios

### GERENTES
1. **Maria Santos**
   - **Email:** `maria@franquia.com`
   - **Senha:** `123456`
   - **Franquia:** Franquia Centro

2. **Pedro Costa**
   - **Email:** `pedro@franquia.com`
   - **Senha:** `123456`
   - **Franquia:** Franquia Industrial

### VENDEDORES
1. **Ana Oliveira**
   - **Email:** `ana@franquia.com`
   - **Senha:** `123456`
   - **Franquia:** Franquia Centro

2. **Carlos Lima**
   - **Email:** `carlos@franquia.com`
   - **Senha:** `123456`
   - **Franquia:** Franquia Centro

3. **Lucia Rocha**
   - **Email:** `lucia@franquia.com`
   - **Senha:** `123456`
   - **Franquia:** Franquia Industrial

## Fluxo de Teste Recomendado

### 1. Login como Dono (joao@franquia.com)
- Visualizar franquias existentes
- Cadastrar um novo gerente
- Criar uma nova franquia
- Visualizar gerentes sem franquia

### 2. Login como Gerente (maria@franquia.com)
- Visualizar vendedores da franquia
- Cadastrar um novo vendedor
- Visualizar ranking de vendedores

### 3. Login como Vendedor (ana@franquia.com)
- Explorar menu do vendedor
- (Funcionalidades de pedidos em desenvolvimento)

## Arquivos de Dados

O sistema cria automaticamente os seguintes arquivos para persistência:
- `usuarios.txt` - Dados de todos os usuários
- `franquias.txt` - Dados das franquias

## Funcionalidades Implementadas

### ✅ Totalmente Funcionais
- Sistema de login/logout
- Cadastro e gerenciamento de usuários
- Cadastro e gerenciamento de franquias
- Validações completas de dados
- Persistência em arquivos
- Diferentes menus por tipo de usuário
- Ranking de vendedores
- Testes unitários

### 🔄 Em Desenvolvimento
- Sistema de produtos e estoque
- Gestão completa de pedidos
- Relatórios avançados
- Interface gráfica

## Conceitos de OO Demonstrados

1. **Classes e Herança**: Hierarquia Usuario → Dono/Gerente/Vendedor
2. **Polimorfismo**: Comportamentos diferentes por tipo de usuário
3. **Encapsulamento**: Atributos privados com getters/setters
4. **Interfaces**: GenericDAO para operações CRUD
5. **Exceções Personalizadas**: ValidacaoException, AutenticacaoException
6. **Coleções**: List, Stream API
7. **Enums**: TipoUsuario, FormaPagamento, ModalidadeEntrega
8. **Pacotes**: Organização lógica do código

## Padrão MVC Implementado

- **Model**: Classes de domínio (Usuario, Franquia, etc.)
- **View**: Interface de linha de comando (SistemaView)
- **Controller**: Lógica de negócio (SistemaController, AutenticacaoController)
- **DAO**: Persistência de dados (UsuarioDAO, FranquiaDAO)

## Validações Implementadas

- CPF (algoritmo completo de validação)
- Email (formato básico)
- Senhas (mínimo 6 caracteres)
- Campos obrigatórios
- Valores monetários
- CEP

## Troubleshooting

### Erro de Compilação
Certifique-se de que está usando Java 21+ e Maven está instalado.

### Arquivos de Dados Corrompidos
Delete os arquivos .txt na raiz do projeto e execute novamente - dados de exemplo serão recriados.

### Problemas de Encoding
No Windows, se houver problemas com caracteres especiais, execute:
```bash
chcp 65001
```
