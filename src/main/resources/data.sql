INSERT INTO curso(nombre) VALUES('Primero DAW');
INSERT INTO curso(nombre) VALUES('Segundo DAW');
INSERT INTO curso(nombre) VALUES('Primero DAM');
INSERT INTO curso(nombre) VALUES('Segundo DAM');

INSERT INTO plan(nombre,FK_CURSO) VALUES('Plan Uno',1);
INSERT INTO plan(nombre,FK_CURSO) VALUES('Plan Dos',2);
INSERT INTO plan(nombre,FK_CURSO) VALUES('Plan Tres',4);
INSERT INTO plan(nombre,FK_CURSO) VALUES('Plan Cuatro',1);

INSERT INTO tutor(nombre,plan_id) VALUES('Sergio',1);
INSERT INTO tutor(nombre,plan_id) VALUES('Pablo',2);
INSERT INTO tutor(nombre,plan_id) VALUES('Mario',3);
INSERT INTO tutor(nombre,plan_id) VALUES('Jandro',4);
INSERT INTO tutor(nombre) VALUES('Vacio');


INSERT INTO actividad(nombre,descripcion,obligatoria) VALUES('Capgemini','viene Capgemini y punto',false);
INSERT INTO actividad(nombre,descripcion,obligatoria) VALUES('Segunda','Segunda',false);
INSERT INTO actividad(nombre,descripcion,obligatoria) VALUES('Tercera','Tercera',true);
INSERT INTO actividad(nombre,descripcion,obligatoria) VALUES('Cuarta','Cuarta',true);

INSERT INTO enmarca(plan_id,actividad_id,fecha) VALUES(1,2,'2024-12-2');
INSERT INTO enmarca(plan_id,actividad_id,fecha) VALUES(3,2,'2024-12-2');
INSERT INTO enmarca(plan_id,actividad_id,fecha) VALUES(4,2,'2024-12-2');
INSERT INTO enmarca(plan_id,actividad_id,fecha) VALUES(2,2,'2024-12-2');


