# Sistema de Gerenciamento de Franquias

Trabalho Prático de Orientação a Objetos - DCC025  
Professor: Gleiph Ghiotto Lima de Menezes  
UFJF - Universidade Federal de Juiz de Fora

## Descrição

Este projeto implementa um sistema de gerenciamento de franquias que permite o controle de diferentes tipos de usuários (Dono, Gerente e Vendedor), com funcionalidades específicas para cada perfil.

## Funcionalidades Implementadas

### ✅ Conceitos de OO Utilizados
- **Classes e Objetos**: Modelagem completa do domínio
- **Pacotes**: Organização em pacotes lógicos (model, view, controller, dao, util, exceptions)
- **Herança**: Hierarquia de usuários (Usuario → Dono/Gerente/Vendedor)
- **Polimorfismo**: Diferentes comportamentos para tipos de usuários
- **Interfaces**: GenericDAO para operações CRUD
- **Coleções**: List, Stream API para manipulação de dados
- **Tratamento de Exceções**: Exceções personalizadas e validações
- **Persistência em Arquivos**: Armazenamento em arquivos texto

### 🏢 Funcionalidades do Dono
- ✅ Gerenciar franquias (cadastrar, listar, remover)
- ✅ Gerenciar gerentes (cadastrar, remover, listar sem franquia)
- ✅ Visualizar indicadores básicos das franquias
- 🔄 Ranking de vendedores (em desenvolvimento)

### 👔 Funcionalidades do Gerente
- ✅ Gerenciar vendedores da sua franquia
- ✅ Listar vendedores por desempenho
- 🔄 Controle de pedidos (em desenvolvimento)
- 🔄 Administração de estoque (em desenvolvimento)
- 🔄 Relatórios da unidade (em desenvolvimento)

### 🛒 Funcionalidades do Vendedor
- 🔄 Cadastrar pedidos (em desenvolvimento)
- 🔄 Visualizar seus pedidos (em desenvolvimento)
- 🔄 Solicitar alterações de pedidos (em desenvolvimento)

## Arquitetura - Padrão MVC

### Model (Modelo)
- `Usuario` (classe abstrata): Dono, Gerente, Vendedor
- `Franquia`: Representa uma unidade da rede
- `Endereco`: Informações de localização
- `Produto`: Itens do estoque
- `Pedido` e `ItemPedido`: Vendas realizadas
- Enums: `TipoUsuario`, `FormaPagamento`, `ModalidadeEntrega`

### View (Visão)
- `SistemaView`: Interface de linha de comando
- Menus específicos para cada tipo de usuário
- Validação de entrada do usuário

### Controller (Controlador)
- `SistemaController`: Lógica de negócio principal
- `AutenticacaoController`: Controle de login/logout
- Validação de permissões por tipo de usuário

### DAO (Data Access Object)
- `GenericDAO<T>`: Interface para operações CRUD
- `UsuarioDAO`: Persistência de usuários
- `FranquiaDAO`: Persistência de franquias
- Armazenamento em arquivos texto

## Validações Implementadas

### ValidadorUtil
- ✅ Validação de strings obrigatórias (mínimo 2 caracteres)
- ✅ Validação de email (formato básico)
- ✅ Validação de CPF (algoritmo completo)
- ✅ Validação de senha (mínimo 6 caracteres)
- ✅ Validação de valores monetários
- ✅ Validação de quantidades
- ✅ Validação de CEP

## Como Executar

### Pré-requisitos
- Java 21 ou superior
- Maven (para gerenciamento de dependências)

### Executando o Sistema
```bash
# Compilar o projeto
mvn compile

# Executar a aplicação
mvn exec:java
```

### Dados de Exemplo
O sistema cria automaticamente usuários de teste:

**DONO:**
- Email: `joao@franquia.com`
- Senha: `123456`

**GERENTES:**
- Email: `maria@franquia.com` / Senha: `123456`
- Email: `pedro@franquia.com` / Senha: `123456`

**VENDEDORES:**
- Email: `ana@franquia.com` / Senha: `123456`
- Email: `carlos@franquia.com` / Senha: `123456`
- Email: `lucia@franquia.com` / Senha: `123456`

## Testes Unitários

Execute os testes de validação:
```bash
java com.mycompany.trabalho03oo.test.ValidadorUtilTest
```

## Estrutura do Projeto

```
src/main/java/com/mycompany/trabalho03oo/
├── Trabalho03OO.java           # Classe principal
├── controller/                 # Controladores (MVC)
│   ├── AutenticacaoController.java
│   └── SistemaController.java
├── dao/                        # Acesso a dados
│   ├── GenericDAO.java
│   ├── UsuarioDAO.java
│   └── FranquiaDAO.java
├── exceptions/                 # Exceções personalizadas
│   ├── ValidacaoException.java
│   └── AutenticacaoException.java
├── model/                      # Modelos de dados
│   ├── Usuario.java (abstrata)
│   ├── Dono.java
│   ├── Gerente.java
│   ├── Vendedor.java
│   ├── Franquia.java
│   ├── Endereco.java
│   ├── Produto.java
│   ├── Pedido.java
│   ├── ItemPedido.java
│   └── enums/
├── util/                       # Utilitários
│   ├── ValidadorUtil.java
│   └── InicializadorDados.java
├── view/                       # Interface do usuário
│   └── SistemaView.java
└── test/                       # Testes unitários
    └── ValidadorUtilTest.java
```

## Persistência de Dados

Os dados são armazenados em arquivos texto no diretório raiz:
- `usuarios.txt`: Informações de todos os usuários
- `franquias.txt`: Dados das franquias

## Funcionalidades Futuras

- [ ] Sistema completo de produtos e estoque
- [ ] Gestão completa de pedidos
- [ ] Relatórios avançados e indicadores
- [ ] Interface gráfica (Swing/JavaFX)
- [ ] Exportação de relatórios
- [ ] Sistema de backup automático
- [ ] Logs de auditoria

## Autor

**Nome:** [Seu Nome]  
**Matrícula:** 84398  
**Disciplina:** DCC025 - Orientação a Objetos  
**Universidade:** UFJF - Universidade Federal de Juiz de Fora
