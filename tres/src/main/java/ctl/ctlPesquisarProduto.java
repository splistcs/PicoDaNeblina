/*
 * O que PesquisarProduto precisa fazer?
 *
 * i. retornar produtos por nome
 *
 *    SELECT * FROM produto
 *    WHERE nome_prod LIKE %?%
 *
 * ii. retornar produtos por categoria
 *    
 *    SELECT *
 *    FROM (categoria INNER JOIN tem_categoria ON (id_cat = id_categoria)) 
 *                    INNER JOIN produto ON (id_produto = id_prod)
 *    WHERE id_cat = ?
 *
 * iii. retornar produtos por nome e categoria
 *
 *    SELECT *
 *    FROM (categoria INNER JOIN tem_categoria ON (id_cat = id_categoria)) 
 *                    INNER JOIN produto ON (id_produto = id_prod)
 *    WHERE (id_cat = ?) AND (nome_prod LIKE %?%)
 *
 *
 */
