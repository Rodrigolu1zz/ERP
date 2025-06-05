// prototipo/src/main/java/com/prototipo/service/ProdutoService.java
package com.prototipo.service;

import com.prototipo.model.Produto;
import com.prototipo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrarProduto(Produto produto) {
        if (produto.getId() == null) {
            produto.setDtCadastro(LocalDate.now());
            // Garante que novos produtos não são eliminados por padrão
            if (produto.getEliminado() == null) {
                produto.setEliminado(false);
            }
        }
        if (produto.getNome() == null || produto.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    /**
     * Lista todos os produtos que NÃO foram eliminados logicamente.
     * @return Uma lista de produtos não eliminados.
     */
    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findByEliminadoFalse(); // Altera para buscar apenas não eliminados
    }

    public Produto atualizarProduto(Integer id, Produto produtoDadosNovos) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            // Atualiza todos os campos relevantes.
            // Para exclusão lógica, o campo 'eliminado' é atualizado aqui.
            // Certifique-se de que todos os campos que vêm do frontend são mapeados corretamente.
            produtoExistente.setAtivo(produtoDadosNovos.getAtivo());
            produtoExistente.setNome(produtoDadosNovos.getNome());
            produtoExistente.setCodigo(produtoDadosNovos.getCodigo());
            produtoExistente.setNomeReduzido(produtoDadosNovos.getNomeReduzido());
            produtoExistente.setUnidade(produtoDadosNovos.getUnidade());
            produtoExistente.setIdGrupo(produtoDadosNovos.getIdGrupo());
            produtoExistente.setIdFabricante(produtoDadosNovos.getIdFabricante());
            produtoExistente.setIdFornecedor(produtoDadosNovos.getIdFornecedor());
            produtoExistente.setReferencia(produtoDadosNovos.getReferencia());
            produtoExistente.setEmbalagem(produtoDadosNovos.getEmbalagem());
            produtoExistente.setCst(produtoDadosNovos.getCst());
            produtoExistente.setEstoqueSeguranca(produtoDadosNovos.getEstoqueSeguranca());
            produtoExistente.setPrazoValidade(produtoDadosNovos.getPrazoValidade());
            produtoExistente.setComissionaProduto(produtoDadosNovos.getComissionaProduto());
            produtoExistente.setComissao(produtoDadosNovos.getComissao());
            produtoExistente.setItemEspecial(produtoDadosNovos.getItemEspecial());
            produtoExistente.setItemCritico(produtoDadosNovos.getItemCritico());
            produtoExistente.setMateriaPrima(produtoDadosNovos.getMateriaPrima());
            produtoExistente.setBaixarComposicao(produtoDadosNovos.getBaixarComposicao());
            produtoExistente.setLocalizacao(produtoDadosNovos.getLocalizacao());
            produtoExistente.setPesoBruto(produtoDadosNovos.getPesoBruto());
            produtoExistente.setPesoLiquido(produtoDadosNovos.getPesoLiquido());
            // dtCadastro normalmente não seria atualizado.
            produtoExistente.setDtUltimaCompra(produtoDadosNovos.getDtUltimaCompra());
            produtoExistente.setQuantUltimaCompra(produtoDadosNovos.getQuantUltimaCompra());
            produtoExistente.setDescontoMax(produtoDadosNovos.getDescontoMax());
            produtoExistente.setObservacoes(produtoDadosNovos.getObservacoes());
            produtoExistente.setReajustaAuto(produtoDadosNovos.getReajustaAuto());
            produtoExistente.setDescontoTabela(produtoDadosNovos.getDescontoTabela());
            produtoExistente.setDescontoCompra(produtoDadosNovos.getDescontoCompra());
            produtoExistente.setIcmsCompra(produtoDadosNovos.getIcmsCompra());
            produtoExistente.setIpiCompra(produtoDadosNovos.getIpiCompra());
            produtoExistente.setFreteCompra(produtoDadosNovos.getFreteCompra());
            produtoExistente.setOutrasDespesas(produtoDadosNovos.getOutrasDespesas());
            produtoExistente.setCustoFinanceiro(produtoDadosNovos.getCustoFinanceiro());
            produtoExistente.setPisCredito(produtoDadosNovos.getPisCredito());
            produtoExistente.setCofinsCredito(produtoDadosNovos.getCofinsCredito());
            produtoExistente.setCustoLiquido(produtoDadosNovos.getCustoLiquido());
            produtoExistente.setCustoMedio(produtoDadosNovos.getCustoMedio());
            produtoExistente.setCustoTotal(produtoDadosNovos.getCustoTotal());
            produtoExistente.setMarkup(produtoDadosNovos.getMarkup());
            produtoExistente.setLucroVenda(produtoDadosNovos.getLucroVenda());
            produtoExistente.setAgregadoIcms(produtoDadosNovos.getAgregadoIcms());
            produtoExistente.setPisDebito(produtoDadosNovos.getPisDebito());
            produtoExistente.setCofinsDebito(produtoDadosNovos.getCofinsDebito());
            produtoExistente.setReducaoIcms(produtoDadosNovos.getReducaoIcms());
            produtoExistente.setIcmsVenda(produtoDadosNovos.getIcmsVenda());
            produtoExistente.setCustoVenda1(produtoDadosNovos.getCustoVenda1());
            produtoExistente.setCustoVenda2(produtoDadosNovos.getCustoVenda2());
            produtoExistente.setCustoVenda3(produtoDadosNovos.getCustoVenda3());
            produtoExistente.setCustoVenda4(produtoDadosNovos.getCustoVenda4());
            produtoExistente.setCustoVenda5(produtoDadosNovos.getCustoVenda5());
            produtoExistente.setPrecoSemLucro(produtoDadosNovos.getPrecoSemLucro());
            produtoExistente.setPrecoSugerido(produtoDadosNovos.getPrecoSugerido());
            produtoExistente.setPrecoVenda(produtoDadosNovos.getPrecoVenda());
            produtoExistente.setPrecoVenda2(produtoDadosNovos.getPrecoVenda2());
            produtoExistente.setPrecoVenda3(produtoDadosNovos.getPrecoVenda3());
            produtoExistente.setPrecoVenda4(produtoDadosNovos.getPrecoVenda4());
            produtoExistente.setPrecoVenda5(produtoDadosNovos.getPrecoVenda5());
            produtoExistente.setMargemLiquida(produtoDadosNovos.getMargemLiquida());
            produtoExistente.setMargemMedia(produtoDadosNovos.getMargemMedia());
            produtoExistente.setEliminado(produtoDadosNovos.getEliminado()); // <<< CAMPO ELIMINADO ATUALIZADO AQUI
            produtoExistente.setEliminadoPor(produtoDadosNovos.getEliminadoPor());
            produtoExistente.setDtEliminacao(produtoDadosNovos.getDtEliminacao());
            produtoExistente.setDescontoMaxGerencia(produtoDadosNovos.getDescontoMaxGerencia());
            produtoExistente.setEstoqueMinimo(produtoDadosNovos.getEstoqueMinimo());
            produtoExistente.setProducaoPropria(produtoDadosNovos.getProducaoPropria());
            produtoExistente.setAliqEcf(produtoDadosNovos.getAliqEcf());
            produtoExistente.setPesavel(produtoDadosNovos.getPesavel());
            produtoExistente.setCodigoNcm(produtoDadosNovos.getCodigoNcm());
            produtoExistente.setTipoItem(produtoDadosNovos.getTipoItem());
            produtoExistente.setCodGen(produtoDadosNovos.getCodGen());
            produtoExistente.setContaContabil(produtoDadosNovos.getContaContabil());
            produtoExistente.setClassifFiscal(produtoDadosNovos.getClassifFiscal());
            produtoExistente.setRendimento(produtoDadosNovos.getRendimento());
            produtoExistente.setIat(produtoDadosNovos.getIat());
            produtoExistente.setIppt(produtoDadosNovos.getIppt());
            produtoExistente.setCfopEi(produtoDadosNovos.getCfopEi());
            produtoExistente.setCfopEe(produtoDadosNovos.getCfopEe());
            produtoExistente.setCfopSi(produtoDadosNovos.getCfopSi());
            produtoExistente.setCfopSe(produtoDadosNovos.getCfopSe());
            produtoExistente.setPrecoCompra(produtoDadosNovos.getPrecoCompra());
            produtoExistente.setPrecoTabela(produtoDadosNovos.getPrecoTabela());
            produtoExistente.setSegunda(produtoDadosNovos.getSegunda());
            produtoExistente.setTerca(produtoDadosNovos.getTerca());
            produtoExistente.setQuarta(produtoDadosNovos.getQuarta());
            produtoExistente.setQuinta(produtoDadosNovos.getQuinta());
            produtoExistente.setSexta(produtoDadosNovos.getSexta());
            produtoExistente.setSabado(produtoDadosNovos.getSabado());
            produtoExistente.setDomingo(produtoDadosNovos.getDomingo());
            produtoExistente.setPontoReposLj(produtoDadosNovos.getPontoReposLj());
            produtoExistente.setEstMaxLj(produtoDadosNovos.getEstMaxLj());
            produtoExistente.setDataMovimento(produtoDadosNovos.getDataMovimento());
            produtoExistente.setTipoTributacao(produtoDadosNovos.getTipoTributacao());
            produtoExistente.setLock(produtoDadosNovos.getLock());
            produtoExistente.setPalavraChave(produtoDadosNovos.getPalavraChave());
            produtoExistente.setRegimeEspecial(produtoDadosNovos.getRegimeEspecial());
            produtoExistente.setExTipi(produtoDadosNovos.getExTipi());
            produtoExistente.setCsosn(produtoDadosNovos.getCsosn());
            produtoExistente.setAliqCredito(produtoDadosNovos.getAliqCredito());
            produtoExistente.setPedeSerial(produtoDadosNovos.getPedeSerial());
            produtoExistente.setFatorEntrada(produtoDadosNovos.getFatorEntrada());
            produtoExistente.setPrecoPedido(produtoDadosNovos.getPrecoPedido());
            produtoExistente.setGtin(produtoDadosNovos.getGtin());
            produtoExistente.setAtualizaPeloNcm(produtoDadosNovos.getAtualizaPeloNcm());
            produtoExistente.setGarantia(produtoDadosNovos.getGarantia());
            produtoExistente.setFoto(produtoDadosNovos.getFoto());
            produtoExistente.setDivisorEntrada(produtoDadosNovos.getDivisorEntrada());
            produtoExistente.setDescricaoSite(produtoDadosNovos.getDescricaoSite());
            produtoExistente.setAltura(produtoDadosNovos.getAltura());
            produtoExistente.setLargura(produtoDadosNovos.getLargura());
            produtoExistente.setEspessura(produtoDadosNovos.getEspessura());
            produtoExistente.setExpedicao(produtoDadosNovos.getExpedicao());
            produtoExistente.setFatorTransf(produtoDadosNovos.getFatorTransf());
            produtoExistente.setPedidoPadrao(produtoDadosNovos.getPedidoPadrao());
            produtoExistente.setMaq1(produtoDadosNovos.getMaq1());
            produtoExistente.setMaq2(produtoDadosNovos.getMaq2());
            produtoExistente.setMaq3(produtoDadosNovos.getMaq3());
            produtoExistente.setMaq4(produtoDadosNovos.getMaq4());
            produtoExistente.setMaq5(produtoDadosNovos.getMaq5());
            produtoExistente.setMaq6(produtoDadosNovos.getMaq6());
            produtoExistente.setMaq7(produtoDadosNovos.getMaq7());
            produtoExistente.setMaq8(produtoDadosNovos.getMaq8());
            produtoExistente.setMaq9(produtoDadosNovos.getMaq9());
            produtoExistente.setMaq10(produtoDadosNovos.getMaq10());
            produtoExistente.setObra1(produtoDadosNovos.getObra1());
            produtoExistente.setObra2(produtoDadosNovos.getObra2());
            produtoExistente.setObra3(produtoDadosNovos.getObra3());
            produtoExistente.setObra4(produtoDadosNovos.getObra4());
            produtoExistente.setObra5(produtoDadosNovos.getObra5());
            produtoExistente.setObra6(produtoDadosNovos.getObra6());
            produtoExistente.setObra7(produtoDadosNovos.getObra7());
            produtoExistente.setObra8(produtoDadosNovos.getObra8());
            produtoExistente.setObra9(produtoDadosNovos.getObra9());
            produtoExistente.setObra10(produtoDadosNovos.getObra10());
            produtoExistente.setBaseFrete(produtoDadosNovos.getBaseFrete());
            produtoExistente.setIcmsFrete(produtoDadosNovos.getIcmsFrete());
            produtoExistente.setPerc2(produtoDadosNovos.getPerc2());
            produtoExistente.setPerc3(produtoDadosNovos.getPerc3());
            produtoExistente.setPerc4(produtoDadosNovos.getPerc4());
            produtoExistente.setPerc5(produtoDadosNovos.getPerc5());
            produtoExistente.setCstPisEntrada(produtoDadosNovos.getCstPisEntrada());
            produtoExistente.setCstPisSaida(produtoDadosNovos.getCstPisSaida());
            produtoExistente.setCstCofinsEntrada(produtoDadosNovos.getCstCofinsEntrada());
            produtoExistente.setCstCofinsSaida(produtoDadosNovos.getCstCofinsSaida());
            produtoExistente.setRendimentoUn(produtoDadosNovos.getRendimentoUn());
            produtoExistente.setEstoqueRetroativo(produtoDadosNovos.getEstoqueRetroativo());
            produtoExistente.setTipoItemSef2(produtoDadosNovos.getTipoItemSef2());
            produtoExistente.setImpostos(produtoDadosNovos.getImpostos());
            produtoExistente.setNatReceitaPiscofins(produtoDadosNovos.getNatReceitaPiscofins());
            produtoExistente.setEnderecoLoja(produtoDadosNovos.getEnderecoLoja());
            produtoExistente.setEnderecoDeposito(produtoDadosNovos.getEnderecoDeposito());
            produtoExistente.setCodigoInicioDia(produtoDadosNovos.getCodigoInicioDia());
            produtoExistente.setNomeInicioDia(produtoDadosNovos.getNomeInicioDia());
            produtoExistente.setUnidadeInicioDia(produtoDadosNovos.getUnidadeInicioDia());
            produtoExistente.setDataMovimentoInicioDia(produtoDadosNovos.getDataMovimentoInicioDia());
            produtoExistente.setEmPromocao(produtoDadosNovos.getEmPromocao());
            produtoExistente.setPercTribFed(produtoDadosNovos.getPercTribFed());
            produtoExistente.setPercTribEst(produtoDadosNovos.getPercTribEst());
            produtoExistente.setPercTribMun(produtoDadosNovos.getPercTribMun());
            produtoExistente.setIdFamilia(produtoDadosNovos.getIdFamilia());
            produtoExistente.setModoPreparo(produtoDadosNovos.getModoPreparo());
            produtoExistente.setVisivelSite(produtoDadosNovos.getVisivelSite());
            produtoExistente.setCest(produtoDadosNovos.getCest());
            produtoExistente.setVerificaQuantEntrada(produtoDadosNovos.getVerificaQuantEntrada());
            produtoExistente.setCnpjEstab(produtoDadosNovos.getCnpjEstab());
            produtoExistente.setCnpjEstabInicioDia(produtoDadosNovos.getCnpjEstabInicioDia());
            produtoExistente.setCodigoAnp(produtoDadosNovos.getCodigoAnp());
            produtoExistente.setFcp(produtoDadosNovos.getFcp());
            produtoExistente.setPontos(produtoDadosNovos.getPontos());
            produtoExistente.setDtVendaMedia(produtoDadosNovos.getDtVendaMedia());
            produtoExistente.setDiasVendaMedia(produtoDadosNovos.getDiasVendaMedia());
            produtoExistente.setDtFab(produtoDadosNovos.getDtFab());
            produtoExistente.setDtVal(produtoDadosNovos.getDtVal());
            produtoExistente.setLote(produtoDadosNovos.getLote());
            produtoExistente.setConteudo(produtoDadosNovos.getConteudo());
            produtoExistente.setSetorExpedicao(produtoDadosNovos.getSetorExpedicao());
            produtoExistente.setSetorProducao(produtoDadosNovos.getSetorProducao());
            produtoExistente.setQtPorConvidado(produtoDadosNovos.getQtPorConvidado());
            produtoExistente.setQtPax(produtoDadosNovos.getQtPax());
            produtoExistente.setQtPorPax(produtoDadosNovos.getQtPorPax());
            produtoExistente.setPax(produtoDadosNovos.getPax());
            produtoExistente.setQtdeAtacado(produtoDadosNovos.getQtdeAtacado());
            produtoExistente.setQuantAtacado2(produtoDadosNovos.getQuantAtacado2());
            produtoExistente.setQuantAtacado3(produtoDadosNovos.getQuantAtacado3());
            produtoExistente.setQuantAtacado4(produtoDadosNovos.getQuantAtacado4());
            produtoExistente.setQuantAtacado5(produtoDadosNovos.getQuantAtacado5());
            produtoExistente.setFatorMultiploSaida(produtoDadosNovos.getFatorMultiploSaida());
            produtoExistente.setIdEditora(produtoDadosNovos.getIdEditora());
            produtoExistente.setAutor(produtoDadosNovos.getAutor());
            produtoExistente.setIdEstProducao(produtoDadosNovos.getIdEstProducao());
            produtoExistente.setOrdemMobile(produtoDadosNovos.getOrdemMobile());
            produtoExistente.setVisivelMobile(produtoDadosNovos.getVisivelMobile());
            produtoExistente.setControlaLote(produtoDadosNovos.getControlaLote());
            produtoExistente.setQuantParaBonificacao(produtoDadosNovos.getQuantParaBonificacao());
            produtoExistente.setQuantBonificada(produtoDadosNovos.getQuantBonificada());
            produtoExistente.setAgregadoIcmsAntecipacao(produtoDadosNovos.getAgregadoIcmsAntecipacao());
            produtoExistente.setHabilitaCalcAtecipacao(produtoDadosNovos.getHabilitaCalcAtecipacao());
            produtoExistente.setIndEscala(produtoDadosNovos.getIndEscala());
            produtoExistente.setCnpjFab(produtoDadosNovos.getCnpjFab());
            produtoExistente.setGlpDerivado(produtoDadosNovos.getGlpDerivado());
            produtoExistente.setGlgnn(produtoDadosNovos.getGlgnn());
            produtoExistente.setGlgni(produtoDadosNovos.getGlgni());
            produtoExistente.setValorPartida(produtoDadosNovos.getValorPartida());
            produtoExistente.setIpiSaida(produtoDadosNovos.getIpiSaida());
            produtoExistente.setFormaCadastro(produtoDadosNovos.getFormaCadastro());
            produtoExistente.setHoraCadastro(produtoDadosNovos.getHoraCadastro());
            produtoExistente.setPainelCozinha(produtoDadosNovos.getPainelCozinha());
            produtoExistente.setTurnoProducao(produtoDadosNovos.getTurnoProducao());
            produtoExistente.setCstIpiSaida(produtoDadosNovos.getCstIpiSaida());
            produtoExistente.setCstIpiEntrada(produtoDadosNovos.getCstIpiEntrada());
            produtoExistente.setPercDescontoAuto(produtoDadosNovos.getPercDescontoAuto());
            produtoExistente.setSeg(produtoDadosNovos.getSeg());
            produtoExistente.setTerca(produtoDadosNovos.getTerca());
            produtoExistente.setQuarta(produtoDadosNovos.getQuarta());
            produtoExistente.setQuinta(produtoDadosNovos.getQuinta());
            produtoExistente.setSexta(produtoDadosNovos.getSexta());
            produtoExistente.setSabado(produtoDadosNovos.getSabado());
            produtoExistente.setDomingo(produtoDadosNovos.getDomingo());
            produtoExistente.setCustoCompra1(produtoDadosNovos.getCustoCompra1());
            produtoExistente.setIss(produtoDadosNovos.getIss());
            produtoExistente.setCnaeServico(produtoDadosNovos.getCnaeServico());
            produtoExistente.setCodigoServico(produtoDadosNovos.getCodigoServico());
            produtoExistente.setDtNfUltimaEntrada(produtoDadosNovos.getDtNfUltimaEntrada());
            produtoExistente.setDtNfUltimoLancamento(produtoDadosNovos.getDtNfUltimoLancamento());
            produtoExistente.setIntegracaoSite(produtoDadosNovos.getIntegracaoSite());
            produtoExistente.setIgnorarReferencia(produtoDadosNovos.getIgnorarReferencia());
            produtoExistente.setForaDeLinha(produtoDadosNovos.getForaDeLinha());
            produtoExistente.setIdLinha(produtoDadosNovos.getIdLinha());
            produtoExistente.setReceita1(produtoDadosNovos.getReceita1());
            produtoExistente.setReceita2(produtoDadosNovos.getReceita2());
            produtoExistente.setReceita3(produtoDadosNovos.getReceita3());
            produtoExistente.setReceita4(produtoDadosNovos.getReceita4());
            produtoExistente.setReceita5(produtoDadosNovos.getReceita5());
            produtoExistente.setProdNuvem(produtoDadosNovos.getProdNuvem());
            produtoExistente.setIdLocalizacao(produtoDadosNovos.getIdLocalizacao());
            produtoExistente.setIdEnderecoLoja(produtoDadosNovos.getIdEnderecoLoja());
            produtoExistente.setIdEnderecoDeposito(produtoDadosNovos.getIdEnderecoDeposito());
            produtoExistente.setConfConveniencia(produtoDadosNovos.getConfConveniencia());
            produtoExistente.setCodBenefCstIcms(produtoDadosNovos.getCodBenefCstIcms());
            produtoExistente.setMotBenefCstIcms(produtoDadosNovos.getMotBenefCstIcms());
            produtoExistente.setPerBenefCstIcms(produtoDadosNovos.getPerBenefCstIcms());
            produtoExistente.setAliqBenefCstIcms(produtoDadosNovos.getAliqBenefCstIcms());
            produtoExistente.setTara(produtoDadosNovos.getTara());
            produtoExistente.setCor(produtoDadosNovos.getCor());
            produtoExistente.setMarca(produtoDadosNovos.getMarca());
            produtoExistente.setMetaChave(produtoDadosNovos.getMetaChave());
            produtoExistente.setMetaDescricao(produtoDadosNovos.getMetaDescricao());
            produtoExistente.setMetaTitulo(produtoDadosNovos.getMetaTitulo());
            produtoExistente.setNomeAmigavel(produtoDadosNovos.getNomeAmigavel());


            return produtoRepository.save(produtoExistente);
        }).orElse(null);
    }

    /**
     * Realiza a exclusão lógica de um produto, definindo o campo 'eliminado' como true.
     * Além disso, registra a data e o usuário que realizou a eliminação (simulado).
     * @param id O ID do produto a ser logicamente excluído.
     * @return true se o produto foi marcado como eliminado com sucesso, false caso contrário.
     */
    public boolean excluirProduto(Integer id) {
        return produtoRepository.findById(id).map(produtoExistente -> {
            produtoExistente.setEliminado(true); // Marca como eliminado
            produtoExistente.setDtEliminacao(LocalDate.now()); // Registra a data
            // produtoExistente.setEliminadoPor(idDoUsuarioLogado); // Em um sistema real, você pegaria o ID do usuário logado
            produtoRepository.save(produtoExistente);
            return true;
        }).orElse(false);
    }
}