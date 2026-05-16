/* =========================================================
   PROJETO: Sistema de Gerenciamento de Academia
   GRUPO: Camila Bandeira de Oliveira, Gabriel Rodrigues Lopes,
          John Lucas Garcia dos Santos, Marina Pereira Marcelino,
          Marina Santos Morais, Vitor Daniel Dorea Santos
   DISCIPLINA: Banco de Dados
   PROFESSOR: Caio Gustavo R. da Cruz
   DATA: 16/05/2026

   DESCRIÇÃO:
   Script para criação das tabelas do sistema.

   REFERÊNCIA:
   Script baseado no dicionário de dados versão 1.0
   ========================================================= */

/* =========================================================
   CONFIGURAÇÕES INICIAIS
   ========================================================= */
SET search_path TO public;

/* =========================================================
   CRIAÇÃO DE OBJETOS
   ========================================================= */

CREATE TABLE plano (
                       id SERIAL PRIMARY KEY,
                       nome_plano VARCHAR(30) NOT NULL,
                       descricao VARCHAR(255),
                       valor_mensal NUMERIC(6,2) NOT NULL,
                       duracao_meses INTEGER NOT NULL,
                       beneficios_incluidos VARCHAR(255)
);

CREATE TABLE aluno (
                       id SERIAL PRIMARY KEY,
                       nome VARCHAR(50) NOT NULL,
                       cpf VARCHAR(14) UNIQUE NOT NULL,
                       telefone VARCHAR(20),
                       data_nascimento DATE NOT NULL,
                       email VARCHAR(100) UNIQUE,
                       data_matricula DATE NOT NULL,
                       id_plano INTEGER,
                       CONSTRAINT fk_aluno_plano FOREIGN KEY (id_plano) REFERENCES plano(id)
);

CREATE TABLE instrutor (
                           id SERIAL PRIMARY KEY,
                           nome VARCHAR(50) NOT NULL,
                           cpf VARCHAR(14) UNIQUE NOT NULL,
                           telefone VARCHAR(20),
                           especialidade VARCHAR(100),
                           horario_trabalho VARCHAR(20)
);

CREATE TABLE aula (
                      id SERIAL PRIMARY KEY,
                      nome VARCHAR(50) NOT NULL,
                      descricao VARCHAR(255),
                      horario VARCHAR(20) NOT NULL,
                      duracao INTEGER NOT NULL,
                      capacidade_maxima INTEGER NOT NULL,
                      alunoinscritos INTEGER,
                      id_instrutor INTEGER,
                      CONSTRAINT fk_aula_instrutor FOREIGN KEY (id_instrutor) REFERENCES instrutor(id)
);

CREATE TABLE inscricao (
                           id SERIAL PRIMARY KEY,
                           id_aluno INTEGER,
                           id_aula INTEGER,
                           CONSTRAINT fk_inscricao_aluno FOREIGN KEY (id_aluno) REFERENCES aluno(id),
                           CONSTRAINT fk_inscricao_aula FOREIGN KEY (id_aula) REFERENCES aula(id)
);

CREATE TABLE frequencia (
                            id SERIAL PRIMARY KEY,
                            id_aluno INTEGER,
                            id_aula INTEGER,
                            data_entrada TIMESTAMP,
                            CONSTRAINT fk_frequencia_aluno FOREIGN KEY (id_aluno) REFERENCES aluno(id),
                            CONSTRAINT fk_frequencia_aula FOREIGN KEY (id_aula) REFERENCES aula(id)
);

/* =========================================================
OBSERVAÇÕES
========================================================= */

-- Dependência de execução dos scripts:
-- 1. Executar 01_create_database.sql
-- 2. Conectar ao banco academiaDB
-- 3. Executar 02_create_tables.sql

-- Ordem recomendada de execução das tabelas:
-- 1. plano
-- 2. aluno
-- 3. instrutor
-- 4. aula
-- 5. inscricao
-- 6. frequencia

-- Executar após criação do banco academiaDB