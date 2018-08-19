INSERT INTO public.categoria(name) VALUES ('Informatica'),('Escritorio');
INSERT INTO public.producto (name, price) VALUES ('Computadora', 2000.00) , ('Impresora', 800.00), ('Mouse', 80.00);
INSERT INTO public.producto_categoria(producto_id, category_id) VALUES (1, 1), (2,1), (2,2), (3,1);
INSERT INTO public.estado(name) VALUES ('Minas Gerais'), ('Sao Paulo');
INSERT INTO public.ciudad(name, state_id) VALUES ('Uberlandia', 1), ('Sao Paulo', 2), ('Campinas', 2);


INSERT INTO cliente(cpf_ou_cnpj, email, name, type) VALUES ('36378912377', 'maria@gmail.com','Maria Silva',1);
INSERT INTO telefono (cliente_id, phone) VALUES (1,'93838393'),(1,'27363323');

INSERT INTO direccion (cep,complement, district, number, street, city_id, client_id) VALUES ('38220834', 'Apto 203', 'Jardim', 300, 'Rua Flores', 1, 1);
