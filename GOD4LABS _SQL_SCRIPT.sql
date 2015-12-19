CREATE DATABASE GOD4LABS;

USE GOD4LABS;

CREATE TABLE usuario(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome VARCHAR(100),
	email VARCHAR(100),
	username VARCHAR(45) UNIQUE,
	senha VARCHAR(45),
	descricao TEXT,
	data_cadastro DATETIME NOT NULL,
	data_nascimento DATE NOT NULL,
	telefoneFixo VARCHAR(15),
	celular VARCHAR(15),
	rua VARCHAR(200),
	numeroCasa INT,
	bairro VARCHAR(50),
	cidade VARCHAR(40),
	estado CHAR(2),
	cep VARCHAR(15),
    cadastrado_por INT,
    FOREIGN KEY (cadastrado_por) REFERENCES usuario(id)
);

CREATE TABLE autoridade(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome VARCHAR(45) UNIQUE,
);

CREATE TABLE usuario_autoridade(
	id_usuario INT NOT NULL,
	id_autoridade INT NOT NULL,
	PRIMARY KEY(id_usuario, id_autoridade),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_autoridade) REFERENCES autoridade(id)
);

CREATE TABLE categoria_equipamento(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome VARCHAR(100) NOT NULL UNIQUE,
	descricao VARCHAR(255)NULL
);

CREATE TABLE equipamento(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome VARCHAR(100) NOT NULL,
	fabricante VARCHAR(50),
	descricao TEXT,
	instrucoes TEXT,
	precaucoes TEXT,
	data_cadastro DATETIME,
	quantidade INT,
	cadastrado_por INT,
	id_categoria INT,
	tombamento VARCHAR(100),
	FOREIGN KEY (cadastrado_por) REFERENCES usuario(id),
	FOREIGN KEY (id_categoria) REFERENCES categoria_equipamento(id)
);

CREATE TABLE usuario_reserva_equipamento(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	descricao TEXT,
	data_inicio DATETIME,
	data_final DATETIME,
	id_usuario INT,
	id_equipamento INT,
	UNIQUE(data_inicio,data_final,id_equipamento),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_equipamento) REFERENCES equipamento(id)
);

CREATE TABLE projeto(
	id INT NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome VARCHAR(100),
	descricao TEXT,
	data_inicio DATE,
	data_final DATE,
	cadastrado_por INT,
	FOREIGN KEY (cadastrado_por) REFERENCES usuario(id)
);

CREATE TABLE usuario_projeto(
	id_usuario INT NOT NULL,
	id_projeto INT NOT NULL,
	cadastrado_por INT,
	FOREIGN KEY (cadastrado_por) REFERENCES usuario(id),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_projeto) REFERENCES projeto(id)
);

-- criação autoridade
INSERT INTO autoridade VALUES('Administrador');
INSERT INTO autoridade VALUES('Orientador');
INSERT INTO autoridade VALUES('Estudante');
-- criação de administrador
INSERT INTO usuario(username,senha,data_cadastro,data_nascimento,nome) VALUES ('admin','admin12345','12/12/1996','12/12/1996','Administrador primário');
INSERT INTO usuario_autoridade VALUES (1,1);
INSERT INTO usuario_autoridade VALUES (1,2);
INSERT INTO usuario_autoridade VALUES (1,3);


