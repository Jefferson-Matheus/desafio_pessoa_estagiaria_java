### **1\. Preparação do Módulo (Física)**
OBS - Caminho até a pasta para executar os comandos: <onde_esta_o_seu_diretorio/wildfly-39.0.1.Final/standalone/bin
O primeiro passo foi criar a estrutura de pastas e o arquivo `module.xml` dentro do servidor. Como o comando `module add` falhou na primeira vez, a solução foi **limpar e refazer**:

* **Comando de Limpeza:**

```Bash

rm \-rf /home/jeffersonm/Downloads/wildfly-39.0.1.Final/modules/org/postgresql  
``` 

* **Comando de Criação do Módulo:** Note que simplificamos as dependências para evitar conflitos com o Jakarta EE 10:


```Bash

./jboss-cli.sh \--command="module add \--name=org.postgresql \--resources=\<caminho\_jar\> \--dependencies=jakarta.transaction.api"

``` 

---

### **2\. Registro do Driver (Lógica)**

Com o arquivo físico no lugar, precisamos dizer ao subsistema de conexões do WildFly que esse driver existe. **O servidor precisava estar ligado** para este passo:

* **Comando de Registro:**

```Bash

./jboss-cli.sh \--connect \--command="/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver)"

``` 

---

### **3\. Criação do DataSource**

Este passo cria a "ponte" final entre o servidor e o seu banco de dados `db_todo`.

* **Comando de Criação:**

```Bash
./jboss-cli.sh \--connect \--command="data-source add \--name=PostgresDS \--jndi-name=java:jboss/datasources/PostgresDS \--driver-name=postgresql \--connection-url=jdbc:postgresql://localhost:5432/db\_todo \--user-name=\<seu\_usuario\> \--password=\<sua\_senha\>  \--enabled=true"
``` 

---

### **4\. Teste de Conexão**

Para validar se o WildFly conseguia "conversar" com o PostgreSQL:

* **Comando de Teste:**

```Bash

./jboss-cli.sh \--connect \--command="/subsystem=datasources/data-source=PostgresDS:test-connection-in-pool"

``` 

*O retorno esperado foi: `{"outcome" => "success"}`.*

