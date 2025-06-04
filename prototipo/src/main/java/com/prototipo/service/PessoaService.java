package com.prototipo.service;

import com.prototipo.model.Pessoa;
import com.prototipo.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate; // Importe LocalDate
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        if (pessoa.getNome() == null || pessoa.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da pessoa é obrigatório.");
        }
        // Exemplo: definir data de cadastro se for um novo registro
        if (pessoa.getId() == null) {
            pessoa.setDtCadastro(LocalDate.now());
            if (pessoa.getAtivo() == null) { // Garante valor padrão se não informado
                pessoa.setAtivo(false); // Default 'N'::bpchar do script
            }
            if (pessoa.getFornecedor() == null) pessoa.setFornecedor(false);
            if (pessoa.getTransportadora() == null) pessoa.setTransportadora(false);
            if (pessoa.getCliente() == null) pessoa.setCliente(false);
            if (pessoa.getEliminado() == null) pessoa.setEliminado(false);
            if (pessoa.getAtualizaPrecoCompra() == null) pessoa.setAtualizaPrecoCompra(false);
            if (pessoa.getAceitaProdutoDiferente() == null) pessoa.setAceitaProdutoDiferente(false);
            if (pessoa.getInscritoNoSimples() == null) pessoa.setInscritoNoSimples(false);
            if (pessoa.getTemIpi() == null) pessoa.setTemIpi(false);
            if (pessoa.getTemIcmsSubst() == null) pessoa.setTemIcmsSubst(false);
            if (pessoa.getRevenda() == null) pessoa.setRevenda(false);
            if (pessoa.getComissionaVenda() == null) pessoa.setComissionaVenda(false);
            if (pessoa.getConsumidorFinal() == null) pessoa.setConsumidorFinal(false);
            if (pessoa.getCorrentista() == null) pessoa.setCorrentista(false);
            if (pessoa.getVigiado() == null) pessoa.setVigiado(false);
            if (pessoa.getCortado() == null) pessoa.setCortado(false);
            if (pessoa.getConsignacao() == null) pessoa.setConsignacao(false);
            if (pessoa.getEstornoIcms() == null) pessoa.setEstornoIcms(false);
            if (pessoa.getAtualizaFornecedor() == null) pessoa.setAtualizaFornecedor(false);
            if (pessoa.getProtestado() == null) pessoa.setProtestado(false);
            if (pessoa.getAptoNfe() == null) pessoa.setAptoNfe(false);
            if (pessoa.getNegociando() == null) pessoa.setNegociando(false);
            if (pessoa.getDesabilitaFatores() == null) pessoa.setDesabilitaFatores(false);
            if (pessoa.getEstrangeiro() == null) pessoa.setEstrangeiro(false);
            if (pessoa.getProdutorRural() == null) pessoa.setProdutorRural(false);
            if (pessoa.getOrigemMobile() == null) pessoa.setOrigemMobile(false);
            if (pessoa.getSincIncPend() == null) pessoa.setSincIncPend(false);
            if (pessoa.getSincEdiPend() == null) pessoa.setSincEdiPend(false);
            // Default para ncInscEstadual
            if (pessoa.getNcInscEstadual() == null || pessoa.getNcInscEstadual().trim().isEmpty()) {
                pessoa.setNcInscEstadual("S");
            }
        }
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPorId(Integer id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> listarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa atualizarPessoa(Integer id, Pessoa pessoaDadosNovos) {
        return pessoaRepository.findById(id).map(pessoaExistente -> {
            // Atualize cada campo que pode ser alterado
            pessoaExistente.setAtivo(pessoaDadosNovos.getAtivo());
            pessoaExistente.setTipoPessoa(pessoaDadosNovos.getTipoPessoa());
            pessoaExistente.setFornecedor(pessoaDadosNovos.getFornecedor());
            pessoaExistente.setTransportadora(pessoaDadosNovos.getTransportadora());
            pessoaExistente.setCliente(pessoaDadosNovos.getCliente());
            pessoaExistente.setNome(pessoaDadosNovos.getNome());
            pessoaExistente.setNomeFantasia(pessoaDadosNovos.getNomeFantasia());
            pessoaExistente.setCnpjCpf(pessoaDadosNovos.getCnpjCpf());
            pessoaExistente.setInscEstadual(pessoaDadosNovos.getInscEstadual());
            pessoaExistente.setInscMunicipal(pessoaDadosNovos.getInscMunicipal());
            pessoaExistente.setRg(pessoaDadosNovos.getRg());
            pessoaExistente.setOrgaoEmissor(pessoaDadosNovos.getOrgaoEmissor());
            pessoaExistente.setCep(pessoaDadosNovos.getCep());
            pessoaExistente.setRua(pessoaDadosNovos.getRua());
            pessoaExistente.setNumero(pessoaDadosNovos.getNumero());
            pessoaExistente.setComplemento(pessoaDadosNovos.getComplemento());
            pessoaExistente.setBairro(pessoaDadosNovos.getBairro());
            pessoaExistente.setCidade(pessoaDadosNovos.getCidade());
            pessoaExistente.setUf(pessoaDadosNovos.getUf());
            pessoaExistente.setPais(pessoaDadosNovos.getPais());
            pessoaExistente.setDdd(pessoaDadosNovos.getDdd());
            pessoaExistente.setFone(pessoaDadosNovos.getFone());
            pessoaExistente.setFone2(pessoaDadosNovos.getFone2());
            pessoaExistente.setFax(pessoaDadosNovos.getFax());
            pessoaExistente.setContato(pessoaDadosNovos.getContato());
            pessoaExistente.setCelular(pessoaDadosNovos.getCelular());
            pessoaExistente.setEmail(pessoaDadosNovos.getEmail());
            pessoaExistente.setSite(pessoaDadosNovos.getSite());
            pessoaExistente.setObservacoes(pessoaDadosNovos.getObservacoes());
            // dtCadastro normalmente não é atualizado.
            // pessoaExistente.setCadastradoPor(pessoaDadosNovos.getCadastradoPor());
            pessoaExistente.setEliminado(pessoaDadosNovos.getEliminado());
            pessoaExistente.setEliminadoPor(pessoaDadosNovos.getEliminadoPor());
            pessoaExistente.setDtEliminacao(pessoaDadosNovos.getDtEliminacao());
            pessoaExistente.setDtUltimaCompra(pessoaDadosNovos.getDtUltimaCompra());
            pessoaExistente.setAtualizaPrecoCompra(pessoaDadosNovos.getAtualizaPrecoCompra());
            pessoaExistente.setAceitaProdutoDiferente(pessoaDadosNovos.getAceitaProdutoDiferente());
            pessoaExistente.setInscritoNoSimples(pessoaDadosNovos.getInscritoNoSimples());
            pessoaExistente.setTemIpi(pessoaDadosNovos.getTemIpi());
            pessoaExistente.setTemIcmsSubst(pessoaDadosNovos.getTemIcmsSubst());
            pessoaExistente.setIdLinha(pessoaDadosNovos.getIdLinha());
            pessoaExistente.setCfop(pessoaDadosNovos.getCfop());
            pessoaExistente.setIdDespesa(pessoaDadosNovos.getIdDespesa());
            pessoaExistente.setTipoFrete(pessoaDadosNovos.getTipoFrete());
            pessoaExistente.setPrazo1(pessoaDadosNovos.getPrazo1());
            pessoaExistente.setPrazo2(pessoaDadosNovos.getPrazo2());
            pessoaExistente.setPrazo3(pessoaDadosNovos.getPrazo3());
            pessoaExistente.setPrazo4(pessoaDadosNovos.getPrazo4());
            pessoaExistente.setPrazo5(pessoaDadosNovos.getPrazo5());
            pessoaExistente.setPrazo6(pessoaDadosNovos.getPrazo6());
            pessoaExistente.setIdComprador(pessoaDadosNovos.getIdComprador());
            pessoaExistente.setFormaPgto(pessoaDadosNovos.getFormaPgto());
            pessoaExistente.setDiaVisita(pessoaDadosNovos.getDiaVisita());
            pessoaExistente.setHoraVisita(pessoaDadosNovos.getHoraVisita());
            pessoaExistente.setPrazoEntrega(pessoaDadosNovos.getPrazoEntrega());
            pessoaExistente.setFrequenciaCompra(pessoaDadosNovos.getFrequenciaCompra());
            pessoaExistente.setDiasDeEstoqueA(pessoaDadosNovos.getDiasDeEstoqueA());
            pessoaExistente.setDiasDeEstoqueB(pessoaDadosNovos.getDiasDeEstoqueB());
            pessoaExistente.setDiasDeEstoqueC(pessoaDadosNovos.getDiasDeEstoqueC());
            pessoaExistente.setDescontoVarejo(pessoaDadosNovos.getDescontoVarejo());
            pessoaExistente.setDescontoAtacado(pessoaDadosNovos.getDescontoAtacado());
            pessoaExistente.setDescontoGerente(pessoaDadosNovos.getDescontoGerente());
            pessoaExistente.setAcompanhamento(pessoaDadosNovos.getAcompanhamento());
            pessoaExistente.setSexo(pessoaDadosNovos.getSexo());
            pessoaExistente.setNomePai(pessoaDadosNovos.getNomePai());
            pessoaExistente.setNomeMae(pessoaDadosNovos.getNomeMae());
            pessoaExistente.setNaturalidade(pessoaDadosNovos.getNaturalidade());
            pessoaExistente.setFirmaOndeTrabalha(pessoaDadosNovos.getFirmaOndeTrabalha());
            pessoaExistente.setAdmissao(pessoaDadosNovos.getAdmissao());
            pessoaExistente.setRenda(pessoaDadosNovos.getRenda());
            pessoaExistente.setNomeConjuge(pessoaDadosNovos.getNomeConjuge());
            pessoaExistente.setCpfConjuge(pessoaDadosNovos.getCpfConjuge());
            pessoaExistente.setRevenda(pessoaDadosNovos.getRevenda());
            pessoaExistente.setNomeSocio1(pessoaDadosNovos.getNomeSocio1());
            pessoaExistente.setCpfSocio1(pessoaDadosNovos.getCpfSocio1());
            pessoaExistente.setRgSocio1(pessoaDadosNovos.getRgSocio1());
            pessoaExistente.setPercSocio1(pessoaDadosNovos.getPercSocio1());
            pessoaExistente.setNomeSocio2(pessoaDadosNovos.getNomeSocio2());
            pessoaExistente.setCpfSocio2(pessoaDadosNovos.getCpfSocio2());
            pessoaExistente.setRgSocio2(pessoaDadosNovos.getRgSocio2());
            pessoaExistente.setPercSocio2(pessoaDadosNovos.getPercSocio2());
            pessoaExistente.setNomeSocio3(pessoaDadosNovos.getNomeSocio3());
            pessoaExistente.setCpfSocio3(pessoaDadosNovos.getCpfSocio3());
            pessoaExistente.setRgSocio3(pessoaDadosNovos.getRgSocio3());
            pessoaExistente.setPercSocio3(pessoaDadosNovos.getPercSocio3());
            pessoaExistente.setLimiteCredito(pessoaDadosNovos.getLimiteCredito());
            pessoaExistente.setDtNascimento(pessoaDadosNovos.getDtNascimento());
            pessoaExistente.setDescontoPadrao(pessoaDadosNovos.getDescontoPadrao());
            pessoaExistente.setPrazoMaximo(pessoaDadosNovos.getPrazoMaximo());
            pessoaExistente.setProtestarApos(pessoaDadosNovos.getProtestarApos());
            pessoaExistente.setIdCategoria(pessoaDadosNovos.getIdCategoria());
            pessoaExistente.setIdProfissao(pessoaDadosNovos.getIdProfissao());
            pessoaExistente.setIdVendedor(pessoaDadosNovos.getIdVendedor());
            pessoaExistente.setIdTipoCliente(pessoaDadosNovos.getIdTipoCliente());
            pessoaExistente.setMulta(pessoaDadosNovos.getMulta());
            pessoaExistente.setJuros(pessoaDadosNovos.getJuros());
            pessoaExistente.setPortador(pessoaDadosNovos.getPortador());
            pessoaExistente.setComissionaVenda(pessoaDadosNovos.getComissionaVenda());
            pessoaExistente.setConsumidorFinal(pessoaDadosNovos.getConsumidorFinal());
            pessoaExistente.setReferenciasComerciais(pessoaDadosNovos.getReferenciasComerciais());
            pessoaExistente.setReferenciasBancarias(pessoaDadosNovos.getReferenciasBancarias());
            pessoaExistente.setCobCep(pessoaDadosNovos.getCobCep());
            pessoaExistente.setCobRua(pessoaDadosNovos.getCobRua());
            pessoaExistente.setCobNumero(pessoaDadosNovos.getCobNumero());
            pessoaExistente.setCobComplemento(pessoaDadosNovos.getCobComplemento());
            pessoaExistente.setCobBairro(pessoaDadosNovos.getCobBairro());
            pessoaExistente.setCobCidade(pessoaDadosNovos.getCobCidade());
            pessoaExistente.setCobUf(pessoaDadosNovos.getCobUf());
            pessoaExistente.setCorrentista(pessoaDadosNovos.getCorrentista());
            pessoaExistente.setNumeroCartao(pessoaDadosNovos.getNumeroCartao());
            pessoaExistente.setVigiado(pessoaDadosNovos.getVigiado());
            pessoaExistente.setCortado(pessoaDadosNovos.getCortado());
            pessoaExistente.setPontos(pessoaDadosNovos.getPontos());
            pessoaExistente.setSaldo(pessoaDadosNovos.getSaldo());
            pessoaExistente.setTabela(pessoaDadosNovos.getTabela());
            pessoaExistente.setDtProximaCompra(pessoaDadosNovos.getDtProximaCompra());
            pessoaExistente.setConsignacao(pessoaDadosNovos.getConsignacao());
            pessoaExistente.setDataBase(pessoaDadosNovos.getDataBase());
            pessoaExistente.setEstadoCivil(pessoaDadosNovos.getEstadoCivil());
            pessoaExistente.setCodPais(pessoaDadosNovos.getCodPais());
            pessoaExistente.setCodMun(pessoaDadosNovos.getCodMun());
            pessoaExistente.setSuframa(pessoaDadosNovos.getSuframa());
            pessoaExistente.setCapitalSocial(pessoaDadosNovos.getCapitalSocial());
            pessoaExistente.setPontoReferencia(pessoaDadosNovos.getPontoReferencia());
            pessoaExistente.setPalavraChave(pessoaDadosNovos.getPalavraChave());
            pessoaExistente.setSenha(pessoaDadosNovos.getSenha());
            pessoaExistente.setPrazo7(pessoaDadosNovos.getPrazo7());
            pessoaExistente.setPrazo8(pessoaDadosNovos.getPrazo8());
            pessoaExistente.setPrazoFaturamento(pessoaDadosNovos.getPrazoFaturamento());
            pessoaExistente.setIdRede(pessoaDadosNovos.getIdRede());
            pessoaExistente.setCnae(pessoaDadosNovos.getCnae());
            pessoaExistente.setCelular2(pessoaDadosNovos.getCelular2());
            pessoaExistente.setCelular3(pessoaDadosNovos.getCelular3());
            pessoaExistente.setEmail2(pessoaDadosNovos.getEmail2());
            pessoaExistente.setEmail3(pessoaDadosNovos.getEmail3());
            pessoaExistente.setOrdem(pessoaDadosNovos.getOrdem());
            pessoaExistente.setEstornoIcms(pessoaDadosNovos.getEstornoIcms());
            pessoaExistente.setAtualizaFornecedor(pessoaDadosNovos.getAtualizaFornecedor());
            pessoaExistente.setRepresentante(pessoaDadosNovos.getRepresentante());
            pessoaExistente.setRepresentanteFone(pessoaDadosNovos.getRepresentanteFone());
            pessoaExistente.setRepresentanteEmail(pessoaDadosNovos.getRepresentanteEmail());
            pessoaExistente.setComissao(pessoaDadosNovos.getComissao());
            pessoaExistente.setProtestado(pessoaDadosNovos.getProtestado());
            pessoaExistente.setNcInscEstadual(pessoaDadosNovos.getNcInscEstadual());
            pessoaExistente.setFreqMes1(pessoaDadosNovos.getFreqMes1());
            pessoaExistente.setFreqMes2(pessoaDadosNovos.getFreqMes2());
            pessoaExistente.setFreqMes3(pessoaDadosNovos.getFreqMes3());
            pessoaExistente.setFreqMes4(pessoaDadosNovos.getFreqMes4());
            pessoaExistente.setFreqMes5(pessoaDadosNovos.getFreqMes5());
            pessoaExistente.setFreqMes6(pessoaDadosNovos.getFreqMes6());
            pessoaExistente.setFreqMes7(pessoaDadosNovos.getFreqMes7());
            pessoaExistente.setFreqMes8(pessoaDadosNovos.getFreqMes8());
            pessoaExistente.setFreqMes9(pessoaDadosNovos.getFreqMes9());
            pessoaExistente.setFreqMes10(pessoaDadosNovos.getFreqMes10());
            pessoaExistente.setFreqMes11(pessoaDadosNovos.getFreqMes11());
            pessoaExistente.setFreqMes12(pessoaDadosNovos.getFreqMes12());
            pessoaExistente.setPosMes1(pessoaDadosNovos.getPosMes1());
            pessoaExistente.setPosMes2(pessoaDadosNovos.getPosMes2());
            pessoaExistente.setPosMes3(pessoaDadosNovos.getPosMes3());
            pessoaExistente.setPosMes4(pessoaDadosNovos.getPosMes4());
            pessoaExistente.setPosMes5(pessoaDadosNovos.getPosMes5());
            pessoaExistente.setPosMes6(pessoaDadosNovos.getPosMes6());
            pessoaExistente.setPosMes7(pessoaDadosNovos.getPosMes7());
            pessoaExistente.setPosMes8(pessoaDadosNovos.getPosMes8());
            pessoaExistente.setPosMes9(pessoaDadosNovos.getPosMes9());
            pessoaExistente.setPosMes10(pessoaDadosNovos.getPosMes10());
            pessoaExistente.setPosMes11(pessoaDadosNovos.getPosMes11());
            pessoaExistente.setPosMes12(pessoaDadosNovos.getPosMes12());
            pessoaExistente.setVendaMes1(pessoaDadosNovos.getVendaMes1());
            pessoaExistente.setVendaMes2(pessoaDadosNovos.getVendaMes2());
            pessoaExistente.setVendaMes3(pessoaDadosNovos.getVendaMes3());
            pessoaExistente.setVendaMes4(pessoaDadosNovos.getVendaMes4());
            pessoaExistente.setVendaMes5(pessoaDadosNovos.getVendaMes5());
            pessoaExistente.setVendaMes6(pessoaDadosNovos.getVendaMes6());
            pessoaExistente.setVendaMes7(pessoaDadosNovos.getVendaMes7());
            pessoaExistente.setVendaMes8(pessoaDadosNovos.getVendaMes8());
            pessoaExistente.setVendaMes9(pessoaDadosNovos.getVendaMes9());
            pessoaExistente.setVendaMes10(pessoaDadosNovos.getVendaMes10());
            pessoaExistente.setVendaMes11(pessoaDadosNovos.getVendaMes11());
            pessoaExistente.setVendaMes12(pessoaDadosNovos.getVendaMes12());
            pessoaExistente.setInscInativa(pessoaDadosNovos.getInscInativa());
            pessoaExistente.setAptoNfe(pessoaDadosNovos.getAptoNfe());
            pessoaExistente.setDtUltimaConsultaSefaz(pessoaDadosNovos.getDtUltimaConsultaSefaz());
            pessoaExistente.setNegociando(pessoaDadosNovos.getNegociando());
            pessoaExistente.setNegativarApos(pessoaDadosNovos.getNegativarApos());
            pessoaExistente.setIdFormaPag(pessoaDadosNovos.getIdFormaPag());
            pessoaExistente.setDevolverApos(pessoaDadosNovos.getDevolverApos());
            pessoaExistente.setDesabilitaFatores(pessoaDadosNovos.getDesabilitaFatores());
            pessoaExistente.setFilho1(pessoaDadosNovos.getFilho1());
            pessoaExistente.setNiverFilho1(pessoaDadosNovos.getNiverFilho1());
            pessoaExistente.setFilho2(pessoaDadosNovos.getFilho2());
            pessoaExistente.setNiverFilho2(pessoaDadosNovos.getNiverFilho2());
            pessoaExistente.setFilho3(pessoaDadosNovos.getFilho3());
            pessoaExistente.setNiverFilho3(pessoaDadosNovos.getNiverFilho3());
            pessoaExistente.setFilho4(pessoaDadosNovos.getFilho4());
            pessoaExistente.setNiverFilho4(pessoaDadosNovos.getNiverFilho4());
            pessoaExistente.setFilho5(pessoaDadosNovos.getFilho5());
            pessoaExistente.setNiverFilho5(pessoaDadosNovos.getNiverFilho5());
            pessoaExistente.setNomeComprador(pessoaDadosNovos.getNomeComprador());
            pessoaExistente.setFoneComprador(pessoaDadosNovos.getFoneComprador());
            pessoaExistente.setCelularComprador(pessoaDadosNovos.getCelularComprador());
            pessoaExistente.setObsComprador(pessoaDadosNovos.getObsComprador());
            pessoaExistente.setEstrangeiro(pessoaDadosNovos.getEstrangeiro());
            pessoaExistente.setIdentEstrangeiro(pessoaDadosNovos.getIdentEstrangeiro());
            pessoaExistente.setTipoDocEstrangeiro(pessoaDadosNovos.getTipoDocEstrangeiro());
            pessoaExistente.setProdutorRural(pessoaDadosNovos.getProdutorRural());
            pessoaExistente.setOrigemMobile(pessoaDadosNovos.getOrigemMobile());
            pessoaExistente.setSincIncPend(pessoaDadosNovos.getSincIncPend());
            pessoaExistente.setSincEdiPend(pessoaDadosNovos.getSincEdiPend());
            pessoaExistente.setPrestadorServico(pessoaDadosNovos.getPrestadorServico());
            pessoaExistente.setNfeExportacao(pessoaDadosNovos.getNfeExportacao());
            pessoaExistente.setChaveSincronismo(pessoaDadosNovos.getChaveSincronismo());

            return pessoaRepository.save(pessoaExistente);
        }).orElse(null);
    }

    public boolean excluirPessoa(Integer id) {
        if (pessoaRepository.existsById(id)) {
            // Em um sistema real, você deve verificar se existem dependências
            // (ex: produtos vinculados a este fornecedor) antes de excluir.
            // O FOREIGN KEY ON DELETE RESTRICT no banco já ajuda, mas a camada de serviço
            // pode fornecer mensagens de erro mais amigáveis ao usuário.
            pessoaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}