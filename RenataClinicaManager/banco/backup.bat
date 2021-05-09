PGDMP                          y            bancorenata    12.4    12.4 L    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    40970    bancorenata    DATABASE     �   CREATE DATABASE bancorenata WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Portuguese_Brazil.1252' LC_CTYPE = 'Portuguese_Brazil.1252';
    DROP DATABASE bancorenata;
                postgres    false            �            1259    43072    agendamento    TABLE     �   CREATE TABLE public.agendamento (
    age_cod integer NOT NULL,
    pes_cpf character varying NOT NULL,
    tra_cod integer NOT NULL,
    fun_cod integer,
    age_dthr timestamp without time zone,
    age_observacos character varying
);
    DROP TABLE public.agendamento;
       public         heap    postgres    false            �            1259    43117    atestado    TABLE     �   CREATE TABLE public.atestado (
    ate_txtprincipal character varying,
    ate_txtauxiliar character varying,
    ate_endereco character varying,
    ate_local character varying,
    ate_dentista character varying
);
    DROP TABLE public.atestado;
       public         heap    postgres    false            �            1259    43123    cargo    TABLE     �   CREATE TABLE public.cargo (
    fun_cod integer,
    car_cod integer NOT NULL,
    car_nome character varying,
    car_lvl integer
);
    DROP TABLE public.cargo;
       public         heap    postgres    false            �            1259    43040    compra    TABLE     �   CREATE TABLE public.compra (
    com_cod integer NOT NULL,
    com_valor numeric,
    com_data date,
    for_cnpj character varying
);
    DROP TABLE public.compra;
       public         heap    postgres    false            �            1259    43034    config    TABLE     �   CREATE TABLE public.config (
    con_nome character varying,
    con_rua character varying,
    con_numero character varying,
    con_cidade character varying,
    com_tema boolean
);
    DROP TABLE public.config;
       public         heap    postgres    false            �            1259    43056    contaspagar    TABLE       CREATE TABLE public.contaspagar (
    pag_cod integer NOT NULL,
    pag_parcela integer NOT NULL,
    com_cod integer,
    pag_dtvencimento date,
    pag_valor numeric,
    pag_tipo character varying,
    pag_dtpagamento date,
    for_cnpj character varying
);
    DROP TABLE public.contaspagar;
       public         heap    postgres    false            �            1259    43064    contasreceber    TABLE     �   CREATE TABLE public.contasreceber (
    rec_cod integer NOT NULL,
    rec_parcela integer NOT NULL,
    age_cod integer,
    tra_cod integer,
    rec_dtvencimento date,
    rec_dtrecebimento date,
    rec_tipo character varying,
    rec_valor numeric
);
 !   DROP TABLE public.contasreceber;
       public         heap    postgres    false            �            1259    43026    dente    TABLE     `   CREATE TABLE public.dente (
    tra_dentes integer NOT NULL,
    den_dente character varying
);
    DROP TABLE public.dente;
       public         heap    postgres    false            �            1259    43082    feriado    TABLE     u   CREATE TABLE public.feriado (
    fer_cod integer NOT NULL,
    fer_descricao character varying,
    fer_dia date
);
    DROP TABLE public.feriado;
       public         heap    postgres    false            �            1259    43048 
   fornecedor    TABLE     �   CREATE TABLE public.fornecedor (
    for_cnpj character varying NOT NULL,
    for_nome character varying,
    for_contato character varying
);
    DROP TABLE public.fornecedor;
       public         heap    postgres    false            �            1259    42994    funcionario    TABLE       CREATE TABLE public.funcionario (
    pes_cpf character varying NOT NULL,
    fun_cod integer NOT NULL,
    fun_login character varying,
    fun_senha character varying,
    fun_nome character varying,
    fun_telefone character varying,
    fun_horarios character varying
);
    DROP TABLE public.funcionario;
       public         heap    postgres    false            �            1259    43103    itenscompra    TABLE     �   CREATE TABLE public.itenscompra (
    com_cod integer NOT NULL,
    mat_cod integer NOT NULL,
    mat_preco numeric,
    mat_qtde integer
);
    DROP TABLE public.itenscompra;
       public         heap    postgres    false            �            1259    43098    itenstratamento    TABLE     z   CREATE TABLE public.itenstratamento (
    tra_cod integer NOT NULL,
    mat_cod integer NOT NULL,
    mat_qtde integer
);
 #   DROP TABLE public.itenstratamento;
       public         heap    postgres    false            �            1259    43090    material    TABLE     �   CREATE TABLE public.material (
    mat_cod integer NOT NULL,
    mat_nome character varying,
    mat_valor numeric,
    mat_estoque integer
);
    DROP TABLE public.material;
       public         heap    postgres    false            �            1259    42986    paciente    TABLE       CREATE TABLE public.paciente (
    pes_cpf character varying NOT NULL,
    pes_nome character varying,
    pes_telefone character varying,
    pes_cidade character varying,
    pes_rua character varying,
    pes_numero integer,
    pes_bairro character varying,
    pes_data date
);
    DROP TABLE public.paciente;
       public         heap    postgres    false            �            1259    43004    pessoatratamento    TABLE     o   CREATE TABLE public.pessoatratamento (
    tra_cod integer NOT NULL,
    pes_cpf character varying NOT NULL
);
 $   DROP TABLE public.pessoatratamento;
       public         heap    postgres    false            �            1259    43111    receita    TABLE     �   CREATE TABLE public.receita (
    rec_txtprincipal character varying,
    rec_remedios character varying,
    rec_local character varying,
    rec_dentista character varying
);
    DROP TABLE public.receita;
       public         heap    postgres    false            �            1259    43016 
   tratamento    TABLE     �   CREATE TABLE public.tratamento (
    tra_cod integer NOT NULL,
    tra_nome character varying,
    tra_dentes integer,
    tra_peridiocidade integer,
    tra_tempo integer,
    tra_valor numeric
);
    DROP TABLE public.tratamento;
       public         heap    postgres    false            �          0    43072    agendamento 
   TABLE DATA           c   COPY public.agendamento (age_cod, pes_cpf, tra_cod, fun_cod, age_dthr, age_observacos) FROM stdin;
    public          postgres    false    212   `       �          0    43117    atestado 
   TABLE DATA           l   COPY public.atestado (ate_txtprincipal, ate_txtauxiliar, ate_endereco, ate_local, ate_dentista) FROM stdin;
    public          postgres    false    218   "`       �          0    43123    cargo 
   TABLE DATA           D   COPY public.cargo (fun_cod, car_cod, car_nome, car_lvl) FROM stdin;
    public          postgres    false    219   ?`       �          0    43040    compra 
   TABLE DATA           H   COPY public.compra (com_cod, com_valor, com_data, for_cnpj) FROM stdin;
    public          postgres    false    208   \`       �          0    43034    config 
   TABLE DATA           U   COPY public.config (con_nome, con_rua, con_numero, con_cidade, com_tema) FROM stdin;
    public          postgres    false    207   y`       �          0    43056    contaspagar 
   TABLE DATA           �   COPY public.contaspagar (pag_cod, pag_parcela, com_cod, pag_dtvencimento, pag_valor, pag_tipo, pag_dtpagamento, for_cnpj) FROM stdin;
    public          postgres    false    210   �`       �          0    43064    contasreceber 
   TABLE DATA           �   COPY public.contasreceber (rec_cod, rec_parcela, age_cod, tra_cod, rec_dtvencimento, rec_dtrecebimento, rec_tipo, rec_valor) FROM stdin;
    public          postgres    false    211   �`       �          0    43026    dente 
   TABLE DATA           6   COPY public.dente (tra_dentes, den_dente) FROM stdin;
    public          postgres    false    206   �`       �          0    43082    feriado 
   TABLE DATA           B   COPY public.feriado (fer_cod, fer_descricao, fer_dia) FROM stdin;
    public          postgres    false    213   �`       �          0    43048 
   fornecedor 
   TABLE DATA           E   COPY public.fornecedor (for_cnpj, for_nome, for_contato) FROM stdin;
    public          postgres    false    209   
a       �          0    42994    funcionario 
   TABLE DATA           s   COPY public.funcionario (pes_cpf, fun_cod, fun_login, fun_senha, fun_nome, fun_telefone, fun_horarios) FROM stdin;
    public          postgres    false    203   'a       �          0    43103    itenscompra 
   TABLE DATA           L   COPY public.itenscompra (com_cod, mat_cod, mat_preco, mat_qtde) FROM stdin;
    public          postgres    false    216   Da       �          0    43098    itenstratamento 
   TABLE DATA           E   COPY public.itenstratamento (tra_cod, mat_cod, mat_qtde) FROM stdin;
    public          postgres    false    215   aa       �          0    43090    material 
   TABLE DATA           M   COPY public.material (mat_cod, mat_nome, mat_valor, mat_estoque) FROM stdin;
    public          postgres    false    214   ~a       �          0    42986    paciente 
   TABLE DATA           z   COPY public.paciente (pes_cpf, pes_nome, pes_telefone, pes_cidade, pes_rua, pes_numero, pes_bairro, pes_data) FROM stdin;
    public          postgres    false    202   �a       �          0    43004    pessoatratamento 
   TABLE DATA           <   COPY public.pessoatratamento (tra_cod, pes_cpf) FROM stdin;
    public          postgres    false    204   �a       �          0    43111    receita 
   TABLE DATA           Z   COPY public.receita (rec_txtprincipal, rec_remedios, rec_local, rec_dentista) FROM stdin;
    public          postgres    false    217   �a       �          0    43016 
   tratamento 
   TABLE DATA           l   COPY public.tratamento (tra_cod, tra_nome, tra_dentes, tra_peridiocidade, tra_tempo, tra_valor) FROM stdin;
    public          postgres    false    205   �a       �
           2606    43081 #   agendamento agendamento_age_cod_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_age_cod_key UNIQUE (age_cod);
 M   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_age_cod_key;
       public            postgres    false    212            �
           2606    43079    agendamento agendamento_pkey 
   CONSTRAINT     q   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_pkey PRIMARY KEY (age_cod, pes_cpf, tra_cod);
 F   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_pkey;
       public            postgres    false    212    212    212            �
           2606    43130    cargo cargos_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargos_pkey PRIMARY KEY (car_cod);
 ;   ALTER TABLE ONLY public.cargo DROP CONSTRAINT cargos_pkey;
       public            postgres    false    219            �
           2606    43047    compra compra_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (com_cod);
 <   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_pkey;
       public            postgres    false    208            �
           2606    43063    contaspagar contaspagar_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.contaspagar
    ADD CONSTRAINT contaspagar_pkey PRIMARY KEY (pag_cod, pag_parcela);
 F   ALTER TABLE ONLY public.contaspagar DROP CONSTRAINT contaspagar_pkey;
       public            postgres    false    210    210            �
           2606    43071     contasreceber contasreceber_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.contasreceber
    ADD CONSTRAINT contasreceber_pkey PRIMARY KEY (rec_cod, rec_parcela);
 J   ALTER TABLE ONLY public.contasreceber DROP CONSTRAINT contasreceber_pkey;
       public            postgres    false    211    211            �
           2606    43033    dente dente_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.dente
    ADD CONSTRAINT dente_pkey PRIMARY KEY (tra_dentes);
 :   ALTER TABLE ONLY public.dente DROP CONSTRAINT dente_pkey;
       public            postgres    false    206            �
           2606    43089    feriado feriado_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.feriado
    ADD CONSTRAINT feriado_pkey PRIMARY KEY (fer_cod);
 >   ALTER TABLE ONLY public.feriado DROP CONSTRAINT feriado_pkey;
       public            postgres    false    213            �
           2606    43055    fornecedor fornecedor_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.fornecedor
    ADD CONSTRAINT fornecedor_pkey PRIMARY KEY (for_cnpj);
 D   ALTER TABLE ONLY public.fornecedor DROP CONSTRAINT fornecedor_pkey;
       public            postgres    false    209            �
           2606    43003 #   funcionario funcionario_fun_cod_key 
   CONSTRAINT     a   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_fun_cod_key UNIQUE (fun_cod);
 M   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_fun_cod_key;
       public            postgres    false    203            �
           2606    43001    funcionario funcionario_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.funcionario
    ADD CONSTRAINT funcionario_pkey PRIMARY KEY (pes_cpf, fun_cod);
 F   ALTER TABLE ONLY public.funcionario DROP CONSTRAINT funcionario_pkey;
       public            postgres    false    203    203            �
           2606    43110    itenscompra itenscompra_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.itenscompra
    ADD CONSTRAINT itenscompra_pkey PRIMARY KEY (com_cod, mat_cod);
 F   ALTER TABLE ONLY public.itenscompra DROP CONSTRAINT itenscompra_pkey;
       public            postgres    false    216    216            �
           2606    43102 $   itenstratamento itenstratamento_pkey 
   CONSTRAINT     p   ALTER TABLE ONLY public.itenstratamento
    ADD CONSTRAINT itenstratamento_pkey PRIMARY KEY (tra_cod, mat_cod);
 N   ALTER TABLE ONLY public.itenstratamento DROP CONSTRAINT itenstratamento_pkey;
       public            postgres    false    215    215            �
           2606    43097    material materiais_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.material
    ADD CONSTRAINT materiais_pkey PRIMARY KEY (mat_cod);
 A   ALTER TABLE ONLY public.material DROP CONSTRAINT materiais_pkey;
       public            postgres    false    214            �
           2606    42993    paciente paciente_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.paciente
    ADD CONSTRAINT paciente_pkey PRIMARY KEY (pes_cpf);
 @   ALTER TABLE ONLY public.paciente DROP CONSTRAINT paciente_pkey;
       public            postgres    false    202            �
           2606    43015 -   pessoatratamento pessoatratamento_pes_cpf_key 
   CONSTRAINT     k   ALTER TABLE ONLY public.pessoatratamento
    ADD CONSTRAINT pessoatratamento_pes_cpf_key UNIQUE (pes_cpf);
 W   ALTER TABLE ONLY public.pessoatratamento DROP CONSTRAINT pessoatratamento_pes_cpf_key;
       public            postgres    false    204            �
           2606    43011 &   pessoatratamento pessoatratamento_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.pessoatratamento
    ADD CONSTRAINT pessoatratamento_pkey PRIMARY KEY (tra_cod, pes_cpf);
 P   ALTER TABLE ONLY public.pessoatratamento DROP CONSTRAINT pessoatratamento_pkey;
       public            postgres    false    204    204            �
           2606    43013 -   pessoatratamento pessoatratamento_tra_cod_key 
   CONSTRAINT     k   ALTER TABLE ONLY public.pessoatratamento
    ADD CONSTRAINT pessoatratamento_tra_cod_key UNIQUE (tra_cod);
 W   ALTER TABLE ONLY public.pessoatratamento DROP CONSTRAINT pessoatratamento_tra_cod_key;
       public            postgres    false    204            �
           2606    43023    tratamento tratamento_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.tratamento
    ADD CONSTRAINT tratamento_pkey PRIMARY KEY (tra_cod);
 D   ALTER TABLE ONLY public.tratamento DROP CONSTRAINT tratamento_pkey;
       public            postgres    false    205            �
           2606    43025 $   tratamento tratamento_tra_dentes_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.tratamento
    ADD CONSTRAINT tratamento_tra_dentes_key UNIQUE (tra_dentes);
 N   ALTER TABLE ONLY public.tratamento DROP CONSTRAINT tratamento_tra_dentes_key;
       public            postgres    false    205                       2606    43131 $   agendamento agendamento_fun_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_fun_cod_fkey FOREIGN KEY (fun_cod) REFERENCES public.funcionario(fun_cod);
 N   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_fun_cod_fkey;
       public          postgres    false    203    212    2773                       2606    43176 $   agendamento agendamento_pes_cpf_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_pes_cpf_fkey FOREIGN KEY (pes_cpf) REFERENCES public.pessoatratamento(pes_cpf);
 N   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_pes_cpf_fkey;
       public          postgres    false    204    212    2777                       2606    43171 $   agendamento agendamento_tra_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.agendamento
    ADD CONSTRAINT agendamento_tra_cod_fkey FOREIGN KEY (tra_cod) REFERENCES public.pessoatratamento(tra_cod);
 N   ALTER TABLE ONLY public.agendamento DROP CONSTRAINT agendamento_tra_cod_fkey;
       public          postgres    false    212    204    2781            	           2606    43136    cargo cargos_fun_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cargo
    ADD CONSTRAINT cargos_fun_cod_fkey FOREIGN KEY (fun_cod) REFERENCES public.funcionario(fun_cod);
 C   ALTER TABLE ONLY public.cargo DROP CONSTRAINT cargos_fun_cod_fkey;
       public          postgres    false    203    219    2773            �
           2606    43191    compra compra_for_cnpj_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.compra
    ADD CONSTRAINT compra_for_cnpj_fkey FOREIGN KEY (for_cnpj) REFERENCES public.fornecedor(for_cnpj);
 E   ALTER TABLE ONLY public.compra DROP CONSTRAINT compra_for_cnpj_fkey;
       public          postgres    false    2791    209    208            �
           2606    43181 $   contaspagar contaspagar_com_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contaspagar
    ADD CONSTRAINT contaspagar_com_cod_fkey FOREIGN KEY (com_cod) REFERENCES public.compra(com_cod);
 N   ALTER TABLE ONLY public.contaspagar DROP CONSTRAINT contaspagar_com_cod_fkey;
       public          postgres    false    2789    208    210            �
           2606    43186 %   contaspagar contaspagar_for_cnpj_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contaspagar
    ADD CONSTRAINT contaspagar_for_cnpj_fkey FOREIGN KEY (for_cnpj) REFERENCES public.fornecedor(for_cnpj);
 O   ALTER TABLE ONLY public.contaspagar DROP CONSTRAINT contaspagar_for_cnpj_fkey;
       public          postgres    false    2791    209    210                       2606    43166 (   contasreceber contasreceber_age_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contasreceber
    ADD CONSTRAINT contasreceber_age_cod_fkey FOREIGN KEY (age_cod) REFERENCES public.agendamento(age_cod);
 R   ALTER TABLE ONLY public.contasreceber DROP CONSTRAINT contasreceber_age_cod_fkey;
       public          postgres    false    212    211    2797                        2606    43141 (   contasreceber contasreceber_tra_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.contasreceber
    ADD CONSTRAINT contasreceber_tra_cod_fkey FOREIGN KEY (tra_cod) REFERENCES public.tratamento(tra_cod);
 R   ALTER TABLE ONLY public.contasreceber DROP CONSTRAINT contasreceber_tra_cod_fkey;
       public          postgres    false    2783    205    211            �
           2606    43196    dente dente_tra_dentes_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.dente
    ADD CONSTRAINT dente_tra_dentes_fkey FOREIGN KEY (tra_dentes) REFERENCES public.tratamento(tra_dentes);
 E   ALTER TABLE ONLY public.dente DROP CONSTRAINT dente_tra_dentes_fkey;
       public          postgres    false    2785    205    206                       2606    43156 $   itenscompra itenscompra_com_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.itenscompra
    ADD CONSTRAINT itenscompra_com_cod_fkey FOREIGN KEY (com_cod) REFERENCES public.compra(com_cod);
 N   ALTER TABLE ONLY public.itenscompra DROP CONSTRAINT itenscompra_com_cod_fkey;
       public          postgres    false    216    208    2789                       2606    43161 $   itenscompra itenscompra_mat_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.itenscompra
    ADD CONSTRAINT itenscompra_mat_cod_fkey FOREIGN KEY (mat_cod) REFERENCES public.material(mat_cod);
 N   ALTER TABLE ONLY public.itenscompra DROP CONSTRAINT itenscompra_mat_cod_fkey;
       public          postgres    false    216    214    2803                       2606    43146 ,   itenstratamento itenstratamento_mat_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.itenstratamento
    ADD CONSTRAINT itenstratamento_mat_cod_fkey FOREIGN KEY (mat_cod) REFERENCES public.material(mat_cod);
 V   ALTER TABLE ONLY public.itenstratamento DROP CONSTRAINT itenstratamento_mat_cod_fkey;
       public          postgres    false    214    215    2803                       2606    43151 ,   itenstratamento itenstratamento_tra_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.itenstratamento
    ADD CONSTRAINT itenstratamento_tra_cod_fkey FOREIGN KEY (tra_cod) REFERENCES public.tratamento(tra_cod);
 V   ALTER TABLE ONLY public.itenstratamento DROP CONSTRAINT itenstratamento_tra_cod_fkey;
       public          postgres    false    215    2783    205            �
           2606    43201 .   pessoatratamento pessoatratamento_pes_cpf_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pessoatratamento
    ADD CONSTRAINT pessoatratamento_pes_cpf_fkey FOREIGN KEY (pes_cpf) REFERENCES public.paciente(pes_cpf);
 X   ALTER TABLE ONLY public.pessoatratamento DROP CONSTRAINT pessoatratamento_pes_cpf_fkey;
       public          postgres    false    202    204    2771            �
           2606    43206 .   pessoatratamento pessoatratamento_tra_cod_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pessoatratamento
    ADD CONSTRAINT pessoatratamento_tra_cod_fkey FOREIGN KEY (tra_cod) REFERENCES public.tratamento(tra_cod);
 X   ALTER TABLE ONLY public.pessoatratamento DROP CONSTRAINT pessoatratamento_tra_cod_fkey;
       public          postgres    false    204    205    2783            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �     