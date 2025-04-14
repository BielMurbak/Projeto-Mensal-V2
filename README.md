🛒 Projeto Mensal 02 - Sistema de E-commerce

Curso: Análise e Desenvolvimento de Sistemas

Semestre: 3º semestre

🔍 Descrição:
Este projeto consiste em um sistema de e-commerce completo, voltado para a venda de tênis, camisetas, roupas e outros produtos. O sistema foi desenvolvido com foco em usabilidade, segurança e controle administrativo.

🧩 Funcionalidades:
👤 Sistema de login e cadastro de usuários (clientes e administradores)

🛍️ Catálogo de produtos, com tipo, nome, preço e quantidade

🛒 Carrinho de compras e finalização com diferentes formas de pagamento

📦 Escolha entre entrega ou retirada

🔐 Área Administrativa

Cadastro, edição e remoção de produtos

Controle de usuários


.


⚙️ Tecnologias

Java 17: Backend com POO.

PostgreSQL: Banco de dados relacional.

Maven: Gerenciador de dependências e build.

.


🛠 Requisitos Funcionais

Menu interativo com operações CRUD.

Persistência no PostgreSQL.

Tratamento de exceções.

Execução autônoma com instruções no GitHub.

Uso de Programação Orientada a Objetos.

.

🛠 Requisitos para rodar o projeto




Baixar e instalar o Java 17:

Acesse o site oficial do OpenJDK 17 ou o Oracle JDK 17.

Baixe e instale a versão correspondente ao seu sistema operacional.

Após a instalação, configure o JAVA_HOME no seu sistema, apontando para o diretório de instalação do JDK 17.

Configurar o IntelliJ IDEA para Java 17:

Abra o IntelliJ IDEA e vá até File > Project Structure > Project.

Em Project SDK, selecione o JDK 17 que você instalou (caso não esteja na lista, clique em Add SDK e selecione o diretório do JDK 17).

Em Project Language Level, selecione o nível 17 - Sealed types, pattern matching.

Verifique o Maven:

Certifique-se de que o Maven também esteja configurado corretamente para usar o Java 17.

No IntelliJ IDEA, vá até File > Settings > Build, Execution, Deployment > Build Tools > Maven.

Na seção JDK for Importer, selecione o JDK 17.



Baixe e instale o Maven:

Faça o download do arquivo:
apache-maven-3.9.9-bin.zip

Extraia e configure corretamente o Maven no seu sistema.

Instale o PostgreSQL:

Crie um banco de dados com o nome:
eCommerce

Anote seu usuário e senha, pois serão usados na configuração do Hibernate.

Instale uma IDE (recomendado: IntelliJ IDEA):

Clone o repositório do projeto:

Acesse seu terminal ou Git Bash e digite:
git clone git@github.com:BielMurbak/Projeto-Mensal-V2.git

Configure o Hibernate:

No IntelliJ, abra o projeto clonado.

Vá até a pasta:
src/main/resources

Abra o arquivo hibernate.cfg.xml

Edite os campos:

<property name="connection.username">SEU_USUARIO</property>

<property name="connection.password">SUA_SENHA</property>

<property name="connection.url">jdbc:postgresql://localhost:5432/eCommerce</property>

Execute o projeto:

No IntelliJ, vá para:

src/main/java/br/com/ecommerce/system

Abra o arquivo SystemUser.java e clique em Run para executar o sistema.

Em seguida, execute o SystemLoginOrCadastro.java para acessar a área de login e visualizar os produtos disponíveis.

.

🛠 DER (Diagrama Entidade-Relacionamento):

![image](https://github.com/user-attachments/assets/a5c43f80-35f0-4199-a5c6-2752474588e8)

![image](https://github.com/user-attachments/assets/2c2f26b6-87d3-454c-9f01-a83e9fd36ea8)

https://lucid.app/lucidchart/1ee97289-0c4c-4689-ba6f-80c036a646ed/edit?viewport_loc=832%2C-216%2C1609%2C816%2C0_0&invitationId=inv_3cbe8fad-3549-486c-9fe4-087ba063ade8

.

🛠 Intregantes

-Rafael Carlos Scarabelot (https://github.com/RafaelScarabelot)

-Gabriel Murbak Scarabelot (https://github.com/BielMurbak) 

.

🛠 Tecnologias utilizadas:

![Windows](https://img.shields.io/badge/WINDOWS-0078D6?style=for-the-badge&logo=windows&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/INTELLIJIDEA-000000?style=for-the-badge&logo=intellijidea&logoColor=white)
![Java](https://img.shields.io/badge/JAVA-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/POSTGRESQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
<img src="https://github.com/user-attachments/assets/1914a67f-dc17-4547-aa03-5f7d88e21893" width="90">

