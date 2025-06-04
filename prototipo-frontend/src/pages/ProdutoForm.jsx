// src/pages/ProdutoForm.jsx
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams, useLocation } from 'react-router-dom'; // Importe useLocation
import {
  Box,
  Typography,
  TextField,
  Button,
  Paper,
  Grid,
  CircularProgress,
  Alert, // Mantido Alert para erros persistentes (opcional, Snackbar já faz bem)
  Checkbox,
  FormControlLabel,
  Snackbar,
} from '@mui/material';
import {
  Save as SaveIcon,
  Cancel as CancelIcon
} from '@mui/icons-material';

const API_BASE_URL = 'http://localhost:8080/api/produtos';

const formatDateForInput = (dateString) => {
  if (!dateString) return '';
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = (date.getMonth() + 1).toString().padStart(2, '0');
  const day = date.getDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
};

function ProdutoForm() {
  const navigate = useNavigate();
  const { id } = useParams();
  const location = useLocation(); // Hook para acessar o objeto de estado de navegação

  const [produto, setProduto] = useState({
    // ... (Mantenha todos os campos que já definimos antes, com seus valores padrão)
    ativo: true,
    nome: '',
    codigo: '',
    nomereduzido: '',
    unidade: 'UN',
    idGrupo: null, // Alterado para null
    idFabricante: null,
    idFornecedor: null,
    referencia: '',
    embalagem: 0.00,
    cst: '',
    estoqueSeguranca: 0,
    prazovalidade: 0,
    comissionaproduto: false,
    comissao: 0.00,
    itemespecial: false,
    itemcritico: false,
    materiaprima: false,
    baixarcomposicao: false,
    localizacao: '',
    pesobruto: 0.000,
    pesoliquido: 0.000,
    dt_ultimacompra: null,
    quant_ultimacompra: 0.000,
    descontomax: 0.00,
    observacoes: '',
    reajusta_auto: false,
    desconto_tabela: 0.000,
    desconto_compra: 0.00,
    icmscompra: 0.00,
    ipicompra: 0.00,
    fretecompra: 0.00,
    outrasdespesas: 0.00,
    custofinanceiro: 0.00,
    piscredito: 0.00,
    cofinscredito: 0.00,
    custo_liquido: 0.00,
    custo_medio: 0.00,
    custo_total: 0.00,
    markup: 0.00,
    lucrovenda: 0.00,
    agregado_icms: 0.00,
    pisdebito: 0.00,
    cofinsdebito: 0.00,
    reducaoicms: 0.00,
    icmsvenda: 0.00,
    custovenda1: 0.00,
    custovenda2: 0.00,
    custovenda3: 0.00,
    custovenda4: 0.00,
    custovenda5: 0.00,
    preco_sem_lucro: 0.00,
    preco_sugerido: 0.00,
    preco_venda: 0.00,
    preco_venda2: 0.00,
    preco_venda3: 0.00,
    preco_venda4: 0.00,
    preco_venda5: 0.00,
    margem_liquida: 0.00,
    margem_media: 0.00,
    eliminado: false,
    eliminadopor: null,
    dt_eliminacao: null,
    descontomaxgerencia: 0.00,
    estoque_minimo: 0.000,
    producao_propria: false,
    aliqecf: '',
    pesavel: false,
    codigoncm: '',
    tipo_item: '',
    cod_gen: '',
    conta_contabil: '',
    classiffiscal: '',
    rendimento: 0.000,
    iat: '',
    ippt: '',
    cfopei: '',
    cfopee: '',
    cfopsi: '',
    cfopse: '',
    preco_compra: 0.000,
    preco_tabela: 0.000,
    segunda: 0.000,
    terca: 0.000,
    quarta: 0.000,
    quinta: 0.000,
    sexta: 0.000,
    sabado: 0.000,
    domingo: 0.000,
    pontoreposlj: 0.000,
    estmaxlj: 0.000,
    datamovimento: null,
    tipotributacao: '',
    lock: '',
    palavrachave: '',
    regimeespecial: false,
    ex_tipi: '',
    csosn: '',
    aliqcredito: 0.000,
    pedeserial: false,
    fatorentrada: 0.000,
    preco_pedido: 0.000,
    gtin: false,
    atualizapeloncm: false,
    garantia: '',
    foto: '',
    divisorentrada: 0.000,
    descricaosite: '',
    altura: 0.000,
    largura: 0.000,
    espessura: 0.000,
    expedicao: 0,
    fatortransf: 0.000,
    pedidopadrao: 0.000,
    maq1: 0.000, maq2: 0.000, maq3: 0.000, maq4: 0.000, maq5: 0.000, maq6: 0.000, maq7: 0.000, maq8: 0.000, maq9: 0.000, maq10: 0.000,
    obra1: 0.000, obra2: 0.000, obra3: 0.000, obra4: 0.000, obra5: 0.000, obra6: 0.000, obra7: 0.000, obra8: 0.000, obra9: 0.000, obra10: 0.000,
    basefrete: 0.00,
    icmsfrete: 0.00,
    perc2: 0.00, perc3: 0.00, perc4: 0.00, perc5: 0.00,
    cstpisentrada: '',
    cstpissaida: '',
    cstcofinsentrada: '',
    cstcofinssaida: '',
    rendimentoun: 0,
    estoqueretroativo: 0.000,
    tipo_item_sef2: 0,
    impostos: 0.000,
    natreceitapiscofins: '',
    enderecoloja: '',
    enderecodeposito: '',
    codigoiniciodia: '',
    nomeiniciodia: '',
    unidadeiniciodia: '',
    datamovimentoiniciodia: null,
    em_promocao: false,
    perc_trib_fed: 0.00,
    perc_trib_est: 0.00,
    perc_trib_mun: 0.00,
    id_familia: null,
    modo_preparo: '',
    visivel_site: false,
    cest: '',
    verifica_quant_entrada: false,
    cnpjestab: '',
    cnpjestabiniciodia: '',
    codigo_anp: 0,
    fcp: 0.00,
    pontos: 0.00,
    dt_vendamedia: null,
    dias_vendamedia: 0,
    dt_fab: null,
    dt_val: null,
    lote: '',
    conteudo: '',
    setor_expedicao: 0,
    setor_producao: 0,
    qt_porconvidado: 0.000,
    qtpax: 0,
    qtporpax: 0,
    pax: false,
    qtde_atacado: 0.000,
    quant_atacado2: 0.000, quant_atacado3: 0.000, quant_atacado4: 0.000, quant_atacado5: 0.000,
    fatormultiplosaida: 0.00,
    id_editora: null,
    autor: '',
    id_estproducao: 1,
    ordemmobile: null,
    visivelmobile: false,
    controlalote: false,
    quant_parabonificacao: 0.000,
    quant_bonificada: 0.000,
    agregado_icmsantipacao: 0.00,
    habilita_calcantecipacao: false,
    indescala: 0,
    cnpjfab: '',
    glpderivado: 0.0000,
    glgnn: 0.0000,
    glgni: 0.0000,
    valorpartida: 0.000,
    ipisaida: 0.00,
    forma_cadastro: '',
    hora_cadastro: '',
    painel_cozinha: false,
    turno_producao: 0,
    cst_ipi_saida: '',
    cst_ipi_entrada: '',
    percdescontoauto: 0.00,
    seg: false, ter: false, qua: false, qui: false, sex: false, sab: false, dom: false,
    custocompra1: 0.00,
    iss: 0.00,
    cnaeservico: '',
    codigoservico: '',
    dtnf_ultima_entrada: null,
    dtnf_ultimo_lancamento: null,
    integracao_site: false,
    ignorar_referencia: false,
    fora_de_linha: false,
    id_linha: null,
    receita1: '', receita2: '', receita3: '', receita4: '', receita5: '',
    prodnuvem: false,
    id_localizacao: null,
    id_enderecoloja: null,
    id_enderecodeposito: null,
    conf_conveniencia: false,
    codbenef_csticms: '',
    motbenef_csticms: 9,
    perbenef_csticms: 0.00,
    aliqbenef_csticms: 0.00,
    tara: null,
    cor: '',
    marca: '',
    metachave: '',
    metadescricao: '',
    metatitulo: '',
    nomeamigavel: '',
  });

  const [loading, setLoading] = useState(false);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  // Efeito para carregar os dados do produto ou para "incluir similar"
  useEffect(() => {
    // Verifica se há dados de um produto original passados via state da navegação
    if (location.state && location.state.produtoOriginal) {
      const { produtoOriginal } = location.state;
      // Define os dados do produto, mas sem o ID, para que seja um novo registro
      setProduto({
        ...produtoOriginal,
        id: null, // MUITO IMPORTANTE: Garante que é um novo cadastro
        // Limpar campos que devem ser únicos ou redefinidos para um novo produto
        codigo: '', // O código pode ser único, então limpe-o
        dt_cadastro: null, // Data de cadastro será definida pelo backend
        dt_ultimacompra: null,
        dt_eliminacao: null,
        dt_vendamedia: null,
        dt_fab: null,
        dt_val: null,
        dataMovimento: null,
        dataMovimentoInicioDia: null,
        dtnf_ultima_entrada: null,
        dtnf_ultimo_lancamento: null,
        dt_ultimaconsultasefaz: null,
        dt_proximacompra: null,
        // E quaisquer outros campos que não devam ser duplicados
      });
      // Limpa o state da localização para que o formulário não seja pré-preenchido
      // novamente se o usuário navegar para a mesma rota sem intenção de duplicar.
      // Isso pode exigir uma limpeza mais robusta do state na navegação original ou ao sair do formulário.
      // Por simplicidade aqui, vou deixar a limpeza no navigate que chama este formulário.
    } else if (id) { // Se um ID estiver presente na URL e não for um "similar"
      setLoading(true);
      axios.get(`${API_BASE_URL}/${id}`)
        .then(response => {
          const produtoData = response.data;
          // Mapear os nomes das propriedades camelCase do backend para snake_case/padrão do frontend
          const mappedProduto = {
            ...produtoData,
            // Certifique-se de que todos os nomes de campos no frontend correspondem aos do backend
            // (ex: preco_venda no frontend, precoVenda no backend Java)
            // Se o backend retorna camelCase, o frontend também usa camelCase para acesso direto.
            // Para TextFields com `name="preco_venda"`, o estado interno do React usa `produto.preco_venda`.
            // Então, o mapeamento abaixo é necessário para que os valores do backend (camelCase)
            // preencham os campos do estado do React (snake_case, conforme definido no state inicial).
            // Idealmente, seu backend retornaria os nomes de campo que você espera,
            // ou você teria um DTO de resposta que padroniza os nomes.
            // Para simplicidade, vou assumir que o frontend espera snake_case para nomes de input
            // e converte para camelCase antes de enviar para o backend, ou o backend aceita ambos.
            // O código do ProdutoForm que você já tem usa snake_case em alguns `name` props,
            // então vamos seguir isso e ajustar aqui.
            // ... (Copie e cole a lógica de mapeamento do ProdutoForm.jsx anterior aqui,
            //       garantindo que os nomes no lado direito do '=' (vindos do backend)
            //       sejam camelCase e os nomes no lado esquerdo (para o estado do React)
            //       sejam snake_case conforme definido no estado inicial do ProdutoForm.jsx.)

            // Exemplo de mapeamento:
            nomeReduzido: produtoData.nomeReduzido, // Já está correto
            comissionaproduto: produtoData.comissionaProduto,
            itemespecial: produtoData.itemEspecial,
            itemcritico: produtoData.itemCritico,
            materiaprima: produtoData.materiaPrima,
            baixarcomposicao: produtoData.baixarComposicao,
            // ... e assim por diante para todos os campos que têm nomes diferentes
            // E formatar as datas
            dt_ultimacompra: formatDateForInput(produtoData.dtUltimaCompra),
            dt_cadastro: formatDateForInput(produtoData.dtCadastro),
            dt_eliminacao: formatDateForInput(produtoData.dtEliminacao),
            dt_vendamedia: formatDateForInput(produtoData.dtVendaMedia),
            dt_fab: formatDateForInput(produtoData.dtFab),
            dt_val: formatDateForInput(produtoData.dtVal),
            datamovimento: formatDateForInput(produtoData.dataMovimento),
            datamovimentoiniciodia: formatDateForInput(produtoData.dataMovimentoInicioDia),
            dtnf_ultima_entrada: formatDateForInput(produtoData.dtnfUltimaEntrada),
            dtnf_ultimo_lancamento: formatDateForInput(produtoData.dtnfUltimoLancamento),
            dt_ultimaconsultasefaz: formatDateForInput(produtoData.dtUltimaConsultaSefaz),
            dt_proximacompra: formatDateForInput(produtoData.dtProximaCompra),
          };

          setProduto(prev => ({
            ...prev, // Mantém os valores padrão caso não venham do backend
            ...mappedProduto // Sobrescreve com os valores do backend
          }));
          setLoading(false);
        })
        .catch(err => {
          console.error("Erro ao buscar produto para edição:", err);
          showSnackbar("Erro ao carregar dados do produto para edição.", 'error');
          setLoading(false);
        });
    }
  }, [id, location.state]); // Adicionar location.state como dependência

  // ... (O restante do código, incluindo handleChange, handleNumericChange, handleDateChange,
  //      showSnackbar, handleSnackbarClose e handleSubmit, permanece o mesmo) ...

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setSnackbarOpen(false);

    if (!produto.nome || produto.nome.trim() === '') {
      showSnackbar("O nome do produto é obrigatório.", 'error');
      setLoading(false);
      return;
    }
    if (!produto.codigo || produto.codigo.trim() === '') {
      showSnackbar("O código do produto é obrigatório.", 'error');
      setLoading(false);
      return;
    }

    try {
      // Cria um objeto `produtoToSend` para converter nomes de campo de snake_case para camelCase,
      // se necessário, antes de enviar ao backend.
      // No entanto, se o seu backend Spring Boot com Jackson já aceita diretamente o snake_case
      // ou se você está usando o mesmo camelCase para nome da entidade e no frontend,
      // este mapeamento pode ser simplificado.
      // Para fins de demonstração, vou assumir que o backend espera camelCase nos nomes JSON
      // e que o estado do React usa snake_case em alguns inputs.
      const produtoToSend = {
        ...produto,
        // Exemplo de conversão de snake_case para camelCase para o backend,
        // SE os nomes no `produto` (estado do React) estiverem em snake_case
        // e o backend espera camelCase.
        // Se `ProdutoForm.jsx` já usa camelCase para todos os `name` props,
        // esta etapa não seria tão extensa.
        nomeReduzido: produto.nomereduzido,
        comissionaProduto: produto.comissionaproduto,
        itemEspecial: produto.itemespecial,
        itemCritico: produto.itemcritico,
        materiaPrima: produto.materiaprima,
        baixarComposicao: produto.baixarcomposicao,
        pesoBruto: produto.pesobruto,
        pesoLiquido: produto.pesoliquido,
        dtUltimaCompra: produto.dt_ultimacompra,
        quantUltimaCompra: produto.quant_ultimacompra,
        descontoMax: produto.descontomax,
        reajustaAuto: produto.reajusta_auto,
        descontoTabela: produto.desconto_tabela,
        descontoCompra: produto.desconto_compra,
        icmsCompra: produto.icmscompra,
        ipiCompra: produto.ipicompra,
        freteCompra: produto.fretecompra,
        outrasDespesas: produto.outrasdespesas,
        custoFinanceiro: produto.custofinanceiro,
        pisCredito: produto.piscredito,
        cofinsCredito: produto.cofinscredito,
        custoLiquido: produto.custo_liquido,
        custoMedio: produto.custo_medio,
        custoTotal: produto.custo_total,
        markup: produto.markup,
        lucroVenda: produto.lucrovenda,
        agregadoIcms: produto.agregado_icms,
        pisDebito: produto.pisdebito,
        cofinsDebito: produto.cofinsdebito,
        reducaoIcms: produto.reducaoicms,
        icmsVenda: produto.icmsvenda,
        custoVenda1: produto.custovenda1,
        custoVenda2: produto.custovenda2,
        custoVenda3: produto.custovenda3,
        custoVenda4: produto.custovenda4,
        custoVenda5: produto.custovenda5,
        precoSemLucro: produto.preco_sem_lucro,
        precoSugerido: produto.preco_sugerido,
        precoVenda: produto.preco_venda,
        precoVenda2: produto.preco_venda2,
        precoVenda3: produto.preco_venda3,
        precoVenda4: produto.preco_venda4,
        precoVenda5: produto.preco_venda5,
        margemLiquida: produto.margem_liquida,
        margemMedia: produto.margem_media,
        eliminadoPor: produto.eliminadopor,
        dtEliminacao: produto.dt_eliminacao,
        descontoMaxGerencia: produto.descontomaxgerencia,
        estoqueMinimo: produto.estoque_minimo,
        producaoPropria: produto.producao_propria,
        aliqEcf: produto.aliqecf,
        pesavel: produto.pesavel,
        codigoNcm: produto.codigoncm,
        tipoItem: produto.tipo_item,
        codGen: produto.cod_gen,
        contaContabil: produto.conta_contabil,
        classifFiscal: produto.classiffiscal,
        rendimento: produto.rendimento,
        cfopEi: produto.cfopei,
        cfopEe: produto.cfopee,
        cfopSi: produto.cfopsi,
        cfopSe: produto.cfopse,
        precoCompra: produto.preco_compra,
        precoTabela: produto.preco_tabela,
        pontoReposLj: produto.pontoreposlj,
        estMaxLj: produto.estmaxlj,
        dataMovimento: produto.datamovimento,
        tipoTributacao: produto.tipotributacao,
        palavraChave: produto.palavrachave,
        regimeEspecial: produto.regimeespecial,
        exTipi: produto.ex_tipi,
        aliqCredito: produto.aliqcredito,
        pedeSerial: produto.pedeserial,
        fatorEntrada: produto.fatorentrada,
        precoPedido: produto.preco_pedido,
        atualizaPeloNcm: produto.atualizapeloncm,
        divisorEntrada: produto.divisorentrada,
        descricaoSite: produto.descricaosite,
        fatorTransf: produto.fatortransf,
        pedidoPadrao: produto.pedidopadrao,
        baseFrete: produto.basefrete,
        icmsFrete: produto.icmsfrete,
        cstPisEntrada: produto.cstpisentrada,
        cstPisSaida: produto.cstpissaida,
        cstCofinsEntrada: produto.cstcofinsentrada,
        cstCofinsSaida: produto.cstcofinssaida,
        rendimentoUn: produto.rendimentoun,
        estoqueRetroativo: produto.estoqueretroativo,
        tipoItemSef2: produto.tipo_item_sef2,
        natReceitaPiscofins: produto.natreceitapiscofins,
        enderecoLoja: produto.enderecoloja,
        enderecoDeposito: produto.enderecodeposito,
        codigoInicioDia: produto.codigoiniciodia,
        nomeInicioDia: produto.nomeiniciodia,
        unidadeInicioDia: produto.unidadeiniciodia,
        dataMovimentoInicioDia: produto.datamovimentoiniciodia,
        emPromocao: produto.em_promocao,
        percTribFed: produto.perc_trib_fed,
        percTribEst: produto.perc_trib_est,
        percTribMun: produto.perc_trib_mun,
        idFamilia: produto.id_familia,
        modoPreparo: produto.modo_preparo,
        visivelSite: produto.visivel_site,
        verificaQuantEntrada: produto.verifica_quant_entrada,
        cnpjEstab: produto.cnpjestab,
        cnpjEstabInicioDia: produto.cnpjestabiniciodia,
        codigoAnp: produto.codigo_anp,
        fcp: produto.fcp,
        dtVendaMedia: produto.dt_vendamedia,
        diasVendaMedia: produto.dias_vendamedia,
        dtFab: produto.dt_fab,
        dtVal: produto.dt_val,
        setorExpedicao: produto.setor_expedicao,
        setorProducao: produto.setor_producao,
        qtPorConvidado: produto.qt_porconvidado,
        qtPax: produto.qtpax,
        qtPorPax: produto.qtporpax,
        qtdeAtacado: produto.qtde_atacado,
        quantAtacado2: produto.quant_atacado2,
        quantAtacado3: produto.quant_atacado3,
        quantAtacado4: produto.quant_atacado4,
        quantAtacado5: produto.quant_atacado5,
        fatorMultiploSaida: produto.fatormultiplosaida,
        idEditora: produto.id_editora,
        idEstProducao: produto.id_estproducao,
        ordemMobile: produto.ordemmobile,
        visivelMobile: produto.visivelmobile,
        controlaLote: produto.controlalote,
        quantParaBonificacao: produto.quant_parabonificacao,
        quantBonificada: produto.quant_bonificada,
        agregadoIcmsAntecipacao: produto.agregado_icmsantipacao,
        habilitaCalcAtecipacao: produto.habilita_calcantecipacao,
        indEscala: produto.indescala,
        cnpjFab: produto.cnpjfab,
        glpDerivado: produto.glpderivado,
        glgnn: produto.glgnn,
        glgni: produto.glgni,
        valorPartida: produto.valorpartida,
        ipiSaida: produto.ipisaida,
        formaCadastro: produto.forma_cadastro,
        horaCadastro: produto.hora_cadastro,
        painelCozinha: produto.painel_cozinha,
        turnoProducao: produto.turno_producao,
        cstIpiSaida: produto.cst_ipi_saida,
        cstIpiEntrada: produto.cst_ipi_entrada,
        percDescontoAuto: produto.percdescontoauto,
        custoCompra1: produto.custocompra1,
        cnaeServico: produto.cnaeservico,
        codigoServico: produto.codigoservico,
        dtNfUltimaEntrada: produto.dtnf_ultima_entrada,
        dtNfUltimoLancamento: produto.dtnf_ultimo_lancamento,
        integracaoSite: produto.integracao_site,
        ignorarReferencia: produto.ignorar_referencia,
        foraDeLinha: produto.fora_de_linha,
        idLinha: produto.id_linha,
        prodNuvem: produto.prodnuvem,
        idLocalizacao: produto.id_localizacao,
        idEnderecoLoja: produto.id_enderecoloja,
        idEnderecoDeposito: produto.id_enderecodeposito,
        confConveniencia: produto.conf_conveniencia,
        codBenefCstIcms: produto.codbenef_csticms,
        motBenefCstIcms: produto.motbenef_csticms,
        perBenefCstIcms: produto.perbenef_csticms,
        aliqBenefCstIcms: produto.aliqbenef_csticms,
        metaChave: produto.metachave,
        metaDescricao: produto.metadescricao,
        metaTitulo: produto.metatitulo,
        nomeAmigavel: produto.nomeamigavel,
        // Campos que não foram mapeados (ex: seg, ter, qua, etc.) devem ser adicionados aqui se forem usados no formulário
      };
      // Remova campos que não devem ser enviados ou que serão gerados pelo backend
      delete produtoToSend.dt_cadastro; // backend vai gerar
      // delete produtoToSend.id; // se for um novo registro, não envie o ID

      if (id) {
        // Atualizar produto existente
        await axios.put(`${API_BASE_URL}/${id}`, produtoToSend);
        showSnackbar("Produto atualizado com sucesso!", 'success');
      } else {
        // Cadastrar novo produto
        await axios.post(API_BASE_URL, produtoToSend);
        showSnackbar("Produto cadastrado com sucesso!", 'success');
        // Limpar o formulário após o cadastro bem-sucedido
        setProduto({
          // ... (Todos os campos com seus valores iniciais, como no estado inicial do useState)
          // Isso garante que o formulário seja limpo para um novo cadastro
          ativo: true,
          nome: '',
          codigo: '',
          nomereduzido: '',
          unidade: 'UN',
          idGrupo: null, // Alterado para null
          idFabricante: null,
          idFornecedor: null,
          referencia: '',
          embalagem: 0.00,
          cst: '',
          estoqueSeguranca: 0,
          prazovalidade: 0,
          comissionaproduto: false,
          comissao: 0.00,
          itemespecial: false,
          itemcritico: false,
          materiaprima: false,
          baixarcomposicao: false,
          localizacao: '',
          pesobruto: 0.000,
          pesoliquido: 0.000,
          dt_ultimacompra: null,
          quant_ultimacompra: 0.000,
          descontomax: 0.00,
          observacoes: '',
          reajusta_auto: false,
          desconto_tabela: 0.000,
          desconto_compra: 0.00,
          icmscompra: 0.00,
          ipicompra: 0.00,
          fretecompra: 0.00,
          outrasdespesas: 0.00,
          custofinanceiro: 0.00,
          piscredito: 0.00,
          cofinscredito: 0.00,
          custo_liquido: 0.00,
          custo_medio: 0.00,
          custo_total: 0.00,
          markup: 0.00,
          lucrovenda: 0.00,
          agregado_icms: 0.00,
          pisdebito: 0.00,
          cofinsdebito: 0.00,
          reducaoicms: 0.00,
          icmsvenda: 0.00,
          custovenda1: 0.00,
          custovenda2: 0.00,
          custovenda3: 0.00,
          custovenda4: 0.00,
          custovenda5: 0.00,
          preco_sem_lucro: 0.00,
          preco_sugerido: 0.00,
          preco_venda: 0.00,
          preco_venda2: 0.00,
          preco_venda3: 0.00,
          preco_venda4: 0.00,
          preco_venda5: 0.00,
          margem_liquida: 0.00,
          margem_media: 0.00,
          eliminado: false,
          eliminadopor: null,
          dt_eliminacao: null,
          descontomaxgerencia: 0.00,
          estoque_minimo: 0.000,
          producao_propria: false,
          aliqecf: '',
          pesavel: false,
          codigoncm: '',
          tipo_item: '',
          cod_gen: '',
          conta_contabil: '',
          classiffiscal: '',
          rendimento: 0.000,
          iat: '',
          ippt: '',
          cfopei: '',
          cfopee: '',
          cfopsi: '',
          cfopse: '',
          preco_compra: 0.000,
          preco_tabela: 0.000,
          segunda: 0.000,
          terca: 0.000,
          quarta: 0.000,
          quinta: 0.000,
          sexta: 0.000,
          sabado: 0.000,
          domingo: 0.000,
          pontoreposlj: 0.000,
          estmaxlj: 0.000,
          datamovimento: null,
          tipotributacao: '',
          lock: '',
          palavrachave: '',
          regimeespecial: false,
          ex_tipi: '',
          csosn: '',
          aliqcredito: 0.000,
          pedeserial: false,
          fatorentrada: 0.000,
          preco_pedido: 0.000,
          gtin: false,
          atualizapeloncm: false,
          garantia: '',
          foto: '',
          divisorentrada: 0.000,
          descricaosite: '',
          altura: 0.000,
          largura: 0.000,
          espessura: 0.000,
          expedicao: 0,
          fatortransf: 0.000,
          pedidopadrao: 0.000,
          maq1: 0.000, maq2: 0.000, maq3: 0.000, maq4: 0.000, maq5: 0.000, maq6: 0.000, maq7: 0.000, maq8: 0.000, maq9: 0.000, maq10: 0.000,
          obra1: 0.000, obra2: 0.000, obra3: 0.000, obra4: 0.000, obra5: 0.000, obra6: 0.000, obra7: 0.000, obra8: 0.000, obra9: 0.000, obra10: 0.000,
          basefrete: 0.00,
          icmsfrete: 0.00,
          perc2: 0.00, perc3: 0.00, perc4: 0.00, perc5: 0.00,
          cstpisentrada: '',
          cstpissaida: '',
          cstcofinsentrada: '',
          cstcofinssaida: '',
          rendimentoun: 0,
          estoqueretroativo: 0.000,
          tipo_item_sef2: 0,
          impostos: 0.000,
          natreceitapiscofins: '',
          enderecoloja: '',
          enderecodeposito: '',
          codigoiniciodia: '',
          nomeiniciodia: '',
          unidadeiniciodia: '',
          datamovimentoiniciodia: null,
          em_promocao: false,
          perc_trib_fed: 0.00,
          perc_trib_est: 0.00,
          perc_trib_mun: 0.00,
          id_familia: null,
          modo_preparo: '',
          visivel_site: false,
          cest: '',
          verifica_quant_entrada: false,
          cnpjestab: '',
          cnpjestabiniciodia: '',
          codigo_anp: 0,
          fcp: 0.00,
          pontos: 0.00,
          dt_vendamedia: null,
          dias_vendamedia: 0,
          dt_fab: null,
          dt_val: null,
          lote: '',
          conteudo: '',
          setor_expedicao: 0,
          setor_producao: 0,
          qt_porconvidado: 0.000,
          qtpax: 0,
          qtporpax: 0,
          pax: false,
          qtde_atacado: 0.000,
          quant_atacado2: 0.000, quant_atacado3: 0.000, quant_atacado4: 0.000, quant_atacado5: 0.000,
          fatormultiplosaida: 0.00,
          id_editora: null,
          autor: '',
          id_estproducao: 1,
          ordemmobile: null,
          visivelmobile: false,
          controlalote: false,
          quant_parabonificacao: 0.000,
          quant_bonificada: 0.000,
          agregado_icmsantipacao: 0.00,
          habilita_calcantecipacao: false,
          indescala: 0,
          cnpjfab: '',
          glpderivado: 0.0000,
          glgnn: 0.0000,
          glgni: 0.0000,
          valorpartida: 0.000,
          ipisaida: 0.00,
          forma_cadastro: '',
          hora_cadastro: '',
          painel_cozinha: false,
          turno_producao: 0,
          cst_ipi_saida: '',
          cst_ipi_entrada: '',
          percdescontoauto: 0.00,
          seg: false, ter: false, qua: false, qui: false, sex: false, sab: false, dom: false,
          custocompra1: 0.00,
          iss: 0.00,
          cnaeservico: '',
          codigoservico: '',
          dtnf_ultima_entrada: null,
          dtnf_ultimo_lancamento: null,
          integracao_site: false,
          ignorar_referencia: false,
          fora_de_linha: false,
          id_linha: null,
          receita1: '', receita2: '', receita3: '', receita4: '', receita5: '',
          prodnuvem: false,
          id_localizacao: null,
          id_enderecoloja: null,
          id_enderecodeposito: null,
          conf_conveniencia: false,
          codbenef_csticms: '',
          motbenef_csticms: 9,
          perbenef_csticms: 0.00,
          aliqbenef_csticms: 0.00,
          tara: null,
          cor: '',
          marca: '',
          metachave: '',
          metadescricao: '',
          metatitulo: '',
          nomeamigavel: '',
        });
      }
      setTimeout(() => {
        navigate('/produtos');
      }, 1500);

    } catch (err) {
      console.error("Erro ao salvar produto:", err);
      const errorMessage = err.response?.data?.message || "Ocorreu um erro ao salvar o produto.";
      showSnackbar(errorMessage, 'error');
    } finally {
      setLoading(false);
    }
  };

  return (
    <Box sx={{ mt: 4, ml: 3 }}>
      <Typography variant="h4" gutterBottom>
        {id ? 'Editar Produto' : 'Novo Produto'}
      </Typography>

      <Paper elevation={3} sx={{ p: 3, mb: 3 }}>
        <form onSubmit={handleSubmit}>
          {loading && <CircularProgress sx={{ display: 'block', margin: 'auto', mb: 2 }} />}

          <Grid container spacing={3}>
            {/* ... (Os campos do formulário permanecem os mesmos) ... */}
            {/* Campo Ativo */}
            <Grid item xs={12} sm={4}>
              <FormControlLabel
                control={
                  <Checkbox
                    checked={produto.ativo}
                    onChange={handleChange}
                    name="ativo"
                    color="primary"
                  />
                }
                label="Ativo"
              />
            </Grid>
            {/* Campo Nome */}
            <Grid item xs={12} sm={8}>
              <TextField
                label="Nome"
                name="nome"
                value={produto.nome}
                onChange={handleChange}
                fullWidth
                required
                margin="normal"
                size="small"
              />
            </Grid>
            {/* Campo Código */}
            <Grid item xs={12} sm={6}>
              <TextField
                label="Código"
                name="codigo"
                value={produto.codigo}
                onChange={handleChange}
                fullWidth
                required
                margin="normal"
                size="small"
              />
            </Grid>
            {/* Campo Nome Reduzido */}
            <Grid item xs={12} sm={6}>
              <TextField
                label="Nome Reduzido"
                name="nomereduzido" // Este nome é para o estado do React
                value={produto.nomereduzido}
                onChange={handleChange}
                fullWidth
                margin="normal"
                size="small"
              />
            </Grid>
            {/* Campo Unidade */}
            <Grid item xs={12} sm={4}>
              <TextField
                label="Unidade"
                name="unidade"
                value={produto.unidade}
                onChange={handleChange}
                fullWidth
                margin="normal"
                size="small"
              />
            </Grid>
            {/* Campo Preço de Venda */}
            <Grid item xs={12} sm={4}>
              <TextField
                label="Preço de Venda"
                name="preco_venda" // Este nome é para o estado do React
                type="number"
                value={produto.preco_venda}
                onChange={handleNumericChange}
                fullWidth
                margin="normal"
                size="small"
                inputProps={{ step: "0.01" }}
              />
            </Grid>
             {/* Campo de Observações */}
             <Grid item xs={12}>
              <TextField
                label="Observações"
                name="observacoes"
                value={produto.observacoes}
                onChange={handleChange}
                fullWidth
                multiline
                rows={4}
                margin="normal"
                size="small"
              />
            </Grid>
            {/* Exemplo de campo de data */}
            <Grid item xs={12} sm={6}>
              <TextField
                label="Data Última Compra"
                name="dt_ultimacompra" // Este nome é para o estado do React
                type="date"
                value={produto.dt_ultimacompra || ''}
                onChange={handleDateChange}
                fullWidth
                margin="normal"
                InputLabelProps={{ shrink: true }}
                size="small"
              />
            </Grid>
            {/* Exemplo de campo numérico */}
            <Grid item xs={12} sm={6}>
              <TextField
                label="Estoque de Segurança"
                name="estoqueSeguranca" // Este nome é para o estado do React (camelCase)
                type="number"
                value={produto.estoqueSeguranca}
                onChange={handleNumericChange}
                fullWidth
                margin="normal"
                size="small"
              />
            </Grid>

            {/* Você precisaria expandir para todos os outros campos aqui  */}

          </Grid>

          <Box sx={{ mt: 3, display: 'flex', justifyContent: 'flex-end', gap: 2 }}>
            <Button
              variant="contained"
              color="primary"
              startIcon={<SaveIcon />}
              type="submit"
              disabled={loading}
            >
              {loading ? <CircularProgress size={24} /> : 'Salvar'}
            </Button>
            <Button
              variant="outlined"
              color="secondary"
              startIcon={<CancelIcon />}
              onClick={() => navigate('/produtos')}
              disabled={loading}
            >
              Cancelar
            </Button>
          </Box>
        </form>
      </Paper>

      <Snackbar
        open={snackbarOpen}
        autoHideDuration={6000}
        onClose={handleSnackbarClose}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
      >
        <Alert onClose={handleSnackbarClose} severity={snackbarSeverity} sx={{ width: '100%' }}>
          {snackbarMessage}
        </Alert>
      </Snackbar>
    </Box>
  );
}

export default ProdutoForm;