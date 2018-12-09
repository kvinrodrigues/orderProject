INSERT INTO public.categoria(name) VALUES
                    ('Informatica'),('Escritorio'),
                    ('Cama mesa e banho'), ('Electronicos'),
                    ('Jardineria'), ('Decoracion');
INSERT INTO public.producto (name, price) VALUES
      ('Computadora', 2000.00),
      ('Impresora', 800.00),
      ('Mouse', 80.00),
      ('Mesa de Escritorio',300.00),
      ('Toalla', 50.00),
      ('Colcha', 200.00),
      ('TV true color', 1200.00),
      ('Siega', 800.00),
      ('Pantalla', 100.00),
      ('Pendiente', 180.00),
      ('Shampoo', 90.00);
INSERT INTO public.producto_categoria(category_id, producto_id) VALUES
      (1, 1), (1,2), (1,3),
      (2,2), (2,4),
      (3,5), (3,6),
      (4,1), (4,2), (4,3),(4,7),
      (5,8),
      (6,9), (6,10),
      (7,11);

INSERT INTO public.estado(name) VALUES ('Minas Gerais'), ('Sao Paulo');
INSERT INTO public.ciudad(name, state_id) VALUES ('Uberlandia', 1), ('Sao Paulo', 2), ('Campinas', 2);


INSERT INTO cliente(cpf_ou_cnpj, email, name, type) VALUES ('36378912377', 'maria@gmail.com','Maria Silva',1);
INSERT INTO telefono (cliente_id, phone) VALUES (1,'93838393'),(1,'27363323');

INSERT INTO direccion (cep,complement, district, number, street, city_id, client_id) VALUES ('38220834', 'Apto 203', 'Jardim', 300, 'Rua Flores', 1, 1);

INSERT INTO public.pedido (adress_id, moment, client_id) VALUES (1,'30/09/2017 10:32',1);
INSERT INTO public.pedido (adress_id, moment, client_id) VALUES (1,'10/10/2017 19:35',1);


INSERT INTO public.pago(order_id, adress_id, pay_state) VALUES (1,1,2);
INSERT INTO public.pago_con_tarjeta(plots_number, order_id) VALUES (6, 1);


INSERT INTO public.pago(order_id, adress_id, pay_state) VALUES (2,1,1);
INSERT INTO public.pago_con_boleto(expiration_data, pay_data, order_id) VALUES ('20/10/2017 00:00', null, 2);

INSERT INTO public.item_pedido(discount, price, quantity, product_id, order_id) VALUES (0.00, 2000.00, 1, 1, 1);

INSERT INTO public.item_pedido(discount, price, quantity, product_id, order_id) VALUES (0.00, 80.00, 2, 3, 1);

INSERT INTO public.item_pedido(discount, price, quantity, product_id, order_id) VALUES (100.00, 800.00, 1, 2, 2);
