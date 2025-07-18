-- USUARIO
INSERT INTO tb_usuario (login, senha, nivel_acesso) VALUES ('admin', '123456', 'ADMIN');
INSERT INTO tb_usuario (login, senha, nivel_acesso) VALUES ('mariana.santos@person.com', '123456', 'PACIENTE');
INSERT INTO tb_usuario (login, senha, nivel_acesso) VALUES ('ana.paula@nutri.com', '123456', 'NUTRICIONISTA');


-- PACIENTE
INSERT INTO tb_paciente (nome, email, senha) VALUES ('Lucas Almeida', 'lucas.almeida@person.com', '123456');
INSERT INTO tb_paciente (nome, email, senha) VALUES ('Mariana Santos', 'mariana.santos@person.com', '123456');
INSERT INTO tb_paciente (nome, email, senha) VALUES ('Fernando Costa', 'fernando.costa@person.com', '123456');

-- NUTRICIONISTA
INSERT INTO tb_nutricionista (nome, email, senha, foto, instagram, email_contato, telefone) VALUES ('Dra. Ana Paula', 'ana.paula@nutri.com', '123456', NULL, 'nutri.anapaula', 'contato@nutri.com', 11999998888);
INSERT INTO tb_nutricionista (nome, email, senha, foto, instagram, email_contato, telefone) VALUES ('Dr. João Vitor', 'joao.vitor@nutri.com', '123456', NULL, 'dr.joaonutri', 'joaovitor@nutrisaude.com', 21988887777);
INSERT INTO tb_nutricionista (nome, email, senha, foto, instagram, email_contato, telefone) VALUES ('Dra. Camila Rocha', 'camila.rocha@nutri.com', '123456', NULL, 'nutri.camilarocha', 'contato@equilibrio.com', 31977776666);

-- RECEITA
INSERT INTO tb_receita (nutricionista_id, titulo, rendimento, tempo, preparo, horario, foto) VALUES (1, 'Salada de Frango', 2, 15.5, 'Corte os ingredientes e misture tudo.', 2, NULL);
INSERT INTO tb_receita (nutricionista_id, titulo, rendimento, tempo, preparo, horario, foto) VALUES (2, 'Panqueca Integral', 4, 30.0, 'Misture os ingredientes e frite em uma frigideira.', 0, NULL);
INSERT INTO tb_receita (nutricionista_id, titulo, rendimento, tempo, preparo, horario, foto) VALUES (3, 'Sopa de Legumes', 6, 45.0, 'Cozinhe os legumes até ficarem macios.', 3, NULL);

-- INGREDIENTE
INSERT INTO tb_ingrediente (descricao, calorias, foto) VALUES ('Peito de Frango', 165.0, NULL), ('Alface', 15.0, NULL), ('Tomate', 18.0, NULL);
INSERT INTO tb_ingrediente (descricao, calorias, foto) VALUES ('Farinha Integral', 340.0, NULL), ('Ovo', 68.0, NULL), ('Leite Desnatado', 42.0, NULL);
INSERT INTO tb_ingrediente (descricao, calorias, foto) VALUES ('Cenoura', 41.0, NULL), ('Batata', 77.0, NULL), ('Abobrinha', 17.0, NULL);

-- RECEITA_INGREDIENTE
INSERT INTO tb_receita_ingrediente (receita_id, ingrediente_id, quantidade) VALUES (1, 1, '150g'), (1, 2, '3 folhas'), (1, 3, '1 unidade'); 
INSERT INTO tb_receita_ingrediente (receita_id, ingrediente_id, quantidade) VALUES (2, 4, '100g'), (2, 5, '2 unidades'), (2, 6, '150ml');
INSERT INTO tb_receita_ingrediente (receita_id, ingrediente_id, quantidade) VALUES (3, 7, '1 unidade'), (3, 8, '2 unidades'), (3, 9, '1/2 unidade');

-- PACIENTE_RECEITA
INSERT INTO tb_paciente_receita (paciente_id, receita_id, data_favoritacao) VALUES (1, 1, '2025-07-10'), (2, 2, '2025-07-11'), (3, 3, '2025-07-12'), (2, 1, '2025-07-13'), (3, 2, '2025-07-14'); 

-- CONSUMO 
INSERT INTO tb_consumo (paciente_id, datahora_consumo) VALUES (1, '2025-07-14 08:30:00'), (2, '2025-07-14 12:15:00'), (3, '2025-07-14 19:45:00'); 



