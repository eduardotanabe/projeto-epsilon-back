 
    create table imagem (
       id int8 generated by default as identity,
        data bytea,
        data_atualizacao timestamp,
        data_criacao timestamp,
        nome varchar(255),
        tamanho int8 not null,
        tipo_arquivo varchar(255),
        veiculo_id int8,
        primary key (id)
    );

    create table marca (
       id int4 generated by default as identity,
        nome varchar(255),
        primary key (id)
    );

    create table tipo_veiculo (
       id int4 generated by default as identity,
        nome varchar(255),
        primary key (id)
    );

    create table usuario (
       id int8 generated by default as identity,
        cargo varchar(255),
        cidade varchar(255),
        contato_particular varchar(255),
        contato_profissional varchar(255),
        cpf varchar(255),
        email varchar(255),
        lotacao varchar(255),
        nome varchar(255),
        orgao varchar(255),
        uf varchar(255),
        primary key (id)
    );

    create table veiculo (
       id int8 generated by default as identity,
        ano_fabricacao int4,
        ano_modelo int4,
        modelo varchar(255),
        placa_licenca varchar(255),
        sequencial_chassi varchar(255),
        sequencial_motor varchar(255),
        marca_id int4,
        tipo_veiculo_id int4,
        primary key (id)
    );

    alter table if exists imagem 
       add constraint FK1kes45yh35mrpd3nipdggb69a 
       foreign key (veiculo_id) 
       references veiculo;

    alter table if exists veiculo 
       add constraint FK9g5yovckuf4squ6rf8vpxu809 
       foreign key (marca_id) 
       references marca;

    alter table if exists veiculo 
       add constraint FKgc2ggwxavgll78ola3dfu21t9 
       foreign key (tipo_veiculo_id) 
       references tipo_veiculo;