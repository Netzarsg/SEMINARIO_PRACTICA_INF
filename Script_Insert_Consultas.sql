-- Insercion datos Rol
INSERT INTO ROL VALUES (1,'Administrador');
INSERT INTO ROL VALUES (2,'Operador');

-- Insercion datos Usuario
INSERT INTO USUARIO (NombreUsuario,Contraseña,RolId) VALUES('UsuarioPrueba1','1234',1);

-- Insercion datos Producto
INSERT INTO PRODUCTO VALUES (1,'TANQUE TK');

-- Insercion datos OrdenTrabajo
INSERT INTO ORDENTRABAJO (IdOrdenTrabajo,NumeroOrden,Fecha,UsuarioId,ProductoId) VALUES (1,'TK-0001',current_date(),1,1);

-- Insercion datos FabricacionProducto
INSERT INTO FABRICACIONPRODUCTO (IdFabricacionProducto,OrdenId) VALUES (1,1);

-- Insercion datos Tarea
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (10,1,1,'LIMPIEZA DE MATRIZ','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (20,1,1,'ENCERADO','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (30,1,1,'COLOCACION DE VELO','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (40,1,1,'BARRERA QUIMICA','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (50,1,1,'REFUERZO MECANICO','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (60,1,1,'DESMOLDE','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (70,1,1,'AMOLADO PERFILADO','FONDO');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (80,1,1,'PINTURA CON GEL COAT','FINAL');
INSERT INTO TAREA (OrdenTarea,FabricacionProductoId,ProductoId,Descripcion,Observaciones) VALUES (90,1,1,'INSPECCION FINAL','FINAL');

-- Consulta de datos de prueba
SELECT OT.NumeroOrden, OT.FECHA, U.NOMBREUSUARIO, P.NOMBREPRODUCTO,T.ORDENTAREA,T.DESCRIPCION,T.OBSERVACIONES
FROM ORDENTRABAJO OT INNER JOIN FABRICACIONPRODUCTO FP ON OT.IDORDENTRABAJO=FP.OrdenId
INNER JOIN USUARIO U ON OT.USUARIOID=U.IDUSUARIO
INNER JOIN TAREA T ON FP.IDFABRICACIONPRODUCTO=T.FABRICACIONPRODUCTOID
INNER JOIN PRODUCTO P ON T.PRODUCTOID=P.IDPRODUCTO