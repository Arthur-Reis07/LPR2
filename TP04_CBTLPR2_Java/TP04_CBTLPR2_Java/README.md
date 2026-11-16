# Trabalho Prático 04 — CBTLPR2 (Java)

**Disciplina:** CBTLPR2 (Java) — ADS 471  
**Professor:** Wellington Tuler Moraes  
**Instituição:** IFSP — Campus Cubatão  

## Estrutura dos Arquivos

```text
TP04_CBTLPR2_Java/
├── sql/
│   └── script_aulajava.sql      # Criação do banco aulajava, tabelas tbcargos e tbfuncs e registros iniciais
├── src/
│   ├── Conexao.java             # Configuração da conexão JDBC com o SQL Server
│   └── TrabalhoPratico04.java   # Interface Swing, pesquisa com LIKE + PreparedStatement e navegação no ResultSet
└── README.md                    # Instruções de execução
```

## Passo a Passo para Executar

### 1. Criar o Banco de Dados no SQL Server
1. Abra o **SQL Server Management Studio (SSMS)** (ou Azure Data Studio).
2. Abra e execute o arquivo `sql/script_aulajava.sql`.
3. O script criará o banco `aulajava`, as tabelas `tbcargos` e `tbfuncs` com chave primária e estrangeira, e inserirá registros de exemplo (incluindo `Marcelo`, salário `2000.00`, cargo `Administrativo`).

### 2. Configurar a Conexão JDBC
1. Abra `src/Conexao.java` e ajuste as constantes `SERVIDOR`, `PORTA`, `USUARIO` e `SENHA` conforme a instância do seu SQL Server.
2. Adicione o driver oficial **Microsoft JDBC Driver for SQL Server** (`mssql-jdbc-*.jar`) ao *Classpath* / *Build Path* do seu projeto (Eclipse, NetBeans, IntelliJ ou VS Code).

### 3. Compilar e Executar
- **Via IDE:** Importe os arquivos da pasta `src/` no seu projeto Java, adicione o `.jar` do driver JDBC ao Build Path e execute `TrabalhoPratico04.java`.
- **Via Terminal:**
  ```bash
  javac -cp ".;caminho/para/mssql-jdbc.jar" -d bin src/*.java
  java -cp ".;bin;caminho/para/mssql-jdbc.jar" TrabalhoPratico04
  ```
