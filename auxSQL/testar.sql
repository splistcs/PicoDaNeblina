INSERT INTO categoria(nome_cat, slug, ativo, id_pai)
VALUES ('a', 'a', true, NULL),
       ('b', 'b', true, NULL),
       ('c', 'c', true, 1);

INSERT INTO produto(nome_prod, descricao, material, marca, ativo, pwd_img)
VALUES ('d', 'd', 'd', 'd', true, 'd'),
       ('f', 'f', 'f', 'f', true, 'f');

INSERT INTO tem_categoria (id_produto, id_categoria)
VALUES (1, 3),
       (2, 2);

SELECT * FROM categoria;
SELECT nome_prod, nome_cat FROM
(categoria INNER JOIN tem_categoria ON (id_cat = id_categoria)) 
           INNER JOIN produto ON (id_produto = id_prod); 
