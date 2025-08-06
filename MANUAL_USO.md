# Sistema de Gerenciamento de Franquias - Interface Swing

## Visão Geral
Este sistema foi desenvolvido seguindo o padrão MVC (Model-View-Controller) com interface gráfica moderna usando Swing.

## Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven 3.6 ou superior

### Compilação
```bash
mvn compile
```

### Execução
```bash
java -cp target\classes com.mycompany.trabalho03oo.Trabalho03OO
```

## Usuários Padrão

### Dono (Admin)
- **Email**: admin@franquia.com
- **Senha**: 123456
- **Permissões**: Acesso total ao sistema

### Gerente de Exemplo
- **Email**: gerente1@franquia.com
- **Senha**: 123456
- **Permissões**: Gerenciar sua franquia e vendedores

### Vendedor de Exemplo
- **Email**: vendedor1@franquia.com
- **Senha**: 123456
- **Permissões**: Visualizar informações básicas

## Interface Gráfica (Swing)

### Tela de Login
- Interface moderna com campos de email e senha
- Validação de credenciais
- Mensagens de erro informativas

### Tela Principal
- **Menu Superior**: Navegação por funcionalidades
- **Área Central**: Painéis dinâmicos conforme a funcionalidade selecionada
- **Franquias**: Listagem em tabela, cadastro via dialog
- **Gerentes**: Gestão de gerentes sem franquia
- **Vendedores**: Listagem e cadastro de vendedores
- **Rankings**: Relatórios visuais de performance

### Componentes da Interface
- **FranquiaListPanel**: Tabela com listagem de franquias
- **CadastroFranquiaDialog**: Formulário para nova franquia
- **GerenteListPanel**: Listagem de gerentes disponíveis
- **VendedorListPanel**: Gerenciamento de vendedores
- **RankingVendedoresPanel**: Relatórios de vendas

## Funcionalidades por Tipo de Usuário

### Dono
- Cadastrar e gerenciar franquias
- Cadastrar e gerenciar gerentes
- Cadastrar e gerenciar vendedores
- Visualizar rankings e relatórios
- Acesso completo a todas as funcionalidades

### Gerente
- Gerenciar vendedores de sua franquia
- Visualizar informações da franquia
- Acessar relatórios de vendas

### Vendedor
- Visualizar informações básicas
- Consultar dados da franquia

## Arquitetura MVC

### Model (Modelo)
- **Usuario** (hierarquia): Dono, Gerente, Vendedor
- **Franquia**: Informações das franquias
- **Produto**: Catálogo de produtos
- **Pedido**: Pedidos realizados
- **Enums**: TipoUsuario, StatusPedido, etc.

### View (Visão)
- **Swing**: LoginView, MainView, painéis especializados e dialogs
- Interface gráfica moderna e intuitiva
- Formulários de cadastro e edição
- Tabelas para listagem de dados

### Controller (Controlador)
- **AutenticacaoController**: Gerencia login e autenticação
- **SistemaController**: Lógica de negócio principal

### DAO (Acesso a Dados)
- **UsuarioDAO**: Persistência de usuários
- **FranquiaDAO**: Persistência de franquias
- Armazenamento em arquivos de texto

## Funcionalidades Implementadas

### Autenticação
- Login seguro com validação de credenciais
- Controle de acesso baseado em papéis
- Sessão de usuário ativa

### Gestão de Franquias
- Cadastro de novas franquias
- Listagem em tabela interativa
- Atribuição de gerentes
- Edição e visualização

### Gestão de Usuários
- Cadastro de gerentes e vendedores
- Validação de CPF e email
- Controle de permissões por tipo de usuário

### Interface Gráfica
- Look and Feel nativo do sistema operacional
- Formulários intuitivos
- Tabelas com dados organizados
- Diálogos de confirmação
- Navegação por menus

### Validações
- CPF brasileiro válido
- Email em formato correto
- Campos obrigatórios
- Regras de negócio específicas

## Persistência
- Dados salvos em arquivos de texto
- Parsing automático na inicialização
- Backup automático dos dados

## Tratamento de Erros
- Exceções customizadas (AutenticacaoException, etc.)
- Validação de entrada de dados
- Mensagens de erro informativas na interface
- Recuperação graceful de falhas

## Estrutura de Arquivos
```
src/main/java/com/mycompany/trabalho03oo/
├── model/           # Entidades de negócio
├── controller/      # Lógica de controle
├── view/           
│   └── swing/      # Interface gráfica Swing
├── dao/            # Acesso a dados
├── util/           # Utilitários e validações
└── exception/      # Exceções customizadas
```

## Exemplo de Uso

1. **Inicie o sistema**:
   ```bash
   java -cp target\classes com.mycompany.trabalho03oo.Trabalho03OO
   ```

2. **Faça login como Dono**:
   - Email: admin@franquia.com
   - Senha: 123456

3. **Explore as funcionalidades**:
   - Clique em "Franquias" → "Cadastrar Nova Franquia"
   - Preencha o formulário e selecione um gerente
   - Visualize a franquia criada na tabela
   - Acesse "Gerentes" para ver os disponíveis
   - Cadastre vendedores conforme necessário

4. **Teste outros usuários**:
   - Faça logout e login com gerente/vendedor
   - Observe as diferentes permissões e menus disponíveis

## Características da Interface Swing

### Design Moderno
- Look and Feel do sistema operacional
- Cores e fontes consistentes
- Layout responsivo

### Usabilidade
- Navegação intuitiva por menus
- Formulários organizados
- Feedback visual para ações
- Mensagens de confirmação

### Funcionalidades Avançadas
- Tabelas com dados em tempo real
- Refresh automático de dados
- Diálogos modais para cadastros
- Validação em tempo real

## Notas Técnicas

### Padrão MVC Simplificado
- Interface exclusivamente Swing
- Controller único otimizado
- Models reutilizáveis
- Views especializadas

### Boas Práticas Aplicadas
- Separação clara de responsabilidades
- Validação rigorosa de entrada
- Tratamento robusto de exceções
- Código documentado e modular

### Performance
- Carregamento rápido da interface
- Atualização eficiente de dados
- Gerenciamento adequado de memória

---
**Sistema de Gerenciamento de Franquias - Interface Swing**  
**Trabalho Prático de Orientação a Objetos - DCC025/2025-1**
