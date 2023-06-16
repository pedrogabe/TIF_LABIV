CREATE DATABASE clinica_medica;

USE clinica_medica;

CREATE TABLE Perfiles (
IdPerfil INT AUTO_INCREMENT PRIMARY KEY,
Descripcion VARCHAR(50) NOT NULL,
Estado BIT NOT NULL
);

CREATE TABLE Jornadas (
IdJornada INT AUTO_INCREMENT PRIMARY KEY,
Descripcion VARCHAR(50) NOT NULL,
Estado BIT NOT NULL,
Inicio INT NOT NULL,
Fin INT NOT NULL,
CONSTRAINT UK_JornadaInicioFin UNIQUE (IdJornada, Inicio, Fin)
);

CREATE TABLE Especialidades (
IdEspecialidad INT AUTO_INCREMENT PRIMARY KEY,
Descripcion VARCHAR(50) NOT NULL,
Estado BIT NOT NULL
);

CREATE TABLE Medicos (
Id INT AUTO_INCREMENT PRIMARY KEY,
IdPerfil INT NULL,
Dni VARCHAR(10) NULL,
Nombre VARCHAR(50) NOT NULL,
Apellido VARCHAR(50) NOT NULL,
Sexo VARCHAR(20) NULL,
Nacionalidad VARCHAR(50) NULL,
FechaNacimiento DATETIME NOT NULL,
Direccion VARCHAR(50) NULL,
Localidad VARCHAR(50) NULL,
Provincia VARCHAR(50) NULL,
CorreoElectronico VARCHAR(50) NULL,
Telefono VARCHAR(50) NULL,
IdJornada INT NOT NULL,
Estado BIT NOT NULL,
CONSTRAINT FK_MedicoPerfil FOREIGN KEY (IdPerfil) REFERENCES Perfiles(IdPerfil),
CONSTRAINT FK_MedicoIdJornada FOREIGN KEY (IdJornada) REFERENCES Jornadas(IdJornada)
);

CREATE TABLE Usuarios (
IdUsuario INT AUTO_INCREMENT PRIMARY KEY,
UserLogin VARCHAR(50) NOT NULL,
Password VARCHAR(50) NOT NULL,
IdPerfil INT NOT NULL,
CONSTRAINT FK_IdPerfil FOREIGN KEY (IdPerfil) REFERENCES Medicos(Id)
);

CREATE TABLE Pacientes (
Id INT AUTO_INCREMENT PRIMARY KEY,
Dni VARCHAR(10) NULL,
Nombre VARCHAR(50) NOT NULL,
Apellido VARCHAR(50) NOT NULL,
Sexo VARCHAR(20) NULL,
Nacionalidad VARCHAR(50) NULL,
FechaNacimiento DATE NOT NULL,
Direccion VARCHAR(50) NULL,
Localidad VARCHAR(50) NULL,
Provincia VARCHAR(50) NULL,
CorreoElectronico VARCHAR(50) NULL,
Telefono VARCHAR(50) NULL,
IdJornada INT NOT NULL,
Estado BIT NOT NULL
);

CREATE TABLE MedicosEspecialidades (
IdMedico INT NOT NULL,
IdEspecialidad INT NOT NULL,
Estado BIT NULL,
PRIMARY KEY (IdMedico, IdEspecialidad),
CONSTRAINT FK_MedicoIdEspecialidad FOREIGN KEY (IdEspecialidad) REFERENCES Especialidades(IdEspecialidad)
);

CREATE TABLE SituacionesTurnos (
IdSituacion INT AUTO_INCREMENT PRIMARY KEY,
Situacion VARCHAR(20) NOT NULL,
Estado BIT NULL
);

CREATE TABLE Turnos (
    IdTurnos INT AUTO_INCREMENT PRIMARY KEY,
    IdMedico INT NULL,
    IdPaciente INT NULL,
    IdEspecialidad INT NULL,
    FechaReserva DATETIME NOT NULL,
    Observacion TEXT NOT NULL,
    IdSituacion INT NULL,
    Hora INT NULL,
    
    CONSTRAINT FK_TurnoIdMedicos FOREIGN KEY (IdMedico) REFERENCES Medicos (Id),
    CONSTRAINT FK_TurnoIdPaciente FOREIGN KEY (IdPaciente) REFERENCES Pacientes (Id),
    CONSTRAINT FK_TurnoIdEspecialidad FOREIGN KEY (IdEspecialidad) REFERENCES Especialidades (IdEspecialidad),
    CONSTRAINT FK_TurnoIdSituacion FOREIGN KEY (IdSituacion) REFERENCES SituacionesTurnos (IdSituacion)
);

ALTER TABLE Usuarios
ADD CONSTRAINT FK_Usuarios_Medicos FOREIGN KEY (IdPerfil) REFERENCES Medicos (Id);

-- SELECT * FROM clinica_medica.pacientes;
-- INSERT INTO clinica_medica.pacientes (Dni, Nombre, Apellido, Sexo, Nacionalidad,
-- FechaNacimiento, Direccion, Localidad, Provincia, CorreoElectronico, Telefono, Estado) 
-- VALUES (?,?,?,?,?,?,?,?,?,?,?,?);

