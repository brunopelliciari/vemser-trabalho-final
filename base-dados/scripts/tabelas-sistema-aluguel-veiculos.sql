DROP TABLE LOCACAO;
DROP TABLE VEICULO;
DROP TABLE CARTAO_CREDITO;
DROP TABLE CLIENTE;
DROP TABLE CONTATO;
DROP TABLE ENDERECO_CLIENTE;
DROP TABLE FUNCIONARIO;

DROP SEQUENCE SEQ_LOCACAO;
DROP SEQUENCE SEQ_VEICULO;
DROP SEQUENCE SEQ_CARTAO_CREDITO;
DROP SEQUENCE SEQ_CLIENTE;
DROP SEQUENCE SEQ_CONTATO;
DROP SEQUENCE SEQ_ENDERECO_CLIENTE;
DROP SEQUENCE SEQ_FUNCIONARIO;


CREATE TABLE FUNCIONARIO(
id_funcionario INT PRIMARY KEY NOT NULL,
nome VARCHAR2 (200) NOT NULL,
cpf CHAR (11) UNIQUE NOT NULL,
matricula INT UNIQUE NOT NULL
);

CREATE TABLE ENDERECO_CLIENTE (
id_endereco INT PRIMARY KEY NOT NULL,
rua VARCHAR2 (200) NOT NULL,
numero VARCHAR2(6) NOT NULL,
bairro VARCHAR2(50) NOT NULL,
cidade VARCHAR2(40) NOT NULL,
estado VARCHAR2(40) NOT NULL,
cep CHAR (9) NOT NULL,
complemento VARCHAR2(100) NOT NULL
);

CREATE TABLE CONTATO (
id_contato INT PRIMARY KEY NOT NULL,
telefone VARCHAR2(20) NOT NULL,
email VARCHAR2(50) NOT NULL
);

CREATE TABLE CLIENTE (
id_cliente INT PRIMARY KEY NOT NULL,
nome VARCHAR2 (200) NOT NULL,
cpf CHAR (11) UNIQUE NOT NULL,
id_contato INT NOT NULL,
id_endereco INT NOT NULL,
CONSTRAINT FK_CLIENTE_CONTATO_ID_CONTATO FOREIGN KEY ( ID_CONTATO ) REFERENCES CONTATO( ID_CONTATO ),
CONSTRAINT FK_CLIENTE_ENDERECO_ID_ENDER FOREIGN KEY ( ID_ENDERECO ) REFERENCES ENDERECO_CLIENTE( ID_ENDERECO )
);

CREATE TABLE CARTAO_CREDITO(
id_cartao INT PRIMARY KEY NOT NULL,
numero VARCHAR2 (20) NOT NULL,
bandeira_cartao VARCHAR2(10),
validade VARCHAR2 (7) NOT NULL,
limite NUMBER (10, 2) NOT NULL
);

CREATE TABLE VEICULO(
id_veiculo INT PRIMARY KEY NOT NULL,
marca VARCHAR2(200) NOT NULL,
modelo VARCHAR2(200) NOT NULL,
cor VARCHAR2 (40) NOT NULL,
ano NUMBER (4, 0) NOT NULL,
quilometragem NUMBER (8, 2) NOT NULL,
valor_locacao_diario NUMBER (6,2) NOT NULL,
disponibilidade INT NOT NULL,
placa VARCHAR2(7) NOT NULL
);


CREATE TABLE LOCACAO(
id_locacao INT PRIMARY KEY NOT NULL,
data_locacao DATE NOT NULL,
data_devolucao DATE NOT NULL,
quilometragem_adicao NUMBER (8, 2) NOT NULL,
valor_locacao_total NUMBER (14, 2) NOT NULL,
id_cliente INT NOT NULL,
id_veiculo INT NOT NULL,
id_funcionario INT NOT NULL,
id_cartao INT NOT NULL,
CONSTRAINT FK_LOCACAO_CLIENTE_ID_CLIENTE FOREIGN KEY ( ID_CLIENTE ) REFERENCES CLIENTE( ID_CLIENTE ),
CONSTRAINT FK_LOCACAO_VEICULO_ID_VEICULO FOREIGN KEY ( ID_VEICULO ) REFERENCES VEICULO( ID_VEICULO ),
CONSTRAINT FK_LOCACAO_FUNCIONARIO_ID_FUN FOREIGN KEY ( ID_FUNCIONARIO ) REFERENCES FUNCIONARIO( ID_FUNCIONARIO ),
CONSTRAINT FK_LOCACAO_CARTAO_ID_CARTAO FOREIGN KEY ( ID_CARTAO ) REFERENCES CARTAO_CREDITO(ID_CARTAO )
);


CREATE SEQUENCE SEQ_VEICULO
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE SEQ_FUNCIONARIO
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE SEQ_CLIENTE
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE SEQ_LOCACAO
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE SEQ_CARTAO_CREDITO
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE SEQ_CONTATO
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;

CREATE SEQUENCE SEQ_ENDERECO_CLIENTE
START WITH 1
INCREMENT BY 1
NOCACHE NOCYCLE;


INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Honda', 'Civic', 'Preto', 2019, 10000, 220, 1, 'APT0192');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Honda', 'Fit', 'Preto', 2020, 8000, 160, 1, 'TSR1635');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Toyota', 'Corolla', 'Cinza', 2019, 12000, 220, 1, 'BEH8574');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Fiat', 'Uno', 'Verde', 2021, 7000, 120, 1, 'IAT6354');

INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Volkswagen', 'Taos', 'azul', 2021, 0000, 400, 1, 'NUS8273');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Volkswagen', 'jetta', 'Preto', 2022, 80, 250, 1, 'PQI9832');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'hyundai', 'Creta', 'Branco', 2019, 1300, 200, 1, 'MCN0932');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Mercedes-Benz', 'GLB', 'Cinza', 2022, 500, 1200, 2, 'INT7463');

INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Chevrolet', 'Corsa Premium', 'Preto', 2009, 56000, 120, 2, 'VAR3121');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Chevrolet', 'Onix', 'Prata', 2016, 98000, 220, 2, 'LUI2924');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Volkswagem', 'Gol G6', 'Vermelho', 2020, 87000, 200, 2, 'KAR0620');
INSERT INTO VEICULO (ID_VEICULO, MARCA, MODELO, COR, ANO, QUILOMETRAGEM, VALOR_LOCACAO_DIARIO, DISPONIBILIDADE, PLACA)
VALUES (SEQ_VEICULO.nextval, 'Renault', 'Clio', 'Branco', 2010, 70000, 130, 2, 'TUL0932');

SELECT * FROM VEICULO;

INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Dudu do Palmeiras', '85235795125', '123');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Luciano Huck', '89512595125', '456');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Michael Jackson', '19735795108', '789');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Silvio Santos', '98563210444', '145');

INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Maria fernandes', '52355125209', '369');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Eva Nunes', '99512595025', '586');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Lucas Souza', '89735795105', '776');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Nathanael Queiroz', '18554210444', '195');

INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Nikola Tesla', '35184287612', '140');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Thomas Edison', '98531647862', '220');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Michael Faraday', '97963521584', '333');
INSERT INTO FUNCIONARIO (ID_FUNCIONARIO, NOME, CPF, MATRICULA)
VALUES (SEQ_FUNCIONARIO.nextval, 'Albert Einstein', '97956412853', '240');

SELECT * FROM FUNCIONARIO;

INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(11) 99999-8888', 'thiago@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(47) 33333-8888', 'marcos@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(16) 22222-8888', 'carlos@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(11) 11111-8888', 'sandro@email.com');

INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(46) 99191-8568', 'juliaf06@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(93) 99234-8913', 'carlosperez@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(92) 99056-0965', 'lucas.nobre@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(11) 99114-5687', 'rebeca@email.com');

INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(11) 77777-2222', 'peterparker@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(47) 88888-1111', 'steven.strange@email.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(16) 99999-3333', 'tony@starkindustries.com');
INSERT INTO CONTATO (ID_CONTATO, TELEFONE, EMAIL)
VALUES (SEQ_CONTATO.nextval, '(11) 44444-6666', 'banner@hotmail.com.br');

SELECT * FROM CONTATO;

INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua da Árvore', '121', 'Bairro da Árvore', 'Goiânia', 'Goiás', '15987-956', ' ');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua do Céu', '1997', 'Bairro Azul', 'São Paulo', 'São Paulo', '95357-365', ' ');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua do Chinelo', '68', 'Bairro Amarelo', 'Bauru', 'São Paulo', '95127-856', ' ');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua Bela', '785', 'Bela Vista', 'Porto Alegre', 'Rio Grande do Sul', '65432-755', ' ');

INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua Do lago', '105', 'São Jerônimo', 'São Paulo', 'Goiás', '97845-250', 'casa');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua Antônio de Andrade', '320', 'Nossa Chácara', 'Rio de Janeiro', 'São Paulo', '82574-355', 'casa');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua Raúl Pila', '47', 'Vista Alegre', 'Bauru', 'Rio Grande do Sul', '31678-120', 'casa');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Avenida Brásil', '71', 'Cohab A', 'Porto Alegre', 'Bahia', '39825-975', 'casa');

INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua Brasil', '746', 'Bairro do Liberdade', 'Pato Branco', 'Paraná', '69314-956', ' ');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua itamaraty', '190', 'Bairro floresta', 'Beltrão', 'Paraná', '99087-653', ' ');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua São LUcas', '902', 'Bairro Buriti', 'Boa Vista', 'Roraima', '85510-846', ' ');
INSERT INTO ENDERECO_CLIENTE (ID_ENDERECO, RUA, NUMERO, BAIRRO, CIDADE, ESTADO, CEP, COMPLEMENTO)
VALUES (SEQ_ENDERECO_CLIENTE.nextval, 'Rua Bela Vista', '778', 'Bairro Bom Jardim', 'Santarem', 'Para', '68432-551', ' ');

SELECT * FROM ENDERECO_CLIENTE;

INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Thiago', '85226595885', 1, 1);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Marcos', '75335795188', 2, 1);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Carlos', '11122233344', 3, 3);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Sandro', '55566677788', 4, 4);

INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Julia', '69226595895', 5, 5);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Carlos', '70335795182', 6,6 );
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Lucas', '10983456204', 7, 7);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Rebeca', '01265396710', 8, 8);

INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Peter Parker', '22659588587', 9, 9);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Steven Strange', '65432189076', 10, 10);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Tony Stark', '03421876542', 11, 11);
INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ID_CONTATO, ID_ENDERECO)
VALUES (SEQ_CLIENTE.nextval, 'Bruce Banner', '07432198753', 12, 12);

SELECT * FROM CLIENTE;

INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '999', 'VISA', '05/2030', '50000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '3333 7777 7777 6666', 'MASTERCARD', '08/2030', '75000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '1111 8888 7777 6666', 'VISA', '05/2025', '30000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '2222 9999 7777 6666', 'MASTERCARD', '02/2023', '20000');

INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '7834 1234 0921 6982', 'VISA', '06/2031', '5000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '3034 7342 7453 6666', 'MASTERCARD', '08/2032', '1200');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '1294 8812 7721 6216', 'VISA', '06/2025', '8000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '2122 9934 7547 6096', 'MASTERCARD', '04/2023', '87000');

INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '3333 8888 5555 2222', 'VISA', '06/2025', '60000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '2222 9999 7777 4444', 'MASTERCARD', '08/2024', '85500');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '8888 1111 6666 3333', 'VISA', '05/2030', '99000');
INSERT INTO CARTAO_CREDITO (ID_CARTAO, NUMERO, BANDEIRA_CARTAO, VALIDADE, LIMITE)
VALUES (SEQ_CARTAO_CREDITO.nextval, '1111 9999 3333 7777', 'MASTERCARD', '03/2028', '22700');

SELECT * FROM CARTAO_CREDITO;

INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
VALUES (SEQ_LOCACAO.nextval, TO_DATE ('05-03-2022','dd-mm-yyyy'), TO_DATE ('10-03-2022','dd-mm-yyyy'), 50, 1100, 1, 1, 1, 1);
INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
VALUES (SEQ_LOCACAO.nextval, TO_DATE ('10-03-2022','dd-mm-yyyy'), TO_DATE ('12-03-2022','dd-mm-yyyy'), 25, 320, 2, 2, 2, 2);
INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
VALUES (SEQ_LOCACAO.nextval, TO_DATE ('10-03-2022','dd-mm-yyyy'), TO_DATE ('20-03-2022','dd-mm-yyyy'), 50, 2200, 3, 3, 3, 3);
INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
VALUES (SEQ_LOCACAO.nextval, TO_DATE ('05-03-2022','dd-mm-yyyy'), TO_DATE ('06-03-2022','dd-mm-yyyy'), 25, 120, 4, 4, 4, 4);

INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
VALUES (SEQ_LOCACAO.nextval, TO_DATE ('09-03-2022','dd-mm-yyyy'), TO_DATE ('10-03-2022','dd-mm-yyyy'), 35, 400, 5, 5, 5, 5);
INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
VALUES (SEQ_LOCACAO.nextval, TO_DATE ('10-05-2022','dd-mm-yyyy'), TO_DATE ('12-05-2022','dd-mm-yyyy'), 98, 250, 6, 6, 6, 6);
--INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
--VALUES (SEQ_LOCACAO.nextval, TO_DATE ('16-07-2022','dd-mm-yyyy'), TO_DATE ('20-07-2022','dd-mm-yyyy'), 58, 200, 7, 7, 7, 7);

--INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
--VALUES (SEQ_LOCACAO.nextval, TO_DATE ('02-09-2022','dd-mm-yyyy'), TO_DATE ('06-10-2022','dd-mm-yyyy'), 21, 1200, 8, 8, 8, 8);
--INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
--VALUES (SEQ_LOCACAO.nextval, TO_DATE ('05-03-2022','dd-mm-yyyy'), TO_DATE ('06-10-2022','dd-mm-yyyy'), 50, 1700, 9, 9, 9, 9);
--INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
--VALUES (SEQ_LOCACAO.nextval, TO_DATE ('05-04-2022','dd-mm-yyyy'), TO_DATE ('07-04-2022','dd-mm-yyyy'), 25, 2000, 10, 10, 10, 10);
--INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
--VALUES (SEQ_LOCACAO.nextval, TO_DATE ('08-04-2022','dd-mm-yyyy'), TO_DATE ('10-04-2022','dd-mm-yyyy'), 50, 1100, 11, 11, 11, 11);
--INSERT INTO LOCACAO(ID_LOCACAO, DATA_LOCACAO, DATA_DEVOLUCAO, QUILOMETRAGEM_ADICAO, VALOR_LOCACAO_TOTAL, ID_CLIENTE, ID_VEICULO, ID_FUNCIONARIO, ID_CARTAO)
--VALUES (SEQ_LOCACAO.nextval, TO_DATE ('11-04-2022','dd-mm-yyyy'), TO_DATE ('13-04-2022','dd-mm-yyyy'), 25, 2300, 12, 12, 12, 12);

SELECT * FROM LOCACAO;



