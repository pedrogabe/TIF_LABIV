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
    Descripcion VARCHAR(50) NOT NULL,
    Estado BIT NOT NULL
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

CREATE TABLE Jornadas (
    IdJornada INT AUTO_INCREMENT PRIMARY KEY,
    Descripcion VARCHAR(50) NOT NULL,
    Estado BIT NOT NULL,
    Inicio INT NOT NULL,
    Fin INT NOT NULL,
    CONSTRAINT UK_JornadaInicioFin UNIQUE (IdJornada, Inicio, Fin)
);

CREATE TABLE Pacientes (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    IdPerfil INT NULL,
    Dni VARCHAR(10) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Sexo VARCHAR(20) NOT NULL,
    IdNacionalidad INT NOT NULL,
    FechaNacimiento DATETIME NOT NULL,
    Direccion VARCHAR(50) NOT NULL,
    IdLocalidad INT NOT NULL,
    CorreoElectronico VARCHAR(50) NOT NULL,
    Telefono VARCHAR(50) NOT NULL,
    IdJornada INT NOT NULL,
    Estado BIT NOT NULL,
    CONSTRAINT FK_PacientePerfil FOREIGN KEY (IdPerfil) REFERENCES Perfiles(IdPerfil),
    CONSTRAINT FK_PacienteNacionalidad FOREIGN KEY (IdNacionalidad) REFERENCES Nacionalidades(IdNacionalidad),
    CONSTRAINT FK_PacienteLocalidad FOREIGN KEY (IdLocalidad) REFERENCES Localidades(IdLocalidad)
);

CREATE TABLE Medicos (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    IdUsuario INT NOT NULL,
    IdPerfil INT NOT NULL,
    Dni VARCHAR(10) NOT NULL,
    Nombre VARCHAR(50) NOT NULL,
    Apellido VARCHAR(50) NOT NULL,
    Sexo VARCHAR(20) NOT NULL,
    IdNacionalidad INT NOT NULL,
    FechaNacimiento DATETIME NOT NULL,
    Direccion VARCHAR(50) NOT NULL,
    IdLocalidad INT NOT NULL,
    CorreoElectronico VARCHAR(50) NOT NULL,
    Telefono VARCHAR(50) NOT NULL,
    IdJornada INT NOT NULL,
    Estado BIT NOT NULL,
    CONSTRAINT FK_MedicoPerfil FOREIGN KEY (IdPerfil) REFERENCES Perfiles(IdPerfil),
    CONSTRAINT FK_MedicoJornada FOREIGN KEY (IdJornada) REFERENCES Jornadas(IdJornada),
    CONSTRAINT FK_MedicoLocalidad FOREIGN KEY (IdLocalidad) REFERENCES Localidades(IdLocalidad),
    CONSTRAINT FK_MedicoNacionalidad FOREIGN KEY (IdNacionalidad) REFERENCES Nacionalidades(IdNacionalidad),
    CONSTRAINT FK_MedicoUsuario FOREIGN KEY (IdUsuario) REFERENCES Usuarios(IdUsuario)
);

CREATE TABLE MedicosEspecialidades (
    IdMedico INT NOT NULL,
    IdEspecialidad INT NOT NULL,
    Estado BIT NULL,
    PRIMARY KEY (IdMedico, IdEspecialidad),
    CONSTRAINT FK_MedicoEspecialidadMedico FOREIGN KEY (IdMedico) REFERENCES Medicos(Id),
    CONSTRAINT FK_MedicoEspecialidadEspecialidad FOREIGN KEY (IdEspecialidad) REFERENCES Especialidades(IdEspecialidad)
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
    Estado BIT NOT NULL,
    CONSTRAINT FK_TurnoMedico FOREIGN KEY (IdMedico) REFERENCES Medicos(Id),
    CONSTRAINT FK_TurnoPaciente FOREIGN KEY (IdPaciente) REFERENCES Pacientes(Id),
    CONSTRAINT FK_TurnoEspecialidad FOREIGN KEY (IdEspecialidad) REFERENCES Especialidades(IdEspecialidad)        
);

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
