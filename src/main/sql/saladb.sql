create database saladb;
use saladb;
CREATE TABLE sala_reuniao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL,
    localizacao VARCHAR(100) NOT NULL,
    possuiProjetor BOOLEAN NOT NULL,
    possuiArCondicionado BOOLEAN NOT NULL,
    numeroCadeiras INT NOT NULL,
    tipoMesa VARCHAR(50),
    recursosAdicionais VARCHAR(255)
);
