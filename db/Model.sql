DROP TABLE usuarios;
DROP TABLE carros;

CREATE TABLE usuarios (
	"id" bigserial NOT NULL,
	"nome" varchar(255) NOT NULL,
	"email" varchar(255) NOT NULL,
	"senha" varchar(255) NOT NULL,
	"qrcode" varchar(255) NULL,
	"admin" boolean NULL,
	CONSTRAINT usuarios_pkey PRIMARY KEY (id)
);


CREATE TABLE carros (
	"id" bigserial NOT NULL,
	"nome" varchar(255) NOT NULL,
	"preco" float8 NOT NULL,
	"ano" int4 NOT NULL,
	"marca" varchar(255) NOT NULL,
	"cidade" varchar(255) NOT NULL,
	"placa" varchar(255) NOT NULL,
	"qrcode" varchar(255) NULL,
	CONSTRAINT carros_pkey PRIMARY KEY (id)
);