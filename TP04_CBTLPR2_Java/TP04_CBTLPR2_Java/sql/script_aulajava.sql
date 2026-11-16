-- ============================================================================
-- Disciplina: CBTLPR2 (Java) - ADS 471
-- Professor: Wellington Tuler Moraes
-- Trabalho Prático 04 - Banco de Dados SQL Server: aulajava
-- ============================================================================

-- 1. Criação do banco de dados
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = N'aulajava')
BEGIN
    CREATE DATABASE aulajava;
END
GO

USE aulajava;
GO

-- 2. Criação das tabelas (removendo previamente caso já existam)
IF OBJECT_ID('dbo.tbfuncs', 'U') IS NOT NULL
    DROP TABLE dbo.tbfuncs;
GO

IF OBJECT_ID('dbo.tbcargos', 'U') IS NOT NULL
    DROP TABLE dbo.tbcargos;
GO

CREATE TABLE tbcargos (
    cod_cargo SMALLINT NOT NULL,
    ds_cargo  CHAR(20) NULL,
    CONSTRAINT PK_tbcargos PRIMARY KEY (cod_cargo)
);
GO

CREATE TABLE tbfuncs (
    cod_func  DECIMAL(18, 0) IDENTITY(1, 1) NOT NULL,
    nome_func CHAR(30) NULL,
    sal_func  MONEY NULL,
    cod_cargo SMALLINT NULL,
    CONSTRAINT PK_tbfuncs PRIMARY KEY (cod_func),
    CONSTRAINT FK_tbfuncs_tbcargos FOREIGN KEY (cod_cargo)
        REFERENCES tbcargos (cod_cargo)
);
GO

-- 3. Inserção de registros respeitando chaves e relacionamento
INSERT INTO tbcargos (cod_cargo, ds_cargo) VALUES
(1, 'Administrativo'),
(2, 'Desenvolvedor'),
(3, 'Analista de Sistemas'),
(4, 'Gerente de Projetos'),
(5, 'Suporte Técnico');
GO

INSERT INTO tbfuncs (nome_func, sal_func, cod_cargo) VALUES
('Marcelo',           2000.00, 1),
('Marcelo Silva',     4500.00, 2),
('Marcelo Santos',    5200.50, 3),
('Mariana Costa',     3800.00, 1),
('Carlos Oliveira',   6800.00, 4),
('Ana Paula Souza',   2950.00, 5),
('Fernando Almeida',  4900.00, 2);
GO

-- Consulta de verificação
SELECT f.cod_func, f.nome_func, f.sal_func, f.cod_cargo, c.ds_cargo
FROM tbfuncs f
LEFT JOIN tbcargos c ON f.cod_cargo = c.cod_cargo;
GO
