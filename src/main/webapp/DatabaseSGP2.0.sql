CREATE DATABASE sgp;

USE sgp;

CREATE TABLE perfil(
id_perfil int auto_increment NOT NULL,
nome varchar(150) NOT NULL,
descricao varchar(255),
CONSTRAINT perfil_pkey PRIMARY KEY(id_perfil)
);
CREATE TABLE grau_instrucao(
id_grauInstrucao int auto_increment NOT NULL,
descricao varchar(255),
CONSTRAINT grauInstrucao_pkey PRIMARY KEY(id_grauInstrucao)
);

CREATE TABLE profissao(
id_profissao int auto_increment NOT NULL,
descricao varchar(255),
CONSTRAINT grauInstrucao_pkey PRIMARY KEY(id_profissao)
);


--Grau de Instrução
INSERT INTO grau_instrucao (descricao) VALUES ('Ensino Fundamental Completo');
INSERT INTO grau_instrucao (descricao) VALUES ('Ensino Fundamental Incompleto');
INSERT INTO grau_instrucao (descricao) VALUES ('Ensino Médio Completo' );
INSERT INTO grau_instrucao (descricao) VALUES ('Ensino Médio Incompleto');
INSERT INTO grau_instrucao (descricao) VALUES ('Ensino Superior Completo');
INSERT INTO grau_instrucao (descricao) VALUES ('Ensino Superior Incompleto');
INSERT INTO grau_instrucao (descricao) VALUES ('Pós Graduação');
INSERT INTO grau_instrucao (descricao) VALUES ('Mestrado');
INSERT INTO grau_instrucao (descricao) VALUES ('Pós Doutorado');


INSERT INTO usuario(nome, email, cpf, senha, celular, id_cidade, id_perfil, ativo) VALUES ('Cícero Regis', 'ciceroregis25@gmail.com','04292329184', md5('123'),'6111111111',2,1,true);


INSERT INTO perfil (nome, descricao) VALUES ('ROLE_ADMINISTRADOR','Administrador');
INSERT INTO perfil (nome, descricao) VALUES ('ROLE_COORDENADOR','Coordenador');
INSERT INTO perfil (nome, descricao) VALUES ('ROLE_COLABORADOR','Colaborador');
INSERT INTO perfil (nome, descricao) VALUES ('ROLE_ELEITOR','Eleitor');


INSERT INTO cidade (nome, latitude, longitude) VALUES ('Águas Claras', -15.848582, -48.015855);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Brasilia', -15.793723, -47.882928);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Brazlândia', -15.669534, -48.19794);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Candangolândia', -15.856065, -47.946796);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Ceilândia', -15.816546, -48.116534);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Cruzeiro', -15.79067, -47.937495);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Fercal', -15.597162, -47.872274);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Gama', -16.015832, -48.064189);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Guará', -15.830812, -47.972742);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Itapoã', -15.750582, -47.771265);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Jardim Botânico', -15.750582, -47.771265);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Lago Norte', -15.740331, -47.856359);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Lago Sul', -15.844859, -47.839361);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Núcleo Bandeirante', -15.871013, -47.969031);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Paranoá', -15.770335, -47.779577);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Park Way', -15.869207, -47.986256);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Planaltina', -15.617693, -47.657975);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Recanto das Emas', -15.909893, -48.082333);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Riacho Fundo I', -15.884723, -48.011168);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Riacho Fundo II', -15.920018, -48.0036381);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Samambaia', -15.879334, -48.098012);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Santa Maria', -16.028062, -48.003073);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('São Sebastião', -15.904397, -47.770858);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('SCIA-Estrutural', -15.782993, -47.98683);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('SIA', -15.801658, -47.966445);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Sobradinho I',-15.64877, -47.793515);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Sobradinho II',-15.637746, -47.826797);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Sudoeste-Octogonal', -15.794583, -47.921973);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Taguatinga', -15.832633, -48.056812);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Varjão', -15.708919, -47.877904);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Vicente Pires', -15.805762, -48.025969);

-- GOIAS

INSERT INTO cidade (nome, latitude, longitude) VALUES ('Águas Lindas de Goiás', -15.7714, -48.2488);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Céu Azul', -16.055436, -48.001566);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Jardim Ingá', -16.146013, -47.946582);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Lago Azul', -16.095201, -48.074122);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Luziânia', -16.204685, -47.926820);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Ocidental', -16.123985, -47.815436);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Novo Gama', -16.125794, -48.079837);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Pacaembu', -16.098941, -48.018852);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Pedregal', -16.082709, -48.035030);
INSERT INTO cidade (nome, latitude, longitude) VALUES ('Valparaíso', -16.069866, -47.985001);


--Apenas algumas das principais profissões  
INSERT INTO profissao(descricao) VALUES('Reparador de equipamentos elétricos');
INSERT INTO profissao(descricao) VALUES('Agente imobiliário');
INSERT INTO profissao(descricao) VALUES('Faxineiro');
INSERT INTO profissao(descricao) VALUES('Zelador');
INSERT INTO profissao(descricao) VALUES('Técnico em Engenharia');
INSERT INTO profissao(descricao) VALUES('Executivo Sênior');
INSERT INTO profissao(descricao) VALUES('Escritor');
INSERT INTO profissao(descricao) VALUES('Motorista');
INSERT INTO profissao(descricao) VALUES('Professor');
INSERT INTO profissao(descricao) VALUES('Serralheiro');
INSERT INTO profissao(descricao) VALUES('Gerente de hotel');
INSERT INTO profissao(descricao) VALUES('Lixeiro');
INSERT INTO profissao(descricao) VALUES('Engenheiro');
INSERT INTO profissao(descricao) VALUES('Caixa');
INSERT INTO profissao(descricao) VALUES('Estoquista');
INSERT INTO profissao(descricao) VALUES('Bombeiro');
INSERT INTO profissao(descricao) VALUES('Policial');
INSERT INTO profissao(descricao) VALUES('Marinheiro');
INSERT INTO profissao(descricao) VALUES('Pintor');
INSERT INTO profissao(descricao) VALUES('Comprador');
INSERT INTO profissao(descricao) VALUES('Vendedor');
INSERT INTO profissao(descricao) VALUES('Fotografo');
INSERT INTO profissao(descricao) VALUES('Doméstica');
INSERT INTO profissao(descricao) VALUES('Militar');
INSERT INTO profissao(descricao) VALUES('Açogueiro');
INSERT INTO profissao(descricao) VALUES('Radialista');
INSERT INTO profissao(descricao) VALUES('Reporter');
INSERT INTO profissao(descricao) VALUES('Leitor de água e luz');
INSERT INTO profissao(descricao) VALUES('Lavador de pratos');
INSERT INTO profissao(descricao) VALUES('Dentista');
INSERT INTO profissao(descricao) VALUES('Dermatologista');
INSERT INTO profissao(descricao) VALUES('Contador');
INSERT INTO profissao(descricao) VALUES('Médico');
INSERT INTO profissao(descricao) VALUES('Desenvolvedor web');
INSERT INTO profissao(descricao) VALUES('Professor');
INSERT INTO profissao(descricao) VALUES('Engenheiro ambiental');
INSERT INTO profissao(descricao) VALUES('Geólogo');
INSERT INTO profissao(descricao) VALUES('Engenheiro cívil');
INSERT INTO profissao(descricao) VALUES('Advogado');
INSERT INTO profissao(descricao) VALUES('Analista de Banco de Dados');
INSERT INTO profissao(descricao) VALUES('Designer');
INSERT INTO profissao(descricao) VALUES('DBA');
INSERT INTO profissao(descricao) VALUES('DJ');
INSERT INTO profissao(descricao) VALUES('Digitador');
INSERT INTO profissao(descricao) VALUES('Adminstrador');
INSERT INTO profissao(descricao) VALUES('HR');
INSERT INTO profissao(descricao) VALUES('Auxiliar admnistrativo');
INSERT INTO profissao(descricao) VALUES('Mecânico');
INSERT INTO profissao(descricao) VALUES('Pedreiro');
INSERT INTO profissao(descricao) VALUES('Atendente');
INSERT INTO profissao(descricao) VALUES('Programador');
INSERT INTO profissao(descricao) VALUES('Veterinário');
INSERT INTO profissao(descricao) VALUES('Vigilante');
INSERT INTO profissao(descricao) VALUES('Arquitato');
INSERT INTO profissao(descricao) VALUES('Auxiliar de Produção');
INSERT INTO profissao(descricao) VALUES('Garçom');
INSERT INTO profissao(descricao) VALUES('Empregada Doméstica');
INSERT INTO profissao(descricao) VALUES('Motorista');
INSERT INTO profissao(descricao) VALUES('Fiscal');
INSERT INTO profissao(descricao) VALUES('Auxiliar de Limpeza');






CREATE TABLE cidade(
id_cidade int auto_increment NOT NULL,
nome varchar(150) NOT NULL,
latitude double,
longitude double,
CONSTRAINT cidade_pkey PRIMARY KEY(id_cidade)
);

CREATE TABLE usuario(
 id_usuario  int auto_increment NOT NULL,
 id_responsavel int,
 nome varchar(255) NOT NULL,
 email text,
 cpf varchar(14),
 senha text,
 telefone text,
 celular text,
 whatsapp text,
 telegram text,
 facebook text,
 instagram text,
 twiter text,
 titulo varchar(255),
 zona varchar(255),
 secao varchar(255),
 dt_nascimento date,
 dt_registro date,
 id_cidade int,
 id_perfil int,
 id_grauInstrucao int,
 id_profissao int,
 ativo boolean default true,
 pw text,
 CONSTRAINT usuario_pkey PRIMARY KEY(id_usuario)
);


CREATE TABLE mensagem(
id_mensagem int auto_increment NOT NULL,
id_remetente int,
id_destinatario int,
dt_registro date,
assunto varchar(255),
mensagem text,
ic_status int,
CONSTRAINT mensagem_pkey PRIMARY KEY(id_mensagem)
);

CREATE TABLE log_cadastro(
id_log_cadastro int auto_increment NOT NULL,
id_usuario int,
id_cadastro int,
dt_registro date,
CONSTRAINT log_cadastro_pkey PRIMARY KEY(id_log_cadastro)
);