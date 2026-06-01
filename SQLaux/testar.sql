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
