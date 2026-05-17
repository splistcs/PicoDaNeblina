CREATE DATABASE pdn;

\c pdn

CREATE TABLE cliente (
  id              SERIAL,
  nome_completo   VARCHAR(70),
  email           VARCHAR(70),
  telefone        VARCHAR(21),
  cpf             VARCHAR(14),
  senhaHash       VARCHAR(70),
  ativo           BOOLEAN,
  tipoCadastro    INT,  -- Aqui entra o ENUM de TipoCadastro
  PRIMARY KEY (id)
);

CREATE TABLE endereco (
  id_e          SERIAL,
  cep           VARCHAR(9),
  numero        VARCHAR(10),
  complemento   VARCHAR(14),
  bairro        VARCHAR(14),
  rua           VARCHAR(14),
  cidade        VARCHAR(14),
  PRIMARY KEY (id_e)
);

CREATE TABLE tem_endereco (
  id_cliente    INT,
  id_endereco   INT,
  FOREIGN KEY (id_cliente) REFERENCES cliente (id),
  FOREIGN KEY (id_endereco) REFERENCES endereco (id_e),
  PRIMARY KEY (id_cliente, id_endereco)
  -- Como a chave é os dois id 1,1 e 1,2 são diferentes (espero)
);

CREATE TABLE carrinho (
  id_car            SERIAL,
  id_cliente        INT UNIQUE, -- Só pode ter um carrinho por id de cliente
  id_sessao         VARCHAR(21),
  data_car          DATE,
  valor_total_car   NUMERIC(12,2),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id),
  PRIMARY KEY (id_car)
);

CREATE TABLE endereco_pedido ( -- Entidade
  id_e          SERIAL,
  cep           VARCHAR(9),
  numero        VARCHAR(10),
  complemento   VARCHAR(14),
  bairro        VARCHAR(14),
  rua           VARCHAR(14),
  cidade        VARCHAR(14),
  PRIMARY KEY (id_e)
);

CREATE TABLE pedido (
  id_p          SERIAL,
  id_cliente    INT,
  id_destino    INT UNIQUE,   -- Queremos duplicar o end. para manter registro e fazer cópia da cópia... 
  data_p        DATE,
  valor_total   NUMERIC(12,2), -- 999.999.999.999,99 ninguém vai comprar algo com esse valor...
  valor_frete   NUMERIC(12,2),
  status_p      INT,           -- Aqui entra o ENUM de StatusPedido 
  FOREIGN KEY (id_cliente) REFERENCES cliente (id),
  FOREIGN KEY (id_destino) REFERENCES endereco_pedido (id_e),
  PRIMARY KEY (id_p)
);

CREATE TABLE categoria (
  id_cat    SERIAL,
  nome_cat  VARCHAR(70),
  slug      VARCHAR(70),
  ativo     BOOLEAN,
  id_pai    INT,
  FOREIGN KEY (id_pai) REFERENCES categoria (id_cat) ON DELETE SET NULL, 
  -- Se não houver essa restrição, vamos apagar um e levamos os pais
  PRIMARY KEY (id_cat)
);

CREATE TABLE produto (
  id_prod     SERIAL,
  nome_prod   VARCHAR(70),
  descricao   VARCHAR(210),
  material    VARCHAR(70),
  marca       VARCHAR(70),
  ativo       BOOLEAN,
  pwd_img     VARCHAR(100),
  PRIMARY KEY (id_prod)
);

CREATE TABLE tem_categoria (
  id_produto    INT,
  id_categoria  INT,
  FOREIGN KEY (id_produto) REFERENCES produto (id_prod),
  FOREIGN KEY (id_categoria) REFERENCES categoria (id_cat),
  PRIMARY KEY (id_produto, id_categoria)
);

CREATE TABLE sku (
  id_sku    SERIAL,
  id_prod   INT,
  estoque   INT,
  preco     NUMERIC(12,2),
  peso      INT,
  cod_uni   VARCHAR(100),
  alt_cm    INT,
  larg_cm   INT,
  compr_cm  INT,
  FOREIGN KEY (id_prod) REFERENCES produto (id_prod),
  PRIMARY KEY (id_sku)
);

-- hashMap especificacao, minha gambiarra criar uma nova tabela.
-- no postgrep existe o JSON ou Array, mas como são tam. fixo talvez de ruim.
CREATE TABLE especificacao (
  id_hp   INT,
  var     VARCHAR(70),
  valor   VARCHAR(70),
  FOREIGN KEY (id_hp) REFERENCES sku (id_sku),
  PRIMARY KEY (id_hp, var)
);

CREATE TABLE item_carrinho (
  id_item_car SERIAL,
  id_car      INT,
  id_sku      INT,
  quant       INT,
  FOREIGN KEY (id_car) REFERENCES carrinho (id_car),
  FOREIGN KEY (id_sku) REFERENCES sku (id_sku),
  PRIMARY KEY (id_item_car)
);

CREATE TABLE item_pedido (
  id_item_p    SERIAL,
  id_pedido    INT,
  id_sku       INT,
  quant        INT,
  preco_unit   NUMERIC(12,2),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_p),
  FOREIGN KEY (id_sku) REFERENCES sku (id_sku),
  PRIMARY KEY (id_item_p)
);
