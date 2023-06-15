CREATE DATABASE siga

CREATE TABLE alu(
	nome varchar (100),
	email varchar (150),
    senha varchar (20),
    ra varchar (15)
);

CREATE TABLE pro(
	nome varchar (100),
	email varchar (150),
    senha varchar (20),
    area varchar (100)
);

CREATE TABLE tcc(
	titulo varchar (100),
	tema varchar (150),
    in1 varchar (100),
    in2 varchar (100),
    in3 varchar (100),
    pro varchar (100),
    des varchar (255)
);