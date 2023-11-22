--Base de datos nombre Proyecto

drop schema if exists proyecto;
drop user if exists usuario_proyecto;
CREATE SCHEMA proyecto;

create user 'usuario_proyecto'@'%' identified by 'grupo7';

grant all privileges on proyecto.* to 'usuario_proyecto'@'%';
flush privileges;

CREATE TABLE proyecto.pedidos (
    id_pedido INT PRIMARY KEY AUTO_INCREMENT,
    descripcion VARCHAR(30) NOT NULL,  
    ruta_imagen varchar(1024),
    pagado bool
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO proyecto.pedidos (id_pedido, descripcion, ruta_imagen, pagado) VALUES
('1','Pedido de Andrés','https://cdn-icons-png.flaticon.com/512/5556/5556493.png', true),
('2','Pedido de Pablo', 'https://cdn-icons-png.flaticon.com/512/5556/5556499.png', true),
('3','Pedido de Kevin', 'https://cdn-icons-png.flaticon.com/512/5556/5556468.png', true),
('4','Pedido de Wilbert', 'https://cdn-icons-png.flaticon.com/512/5556/5556512.png', true),
('5','Pedido de Raúl', 'https://cdn-icons-png.flaticon.com/512/5556/5556529.png', true);

CREATE TABLE proyecto.producto (
    id_producto INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    nombre VARCHAR(200) NOT NULL,
    descripcion TEXT,
    precio DOUBLE,
    cantidad INT NOT NULL,
    ruta_imagen varchar(1024),
    stock bool,
    FOREIGN KEY fk_producto_pedido (id_pedido) REFERENCES pedidos(id_pedido) 
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO proyecto.producto (id_producto, id_pedido, nombre, descripcion, precio, cantidad, ruta_imagen, stock) VALUES
(1, 1, 'Mancuerna Athletic Works De Hule - 25lb', 'Color:Negro Deporte:Pesas',12400, 10, 'https://walmartcr.vtexassets.com/arquivos/ids/241004-800-600?v=637696508909530000&width=800&height=600&aspect=true', true),
(2, 1, '3 Pack Atún Calvo Trocitos Aceite -426gr', 'Precio Especial',2800, 5, 'https://walmartcr.vtexassets.com/arquivos/ids/226247-800-600?v=637655121158870000&width=800&height=600&aspect=true', true),
(3, 1, '2 Pack Refresco Tropical Cero Frutos Blancos -2500ml', 'Oferta Unica', 2120, 20, 'https://walmartcr.vtexassets.com/arquivos/ids/374700-800-600?v=638084758082800000&width=800&height=600&aspect=true', true),
(4, 2, 'Ron Flor de Caña Ultra Lite 4 años - 750ml', 'Producto con venta restringida, solo para mayores de 18 años.', 7380, 15, 'https://walmartcr.vtexassets.com/arquivos/ids/210154-800-600?v=637600675195030000&width=800&height=600&aspect=true', true),
(5, 2, 'Helado Dos Pinos Deleite Caramelo Fud - 565gr 19,9oz', 'Marca: Dos Pinos', 3000, 8, 'https://walmartcr.vtexassets.com/arquivos/ids/432621-800-600?v=638276242020630000&width=800&height=600&aspect=true', true),
(6, 2, 'Pan Bimbo Boller Hamburguesa Bimbollo -450gr', 'Fabricante: Bimbo Centroamerica S.A', 1700, 12, 'https://walmartcr.vtexassets.com/arquivos/ids/314416-800-600?v=637889386337930000&width=800&height=600&aspect=true', true),
(7, 3, 'Huevo Gallina Don Cristobal Marrón Cartón', ' Recuerda que este producto viene en presentación de cartón completo7', 2080, 18, 'https://walmartcr.vtexassets.com/arquivos/ids/472149-800-600?v=638346334883600000&width=800&height=600&aspect=true', true),
(8, 3, 'Chorizo Precocido Don Cristobal Sabor Hierbas -600gr', 'Chorizo Precocido Saborizado', 2400, 25, 'https://walmartcr.vtexassets.com/arquivos/ids/366893-800-600?v=638052682171270000&width=800&height=600&aspect=true', true),
(9, 3, 'Hongo Empacado - 250Gr', 'Buenas prácticas para almacenar el producto: Lugar Seco Y Fresco', 1700, 30, 'https://walmartcr.vtexassets.com/arquivos/ids/335578-800-600?v=637970713984070000&width=800&height=600&aspect=true', true),
(10, 4, 'Chuleta De Cerdo Ahumada La Granja - 500g', 'Agrega a tu carrito la Chuleta Ahumada La Granja y disfrutá un delicioso almuerzo o una grandiosa cena. ¡Provecho!',3350, 7, 'https://walmartcr.vtexassets.com/arquivos/ids/455061-800-600?v=638316312305100000&width=800&height=600&aspect=true', true),
(11, 4, 'Lavaplatos Zagaz Frutos Rojos - 1000gr', 'Tamaño (Gramaje, Volumen): 1000gr', 1020, 15, 'https://walmartcr.vtexassets.com/arquivos/ids/204972-800-600?v=637583235121100000&width=800&height=600&aspect=true', true),
(12, 4, '3 Pack Jabón De Tocador Neutro Skin Exfoliacion Total - 330gr', 'Oferta Unica', 2000, 22, 'https://walmartcr.vtexassets.com/arquivos/ids/304349-800-600?v=637850507022000000&width=800&height=600&aspect=true', true),
(13, 5, 'Olla arrocera Oster multiusos 10 tazas Color negro Modelo CKSTRC1700B', 'Da el paso a una vida más sana. 10 tazas - recubrimiento antiadherente', 19900, 10, 'https://walmartcr.vtexassets.com/arquivos/ids/358909-800-600?v=638031303931330000&width=800&height=600&aspect=true', true),
(14, 5, 'Sillón Mainstays, Reclinable Doble Blackberry', 'Sillón Mainstays, Reclinable Doble Blackberry', 229900, 5, 'https://walmartcr.vtexassets.com/arquivos/ids/456375-800-600?v=638319549704070000&width=800&height=600&aspect=true', true),
(15, 5, 'Pelotas de tenis Athletic works. Modelo WCO2101', 'Pelotas de tenis, 15 piezas por juego', 5500, 10, 'https://walmartcr.vtexassets.com/arquivos/ids/344848-800-600?v=637998251332500000&width=800&height=600&aspect=true', true);

CREATE TABLE proyecto.contacto (
    id_contacto INT PRIMARY KEY AUTO_INCREMENT,
    descripcion TEXT,
    telefono text,
    ubicacion varchar(1024),
    horario text,
    redes varchar(1024)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO proyecto.contacto (id_contacto, descripcion, telefono, ubicacion, horario, redes) VALUES
(1, 'Desde su humilde inicio en [Año de Fundación], [Nombre de la Tienda] ha evolucionado en una vibrante experiencia gastronómica, fusionando la autenticidad de los sabores con un compromiso inquebrantable con la frescura y la calidad, celebrando la diversidad culinaria y creando recuerdos deliciosos en cada bocado.', 
  '8572 8468', 'https://www.openstreetmap.org/note/3998061#map=18/9.85816/-83.94015&layers=N',
  'De Lunes a Domingo de 5:30 p.m a 10:30 p.m', 'https://www.facebook.com/sodadivinonino/?locale=es_LA');

create table proyecto.rol (
  id_rol INT NOT NULL AUTO_INCREMENT,
  nombre varchar(20),
  id_usuario int,
  PRIMARY KEY (id_rol),
  foreign key fk_rol_usuario (id_usuario) references usuario(id_usuario)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

insert into proyecto.rol (id_rol, nombre, id_usuario) values
 (1,'ROLE_ADMIN',1), (2,'ROLE_VENDEDOR',1), (3,'ROLE_USER',1),
 (4,'ROLE_VENDEDOR',2), (5,'ROLE_USER',2),
 (6,'ROLE_USER',3);
