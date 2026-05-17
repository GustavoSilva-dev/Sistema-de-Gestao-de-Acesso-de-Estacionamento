# 🚗 Sistema de Gestão de Acesso de Estacionamento

Sistema desktop para gerenciamento de acesso em estacionamentos, desenvolvido em **Java Swing** com banco de dados **MySQL**. Projeto educacional focado em praticar manipulação de dados, operações CRUD e integração com banco de dados relacional.

## 🔨 Tecnologias Utilizadas

- **Java SE 8** - Linguagem de programação
- **Java Swing** - Interface gráfica
- **MySQL** - Banco de dados relacional
- **NetBeans IDE 8.0.2** - Ambiente de desenvolvimento
- **JDBC** - Conexão com banco de dados

---

## 📋 Funcionalidades Principais

### 👤 Gestão de Visitantes
- **Cadastro**: Adicionar novos visitantes com dados pessoais
- **Consulta**: Visualizar lista de todos os visitantes cadastrados
- **Alteração**: Editar informações de visitantes existentes
- **Exclusão**: Remover visitantes do sistema
- **Foto do Visitante**: Upload e armazenamento de foto

### 🚙 Gestão de Veículos
- **Cadastro de Veículo**: Registrar placa, modelo, cor e tipo do veículo
- **Associação ao Visitante**: Vincular veículo via CPF do visitante
- **Tipos de Veículos**: Carro, Moto, Caminhonete
- **Tipos de Vagas**: Colaborador, Visitante, Carga/Descarga
- **Consulta**: Visualizar todos os veículos estacionados

### ⏰ Controle de Entrada e Saída
- **Registro de Entrada**: Timestamp automático ao cadastrar veículo
- **Registro de Saída**: Atualizar hora de saída do estacionamento
- **Retorno de Veículo**: Registrar retorno do veículo ao estacionamento
- **Status do Veículo**: ESTACIONADO ou FORA
- **Histórico**: Acompanhamento de entrada e saída com data/hora

---

## 🗄️ Estrutura do Banco de Dados

### Tabela: Visitante
```
- cli_cpf (PK): CPF do visitante
- cli_nome: Nome completo
- cli_empresa: Empresa representante
- cli_motivo: Motivo da visita
- cli_colaborador: Nome do colaborador relacionado
- cli_foto: Caminho da foto
- cli_placa: Placa do veículo associado
```

### Tabela: Veículo
```
- vei_placa (PK): Placa do veículo
- vei_modelo: Modelo do veículo
- vei_cpf (FK): CPF do proprietário/visitante
- vei_cor: Cor do veículo
- vei_vaga: Tipo de vaga
- vei_tipo: Tipo do veículo
- vei_entrada: Data/hora de entrada (TIMESTAMP)
- vei_saida: Data/hora de saída (TIMESTAMP)
- vei_status: Status atual (ESTACIONADO/FORA)
```

---

## 🚀 Como Instalar e Executar

### Pré-requisitos
- Java SE 8 instalado
- MySQL instalado e em execução
- NetBeans IDE 8.0.2 (opcional, mas recomendado)

### Passo 1: Configurar o Banco de Dados

1. Abra o MySQL e crie um novo banco:
```sql
CREATE DATABASE estacionamento;
USE estacionamento;
```

2. Execute o script SQL fornecido na pasta `/BD` para criar as tabelas necessárias.

### Passo 2: Configurar a Conexão

Abra o arquivo `src/Controller/VEICULO_DAO.java` e configure os dados de conexão:

```java
public static String url = "jdbc:mysql://localhost:3306/estacionamento";
public static String username = "root";
public static String password = "sua_senha";
```

### Passo 3: Compilar e Executar

No NetBeans:
1. Abra o projeto
2. Clique em `Run Project` (F6)
3. Ou compile com: `ant run`

---

## 🔐 Autenticação

O sistema possui uma **autenticação simples predefinida** para fins educacionais:

**Credenciais padrão:**
- **Usuário**: admin@gmail.com
- **Senha**: admin123

Esta é uma autenticação básica implementada para demonstrar conceitos de validação de entrada e controle de acesso.

---

## 🎯 Fluxo de Uso

### 1. Login
- Insira as credenciais predefinidas
- Acesso ao menu principal

### 2. Cadastrar Visitante
- Preencha os dados do visitante
- Selecione uma foto
- Sistema registra data/hora de entrada automaticamente

### 3. Cadastrar Veículo
- Vinculado ao visitante via CPF
- Insira dados: placa, modelo, cor, tipo
- Sistema registra entrada automática

### 4. Visualizar Registros
- Consulte lista de visitantes e veículos
- Acompanhe status em tempo real (ESTACIONADO/FORA)

### 5. Registrar Saída
- Localize o veículo pela placa
- Clique em "Registrar Saída"
- Sistema atualiza timestamp e status

### 6. Registrar Retorno
- Veículo retorna ao estacionamento
- Status volta para ESTACIONADO
- Timestamp de entrada atualizado

---

## 📁 Estrutura do Projeto

```
src/
├── View/              # Interfaces gráficas (Swing)
│   ├── Inicio_GUI.java
│   ├── Menu_GUI.java
│   ├── CadastrarVisitante_GUI.java
│   ├── CadastrarVeiculo_GUI.java
│   ├── VisualizarVisitantes_GUI.java
│   └── VisualizarVeiculo_GUI.java
├── Controller/        # Lógica de negócio e DAOs
│   ├── VISITANTE_DAO.java
│   ├── VEICULO_DAO.java
│   └── TABELA_DAO.java
└── Model/             # Classes de modelo
    ├── Visitante.java
    ├── Veiculo.java
    └── Conecta_DB.java

BD/                   # Scripts de banco de dados
build.xml             # Configuração de build
manifest.mf           # Manifest da aplicação
```

---

## 💡 Conceitos Praticados

✅ **Banco de Dados Relacional**: Estrutura de tabelas com chaves primárias e estrangeiras  
✅ **CRUD Completo**: Create, Read, Update, Delete de dados  
✅ **Conexão JDBC**: Integração entre Java e MySQL  
✅ **Interface Gráfica**: Desenvolvimento com Java Swing  
✅ **Padrão MVC**: Separação entre View, Controller e Model  
✅ **Tratamento de Exceções**: Gerenciamento de erros SQL e de conexão  
✅ **PreparedStatement**: Execução segura de queries SQL  
✅ **Timestamps**: Registro automático de data e hora de eventos  

---

## 📝 Exemplo de Operação

**Cenário: Registrar entrada e saída de um visitante**

1. Novo visitante chega: `João Silva`
2. Cadastro no sistema com CPF e dados pessoais
3. Veículo cadastrado: Placa `ABC-1234`, modelo `Civic`, cor `Prata`
4. Sistema registra: **Entrada = 2026-05-17 14:30:15**
5. Visitante retorna ao veículo
6. Sistema registra saída: **Saída = 2026-05-17 16:45:22**
7. Status do veículo: **FORA**
8. Visitante retorna ao estacionamento
9. Sistema registra retorno: Status volta para **ESTACIONADO**, entrada atualizada

---

## 📌 Notas Importantes

- O projeto foi desenvolvido com fins **educacionais**
- Foco em praticar manipulação de dados e operações com banco de dados
- Recomenda-se estudar o código para aprender sobre integração Java-MySQL
- Possíveis melhorias futuras: adicionar mais validações, logs, e interfaces mais avançadas

---

## 👨‍💻 Autor

**Gustavo Silva**  
Projeto educacional de Sistema de Gestão de Estacionamento

---

## 📄 Licença

Este projeto é fornecido como material educacional.
