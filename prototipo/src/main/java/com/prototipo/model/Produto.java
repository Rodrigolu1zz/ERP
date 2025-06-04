package com.prototipo.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity // Indica que esta classe é uma entidade JPA e mapeia para uma tabela no banco de dados.
@Table(name = "produto") // Especifica o nome da tabela no banco de dados, se for diferente do nome da classe.
@Data // Anotação do Lombok para gerar automaticamente getters, setters, toString, equals e hashCode.
public class Produto {

    @Id // Marca o atributo como a chave primária da entidade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a estratégia de geração de valor para a chave primária (IDENTITY para autoincremento do banco).
    private Integer id;

    @Column(name = "ativo") // Mapeia o atributo para a coluna 'ativo'.
    private Boolean ativo; // 'logico' no PostgreSQL pode ser mapeado para Boolean em Java.

    @Column(name = "nome", length = 50)
    private String nome;

    @Column(name = "codigo", length = 14)
    private String codigo;

    @Column(name = "nomereduzido", length = 30)
    private String nomeReduzido;

    @Column(name = "unidade", length = 6)
    private String unidade;

    @Column(name = "id_grupo")
    private Integer idGrupo;

    @Column(name = "id_fabricante")
    private Integer idFabricante;

    @Column(name = "id_fornecedor")
    private Integer idFornecedor;

    @Column(name = "referencia", length = 30)
    private String referencia;

    @Column(name = "embalagem", precision = 11, scale = 2)
    private BigDecimal embalagem;

    @Column(name = "cst", length = 3)
    private String cst;

    @Column(name = "estoque_seguranca")
    private Integer estoqueSeguranca;

    @Column(name = "prazovalidade")
    private Integer prazoValidade;

    @Column(name = "comissionaproduto")
    private Boolean comissionaProduto;

    @Column(name = "comissao", precision = 11, scale = 2)
    private BigDecimal comissao;

    @Column(name = "itemespecial")
    private Boolean itemEspecial;

    @Column(name = "itemcritico")
    private Boolean itemCritico;

    @Column(name = "materiaprima")
    private Boolean materiaPrima;

    @Column(name = "baixarcomposicao")
    private Boolean baixarComposicao;

    @Column(name = "localizacao", length = 20)
    private String localizacao;

    @Column(name = "pesobruto", precision = 12, scale = 3)
    private BigDecimal pesoBruto;

    @Column(name = "pesoliquido", precision = 12, scale = 3)
    private BigDecimal pesoLiquido;

    @Column(name = "dt_cadastro")
    private LocalDate dtCadastro;

    @Column(name = "dt_ultimacompra")
    private LocalDate dtUltimaCompra;

    @Column(name = "quant_ultimacompra", precision = 12, scale = 3)
    private BigDecimal quantUltimaCompra;

    @Column(name = "descontomax", precision = 11, scale = 2)
    private BigDecimal descontoMax;

    @Column(name = "observacoes")
    private String observacoes; // 'text' no PostgreSQL pode ser mapeado para String.

    @Column(name = "reajusta_auto")
    private Boolean reajustaAuto;

    @Column(name = "desconto_tabela", precision = 12, scale = 3)
    private BigDecimal descontoTabela;

    @Column(name = "desconto_compra", precision = 11, scale = 2)
    private BigDecimal descontoCompra;

    @Column(name = "icmscompra", precision = 11, scale = 2)
    private BigDecimal icmsCompra;

    @Column(name = "ipicompra", precision = 11, scale = 2)
    private BigDecimal ipiCompra;

    @Column(name = "fretecompra", precision = 11, scale = 2)
    private BigDecimal freteCompra;

    @Column(name = "outrasdespesas", precision = 11, scale = 2)
    private BigDecimal outrasDespesas;

    @Column(name = "custofinanceiro", precision = 11, scale = 2)
    private BigDecimal custoFinanceiro;

    @Column(name = "piscredito", precision = 11, scale = 2)
    private BigDecimal pisCredito;

    @Column(name = "cofinscredito", precision = 11, scale = 2)
    private BigDecimal cofinsCredito;

    @Column(name = "custo_liquido", precision = 10, scale = 2)
    private BigDecimal custoLiquido;

    @Column(name = "custo_medio", precision = 10, scale = 2)
    private BigDecimal custoMedio;

    @Column(name = "custo_total", precision = 10, scale = 2)
    private BigDecimal custoTotal;

    @Column(name = "markup", precision = 11, scale = 2)
    private BigDecimal markup;

    @Column(name = "lucrovenda", precision = 11, scale = 2)
    private BigDecimal lucroVenda;

    @Column(name = "agregado_icms", precision = 11, scale = 2)
    private BigDecimal agregadoIcms;

    @Column(name = "pisdebito", precision = 11, scale = 2)
    private BigDecimal pisDebito;

    @Column(name = "cofinsdebito", precision = 11, scale = 2)
    private BigDecimal cofinsDebito;

    @Column(name = "reducaoicms", precision = 11, scale = 2)
    private BigDecimal reducaoIcms;

    @Column(name = "icmsvenda", precision = 11, scale = 2)
    private BigDecimal icmsVenda;

    @Column(name = "custovenda1", precision = 11, scale = 2)
    private BigDecimal custoVenda1;

    @Column(name = "custovenda2", precision = 11, scale = 2)
    private BigDecimal custoVenda2;

    @Column(name = "custovenda3", precision = 11, scale = 2)
    private BigDecimal custoVenda3;

    @Column(name = "custovenda4", precision = 11, scale = 2)
    private BigDecimal custoVenda4;

    @Column(name = "custovenda5", precision = 11, scale = 2)
    private BigDecimal custoVenda5;

    @Column(name = "preco_sem_lucro", precision = 10, scale = 2)
    private BigDecimal precoSemLucro;

    @Column(name = "preco_sugerido", precision = 10, scale = 2)
    private BigDecimal precoSugerido;

    @Column(name = "preco_venda", precision = 10, scale = 2)
    private BigDecimal precoVenda;

    @Column(name = "preco_venda2", precision = 10, scale = 2)
    private BigDecimal precoVenda2;

    @Column(name = "preco_venda3", precision = 10, scale = 2)
    private BigDecimal precoVenda3;

    @Column(name = "preco_venda4", precision = 10, scale = 2)
    private BigDecimal precoVenda4;

    @Column(name = "preco_venda5", precision = 10, scale = 2)
    private BigDecimal precoVenda5;

    @Column(name = "margem_liquida", precision = 11, scale = 2)
    private BigDecimal margemLiquida;

    @Column(name = "margem_media", precision = 11, scale = 2)
    private BigDecimal margemMedia;

    @Column(name = "eliminado")
    private Boolean eliminado;

    @Column(name = "eliminadopor")
    private Integer eliminadoPor;

    @Column(name = "dt_eliminacao")
    private LocalDate dtEliminacao;

    @Column(name = "descontomaxgerencia", precision = 11, scale = 2)
    private BigDecimal descontoMaxGerencia;

    @Column(name = "estoque_minimo", precision = 12, scale = 3)
    private BigDecimal estoqueMinimo;

    @Column(name = "producao_propria")
    private Boolean producaoPropria;

    @Column(name = "aliqecf", length = 4)
    private String aliqEcf;

    @Column(name = "pesavel")
    private Boolean pesavel;

    @Column(name = "codigoncm", length = 8)
    private String codigoNcm;

    @Column(name = "tipo_item", length = 2)
    private String tipoItem;

    @Column(name = "cod_gen", length = 2)
    private String codGen;

    @Column(name = "conta_contabil", length = 15)
    private String contaContabil;

    @Column(name = "classiffiscal", length = 15)
    private String classifFiscal;

    @Column(name = "rendimento", precision = 12, scale = 3)
    private BigDecimal rendimento;

    @Column(name = "iat", length = 1)
    private String iat;

    @Column(name = "ippt", length = 1)
    private String ippt;

    @Column(name = "cfopei", length = 4)
    private String cfopEi;

    @Column(name = "cfopee", length = 4)
    private String cfopEe;

    @Column(name = "cfopsi", length = 4)
    private String cfopSi;

    @Column(name = "cfopse", length = 4)
    private String cfopSe;

    @Column(name = "preco_compra", precision = 12, scale = 3)
    private BigDecimal precoCompra;

    @Column(name = "preco_tabela", precision = 12, scale = 3)
    private BigDecimal precoTabela;

    @Column(name = "segunda", precision = 12, scale = 3)
    private BigDecimal segunda;

    @Column(name = "terca", precision = 12, scale = 3)
    private BigDecimal terca;

    @Column(name = "quarta", precision = 12, scale = 3)
    private BigDecimal quarta;

    @Column(name = "quinta", precision = 12, scale = 3)
    private BigDecimal quinta;

    @Column(name = "sexta", precision = 12, scale = 3)
    private BigDecimal sexta;

    @Column(name = "sabado", precision = 12, scale = 3)
    private BigDecimal sabado;

    @Column(name = "domingo", precision = 12, scale = 3)
    private BigDecimal domingo;

    @Column(name = "pontoreposlj", precision = 12, scale = 3)
    private BigDecimal pontoReposLj;

    @Column(name = "estmaxlj", precision = 12, scale = 3)
    private BigDecimal estMaxLj;

    @Column(name = "datamovimento")
    private LocalDate dataMovimento;

    @Column(name = "tipotributacao", length = 1)
    private String tipoTributacao;

    @Column(name = "lock", length = 32)
    private String lock;

    @Column(name = "palavrachave", length = 80)
    private String palavraChave;

    @Column(name = "regimeespecial")
    private Boolean regimeEspecial;

    @Column(name = "ex_tipi", length = 3)
    private String exTipi;

    @Column(name = "csosn", length = 3)
    private String csosn;

    @Column(name = "aliqcredito", precision = 12, scale = 3)
    private BigDecimal aliqCredito;

    @Column(name = "pedeserial")
    private Boolean pedeSerial;

    @Column(name = "fatorentrada", precision = 12, scale = 3)
    private BigDecimal fatorEntrada;

    @Column(name = "preco_pedido", precision = 12, scale = 3)
    private BigDecimal precoPedido;

    @Column(name = "gtin")
    private Boolean gtin;

    @Column(name = "atualizapeloncm")
    private Boolean atualizaPeloNcm;

    @Column(name = "garantia", length = 80)
    private String garantia;

    @Column(name = "foto", length = 120)
    private String foto;

    @Column(name = "divisorentrada", precision = 12, scale = 3)
    private BigDecimal divisorEntrada;

    @Column(name = "descricaosite", length = 120)
    private String descricaoSite;

    @Column(name = "altura", precision = 12, scale = 3)
    private BigDecimal altura;

    @Column(name = "largura", precision = 12, scale = 3)
    private BigDecimal largura;

    @Column(name = "espessura", precision = 12, scale = 3)
    private BigDecimal espessura;

    @Column(name = "expedicao")
    private Integer expedicao;

    @Column(name = "fatortransf", precision = 12, scale = 3)
    private BigDecimal fatorTransf;

    @Column(name = "pedidopadrao", precision = 12, scale = 3)
    private BigDecimal pedidoPadrao;

    @Column(name = "maq1", precision = 12, scale = 3)
    private BigDecimal maq1;
    @Column(name = "maq2", precision = 12, scale = 3)
    private BigDecimal maq2;
    @Column(name = "maq3", precision = 12, scale = 3)
    private BigDecimal maq3;
    @Column(name = "maq4", precision = 12, scale = 3)
    private BigDecimal maq4;
    @Column(name = "maq5", precision = 12, scale = 3)
    private BigDecimal maq5;
    @Column(name = "maq6", precision = 12, scale = 3)
    private BigDecimal maq6;
    @Column(name = "maq7", precision = 12, scale = 3)
    private BigDecimal maq7;
    @Column(name = "maq8", precision = 12, scale = 3)
    private BigDecimal maq8;
    @Column(name = "maq9", precision = 12, scale = 3)
    private BigDecimal maq9;
    @Column(name = "maq10", precision = 12, scale = 3)
    private BigDecimal maq10;

    @Column(name = "obra1", precision = 12, scale = 3)
    private BigDecimal obra1;
    @Column(name = "obra2", precision = 12, scale = 3)
    private BigDecimal obra2;
    @Column(name = "obra3", precision = 12, scale = 3)
    private BigDecimal obra3;
    @Column(name = "obra4", precision = 12, scale = 3)
    private BigDecimal obra4;
    @Column(name = "obra5", precision = 12, scale = 3)
    private BigDecimal obra5;
    @Column(name = "obra6", precision = 12, scale = 3)
    private BigDecimal obra6;
    @Column(name = "obra7", precision = 12, scale = 3)
    private BigDecimal obra7;
    @Column(name = "obra8", precision = 12, scale = 3)
    private BigDecimal obra8;
    @Column(name = "obra9", precision = 12, scale = 3)
    private BigDecimal obra9;
    @Column(name = "obra10", precision = 12, scale = 3)
    private BigDecimal obra10;

    @Column(name = "basefrete", precision = 11, scale = 2)
    private BigDecimal baseFrete;

    @Column(name = "icmsfrete", precision = 11, scale = 2)
    private BigDecimal icmsFrete;

    @Column(name = "perc2", precision = 11, scale = 2)
    private BigDecimal perc2;
    @Column(name = "perc3", precision = 11, scale = 2)
    private BigDecimal perc3;
    @Column(name = "perc4", precision = 11, scale = 2)
    private BigDecimal perc4;
    @Column(name = "perc5", precision = 11, scale = 2)
    private BigDecimal perc5;

    @Column(name = "cstpisentrada", length = 2)
    private String cstPisEntrada;

    @Column(name = "cstpissaida", length = 2)
    private String cstPisSaida;

    @Column(name = "cstcofinsentrada", length = 2)
    private String cstCofinsEntrada;

    @Column(name = "cstcofinssaida", length = 2)
    private String cstCofinsSaida;

    @Column(name = "rendimentoun")
    private Integer rendimentoUn;

    @Column(name = "estoqueretroativo", precision = 12, scale = 3)
    private BigDecimal estoqueRetroativo;

    @Column(name = "tipo_item_sef2")
    private Integer tipoItemSef2;

    @Column(name = "impostos", precision = 12, scale = 3)
    private BigDecimal impostos;

    @Column(name = "natreceitapiscofins", length = 3)
    private String natReceitaPiscofins;

    @Column(name = "enderecoloja", length = 100)
    private String enderecoLoja;

    @Column(name = "enderecodeposito", length = 100)
    private String enderecoDeposito;

    @Column(name = "codigoiniciodia", length = 14)
    private String codigoInicioDia;

    @Column(name = "nomeiniciodia", length = 50)
    private String nomeInicioDia;

    @Column(name = "unidadeiniciodia", length = 6)
    private String unidadeInicioDia;

    @Column(name = "datamovimentoiniciodia")
    private LocalDate dataMovimentoInicioDia;

    @Column(name = "em_promocao")
    private Boolean emPromocao;

    @Column(name = "perc_trib_fed", precision = 10, scale = 2)
    private BigDecimal percTribFed;

    @Column(name = "perc_trib_est", precision = 10, scale = 2)
    private BigDecimal percTribEst;

    @Column(name = "perc_trib_mun", precision = 10, scale = 2)
    private BigDecimal percTribMun;

    @Column(name = "id_familia")
    private Integer idFamilia;

    @Column(name = "modo_preparo")
    private String modoPreparo;

    @Column(name = "visivel_site")
    private Boolean visivelSite;

    @Column(name = "cest", length = 20)
    private String cest;

    @Column(name = "verifica_quant_entrada")
    private Boolean verificaQuantEntrada;

    @Column(name = "cnpjestab", length = 14)
    private String cnpjEstab;

    @Column(name = "cnpjestabiniciodia", length = 14)
    private String cnpjEstabInicioDia;

    @Column(name = "codigo_anp")
    private Integer codigoAnp;

    @Column(name = "fcp", precision = 11, scale = 2)
    private BigDecimal fcp;

    @Column(name = "pontos", precision = 11, scale = 2)
    private BigDecimal pontos;

    @Column(name = "dt_vendamedia")
    private LocalDate dtVendaMedia;

    @Column(name = "dias_vendamedia")
    private Integer diasVendaMedia;

    @Column(name = "dt_fab")
    private LocalDate dtFab;

    @Column(name = "dt_val")
    private LocalDate dtVal;

    @Column(name = "lote", length = 40)
    private String lote;

    @Column(name = "conteudo", length = 40)
    private String conteudo;

    @Column(name = "setor_expedicao")
    private Integer setorExpedicao;

    @Column(name = "setor_producao")
    private Integer setorProducao;

    @Column(name = "qt_porconvidado", precision = 12, scale = 3)
    private BigDecimal qtPorConvidado;

    @Column(name = "qtpax")
    private Integer qtPax;

    @Column(name = "qtporpax")
    private Integer qtPorPax;

    @Column(name = "pax")
    private Boolean pax;

    @Column(name = "qtde_atacado", precision = 12, scale = 3)
    private BigDecimal qtdeAtacado;

    @Column(name = "quant_atacado2", precision = 12, scale = 3)
    private BigDecimal quantAtacado2;
    @Column(name = "quant_atacado3", precision = 12, scale = 3)
    private BigDecimal quantAtacado3;
    @Column(name = "quant_atacado4", precision = 12, scale = 3)
    private BigDecimal quantAtacado4;
    @Column(name = "quant_atacado5", precision = 12, scale = 3)
    private BigDecimal quantAtacado5;

    @Column(name = "fatormultiplosaida", precision = 11, scale = 2)
    private BigDecimal fatorMultiploSaida;

    @Column(name = "id_editora")
    private Integer idEditora;

    @Column(name = "autor", length = 50)
    private String autor;

    @Column(name = "id_estproducao")
    private Integer idEstProducao;

    @Column(name = "ordemmobile")
    private Integer ordemMobile;

    @Column(name = "visivelmobile")
    private Boolean visivelMobile;

    @Column(name = "controlalote")
    private Boolean controlaLote;

    @Column(name = "quant_parabonificacao", precision = 12, scale = 3)
    private BigDecimal quantParaBonificacao;

    @Column(name = "quant_bonificada", precision = 12, scale = 3)
    private BigDecimal quantBonificada;

    @Column(name = "agregado_icmsantipacao", precision = 11, scale = 2)
    private BigDecimal agregadoIcmsAntecipacao;

    @Column(name = "habilita_calcantecipacao")
    private Boolean habilitaCalcAtecipacao;

    @Column(name = "indescala")
    private Integer indEscala;

    @Column(name = "cnpjfab", length = 14)
    private String cnpjFab;

    @Column(name = "glpderivado", precision = 12, scale = 4)
    private BigDecimal glpDerivado;

    @Column(name = "glgnn", precision = 12, scale = 4)
    private BigDecimal glgnn;

    @Column(name = "glgni", precision = 12, scale = 4)
    private BigDecimal glgni;

    @Column(name = "valorpartida", precision = 12, scale = 3)
    private BigDecimal valorPartida;

    @Column(name = "ipisaida", precision = 11, scale = 2)
    private BigDecimal ipiSaida;

    @Column(name = "forma_cadastro", length = 50)
    private String formaCadastro;

    @Column(name = "hora_cadastro", length = 8)
    private String horaCadastro;

    @Column(name = "painel_cozinha")
    private Boolean painelCozinha;

    @Column(name = "turno_producao")
    private Integer turnoProducao;

    @Column(name = "cst_ipi_saida", length = 2)
    private String cstIpiSaida;

    @Column(name = "cst_ipi_entrada", length = 2)
    private String cstIpiEntrada;

    @Column(name = "percdescontoauto", precision = 11, scale = 2)
    private BigDecimal percDescontoAuto;

    @Column(name = "seg")
    private Boolean seg;
    @Column(name = "ter")
    private Boolean ter;
    @Column(name = "qua")
    private Boolean qua;
    @Column(name = "qui")
    private Boolean qui;
    @Column(name = "sex")
    private Boolean sex;
    @Column(name = "sab")
    private Boolean sab;
    @Column(name = "dom")
    private Boolean dom;

    @Column(name = "custocompra1", precision = 11, scale = 2)
    private BigDecimal custoCompra1;

    @Column(name = "iss", precision = 11, scale = 2)
    private BigDecimal iss;

    @Column(name = "cnaeservico", length = 15)
    private String cnaeServico;

    @Column(name = "codigoservico", length = 15)
    private String codigoServico;

    @Column(name = "dtnf_ultima_entrada")
    private LocalDate dtNfUltimaEntrada;

    @Column(name = "dtnf_ultimo_lancamento")
    private LocalDate dtNfUltimoLancamento;

    @Column(name = "integracao_site")
    private Boolean integracaoSite;

    @Column(name = "ignorar_referencia")
    private Boolean ignorarReferencia;

    @Column(name = "fora_de_linha")
    private Boolean foraDeLinha;

    @Column(name = "id_linha")
    private Integer idLinha;

    @Column(name = "receita1", length = 50)
    private String receita1;
    @Column(name = "receita2", length = 50)
    private String receita2;
    @Column(name = "receita3", length = 50)
    private String receita3;
    @Column(name = "receita4", length = 50)
    private String receita4;
    @Column(name = "receita5", length = 50)
    private String receita5;

    @Column(name = "prodnuvem")
    private Boolean prodNuvem;

    @Column(name = "id_localizacao")
    private Integer idLocalizacao;

    @Column(name = "id_enderecoloja")
    private Integer idEnderecoLoja;

    @Column(name = "id_enderecodeposito")
    private Integer idEnderecoDeposito;

    @Column(name = "conf_conveniencia")
    private Boolean confConveniencia;

    @Column(name = "codbenef_csticms", length = 8)
    private String codBenefCstIcms;

    @Column(name = "motbenef_csticms")
    private Integer motBenefCstIcms;

    // 'numero2dec' foi mapeado para BigDecimal com precisão e escala adequadas,
    // já que é um tipo de dado numérico com duas casas decimais.
    @Column(name = "perbenef_csticms", precision = 11, scale = 2)
    private BigDecimal perBenefCstIcms;

    @Column(name = "aliqbenef_csticms", precision = 11, scale = 2)
    private BigDecimal aliqBenefCstIcms;

    @Column(name = "tara")
    private Integer tara;

    @Column(name = "cor", length = 100)
    private String cor;

    @Column(name = "marca", length = 100)
    private String marca;

    // 'memo' no PostgreSQL é geralmente mapeado para String em Java.
    @Column(name = "metachave")
    private String metaChave;

    @Column(name = "metadescricao")
    private String metaDescricao;

    @Column(name = "metatitulo")
    private String metaTitulo;

    @Column(name = "nomeamigavel")
    private String nomeAmigavel;

    // Construtor padrão (necessário para JPA)
    public Produto() {
    }
}