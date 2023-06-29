CREATE DATABASE clinica_medica;

USE clinica_medica;

CREATE TABLE Perfiles (
    IdPerfil INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL,
    Estado BIT NOT NULL
);

CREATE TABLE Usuarios (
    IdUsuario INT AUTO_INCREMENT PRIMARY KEY,
    UserLogin VARCHAR(50) NOT NULL,
    Password VARCHAR(50) NOT NULL,
    IdPerfil INT NOT NULL,
    CONSTRAINT FK_UsuarioPerfil FOREIGN KEY (IdPerfil) REFERENCES Perfiles(IdPerfil)
);

CREATE TABLE Especialidades (
    IdEspecialidad INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL
);

CREATE TABLE Nacionalidades (
    IdNacionalidad INT AUTO_INCREMENT PRIMARY KEY,
    Nacionalidad VARCHAR(50) NOT NULL
);

CREATE TABLE Provincias (
    IdProvincia INT AUTO_INCREMENT PRIMARY KEY,
    Provincia VARCHAR(50) NOT NULL
);

CREATE TABLE Localidades (
    IdLocalidad INT AUTO_INCREMENT PRIMARY KEY,
    Localidad VARCHAR(50) NOT NULL,
    IdProvincia INT NOT NULL,
    CONSTRAINT FK_ProvinciasLocalidades FOREIGN KEY (IdProvincia) REFERENCES Provincias(IdProvincia)
);

CREATE TABLE jornadas (
	IdJornada int NOT NULL AUTO_INCREMENT,
	Descripcion varchar(50) NOT NULL,
	Estado bit(1) NOT NULL,
	InicioLunes int NOT NULL,
	FinLunes int NOT NULL,
	InicioMartes int NOT NULL,
	FinMartes int NOT NULL,
	InicioMiercoles int NOT NULL,
	FinMiercoles int NOT NULL,
	InicioJueves int NOT NULL,
	FinJueves int NOT NULL,
	InicioViernes int NOT NULL,
	FinViernes int NOT NULL,
	InicioSabado int NOT NULL,
	FinSabado int NOT NULL,
	InicioDomingo int NOT NULL,
	FinDomingo int NOT NULL,
	PRIMARY KEY (IdJornada),
	UNIQUE KEY UK_InicioFin (InicioLunes , FinLunes, InicioMartes  ,FinMartes  ,InicioMiercoles  ,FinMiercoles  ,
	InicioJueves  ,FinJueves  ,InicioViernes  ,FinViernes  ,InicioSabado  ,FinSabado  ,InicioDomingo  ,FinDomingo )
) ;


CREATE TABLE Pacientes (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Dni VARCHAR(10) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Sexo VARCHAR(20) NOT NULL,
    IdNacionalidad INT NOT NULL,
    FechaNacimiento DATETIME NOT NULL,
    Direccion VARCHAR(50) NOT NULL,
    IdProvincia INT NOT NULL,
    IdLocalidad INT NOT NULL,
    CorreoElectronico VARCHAR(50) NOT NULL,
    Telefono VARCHAR(50) NOT NULL,
    Estado BIT NOT NULL,
    CONSTRAINT FK_PacienteNacionalidad FOREIGN KEY (IdNacionalidad) REFERENCES Nacionalidades(IdNacionalidad),
    CONSTRAINT FK_PacienteProvincia FOREIGN KEY (IdProvincia) REFERENCES Provincias(IdProvincia),
    CONSTRAINT FK_PacienteLocalidad FOREIGN KEY (IdLocalidad) REFERENCES Localidades(IdLocalidad)
);

CREATE TABLE medicos (
  Id int NOT NULL AUTO_INCREMENT,
  IdUsuario int DEFAULT NULL,
  Dni varchar(10) DEFAULT NULL,
  Nombre varchar(50) NOT NULL,
  Apellido varchar(50) NOT NULL,
  Sexo varchar(20) DEFAULT NULL,
  IdNacionalidad int DEFAULT NULL,
  FechaNacimiento datetime NOT NULL,
  Direccion varchar(50) DEFAULT NULL,
  IdProvincia int DEFAULT NULL,
  IdLocalidad int DEFAULT NULL,  
  IdEspecialidad int DEFAULT NULL,  
  IdJornada int NOT NULL,
  CorreoElectronico varchar(50) DEFAULT NULL,
  Telefono varchar(50) DEFAULT NULL,
  Estado bit(1) NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT FK_MedicoUsuario FOREIGN KEY (IdUsuario) REFERENCES usuarios (IdUsuario),
  CONSTRAINT FK_MedicoLocalidad FOREIGN KEY (IdLocalidad) REFERENCES localidades (IdLocalidad),
  CONSTRAINT FK_MedicoNacionalidad FOREIGN KEY (IdNacionalidad) REFERENCES nacionalidades (IdNacionalidad),
  CONSTRAINT FK_MedicoProvincia FOREIGN KEY (IdProvincia) REFERENCES provincias (IdProvincia),
  CONSTRAINT FK_MedicoEspecialidad FOREIGN KEY (IdEspecialidad) REFERENCES especialidades (IdEspecialidad),
  CONSTRAINT FK_MedicoJornada FOREIGN KEY (IdJornada) REFERENCES jornadas (IdJornada)
); 

CREATE TABLE EstadosTurno (
  IdEstadoTurno INT PRIMARY KEY,
  Descripcion VARCHAR(255)
);

CREATE TABLE turnos (
  IdTurno int NOT NULL AUTO_INCREMENT,
  IdMedico int NOT NULL,
  IdPaciente int NOT NULL,
  FechaReserva datetime NOT NULL,
  Observacion text NOT NULL,
  IdTurnoEstado int NOT NULL,
  Hora int NOT NULL,
  Estado bit(1) NOT NULL,
  PRIMARY KEY (IdTurno),
  KEY FK_TurnoMedico (IdMedico),
  KEY FK_TurnoPaciente (IdPaciente),
  KEY FK_TurnoEstado (IdTurnoEstado)
) ;


-- Insertar perfil de ADMIN
INSERT INTO Perfiles (Descripcion, Estado)
VALUES ('Administrador', 1);

-- Insertar perfil de médico
INSERT INTO Perfiles (Descripcion, Estado)
VALUES ('Médico', 1);

-- Insertar usuario administrador
INSERT INTO Usuarios (UserLogin, Password, IdPerfil)
VALUES ('admin', 'admin', 1);

-- Insertar usuario médico
INSERT INTO Usuarios (UserLogin, Password, IdPerfil)
VALUES ('medico', 'medico', 2);

-- Insertar nacionalidad

INSERT INTO Nacionalidades (Nacionalidad) VALUES   
    ('Argentina'),
	('Brasil'),
    ('Chile'),
    ('Venezuela');

-- Insertar 4 provincias 

INSERT INTO Provincias (Provincia) VALUES
    ('Buenos Aires'),
    ('Córdoba'),
    ('Santa Fe'),
    ('Mendoza');    


-- insertar 5 localidades para cada provincia

-- Insertar localidades para la provincia de Buenos Aires

INSERT INTO Localidades (Localidad, IdProvincia) VALUES
    ('La Plata', 1),
    ('Mar del Plata', 1),
    ('Bahía Blanca', 1),
    ('Quilmes', 1);

-- Insertar localidades para la provincia de Córdoba

INSERT INTO Localidades (Localidad, IdProvincia) VALUES
    ('Córdoba', 2),
    ('Villa María', 2),
    ('Río Cuarto', 2),
    ('Alta Gracia', 2);

-- Insertar localidades para la provincia de Santa Fe

INSERT INTO Localidades (Localidad, IdProvincia) VALUES
    ('Rosario', 3),
    ('Santa Fe', 3),
    ('Rafaela', 3),
    ('Venado Tuerto', 3);

-- Insertar localidades para la provincia de Mendoza

INSERT INTO Localidades (Localidad, IdProvincia) VALUES
    ('Mendoza', 4),
    ('San Rafael', 4),
    ('Godoy Cruz', 4),
    ('Luján de Cuyo', 4);

INSERT INTO especialidades (Descripcion)
VALUES
  ('Cardiología'),
  ('Dermatología'),
  ('Neurología');

INSERT INTO EstadosTurno (IdEstadoTurno, Descripcion) VALUES
  (1, 'Ocupado'),
  (2, 'Libre'),
  (3, 'Ausente'),
  (4, 'Presente');
  
-- Generar 5 registros enla tabla usuarios para la tabla medicos

INSERT INTO usuarios (IdUsuario, UserLogin, Password, IdPerfil)
VALUES
  (3, 'Juan', 'Juan', 2),
  (4, 'Maria', 'Maria', 2),
  (5, 'Pedro', 'Pedro', 2),
  (6, 'Laura', 'Laura', 2),
  (7, 'Carlos', 'Carlos', 2), 
  (8, 'Ana', 'Ana', 2),
  (9, 'Luis', 'Luis', 2),
  (10, 'Mariana', 'Mariana', 2),
  (11, 'Roberto', 'Roberto', 2),
  (12, 'Carolina', 'Carolina', 2),
  (13, 'Pedro', 'Pedro', 2),
  (14, 'Maria', 'Maria', 2),
  (15, 'Juan', 'Juan', 2),
  (16, 'Sofia', 'Sofia', 2); 
  
INSERT INTO clinica_medica.jornadas (IdJornada,Descripcion,Estado,InicioLunes,FinLunes,InicioMartes,FinMartes,InicioMiercoles,FinMiercoles,InicioJueves,FinJueves,InicioViernes,FinViernes,
InicioSabado,FinSabado,InicioDomingo,FinDomingo)
VALUES
(1,'Lunes a viernes, 8 a 12',1,8,12,8,12,8,12,8,12,8,12,0,0,0,0),
(2,'Lunes a miercoles, 15 a 19',1,15,19,15,19,15,19,0,0,0,0,0,0,0,0),
(3,'Viernes, sábado y domingo, 18 a 21',1,0,0,0,0,0,0,0,0,18,21,18,21,18,21);

INSERT INTO medicos (IdUsuario, Dni, Nombre, Apellido, Sexo, IdNacionalidad, FechaNacimiento, Direccion, IdProvincia, IdLocalidad, IdEspecialidad, IdJornada, CorreoElectronico, Telefono, Estado)
VALUES
  (3, '12345678', 'Juan', 'Perez', 'Masculino', 1, '1990-01-01', 'Calle 123', 1, 1, 1, 1, 'juan@example.com', '123456789', 1),
  (4, '23456789', 'Maria', 'Lopez', 'Femenino', 1, '1992-05-15', 'Avenida 456', 2, 2, 2, 1, 'maria@example.com', '987654321', 1),
  (5, '34567890', 'Pedro', 'Gomez', 'Masculino', 2, '1988-11-30', 'Calle 789', 3, 3, 1, 2, 'pedro@example.com', '456789123', 1),
  (6, '45678901', 'Laura', 'Rodriguez', 'Femenino', 3, '1995-08-20', 'Avenida 789', 4, 4, 3, 2, 'laura@example.com', '321654987', 1),
  (7, '56789012', 'Carlos', 'Fernandez', 'Masculino', 4, '1993-03-10', 'Calle 456', 1, 2, 2, 3, 'carlos@example.com', '987321654', 1), 
  (8, '67890123', 'Ana', 'Garcia', 'Femenino', 1, '1991-02-10', 'Avenida 123', 2, 3, 3, 3, 'ana@example.com', '654987321', 1),
  (9, '78901234', 'Luis', 'Martinez', 'Masculino', 2, '1989-07-20', 'Calle 456', 3, 4, 1, 1, 'luis@example.com', '321789456', 1),
  (10, '89012345', 'Mariana', 'Lopez', 'Femenino', 1, '1994-04-15', 'Avenida 789', 4, 1, 2, 2, 'mariana@example.com', '789456123', 1),
  (11, '90123456', 'Roberto', 'Fernández', 'Masculino', 3, '1990-11-05', 'Calle 123', 1, 2, 3, 3, 'roberto@example.com', '123789456', 1),
  (12, '01234567', 'Carolina', 'Rodriguez', 'Femenino', 4, '1992-09-25', 'Avenida 456', 2, 3, 1, 1, 'carolina@example.com', '456123789', 1),
  (13, '12345098', 'Pedro', 'Fernandez', 'Masculino', 4, '1991-09-02', 'Calle 123', 1, 2, 2, 2, 'pedro@example.com', '321654987', 1),
  (14, '23456709', 'Maria', 'Perez', 'Femenino', 1, '1994-11-11', 'Avenida 456', 2, 3, 3, 3, 'maria@example.com', '987123654', 1),
  (15, '34567810', 'Juan', 'Gonzalez', 'Masculino', 2, '1989-12-18', 'Calle 789', 3, 4, 1, 1, 'juan@example.com', '456789123', 1),
  (16, '45678921', 'Sofia', 'Martinez', 'Femenino', 3, '1993-05-08', 'Avenida 123', 4, 1, 2, 2, 'sofia@example.com', '789321654', 1);

-- Generar 15 registros para la tabla pacientes
INSERT INTO Pacientes (Dni, Nombre, Apellido, Sexo, IdNacionalidad, FechaNacimiento, Direccion, IdProvincia, IdLocalidad, CorreoElectronico, Telefono, Estado)
VALUES
  ('11111111', 'Ana', 'Garcia', 'Femenino', 1, '1991-02-10', 'Calle 123', 1, 1, 'ana@example.com', '123456789', 1),
  ('22222222', 'Luis', 'Martinez', 'Masculino', 2, '1989-07-20', 'Avenida 456', 2, 2, 'luis@example.com', '987654321', 1),
  ('33333333', 'Mariana', 'Lopez', 'Femenino', 1, '1994-04-15', 'Calle 789', 3, 3, 'mariana@example.com', '456789123', 1),
  ('44444444', 'Roberto', 'Fernandez', 'Masculino', 3, '1990-11-05', 'Avenida 789', 4, 4, 'roberto@example.com', '321654987', 1),
  ('55555555', 'Carolina', 'Rodriguez', 'Femenino', 4, '1992-09-25', 'Calle 456', 1, 2, 'carolina@example.com', '987321654', 1),
  ('66666666', 'Juan', 'Perez', 'Masculino', 2, '1995-06-12', 'Avenida 789', 2, 1, 'juan@example.com', '456123789', 1),
  ('77777777', 'Maria', 'Lopez', 'Femenino', 1, '1993-08-29', 'Calle 789', 3, 3, 'maria@example.com', '789456123', 1),
  ('88888888', 'Andres', 'Gonzalez', 'Masculino', 3, '1987-11-17', 'Avenida 456', 4, 4, 'andres@example.com', '123789456', 1),
  ('99999999', 'Laura', 'Fernandez', 'Femenino', 4, '1990-04-05', 'Calle 123', 1, 2, 'laura@example.com', '987456321', 1),
  ('12345678', 'Carlos', 'Martínez', 'Masculino', 1, '1992-10-20', 'Avenida 123', 2, 3, 'carlos@example.com', '321789456', 1),
  ('23456789', 'Ana', 'Lopez', 'Femenino', 2, '1988-07-15', 'Calle 456', 3, 4, 'ana@example.com', '654987321', 1),
  ('34567890', 'Luisa', 'Gonzalez', 'Femenino', 3, '1996-03-25', 'Avenida 789', 4, 1, 'luisa@example.com', '789654123', 1),
  ('45678901', 'Pedro', 'Fernandez', 'Masculino', 4, '1991-09-02', 'Calle 123', 1, 2, 'pedro@example.com', '321654987', 1),
  ('56789012', 'María', 'Pérez', 'Femenino', 1, '1994-11-11', 'Avenida 456', 2, 3, 'maria@example.com', '987123654', 1),
  ('67890123', 'Juan', 'González', 'Masculino', 2, '1989-12-18', 'Calle 789', 3, 4, 'juan@example.com', '456789123', 1),
  ('78901234', 'Sofia', 'Martinez', 'Femenino', 3, '1993-05-08', 'Avenida 123', 4, 1, 'sofia@example.com', '789321654', 1);

  
  
  -- Generar 15 registros para la tabla turnos
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (1,1,1,'2023-06-21 09:00:00','Dolor de cabeza',1,9,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (2,2,2,'2023-06-21 10:00:00','Fiebre',1,10,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (3,3,3,'2023-06-22 11:00:00','Mareos',1,11,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (4,4,4,'2023-06-22 14:00:00','Consulta',1,14,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (5,5,5,'2023-06-23 15:00:00','Consulta',1,15,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (6,1,1,'2023-06-01 15:00:00','Consulta',1,15,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (7,1,2,'2023-06-02 16:00:00','Dolor de pecho',1,16,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (8,1,3,'2023-06-03 18:00:00','Fiebre',1,18,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (9,2,4,'2023-06-04 19:00:00','Cansancio',1,19,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (10,2,5,'2023-06-05 20:00:00','Dolor de cabeza',1,20,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (11,3,3,'2023-06-10 11:00:00','Mareos',1,11,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (12,4,4,'2023-06-11 14:00:00','Consulta',1,14,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (13,5,5,'2023-06-12 15:00:00','Consulta',1,15,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (14,1,1,'2023-06-13 15:00:00','Consulta',1,15,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (15,1,2,'2023-06-14 16:00:00','Dolor de pecho',1,16,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (16,1,2,'2023-05-21 10:00:00','Control',1,10,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (17,2,3,'2023-05-22 11:00:00','Mareos',1,11,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (18,4,4,'2023-05-22 14:00:00','Consulta',1,14,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (19,4,4,'2023-07-22 14:00:00','Consulta',1,14,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (20,1,9,'2023-07-10 00:00:00','',1,9,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (21,6,8,'2023-07-16 00:00:00','',1,20,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (22,10,7,'2023-07-19 00:00:00','',1,8,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (23,12,6,'2023-07-15 00:00:00','',1,20,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (24,5,5,'2023-07-08 00:00:00','',1,18,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (25,3,4,'2023-07-26 00:00:00','',1,16,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (26,4,3,'2023-07-19 00:00:00','',1,17,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (27,4,2,'2023-07-19 00:00:00','',1,15,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (28,9,1,'2023-07-22 00:00:00','',1,20,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (29,13,1,'2023-06-29 00:00:00','',1,11,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (30,1,2,'2023-06-29 00:00:00','',1,10,'1');
INSERT INTO turnos (IdTurno,IdMedico,IdPaciente,FechaReserva,Observacion,IdTurnoEstado,Hora,Estado) VALUES (31,10,3,'2023-06-29 00:00:00','',1,10,'1');

