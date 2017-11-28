package br.cnec.fcsl.persistencia;

import br.cnec.fcsl.entidade.Concessionaria;
import br.cnec.fcsl.entidade.Eletronico;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@RequestScoped
public class EletronicoDao {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public List<Eletronico> listar() {
        return em.createQuery("from Eletronico e").getResultList();
    }
    
    /* Lista Tarifas REST*/
    public List<Concessionaria> listConcessionaria(String nome) {
        String sql = "from Concessionaria c ";

        if (nome != null && !nome.trim().isEmpty()) {
            sql += "where lower(c.tarifakwh) like :tarifakwh ";
        }
        sql += "order by c.tarifakwh";
        Query query = em.createQuery(sql);

        if (nome != null && !nome.trim().isEmpty()){
            query.setParameter("tarifakwh", "%" + nome.toLowerCase() + "%");
        }
        
        return query.getResultList();
    }
    
    /* Buscar Tarifa Concessionaria */
    public double buscarTarifa(int id){
        Concessionaria c;
        c = em.find(Concessionaria.class, id);
            return c.getTarifakwh();
    }
    
    /*Buscar distribuidora */
    public String buscarDistribuidora(int id){
        Concessionaria c;
        c = em.find(Concessionaria.class, id);
            return c.getDistribuidora();
    }
    
    public List<Concessionaria> listarConcessionaria(){
        return em.createQuery("from Concessionaria c").getResultList();
    }

    @Transactional
    public void salvar(Eletronico e) {
        em.merge(e);
    }

    public Eletronico recuperar(Long id) {
        return em.find(Eletronico.class, id);
    }

    @Transactional
    public void excluir(Long id) {
        Eletronico e = recuperar(id);
        em.remove(e);
    }
}
