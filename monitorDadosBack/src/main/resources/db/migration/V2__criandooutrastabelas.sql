create table estacao(
                        id int auto_increment not null primary key,
                        nome varchar(100) not null,
                        localizacao varchar(255) not null,
                        momento_alt timestamp not null,
                        tipo_alt char not null
);

create table tags(
                     id int auto_increment not null primary key,
                     endereco_mac varchar(50) not null unique,
                     funcionario varchar(100) not null,
                     cargo varchar(100) not null,
                     momento_alt timestamp not null,
                     tipo_alt char not null
);

create table controle_ponto(
                               id int auto_increment not null primary key,
                               momento timestamp not null,
                               sequencia int not null,
                               tag int not null,
                               constraint fk_tag foreign key (tag) references tags(id)
);

create table controle_fisico (
                                 id int auto_increment not null primary key,
                                 hora_ini time not null,
                                 hora_fim time not null,
                                 data date not null,
                                 med_temperatura numeric(8,3) not null,
                                 med_luminosidade numeric(8,3) not null,
                                 med_umidade numeric(8,3) not null
);

create table permissoes(
                           id int auto_increment not null primary key,
                           descricao varchar(100) not null,
                           identificacao varchar(15) not null
);

create table usuarios(
                         id int auto_increment not null primary key,
                         nome varchar(100) not null,
                         senha varchar(100) not null,
                         email varchar(100) not null,
                         permissao int not null,

                         constraint fk_permissao foreign key (permissao) references permissoes(id)
);

alter table dados add column estacao int not null;

alter table dados add CONSTRAINT fk_dado_estacao foreign key (estacao) references estacao(id);