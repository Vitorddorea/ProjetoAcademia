

/* =========================================================
   INSERTS DE TESTE - SISTEMA ACADEMIA
   ========================================================= */

-- PLANOS
INSERT INTO plano
(nome_plano, descricao, valor_mensal, duracao_meses, beneficios_incluidos)
VALUES
    ('Básico', 'Acesso à musculação', 89.90, 3, 'Musculação'),
    ('Premium', 'Musculação + aulas coletivas', 149.90, 6, 'Musculação, Yoga, Pilates'),
    ('Black', 'Acesso total', 199.90, 12, 'Tudo incluído');


-- ALUNOS
-- aluno com plano ativo
INSERT INTO aluno
(nome, cpf, telefone, data_nascimento, email, data_matricula, id_plano)
VALUES
    ('João Silva', '11111111111', '11999999999',
     '2000-05-10', 'joao@email.com',
     CURRENT_DATE, 1);

-- aluno com plano vencido
INSERT INTO aluno
(nome, cpf, telefone, data_nascimento, email, data_matricula, id_plano)
VALUES
    ('Maria Souza', '22222222222', '11988888888',
     '1998-09-20', 'maria@email.com',
     '2025-01-01', 2);

-- outro aluno ativo
INSERT INTO aluno
(nome, cpf, telefone, data_nascimento, email, data_matricula, id_plano)
VALUES
    ('Carlos Lima', '33333333333', '11977777777',
     '1995-03-15', 'carlos@email.com',
     CURRENT_DATE, 3);


-- INSTRUTORES
INSERT INTO instrutor
(nome, cpf, telefone, especialidade, horario_trabalho)
VALUES
    ('Fernanda Costa', '44444444444', '11966666666',
     'Musculação', '08:00-16:00'),

    ('Ricardo Alves', '55555555555', '11955555555',
     'Yoga', '14:00-22:00');


-- AULAS
INSERT INTO aula
(nome, descricao, horario, duracao, capacidade_maxima, alunosinscritos, id_instrutor)
VALUES
    ('Musculação Avançada',
     'Treino de hipertrofia',
     '08:00',
     60,
     20,
     0,
     1),

    ('Yoga Iniciante',
     'Alongamento e flexibilidade',
     '18:00',
     50,
     15,
     0,
     2);

-- Aulas no mesmo horário
INSERT INTO aula
(nome, descricao, horario, duracao, capacidade_maxima, alunosinscritos, id_instrutor)
VALUES
    ('Alongamento', 'Alongamento', '18:00', 50, 20, 0, 1),

    ('Pilates', 'Core', '18:00', 50, 20, 0, 2);


-- USUÁRIOS
INSERT INTO usuario
(nome, senha, tipo)
VALUES
    ('admin', '123', 'ADMINISTRADOR'),

    ('funcionario1', '123', 'FUNCIONARIO');
