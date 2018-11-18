--Database: PropostaCalCardTech

--DROP DATABASE "PropostaCalCardTech";

CREATE DATABASE "PropostaCalCardTech" WITH
  OWNER = "postgres"
  ENCODING = 'UTF8'
  TABLESPACE = pg_default;

GRANT CREATE, TEMPORARY, CONNECT
  ON DATABASE "PropostaCalCardTech"
TO PUBLIC;

GRANT CREATE, TEMPORARY, CONNECT
  ON DATABASE "PropostaCalCardTech"
TO postgres;


--Table: public.usuario

--DROP TABLE public.usuario;

CREATE TABLE public.usuario (
  id       bigint NOT NULL,
  "data"   date NOT NULL,
  senha    varchar(150) NOT NULL,
  usuario  varchar(150) NOT NULL,
  /* Keys */
  CONSTRAINT usuario_pkey
    PRIMARY KEY (id)
) WITH (
    OIDS = FALSE
  );

ALTER TABLE public.usuario
  OWNER TO postgres;
  
  
--Sequence: public.tb_usuario_id_seq

--DROP SEQUENCE public.tb_usuario_id_seq;

CREATE SEQUENCE public.tb_usuario_id_seq
  START 1
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

ALTER TABLE public.tb_usuario_id_seq
  OWNER TO postgres;
  

--Table: public.proposta

--DROP TABLE public.proposta;

CREATE TABLE public.proposta (
  id                bigint NOT NULL,
  cpf               varchar(15) NOT NULL,
  "data"            date,
  dependentes       bigint NOT NULL,
  estado            varchar(255) NOT NULL,
  estadocivil       varchar(255) NOT NULL,
  idade             bigint NOT NULL,
  limite            varchar(255),
  nome              varchar(255) NOT NULL,
  renda             varchar(255) NOT NULL,
  resultadoanalise  varchar(255),
  sexo              varchar(255) NOT NULL,
  /* Keys */
  CONSTRAINT proposta_pkey
    PRIMARY KEY (id)
) WITH (
    OIDS = FALSE
  );

ALTER TABLE public.proposta
  OWNER TO postgres;
  
  
--Sequence: public.tb_proposta_id_seq

--DROP SEQUENCE public.tb_proposta_id_seq;

CREATE SEQUENCE public.tb_proposta_id_seq
  START 1
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

ALTER TABLE public.tb_proposta_id_seq
  OWNER TO postgres;
  
  
--Table: public.serasaspc

--DROP TABLE public.serasaspc;

CREATE TABLE public.serasaspc (
  id    bigint NOT NULL,
  cpf   varchar(15) NOT NULL,
  nome  varchar(255) NOT NULL,
  /* Keys */
  CONSTRAINT serasaspc_pkey
    PRIMARY KEY (id)
) WITH (
    OIDS = FALSE
  );

ALTER TABLE public.serasaspc
  OWNER TO postgres; 
  
  
--Sequence: public.tb_serasaspc_id_seq

--DROP SEQUENCE public.tb_serasaspc_id_seq;

CREATE SEQUENCE public.tb_serasaspc_id_seq
  START 3
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;

ALTER TABLE public.tb_serasaspc_id_seq
  OWNER TO postgres;