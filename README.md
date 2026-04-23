# **📝 Gerenciador de Tarefas (To-Do List) \- Desafio Java Web**

Este projeto é uma aplicação Java Web completa desenvolvida como parte de um desafio técnico. A aplicação permite o gerenciamento de tarefas e responsáveis, utilizando tecnologias robustas do ecossistema Jakarta EE.

---

## **🚀 Tecnologias Implementadas**

A aplicação foi desenvolvida atendendo aos seguintes requisitos:

* **\[A\] JavaServer Faces (JSF 4.0):** Utilizado para a criação da interface dinâmica.  
* **\[B\] PostgreSQL:** Banco de dados relacional utilizado para persistência.  
* **\[C\] JPA (Hibernate):** Implementação de persistência para mapeamento objeto-relacional (ORM).  
* **\[F\] PrimeFaces:** Diferencial utilizado para componentes de UI ricos e responsivos.  
* **\[F\] CDI / Weld:** Injeção de dependência para maior desacoplamento e testabilidade.  
* **\[F\] OmniFaces:** Biblioteca utilitária para facilitar o ciclo de vida do JSF.

---

## **🛠️ Funcionalidades Concluídas**

### **Gerenciamento de Tarefas**

* \[x\] **Criar uma tarefa:** Cadastro completo com título, descrição, responsável, prioridade e prazo.  
* \[x\] **Remover uma tarefa:** Exclusão lógica/física do banco de dados.  
* \[x\] **Listar tarefas:** Visualização em tabela organizada.  
* \[x\] **Concluir tarefa:** Alteração de status; após concluída, a tarefa é movida para a visualização correta.

### **Filtros e Consultas**

* \[x\] **Filtrar por ID:** Busca exata por identificador.  
* \[x\] **Filtrar por Título ou Descrição:** Busca textual flexível.  
* \[x\] **Filtrar por Responsável:** Seleção dinâmica baseada nos responsáveis cadastrados.  
* \[x\] **Filtrar por Situação:** Alternância entre tarefas "Em Andamento" e "Concluídas".

### **Gerenciamento de Responsáveis (Diferencial)**

* \[x\] **Criar responsável:** Cadastro de novos usuários.  
* \[x\] **Listar responsáveis:** Visualização de todos os nomes cadastrados.  
* \[x\] **Editar responsável:** Alteração de nomes existentes.  
* \[x\] **Excluir responsável:** Remoção de usuários do sistema.

---

## **📦 Orientações(Execução Local)**

### **Pré-requisitos**

* **Java 17** ou superior.  
* **Maven 3.8+**.  
* **PostgreSQL** (Rodando localmente ou via Docker).  
* **WildFly 39.0.1.Final** (ou servidor compatível com Jakarta EE 10).

### **1\. Configuração do Banco de Dados**

Crie um banco de dados chamado `db_todo` no seu PostgreSQL:

```SQL  
  CREATE DATABASE db\_todo;
```

### **2\. Configuração do Servidor (WildFly)**

É necessário adicionar o Driver JDBC do Postgres como módulo no WildFly e configurar o DataSource com o JNDI: `java:jboss/datasources/PostgresDS`*)*
caso tenha duvida consulte o arquivo de documentação do wildfly.

### **3\. Build da Aplicação**

Na raiz do projeto, execute:

```Bash  
mvn clean install
```

O arquivo `.war` será gerado na pasta `target/`.

### **4\. Deploy**

Mova o arquivo `.war` gerado para a pasta `standalone/deployments` do seu WildFly e inicie o servidor. Acesse: `http://localhost:8080/desafioEsigGroup-0.0.1-SNAPSHOT/`

---

## **📈 Próximas Melhorias (Roadmap)**

Embora o núcleo da aplicação esteja funcional, as seguintes melhorias estão planejadas:

* **☁️ Publicação em Ambiente Cloud (Deploy):** Configuração de pipeline para publicação automática no Heroku, Render ou AWS.  
* **🎨 UI/UX Refinado:** Implementação de `Growl` e `Messages` do PrimeFaces mais detalhados para confirmar ações (Sucesso ao salvar, avisos de erro, etc) e melhorias na responsividade mobile.  
* **🧪 Testes Unitários:** Implementação de cobertura de testes com **JUnit 5** e **Mockito** para validar as regras de negócio das entidades e DAOs.  
* **🔄 Auditoria:** Implementação do Hibernate Envers para rastrear mudanças nas tarefas.

---

## **👨‍💻 Desenvolvedor**

**Jefferson M.** Projeto desenvolvido para fins avaliativos.

