drop schema if exists proyecto;
drop user if exists usuario_proyecto;
CREATE SCHEMA proyecto;

create user 'usuario_proyecto'@'%' identified by 'grupo7';

grant all privileges on proyecto.* to 'usuario_proyecto'@'%';
flush privileges;

