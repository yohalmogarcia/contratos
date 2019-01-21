create database triggers_example;
use triggers_example;

create table people(
	age int,
    name varchar(150)
);

delimiter //
create trigger agecheck BEFORE insert on people for each row 
	if NEW.age<0 then set NEW.age=0;
    end if;
    //
delimiter ;

insert into people values(-20,"Luis"),(24,"Josselin");

select * from people;

CREATE DATABASE contratos_ejercicio;
use contratos_ejercicio;

CREATE TABLE clientes(
	id_cli int not null auto_increment primary key,
    nombre_cli varchar(15),
    apellido_cli varchar(15),
    direccion_cli varchar(55),
    pago_cli double,
    cancelado int
);

CREATE TABLE servicios(
	id_ser int not null auto_increment primary key,
    nombre_ser varchar(40),
    precio_ser double,
    fecha_ser date,
    descuento_ser double
);

CREATE TABLE DETALLES (
	id_det int not null auto_increment primary key,
    total_det double,
    clientes_id_cli int,
    servicios_id_ser int,
    foreign key (clientes_id_cli) references clientes(id_cli),
    foreign key (servicios_id_ser) references servicios(id_ser)
);

INSERT INTO servicios VALUES(NULL, 'Paginas Web', 200, '2018-01-01', 10);
INSERT INTO servicios VALUES(NULL, 'Sistemas ERP', 5000, '2019-01-01', 5);

SELECT * FROM clientes;
DELETE FROM clientes;
SELECT * FROM servicios;
SELECT * FROM detalles;

/*STORE PROCEDURE*/
CREATE procedure sp_insCliente (nombre varchar(15), apellido varchar(15), direccion varchar(55), pago double)
insert into clientes (nombre_cli, apellido_cli, direccion_cli, pago_cli,cancelado) values (nombre, apellido, direccion, pago,1);

/*LLAMADA AL PROCEDIMIENTO DE INSERCION*/
CALL sp_insCliente ('Josselin', 'Ramos','Urb. Las Margaritas V', 0.0);
CALL sp_insCliente ('Emmanuel', 'Urrutia','Urb. Prados de Venecia III', 0.0);
CALL sp_insCliente ('Julia', 'Cortez','Urb. Prados de Venecia IV', 0.0);

/*TRIGGERS O POR LO MENOS EL INTENTO !! xD*/
/*
	mysql> CREATE TABLE account (acct_num INT, amount DECIMAL(10,2));
mysql> CREATE TRIGGER ins_sum BEFORE INSERT ON account
    -> FOR EACH ROW SET @sum = @sum + NEW.amount;
*/
DELIMITER |
CREATE TRIGGER precio_con_descuento BEFORE INSERT ON detalles
FOR EACH ROW 
BEGIN
	DECLARE total double;
    DECLARE precio double;
    DECLARE descuento double;
    SET precio = (SELECT precio_ser FROM servicios WHERE id_ser = NEW.servicios_id_ser);
    SET descuento = (SELECT descuento_ser FROM servicios WHERE id_ser = NEW.servicios_id_ser);
    SET total = precio - (precio*(descuento/100));
    SET NEW.total_det = total;
END
|
DELIMITER ;

/*DROP TRIGGER suma_total_pago;*/
DROP TRIGGER suma_total_pago;

DELIMITER |
CREATE TRIGGER suma_total_pago AFTER INSERT ON detalles
FOR EACH ROW 
BEGIN
	DECLARE pago double;
	SET pago = (select pago_cli from clientes where id_cli=NEW.clientes_id_cli);
    SET pago = pago + NEW.total_det;
    UPDATE clientes SET pago_cli=pago WHERE id_cli=NEW.clientes_id_cli;
END
|
DELIMITER ;


/*PROBANDO TRIGGERS*/

INSERT INTO detalles (clientes_id_cli, servicios_id_ser) VALUES(4,1),(4,2);
/*SELECT @suma AS 'Total a Pagar';*/

