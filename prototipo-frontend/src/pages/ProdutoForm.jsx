// src/pages/ProdutoForm.jsx
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useParams, useLocation } from 'react-router-dom';
import {
  Box,
  Typography,
  TextField,
  Button,
  Paper,
  Grid,
  CircularProgress,
  FormControlLabel,
  Checkbox,
  Snackbar,
  Alert,
} from '@mui/material';
import {
  Save as SaveIcon,
  Cancel as CancelIcon
} from '@mui/icons-material';

const API_BASE_URL = 'http://localhost:8080/api/produtos';

// Função auxiliar para formatar datas para o input type="date"
const formatDateForInput = (dateString) => {
  if (!dateString) return '';
  const date = new Date(dateString);
  // Garante que a data seja interpretada como UTC para evitar problemas de fuso horário
  const year = date.getUTCFullYear();
  const month = (date.getUTCMonth() + 1).toString().padStart(2, '0');
  const day = date.getUTCDate().toString().padStart(2, '0');
  return `${year}-${month}-${day}`;
};

function ProdutoForm() {
  const navigate = useNavigate();
  const { id } = useParams();
  const location = useLocation();

  // Estado inicial do produto com nomes de campos em camelCase para corresponder ao backend
  // e valores padrão para evitar 'undefined' e garantir tipos corretos.
  const [produto, setProduto] = useState({
    ativo: true,
    nome: '',
    codigo: '',
    nomeReduzido: '',
    unidade: 'UN',
    idGrupo: null,
    idFabricante: null,
    idFornecedor: null,
    referencia: '',
    embalagem: 0.00,
    cst: '',
    estoqueSeguranca: 0,
    prazoValidade: 0,
    comissionaProduto: false,
    comissao: 0.00,
    itemEspecial: false,
    itemCritico: false,
    materiaPrima: false,
    baixarComposicao: false,
    localizacao: '',
    pesoBruto: 0.000,
    pesoLiquido: 0.000,
    dtCadastro: null, // Será preenchido pelo backend
    dtUltimaCompra: null,
    quantUltimaCompra: 0.000,
    descontoMax: 0.00,
    observacoes: '',
    reajustaAuto: false,
    descontoTabela: 0.000,
    descontoCompra: 0.00,
    icmsCompra: 0.00,
    ipiCompra: 0.00,
    freteCompra: 0.00,
    outrasDespesas: 0.00,
    custoFinanceiro: 0.00,
    pisCredito: 0.00,
    cofinsCredito: 0.00,
    custoLiquido: 0.00,
    custoMedio: 0.00,
    custoTotal: 0.00,
    markup: 0.00,
    lucroVenda: 0.00,
    agregadoIcms: 0.00,
    pisDebito: 0.00,
    cofinsDebito: 0.00,
    reducaoIcms: 0.00,
    icmsVenda: 0.00,
    custoVenda1: 0.00,
    custoVenda2: 0.00,
    custoVenda3: 0.00,
    custoVenda4: 0.00,
    custoVenda5: 0.00,
    precoSemLucro: 0.00,
    precoSugerido: 0.00,
    precoVenda: 0.00,
    precoVenda2: 0.00,
    precoVenda3: 0.00,
    precoVenda4: 0.00,
    precoVenda5: 0.00,
    margemLiquida: 0.00,
    margemMedia: 0.00,
    eliminado: false,
    eliminadoPor: null,
    dtEliminacao: null,
    descontoMaxGerencia: 0.00,
    estoqueMinimo: 0.000,
    producaoPropria: false,
    aliqEcf: '',
    pesavel: false,
    codigoNcm: '',
    tipoItem: '',
    codGen: '',
    contaContabil: '',
    classifFiscal: '',
    rendimento: 0.000,
    iat: '',
    ippt: '',
    cfopEi: '',
    cfopEe: '',
    cfopSi: '',
    cfopSe: '',
    precoCompra: 0.000,
    precoTabela: 0.000,
    segunda: 0.000,
    terca: 0.000,
    quarta: 0.000,
    quinta: 0.000,
    sexta: 0.000,
    sabado: 0.000,
    domingo: 0.000,
    pontoReposLj: 0.000,
    estMaxLj: 0.000,
    dataMovimento: null,
    tipoTributacao: '',
    lock: '',
    palavraChave: '',
    regimeEspecial: false,
    exTipi: '',
    csosn: '',
    aliqCredito: 0.000,
    pedeSerial: false,
    fatorEntrada: 0.000,
    precoPedido: 0.000,
    gtin: false,
    atualizaPeloNcm: false,
    garantia: '',
    foto: '',
    divisorEntrada: 0.000,
    descricaoSite: '',
    altura: 0.000,
    largura: 0.000,
    espessura: 0.000,
    expedicao: 0,
    fatorTransf: 0.000,
    pedidoPadrao: 0.000,
    maq1: 0.000, maq2: 0.000, maq3: 0.000, maq4: 0.000, maq5: 0.000, maq6: 0.000, maq7: 0.000, maq8: 0.000, maq9: 0.000, maq10: 0.000,
    obra1: 0.000, obra2: 0.000, obra3: 0.000, obra4: 0.000, obra5: 0.000, obra6: 0.000, obra7: 0.000, obra8: 0.000, obra9: 0.000, obra10: 0.000,
    baseFrete: 0.00,
    icmsFrete: 0.00,
    perc2: 0.00, perc3: 0.00, perc4: 0.00, perc5: 0.00,
    cstPisEntrada: '',
    cstPisSaida: '',
    cstCofinsEntrada: '',
    cstCofinsSaida: '',
    rendimentoUn: 0,
    estoqueRetroativo: 0.000,
    tipoItemSef2: 0,
    impostos: 0.000,
    natReceitaPiscofins: '',
    enderecoLoja: '',
    enderecoDeposito: '',
    codigoInicioDia: '',
    nomeInicioDia: '',
    unidadeInicioDia: '',
    dataMovimentoInicioDia: null,
    emPromocao: false,
    percTribFed: 0.00,
    percTribEst: 0.00,
    percTribMun: 0.00,
    idFamilia: null,
    modoPreparo: '',
    visivelSite: false,
    cest: '',
    verificaQuantEntrada: false,
    cnpjEstab: '',
    cnpjEstabInicioDia: '',
    codigoAnp: 0,
    fcp: 0.00,
    pontos: 0.00,
    dtVendaMedia: null,
    diasVendaMedia: 0,
    dtFab: null,
    dtVal: null,
    lote: '',
    conteudo: '',
    setorExpedicao: 0,
    setorProducao: 0,
    qtPorConvidado: 0.000,
    qtPax: 0,
    qtPorPax: 0,
    pax: false,
    qtdeAtacado: 0.000,
    quantAtacado2: 0.000, quantAtacado3: 0.000, quantAtacado4: 0.000, quantAtacado5: 0.000,
    fatorMultiploSaida: 0.00,
    idEditora: null,
    autor: '',
    idEstProducao: 1,
    ordemMobile: null,
    visivelMobile: false,
    controlaLote: false,
    quantParaBonificacao: 0.000,
    quantBonificada: 0.000,
    agregadoIcmsAntecipacao: 0.00,
    habilitaCalcAtecipacao: false,
    indEscala: 0,
    cnpjFab: '',
    glpDerivado: 0.0000,
    glgnn: 0.0000,
    glgni: 0.0000,
    valorPartida: 0.000,
    ipiSaida: 0.00,
    formaCadastro: '',
    horaCadastro: '',
    painelCozinha: false,
    turnoProducao: 0,
    cstIpiSaida: '',
    cstIpiEntrada: '',
    percDescontoAuto: 0.00,
    seg: false, ter: false, qua: false, qui: false, sex: false, sab: false, dom: false,
    custoCompra1: 0.00,
    iss: 0.00,
    cnaeServico: '',
    codigoServico: '',
    dtNfUltimaEntrada: null,
    dtNfUltimoLancamento: null,
    integracaoSite: false,
    ignorarReferencia: false,
    foraDeLinha: false,
    idLinha: null,
    receita1: '', receita2: '', receita3: '', receita4: '', receita5: '',
    prodNuvem: false,
    idLocalizacao: null,
    idEnderecoLoja: null,
    idEnderecoDeposito: null,
    confConveniencia: false,
    codBenefCstIcms: '',
    motBenefCstIcms: 9,
    perBenefCstIcms: 0.00,
    aliqBenefCstIcms: 0.00,
    tara: null,
    cor: '',
    marca: '',
    metaChave: '',
    metaDescricao: '',
    metaTitulo: '',
    nomeAmigavel: '',
  });

  const [loading, setLoading] = useState(false);
  const [snackbarOpen, setSnackbarOpen] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState('success');

  const showSnackbar = (message, severity) => {
    setSnackbarMessage(message);
    setSnackbarSeverity(severity);
    setSnackbarOpen(true);
  };

  const handleSnackbarClose = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }
    setSnackbarOpen(false);
  };

  useEffect(() => {
    // Lógica para "incluir similar"
    if (location.state && location.state.produtoOriginal) {
      const { produtoOriginal } = location.state;
      const mappedProduto = {};
      for (const key in produtoOriginal) {
        if (Object.hasOwnProperty.call(produtoOriginal, key)) {
          let value = produtoOriginal[key];
          if (key.startsWith('dt') || key.startsWith('data')) {
            value = formatDateForInput(value);
          }
          mappedProduto[key] = value;
        }
      }

      setProduto(prev => ({
        ...prev,
        ...mappedProduto,
        id: null, // MUITO IMPORTANTE: Garante que é um novo cadastro ao duplicar
        codigo: '', // O código pode ser único, então limpe-o para um novo produto
        dtCadastro: null, // A data de cadastro será definida pelo backend
      }));
      // Limpar o state da localização para não pré-preencher novamente
      // Isso é melhor feito ao navegar para esta página, por exemplo:
      // navigate('/produtos/novo', { state: {} });
    } else if (id) { // Lógica para edição de produto existente
      setLoading(true);
      axios.get(`${API_BASE_URL}/${id}`)
        .then(response => {
          const produtoData = response.data;
          const mappedProduto = {};
          for (const key in produtoData) {
            if (Object.hasOwnProperty.call(produtoData, key)) {
              let value = produtoData[key];
              // Formata datas para o formato 'yyyy-MM-dd'
              if (key.startsWith('dt') || key.startsWith('data')) {
                value = formatDateForInput(value);
              }
              mappedProduto[key] = value;
            }
          }
          setProduto(prev => ({
            ...prev,
            ...mappedProduto
          }));
          console.log("Produto carregado para edição:", mappedProduto); // Para depuração
          setLoading(false);
        })
        .catch(err => {
          console.error("Erro ao buscar produto para edição:", err);
          showSnackbar("Erro ao carregar dados do produto para edição.", 'error');
          setLoading(false);
        });
    }
  }, [id, location.state]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setProduto(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  const handleNumericChange = (e) => {
    const { name, value } = e.target;
    // Converte para número. Se o valor for uma string vazia, salva como null ou 0, dependendo do campo.
    // Aqui, vamos salvar como null se o campo estiver vazio para permitir campos opcionais,
    // e deixar o backend lidar com padrões se a coluna não aceitar null.
    const numericValue = value === '' ? null : Number(value);

    setProduto(prev => ({
      ...prev,
      [name]: numericValue
    }));
  };

  const handleDateChange = (e) => {
    const { name, value } = e.target;
    setProduto(prev => ({
      ...prev,
      [name]: value || null // Salva a string da data no formato 'yyyy-MM-dd' ou null se vazio
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setSnackbarOpen(false);

    // Validações básicas antes de enviar
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

    // Cria um objeto para enviar ao backend.
    // Como o estado `produto` já está em camelCase, podemos usá-lo diretamente.
    const produtoToSend = { ...produto };

    // Remover o ID se for um novo cadastro
    if (!id) {
      delete produtoToSend.id;
      // O backend definirá a data de cadastro automaticamente para novos produtos
      delete produtoToSend.dtCadastro; //
    }
    // Para campos BigDecimal ou Integer, se estiverem como null (vazio no input),
    // e o backend não aceita null, defina para 0 ou outro padrão adequado.
    // O backend já faz algumas validações, mas é bom ter uma camada aqui.
    for (const key in produtoToSend) {
        if (produtoToSend[key] === null || produtoToSend[key] === '') {
            // Exemplo: se espera um Number, converte null para 0
            if (typeof produto[key] === 'number') {
                produtoToSend[key] = 0;
            }
            // Exemplo: se espera um Boolean, converte null para false
            if (typeof produto[key] === 'boolean') {
                produtoToSend[key] = false;
            }
        }
    }


    console.log("Dados a serem enviados:", produtoToSend); // Para depuração

    try {
      if (id) {
        // Se há um ID, é uma atualização (PUT)
        await axios.put(`${API_BASE_URL}/${id}`, produtoToSend);
        showSnackbar("Produto atualizado com sucesso!", 'success');
      } else {
        // Se não há ID, é um novo cadastro (POST)
        await axios.post(API_BASE_URL, produtoToSend);
        showSnackbar("Produto cadastrado com sucesso!", 'success');
        // Limpar o formulário após o cadastro bem-sucedido para um novo registro
        // Resetar o estado para os valores iniciais limpos
        setProduto({
          ativo: true, nome: '', codigo: '', nomeReduzido: '', unidade: 'UN',
          idGrupo: null, idFabricante: null, idFornecedor: null, referencia: '',
          embalagem: 0.00, cst: '', estoqueSeguranca: 0, prazoValidade: 0,
          comissionaProduto: false, comissao: 0.00, itemEspecial: false,
          itemCritico: false, materiaPrima: false, baixarComposicao: false,
          localizacao: '', pesoBruto: 0.000, pesoLiquido: 0.000, dtCadastro: null,
          dtUltimaCompra: null, quantUltimaCompra: 0.000, descontoMax: 0.00,
          observacoes: '', reajustaAuto: false, descontoTabela: 0.000,
          descontoCompra: 0.00, icmsCompra: 0.00, ipiCompra: 0.00, freteCompra: 0.00,
          outrasDespesas: 0.00, custoFinanceiro: 0.00, pisCredito: 0.00,
          cofinsCredito: 0.00, custoLiquido: 0.00, custoMedio: 0.00,
          custoTotal: 0.00, markup: 0.00, lucroVenda: 0.00, agregadoIcms: 0.00,
          pisDebito: 0.00, cofinsDebito: 0.00, reducaoIcms: 0.00, icmsVenda: 0.00,
          custoVenda1: 0.00, custoVenda2: 0.00, custoVenda3: 0.00, custoVenda4: 0.00,
          custoVenda5: 0.00, precoSemLucro: 0.00, precoSugerido: 0.00,
          precoVenda: 0.00, precoVenda2: 0.00, precoVenda3: 0.00, precoVenda4: 0.00,
          precoVenda5: 0.00, margemLiquida: 0.00, margemMedia: 0.00,
          eliminado: false, eliminadoPor: null, dtEliminacao: null,
          descontoMaxGerencia: 0.00, estoqueMinimo: 0.000, producaoPropria: false,
          aliqEcf: '', pesavel: false, codigoNcm: '', tipoItem: '', codGen: '',
          contaContabil: '', classifFiscal: '', rendimento: 0.000, iat: '', ippt: '',
          cfopEi: '', cfopEe: '', cfopSi: '', cfopSe: '', precoCompra: 0.000,
          precoTabela: 0.000, segunda: 0.000, terca: 0.000, quarta: 0.000,
          quinta: 0.000, sexta: 0.000, sabado: 0.000, domingo: 0.000,
          pontoReposLj: 0.000, estMaxLj: 0.000, dataMovimento: null,
          tipoTributacao: '', lock: '', palavraChave: '', regimeEspecial: false,
          exTipi: '', csosn: '', aliqCredito: 0.000, pedeSerial: false,
          fatorEntrada: 0.000, precoPedido: 0.000, gtin: false,
          atualizaPeloNcm: false, garantia: '', foto: '', divisorEntrada: 0.000,
          descricaoSite: '', altura: 0.000, largura: 0.000, espessura: 0.000,
          expedicao: 0, fatorTransf: 0.000, pedidoPadrao: 0.000, maq1: 0.000,
          maq2: 0.000, maq3: 0.000, maq4: 0.000, maq5: 0.000, maq6: 0.000,
          maq7: 0.000, maq8: 0.000, maq9: 0.000, maq10: 0.000, obra1: 0.000,
          obra2: 0.000, obra3: 0.000, obra4: 0.000, obra5: 0.000, obra6: 0.000,
          obra7: 0.000, obra8: 0.000, obra9: 0.000, obra10: 0.000,
          baseFrete: 0.00, icmsFrete: 0.00, perc2: 0.00, perc3: 0.00, perc4: 0.00,
          perc5: 0.00, cstPisEntrada: '', cstPisSaida: '', cstCofinsEntrada: '',
          cstCofinsSaida: '', rendimentoUn: 0, estoqueRetroativo: 0.000,
          tipoItemSef2: 0, impostos: 0.000, natReceitaPiscofins: '',
          enderecoLoja: '', enderecoDeposito: '', codigoInicioDia: '',
          nomeInicioDia: '', unidadeInicioDia: '', dataMovimentoInicioDia: null,
          emPromocao: false, percTribFed: 0.00, percTribEst: 0.00,
          percTribMun: 0.00, idFamilia: null, modoPreparo: '', visivelSite: false,
          cest: '', verificaQuantEntrada: false, cnpjEstab: '',
          cnpjEstabInicioDia: '', codigoAnp: 0, fcp: 0.00, pontos: 0.00,
          dtVendaMedia: null, diasVendaMedia: 0, dtFab: null, dtVal: null,
          lote: '', conteudo: '', setorExpedicao: 0, setorProducao: 0,
          qtPorConvidado: 0.000, qtPax: 0, qtPorPax: 0, pax: false,
          qtdeAtacado: 0.000, quantAtacado2: 0.000, quantAtacado3: 0.000,
          quantAtacado4: 0.000, quantAtacado5: 0.000, fatorMultiploSaida: 0.00,
          idEditora: null, autor: '', idEstProducao: 1, ordemMobile: null,
          visivelMobile: false, controlaLote: false, quantParaBonificacao: 0.000,
          quantBonificada: 0.000, agregadoIcmsAntecipacao: 0.00,
          habilitaCalcAtecipacao: false, indEscala: 0, cnpjFab: '',
          glpDerivado: 0.0000, glgnn: 0.0000, glgni: 0.0000, valorPartida: 0.000,
          ipiSaida: 0.00, formaCadastro: '', horaCadastro: '',
          painelCozinha: false, turnoProducao: 0, cstIpiSaida: '',
          cstIpiEntrada: '', percDescontoAuto: 0.00, seg: false, ter: false,
          qua: false, qui: false, sex: false, sab: false, dom: false,
          custoCompra1: 0.00, iss: 0.00, cnaeServico: '', codigoServico: '',
          dtNfUltimaEntrada: null, dtNfUltimoLancamento: null,
          integracaoSite: false, ignorarReferencia: false, foraDeLinha: false,
          idLinha: null, receita1: '', receita2: '', receita3: '', receita4: '',
          receita5: '', prodNuvem: false, idLocalizacao: null,
          idEnderecoLoja: null, idEnderecoDeposito: null, confConveniencia: false,
          codBenefCstIcms: '', motBenefCstIcms: 9, perBenefCstIcms: 0.00,
          aliqBenefCstIcms: 0.00, tara: null, cor: '', marca: '',
          metaChave: '', metaDescricao: '', metaTitulo: '', nomeAmigavel: '',
        });
      }
      setTimeout(() => {
        navigate('/produtos');
      }, 1500);

    } catch (err) {
      console.error("Erro ao salvar produto:", err.response ? err.response.data : err);
      const errorMessage = err.response?.data?.message || "Ocorreu um erro ao salvar o produto. Verifique os dados.";
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
                name="nomeReduzido" // Corrigido para camelCase
                value={produto.nomeReduzido}
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
                name="precoVenda" // Corrigido para camelCase
                type="number"
                value={produto.precoVenda}
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
                name="dtUltimaCompra" // Corrigido para camelCase
                type="date"
                value={produto.dtUltimaCompra || ''}
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
                name="estoqueSeguranca" // Já estava em camelCase, mantido
                type="number"
                value={produto.estoqueSeguranca}
                onChange={handleNumericChange}
                fullWidth
                margin="normal"
                size="small"
              />
            </Grid>

            {/* Adicione outros campos do seu modelo Produto aqui, seguindo o padrão camelCase */}

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