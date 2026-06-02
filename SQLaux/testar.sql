INSERT INTO categoria (nome_cat, slug, ativo, id_pai)
VALUES  ('Masculino', 'masc', true, NULL),
        ('Feminino', 'fem', true, NULL),
        ('Camisas', 'camisas', true, 1),
        ('Cuecas', 'cuecas', true, 1),
        ('Calças', 'calcas', true, 2);

INSERT INTO produto (nome_prod, descricao, material, marca, ativo, pwd_img)
VALUES  ('Camisa Polo', 'Polo confortavel para o dia a dia.', 'Algodao', 'Renner', true, 'teste1.jpg'),
        ('Calca Jeans', 'Calca jeans slim fit com elastano.', 'Jeans', 'Levis', true, 'teste1.jpg'),
        ('Cueca Boxer', 'Cueca boxer sem costura super confortavel.', 'Microfibra', 'SiofStyle', true, 'teste1.jpg'),
        ('Camisa Pomba', 'Estampa de alta fidelidade.', 'Microfibra', 'CanvasMake', true, 'teste2.jpeg'),
        ('Camisa Plant', 'Do famoso jogo.', 'Microfibra', 'CanvasMake', true, 'teste3.jpeg'),
        ('Camisa Minion', 'De um filme.', 'Microfibra', 'CanvasMake', true, 'teste4.jpeg');

INSERT INTO sku (id_prod, estoque, preco, peso, cod_uni, alt_cm, larg_cm, compr_cm)
VALUES (4, 10, 11.0, 3, 'cod_100', 10, 10, 10),
       (4, 10, 10.0, 3, 'cod_101', 10, 10, 10),
       (4, 10, 17.0, 3, 'cod_102', 10, 10, 10),
       (5, 10, 21.0, 3, 'cod_200', 10, 10, 10),
       (5, 10, 30.0, 3, 'cod_201', 10, 10, 10),
       (5, 10, 47.0, 3, 'cod_202', 10, 10, 10),
       (6, 10, 81.0, 3, 'cod_300', 10, 10, 10),
       (6, 10, 50.0, 3, 'cod_301', 10, 10, 10),
       (6, 10, 97.0, 3, 'cod_302', 10, 10, 10);



INSERT INTO especificacao (id_hp, var, valor)
VALUES (1, 'TAMANHO', 'P'),
       (1, 'COR', 'BRANCO'),
       (2, 'TAMANHO', 'M'),
       (2, 'COR', 'BRANCO'),
       (3, 'TAMANHO', 'G'),
       (3, 'COR', 'BRANCO'),
       (4, 'TAMANHO', 'P'),
       (4, 'COR', 'PRETO'),
       (5, 'TAMANHO', 'M'),
       (5, 'COR', 'PRETO'),       
       (6, 'TAMANHO', 'G'),
       (6, 'COR', 'PRETO'),
       (7, 'TAMANHO', 'P'),
       (7, 'COR', 'PRETO'),
       (8, 'TAMANHO', 'M'),
       (8, 'COR', 'PRETO'),       
       (9, 'TAMANHO', 'G'),
       (9, 'COR', 'PRETO');

INSERT INTO tem_categoria (id_produto, id_categoria)
VALUES  (1, 1),
        (1, 3),
        (2, 2),
        (2, 5),
        (4, 3),
        (5, 3),
        (6, 3),
        (3, 4);

INSERT INTO cliente(nome_completo, email, telefone, cpf, senhaHash, ativo, tipoCadastro)
VALUES ('Marcus', 'Marc@gmail.com', '1898', '192921', '1234', true, 2);

SELECT * FROM categoria;
SELECT nome_prod, nome_cat FROM
(categoria INNER JOIN tem_categoria ON (id_cat = id_categoria)) 
           INNER JOIN produto ON (id_produto = id_prod); 
