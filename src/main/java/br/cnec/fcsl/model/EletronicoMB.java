package br.cnec.fcsl.model;

import br.cnec.fcsl.entidade.Concessionaria;
import br.cnec.fcsl.report.Relatorio;
import br.cnec.fcsl.entidade.Eletronico;
import br.cnec.fcsl.entidade.Usuario;
import br.cnec.fcsl.persistencia.EletronicoDao;

import java.io.Serializable;
import java.text.DecimalFormat;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "eletronicoMB")
@ViewScoped
public class EletronicoMB implements Serializable {

    @Inject
    private EletronicoDao dao;
    private Eletronico eletronico;
    private boolean comparar;
    private boolean visualizar;
    private Double consumo;
    private int contador;
    private Usuario user;
    
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
   

    @PostConstruct
    public void init() {
        visualizar = false;
    }

    public void verificarCheckBox() {
        if (comparar) {
            contador++;
        }
    }
    
  

    public String calcularConsumo(Eletronico eletronico) {
        DecimalFormat df = new DecimalFormat("#,###.00");
        consumo = ((eletronico.getPotencia() / 1000) * eletronico.getHoras() * eletronico.getDias());
        return df.format(consumo);
    }

    public void salvar() {
        dao.salvar(eletronico);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro salvo com sucesso!"));
        eletronico = null;
    }

    public void novoEletronico() {
        eletronico = new Eletronico();
    }

    public void compararEletronico() {
        comparar = true;
    }

    public void editarEletronico() {
        comparar = false;
    }

    public void excluirEletronico() {
        dao.excluir(eletronico.getId());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Registro excluido com sucesso!"));
        eletronico = null;
    }

    public Eletronico getEletronico() {
        return eletronico;
    }

    public void setEletronico(Eletronico eletronico) {
        this.eletronico = eletronico;
    }

    public List<Eletronico> getListarEletronico() {
        return dao.listar();
    }

    public EletronicoDao getDao() {
        return dao;
    }

    public void setDao(EletronicoDao dao) {
        this.dao = dao;
    }

    public boolean isComparar() {
        return comparar;
    }

    public boolean isVisualizar() {
        return visualizar;
    }

    public void setVisualizar(boolean visualizar) {
        this.visualizar = visualizar;
    }

    public void setComparar(boolean comparar) {
        this.comparar = comparar;
    }

    public Double getConsumo() {
        return consumo;
    }

    public void setConsumo(Double consumo) {
        this.consumo = consumo;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void gerarRelatorio() {
        Relatorio r = new Relatorio();

     
            r.gerar(dao.listar());
       

    }
}
