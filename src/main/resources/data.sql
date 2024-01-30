insert into curso(nombre) values('1A');
insert into curso(nombre) values('2A');
insert into curso(nombre) values('3A');
insert into curso(nombre) values('2B');


insert into plan(nombre, FK_CURSO) values('Plan 1', 1);
insert into plan(nombre, FK_CURSO) values('Plan 2', 2);
insert into plan(nombre) values('Plan 3');
insert into plan(nombre) values('Plan 4');
insert into plan(nombre) values('Plan 5');
insert into plan(nombre) values('Plan 6');
insert into plan(nombre) values('Plan 7');



insert into tutor(id, nombre, plan_id) values('243524', 'Mario', 1);
insert into tutor(id, nombre, plan_id) values('989345', 'Alejandro', 2);
insert into tutor(id, nombre, plan_id) values('563455', 'Pablo', 3);
insert into tutor(id, nombre, plan_id) values('8563747', 'Pablo teacher', 4);
insert into tutor(id, nombre, plan_id) values('857', 'David', 5);
insert into tutor(id, nombre) values('85735135', 'Marcos');


insert into actividad(obligatoria, nombre, descripcion) values(false, 'Futbol', '');
insert into actividad(obligatoria, nombre, descripcion) values(true, 'Informática', '');
insert into actividad(obligatoria, nombre, descripcion) values(false, 'Pin Pong', '');
insert into actividad(obligatoria, nombre, descripcion) values(false, 'Hockey', '');
insert into actividad(obligatoria, nombre, descripcion) values(false, 'Pinga', '');
insert into actividad(obligatoria, nombre, descripcion) values(false, 'Capgemini', '');

insert into enmarca(actividad_id, plan_id, fecha) values(1, 1, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(2, 2, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(3, 3, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(4, 4, '14/09/2024');
insert into enmarca(actividad_id, plan_id, fecha) values(5, 5, '14/09/2024');


