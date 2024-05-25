CREATE DATABASE ZPControlBD;
USE ZPControlBd;

-- -----------------------------------------------------
-- Table Producto
-- -----------------------------------------------------
CREATE TABLE PRODUCTO (
IdProducto INT NOT NULL PRIMARY KEY auto_increment,
NombreProducto VARCHAR(100) NOT NULL
);

-- -----------------------------------------------------
-- Table Rol
-- -----------------------------------------------------

CREATE TABLE ROL (
IdRol INT NOT NULL PRIMARY KEY auto_increment,
NombreRol VARCHAR(50) NOT NULL
);

-- -----------------------------------------------------
-- Table Usuario
-- -----------------------------------------------------
CREATE TABLE Usuario (
IdUsuario INT PRIMARY KEY auto_increment,
NombreUsuario VARCHAR(45) UNIQUE NOT NULL,
Contraseña VARCHAR(45) NOT NULL,
Estado VARCHAR(45) DEFAULT 'Activo',
RolId INT NOT NULL,
CONSTRAINT FK_Rol_Usuario FOREIGN KEY (RolID) REFERENCES ROL(IdRol)
);


-- -----------------------------------------------------
-- Table `ControlBD`.`OrdenTrabajo`
-- -----------------------------------------------------

CREATE TABLE OrdenTrabajo(
IdOrdenTrabajo INT PRIMARY KEY auto_increment,
NumeroOrden VARCHAR (45) UNIQUE NOT NULL,
Fecha DATE NOT NULL,
FechaCierra DATE NULL,
Estado VARCHAR(45) DEFAULT 'Abierta',
UsuarioId INT NOT NULL,
ProductoId INT NOT NULL,
CONSTRAINT FK_Usuario_OrdenTrabajo FOREIGN KEY(UsuarioId) REFERENCES Usuario(IdUsuario),
CONSTRAINT FK_Producto_OrdenTrabajo FOREIGN KEY(ProductoId) REFERENCES Producto(IdProducto)
);


-- -----------------------------------------------------
-- Table Tarea
-- -----------------------------------------------------

CREATE TABLE TAREA (
IdTarea INT PRIMARY KEY AUTO_INCREMENT,
OrdenTarea INT NOT NULL,
FabricacionProductoId INT NOT NULL,
ProductoId INT NOT NULL,
Descripcion VARCHAR(100) NOT NULL,
FechaInicio DATE NULL,
FechaFin DATE NULL,
InsumoUno INT NULL,
InsumoDos INT NULL,
Observaciones VARCHAR(100) NULL,
Estado VARCHAR(45) DEFAULT 'Pendiente',
Usuario VARCHAR(45),
CONSTRAINT FK_Producto_Tarea FOREIGN KEY (ProductoId) REFERENCES Producto(IdProducto)
);


-- -----------------------------------------------------
-- Table FabricacionProducto
-- -----------------------------------------------------

CREATE TABLE FabricacionProducto(
IdFabricacionProducto INT PRIMARY KEY AUTO_INCREMENT,
OrdenId INT NOT NULL,
CONSTRAINT FK_Orden_FabricacionProducto FOREIGN KEY (OrdenId) REFERENCES OrdenTrabajo(IdOrdenTrabajo)
);

-- -----------------------------------------------------
-- Table Documento
-- -----------------------------------------------------

CREATE TABLE Documento(
IdDocumento INT PRIMARY KEY AUTO_INCREMENT,
Ruta VARCHAR(100) NOT NULL,
FabricacionProductoId INT NOT NULL,
CONSTRAINT FK_Fabricacion_Documento FOREIGN KEY (FabricacionProductoId) REFERENCES FabricacionProducto(IdFabricacionProducto)
);




