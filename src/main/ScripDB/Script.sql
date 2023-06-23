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
  CorreoElectronico varchar(50) DEFAULT NULL,
  Telefono varchar(50) DEFAULT NULL,
  Estado bit(1) NOT NULL,
  PRIMARY KEY (Id),
  CONSTRAINT FK_MedicoUsuario FOREIGN KEY (IdUsuario) REFERENCES usuarios (IdUsuario),
  CONSTRAINT FK_MedicoLocalidad FOREIGN KEY (IdLocalidad) REFERENCES localidades (IdLocalidad),
  CONSTRAINT FK_MedicoNacionalidad FOREIGN KEY (IdNacionalidad) REFERENCES nacionalidades (IdNacionalidad),
  CONSTRAINT FK_MedicoProvincia FOREIGN KEY (IdProvincia) REFERENCES provincias (IdProvincia),
  CONSTRAINT FK_MedicoEspecialidad FOREIGN KEY (IdEspecialidad) REFERENCES especialidades (IdEspecialidad)  
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
    ('Argentina');

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
