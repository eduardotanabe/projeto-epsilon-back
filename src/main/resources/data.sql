INSERT INTO marca (nome) VALUES('Honda');

INSERT INTO marca (nome) VALUES('Toyota');

INSERT INTO marca (nome) VALUES('Volkswagen');

INSERT INTO marca (nome) VALUES('Hyundai');

INSERT INTO marca (nome) VALUES('Chevrolet');
	
INSERT INTO marca (nome) VALUES('Fiat');
	
INSERT INTO marca (nome) VALUES('Jeep');
	
INSERT INTO marca (nome) VALUES('Kawazaki');
	
INSERT INTO marca (nome) VALUES('Yamaha');
	
INSERT INTO marca (nome) VALUES('Pegeout');
	
INSERT INTO marca (nome) VALUES('Citroen');

INSERT INTO tipo_veiculo (nome) VALUES('Automóvel');

INSERT INTO tipo_veiculo (nome) VALUES('Motocicleta');

INSERT INTO tipo_veiculo (nome) VALUES('Motoneta');

INSERT INTO tipo_veiculo (nome) VALUES('Caminhonete');

INSERT INTO tipo_veiculo (nome) VALUES('Caminhão');

INSERT INTO tipo_veiculo (nome) VALUES('Caminhão-trator');

INSERT INTO tipo_veiculo (nome) VALUES('Semirreboque');

INSERT INTO veiculo (marca_id, tipo_veiculo_id, modelo, placa_licenca, ano_fabricacao, ano_modelo, sequencial_chassi, sequencial_motor)
	VALUES(7, 1, 'Civic', 'HTD1L56', 2015, 2015, 'fda1s5f1as6fd1f5', 'f1asd56f1sd5a61fdsf1a');
	
INSERT INTO veiculo (marca_id, tipo_veiculo_id, modelo, placa_licenca, ano_fabricacao, ano_modelo, sequencial_chassi, sequencial_motor)
	VALUES(8, 1, 'Fiesta', 'KID8L36', 2003, 2003, '8g9fd1s6g1df1g6d5sf', '1568df1g6fd1h8fd6h');
	
INSERT INTO usuario (nome, cpf, email, contato_particular, contato_profissional, orgao, lotacao, cargo, uf, cidade)
	VALUES('João', '000.000.000-00', 'joao@gmail.com', '99999-9999', '88888-8888', 'CGP', 'Itaquiraí', 'perito', 'MS', 'Itaquiraí');
	
INSERT INTO usuario (nome, cpf, email, contato_particular, contato_profissional, orgao, lotacao, cargo, uf, cidade)
	VALUES('Maria', '111.111.111-11', 'maria@gmail.com', '77777-7777', '66666-6666', 'CGP', 'Eldorado', 'perito', 'MS', 'Eldorado');
	
