package com.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate; // Para tipo 'data' no PostgreSQL

@Entity
@Table(name = "pessoa")
@Data // Anotação do Lombok para gerar getters, setters, toString, equals e hashCode.
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ativo")
    private Boolean ativo = false; // 'logico' DEFAULT 'N'::bpchar

    @Column(name = "tipopessoa", length = 1)
    private String tipoPessoa; // 'character(1)'

    @Column(name = "fornecedor")
    private Boolean fornecedor = false;

    @Column(name = "transportadora")
    private Boolean transportadora = false;

    @Column(name = "cliente")
    private Boolean cliente = false;

    @Column(name = "nome", length = 60)
    private String nome;

    @Column(name = "nome_fantasia", length = 50)
    private String nomeFantasia; // Mapeia nome_fantasia

    @Column(name = "cnpj_cpf", length = 14)
    private String cnpjCpf;

    @Column(name = "insc_estadual", length = 20)
    private String inscEstadual;

    @Column(name = "insc_municipal", length = 20)
    private String inscMunicipal;

    @Column(name = "rg", length = 15)
    private String rg;

    @Column(name = "orgao_emissor", length = 10)
    private String orgaoEmissor;

    @Column(name = "cep", length = 8)
    private String cep;

    @Column(name = "rua", length = 60)
    private String rua;

    @Column(name = "numero", length = 60)
    private String numero;

    @Column(name = "complemento", length = 25)
    private String complemento;

    @Column(name = "bairro", length = 50)
    private String bairro;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "uf", length = 2)
    private String uf;

    @Column(name = "pais", length = 30)
    private String pais;

    @Column(name = "ddd", length = 2)
    private String ddd;

    @Column(name = "fone", length = 9)
    private String fone;

    @Column(name = "fone2", length = 9)
    private String fone2;

    @Column(name = "fax", length = 9)
    private String fax;

    @Column(name = "contato", length = 30)
    private String contato;

    @Column(name = "celular", length = 9)
    private String celular;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "site", length = 50)
    private String site;

    @Column(name = "observacoes")
    private String observacoes; // 'text'

    @Column(name = "dt_cadastro")
    private LocalDate dtCadastro; // 'data'

    @Column(name = "cadastradopor")
    private Integer cadastradoPor;

    @Column(name = "eliminado")
    private Boolean eliminado = false;

    @Column(name = "eliminadopor")
    private Integer eliminadoPor;

    @Column(name = "dt_eliminacao")
    private LocalDate dtEliminacao;

    @Column(name = "dt_ultimacompra")
    private LocalDate dtUltimaCompra;

    @Column(name = "atualizaprecocompra")
    private Boolean atualizaPrecoCompra = false;

    @Column(name = "aceitaprodutodiferente")
    private Boolean aceitaProdutoDiferente = false;

    @Column(name = "inscritonosimples")
    private Boolean inscritoNoSimples = false;

    @Column(name = "temipi")
    private Boolean temIpi = false;

    @Column(name = "temicmssubst")
    private Boolean temIcmsSubst = false;

    @Column(name = "id_linha")
    private Integer idLinha;

    @Column(name = "cfop", length = 4)
    private String cfop;

    @Column(name = "id_despesa")
    private Integer idDespesa;

    @Column(name = "tipofrete", length = 3)
    private String tipoFrete;

    @Column(name = "prazo1")
    private Integer prazo1;

    @Column(name = "prazo2")
    private Integer prazo2;

    @Column(name = "prazo3")
    private Integer prazo3;

    @Column(name = "prazo4")
    private Integer prazo4;

    @Column(name = "prazo5")
    private Integer prazo5;

    @Column(name = "prazo6")
    private Integer prazo6;

    @Column(name = "id_comprador")
    private Integer idComprador;

    @Column(name = "formapgto", length = 30)
    private String formaPgto;

    @Column(name = "diavisita", length = 30)
    private String diaVisita;

    @Column(name = "horavisita", length = 30)
    private String horaVisita;

    @Column(name = "prazoentrega")
    private Integer prazoEntrega;

    @Column(name = "frequenciacompra")
    private Integer frequenciaCompra;

    @Column(name = "dias_de_estoquea")
    private Integer diasDeEstoqueA;

    @Column(name = "dias_de_estoqueb")
    private Integer diasDeEstoqueB;

    @Column(name = "dias_de_estoquec")
    private Integer diasDeEstoqueC;

    @Column(name = "descontovarejo", precision = 11, scale = 2)
    private BigDecimal descontoVarejo;

    @Column(name = "descontoatacado", precision = 11, scale = 2)
    private BigDecimal descontoAtacado;

    @Column(name = "descontogerente", precision = 11, scale = 2)
    private BigDecimal descontoGerente;

    @Column(name = "acompanhamento")
    private String acompanhamento; // 'text'

    @Column(name = "sexo", length = 1)
    private String sexo;

    @Column(name = "nome_pai", length = 50)
    private String nomePai;

    @Column(name = "nome_mae", length = 50)
    private String nomeMae;

    @Column(name = "naturalidade", length = 35)
    private String naturalidade;

    @Column(name = "firma_onde_trabalha", length = 40)
    private String firmaOndeTrabalha;

    @Column(name = "admissao", length = 10)
    private String admissao;

    @Column(name = "renda", precision = 10, scale = 2)
    private BigDecimal renda;

    @Column(name = "nome_conjuge", length = 50)
    private String nomeConjuge;

    @Column(name = "cpf_conjuge", length = 11)
    private String cpfConjuge;

    @Column(name = "revenda")
    private Boolean revenda = false;

    @Column(name = "nome_socio1", length = 50)
    private String nomeSocio1;

    @Column(name = "cpf_socio1", length = 11)
    private String cpfSocio1;

    @Column(name = "rg_socio1", length = 18)
    private String rgSocio1;

    @Column(name = "perc_socio1", precision = 11, scale = 2)
    private BigDecimal percSocio1;

    @Column(name = "nome_socio2", length = 50)
    private String nomeSocio2;

    @Column(name = "cpf_socio2", length = 11)
    private String cpfSocio2;

    @Column(name = "rg_socio2", length = 18)
    private String rgSocio2;

    @Column(name = "perc_socio2", precision = 11, scale = 2)
    private BigDecimal percSocio2;

    @Column(name = "nome_socio3", length = 50)
    private String nomeSocio3;

    @Column(name = "cpf_socio3", length = 11)
    private String cpfSocio3;

    @Column(name = "rg_socio3", length = 18)
    private String rgSocio3;

    @Column(name = "perc_socio3", precision = 11, scale = 2)
    private BigDecimal percSocio3;

    @Column(name = "limitecredito", precision = 11, scale = 2)
    private BigDecimal limiteCredito;

    @Column(name = "dt_nascimento")
    private LocalDate dtNascimento;

    @Column(name = "descontopadrao", precision = 11, scale = 2)
    private BigDecimal descontoPadrao;

    @Column(name = "prazomaximo")
    private Integer prazoMaximo;

    @Column(name = "protestarapos")
    private Integer protestarApos;

    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "id_profissao")
    private Integer idProfissao;

    @Column(name = "id_vendedor")
    private Integer idVendedor;

    @Column(name = "id_tipocliente")
    private Integer idTipoCliente;

    @Column(name = "multa", precision = 11, scale = 2)
    private BigDecimal multa;

    @Column(name = "juros", precision = 11, scale = 2)
    private BigDecimal juros;

    @Column(name = "portador", length = 3)
    private String portador;

    @Column(name = "comissionavenda")
    private Boolean comissionaVenda = false;

    @Column(name = "consumidorfinal")
    private Boolean consumidorFinal = false;

    @Column(name = "referencias_comerciais")
    private String referenciasComerciais; // 'text'

    @Column(name = "referencias_bancarias")
    private String referenciasBancarias; // 'text'

    @Column(name = "cobcep", length = 8)
    private String cobCep;

    @Column(name = "cobrua", length = 60)
    private String cobRua;

    @Column(name = "cobnumero", length = 6)
    private String cobNumero;

    @Column(name = "cobcomplemento", length = 25)
    private String cobComplemento;

    @Column(name = "cobbairro", length = 50)
    private String cobBairro;

    @Column(name = "cobcidade", length = 50)
    private String cobCidade;

    @Column(name = "cobuf", length = 2)
    private String cobUf;

    @Column(name = "correntista")
    private Boolean correntista = false;

    @Column(name = "numero_cartao")
    private Integer numeroCartao;

    @Column(name = "vigiado")
    private Boolean vigiado = false;

    @Column(name = "cortado")
    private Boolean cortado = false;

    @Column(name = "pontos", precision = 11, scale = 2)
    private BigDecimal pontos;

    @Column(name = "saldo", precision = 10, scale = 2)
    private BigDecimal saldo;

    @Column(name = "tabela")
    private Integer tabela;

    @Column(name = "dt_proximacompra")
    private LocalDate dtProximaCompra;

    @Column(name = "consignacao")
    private Boolean consignacao = false;

    @Column(name = "data_base")
    private Integer dataBase;

    @Column(name = "estado_civil")
    private Integer estadoCivil;

    @Column(name = "cod_pais", length = 5)
    private String codPais;

    @Column(name = "cod_mun", length = 7)
    private String codMun;

    @Column(name = "suframa", length = 9)
    private String suframa;

    @Column(name = "capitalsocial", precision = 11, scale = 2)
    private BigDecimal capitalSocial;

    @Column(name = "pontoreferencia")
    private String pontoReferencia; // 'text'

    @Column(name = "palavrachave", length = 120)
    private String palavraChave;

    @Column(name = "senha", length = 10)
    private String senha;

    @Column(name = "prazo7")
    private Integer prazo7;

    @Column(name = "prazo8")
    private Integer prazo8;

    @Column(name = "prazofaturamento")
    private Integer prazoFaturamento;

    @Column(name = "id_rede")
    private Integer idRede;

    @Column(name = "cnae", length = 9)
    private String cnae;

    @Column(name = "celular2", length = 9)
    private String celular2;

    @Column(name = "celular3", length = 9)
    private String celular3;

    @Column(name = "email2", length = 50)
    private String email2;

    @Column(name = "email3", length = 50)
    private String email3;

    @Column(name = "ordem")
    private Integer ordem;

    @Column(name = "estornoicms")
    private Boolean estornoIcms = false;

    @Column(name = "atualizafornecedor")
    private Boolean atualizaFornecedor = false;

    @Column(name = "representante", length = 30)
    private String representante;

    @Column(name = "representante_fone", length = 9)
    private String representanteFone;

    @Column(name = "representante_email", length = 50)
    private String representanteEmail;

    // Comissao neste contexto pode ser diferente da comissao de produto
    @Column(name = "comissao", precision = 11, scale = 2)
    private BigDecimal comissao;

    @Column(name = "protestado")
    private Boolean protestado = false;

    @Column(name = "nc_insc_estadual", length = 1)
    private String ncInscEstadual = "S";

    @Column(name = "freqmes1")
    private Integer freqMes1;
    @Column(name = "freqmes2")
    private Integer freqMes2;
    @Column(name = "freqmes3")
    private Integer freqMes3;
    @Column(name = "freqmes4")
    private Integer freqMes4;
    @Column(name = "freqmes5")
    private Integer freqMes5;
    @Column(name = "freqmes6")
    private Integer freqMes6;
    @Column(name = "freqmes7")
    private Integer freqMes7;
    @Column(name = "freqmes8")
    private Integer freqMes8;
    @Column(name = "freqmes9")
    private Integer freqMes9;
    @Column(name = "freqmes10")
    private Integer freqMes10;
    @Column(name = "freqmes11")
    private Integer freqMes11;
    @Column(name = "freqmes12")
    private Integer freqMes12;

    @Column(name = "posmes1")
    private Integer posMes1;
    @Column(name = "posmes2")
    private Integer posMes2;
    @Column(name = "posmes3")
    private Integer posMes3;
    @Column(name = "posmes4")
    private Integer posMes4;
    @Column(name = "posmes5")
    private Integer posMes5;
    @Column(name = "posmes6")
    private Integer posMes6;
    @Column(name = "posmes7")
    private Integer posMes7;
    @Column(name = "posmes8")
    private Integer posMes8;
    @Column(name = "posmes9")
    private Integer posMes9;
    @Column(name = "posmes10")
    private Integer posMes10;
    @Column(name = "posmes11")
    private Integer posMes11;
    @Column(name = "posmes12")
    private Integer posMes12;

    @Column(name = "vendames1", precision = 10, scale = 2)
    private BigDecimal vendaMes1;
    @Column(name = "vendames2", precision = 10, scale = 2)
    private BigDecimal vendaMes2;
    @Column(name = "vendames3", precision = 10, scale = 2)
    private BigDecimal vendaMes3;
    @Column(name = "vendames4", precision = 10, scale = 2)
    private BigDecimal vendaMes4;
    @Column(name = "vendames5", precision = 10, scale = 2)
    private BigDecimal vendaMes5;
    @Column(name = "vendames6", precision = 10, scale = 2)
    private BigDecimal vendaMes6;
    @Column(name = "vendames7", precision = 10, scale = 2)
    private BigDecimal vendaMes7;
    @Column(name = "vendames8", precision = 10, scale = 2)
    private BigDecimal vendaMes8;
    @Column(name = "vendames9", precision = 10, scale = 2)
    private BigDecimal vendaMes9;
    @Column(name = "vendames10", precision = 10, scale = 2)
    private BigDecimal vendaMes10;
    @Column(name = "vendames11", precision = 10, scale = 2)
    private BigDecimal vendaMes11;
    @Column(name = "vendames12", precision = 10, scale = 2)
    private BigDecimal vendaMes12;

    @Column(name = "insc_inativa", length = 20)
    private String inscInativa;

    @Column(name = "apto_nfe")
    private Boolean aptoNfe = false;

    @Column(name = "dt_ultimaconsultasefaz")
    private LocalDate dtUltimaConsultaSefaz;

    @Column(name = "negociando")
    private Boolean negociando = false;

    @Column(name = "negativarapos")
    private Integer negativarApos;

    @Column(name = "id_formapag")
    private Integer idFormaPag;

    @Column(name = "devolverapos")
    private Integer devolverApos;

    @Column(name = "desabilita_fatores")
    private Boolean desabilitaFatores = false;

    @Column(name = "filho1", length = 40)
    private String filho1;
    @Column(name = "niverfilho1", length = 5)
    private String niverFilho1;
    @Column(name = "filho2", length = 40)
    private String filho2;
    @Column(name = "niverfilho2", length = 5)
    private String niverFilho2;
    @Column(name = "filho3", length = 40)
    private String filho3;
    @Column(name = "niverfilho3", length = 5)
    private String niverFilho3;
    @Column(name = "filho4", length = 40)
    private String filho4;
    @Column(name = "niverfilho4", length = 5)
    private String niverFilho4;
    @Column(name = "filho5", length = 40)
    private String filho5;
    @Column(name = "niverfilho5", length = 5)
    private String niverFilho5;

    @Column(name = "nomecomprador", length = 50)
    private String nomeComprador;

    @Column(name = "fonecomprador", length = 13)
    private String foneComprador;

    @Column(name = "celularcomprador", length = 14)
    private String celularComprador;

    @Column(name = "obscomprador")
    private String obsComprador; // 'text'

    @Column(name = "estrangeiro")
    private Boolean estrangeiro = false;

    @Column(name = "ident_estrangeiro", length = 20)
    private String identEstrangeiro;

    @Column(name = "tipo_doc_estrangeiro", length = 30)
    private String tipoDocEstrangeiro;

    @Column(name = "produtor_rural")
    private Boolean produtorRural = false;

    @Column(name = "origem_mobile")
    private Boolean origemMobile = false;

    @Column(name = "sincincpend")
    private Boolean sincIncPend = false;

    @Column(name = "sincedipend")
    private Boolean sincEdiPend = false;

    @Column(name = "prestador_servico")
    private Boolean prestadorServico;

    @Column(name = "nfe_exportacao")
    private Boolean nfeExportacao;

    @Column(name = "chave_sincronismo", length = 36)
    private String chaveSincronismo;

    // Construtor padrão (necessário para JPA)
    public Pessoa() {
    }
}