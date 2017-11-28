
package br.cnec.fcsl.model;

import br.cnec.fcsl.entidade.Concessionaria;
import br.cnec.fcsl.entidade.Eletronico;
import br.cnec.fcsl.entidade.Usuario;
import br.cnec.fcsl.persistencia.EletronicoDao;
import java.text.DecimalFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value = "conceMB")
@ViewScoped
public class ConcessionariaMB{
    
   private Concessionaria conce;
   private Usuario user;
   private EletronicoMB eletro;
   private String tarifatotal;
   private boolean flag;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getTarifatotal() {
        return tarifatotal;
    }

    public void setTarifatotal(String tarifatotal) {
        this.tarifatotal = tarifatotal;
    }
   
   

    public EletronicoMB getEletro() {
        return eletro;
    }

    public void setEletro(EletronicoMB eletro) {
        this.eletro = eletro;
    }
   @Inject
   EletronicoDao dao;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
   
   
   @PostConstruct
   public void init(){
       conce = new Concessionaria();
       user = new Usuario();
       this.flag = true;
   }
   

    public Concessionaria getConce() {
        return conce;
    }

    public void setConce(Concessionaria conce) {
        this.conce = conce;
    }

    public EletronicoDao getDao() {
        return dao;
    }

    public void setDao(EletronicoDao dao) {
        this.dao = dao;
    }

    
    
    public List<Concessionaria> listarConcessionaria(){
        return dao.listarConcessionaria();
    }
    
    public void novoUsuario(){
         System.out.println(user.getConceid());
        System.out.println(user.getNome());
         System.out.println(user.getEndereco());
    }
    
    public void calcValor(){
        double calc = 0;
        DecimalFormat df = new DecimalFormat("#,###.00");
        double tarifa = dao.buscarTarifa(user.getConceid());
        for(Eletronico e : dao.listar()){
           calc += ((e.getPotencia() / 1000) * e.getHoras() * e.getDias());
        }
       calc *= tarifa;
       this.tarifatotal = "R$" + df.format(calc);
        this.flag = false;
    }
 
}
