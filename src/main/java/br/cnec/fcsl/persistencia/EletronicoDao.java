package br.cnec.fcsl.persistencia;

import br.cnec.fcsl.entidade.Eletronico;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestScoped
public class EletronicoDao {

    @PersistenceContext(unitName = "pu")
    private EntityManager em;

    public List<Eletronico> listar() {
        return em.createQuery("from Eletronico e").getResultList();
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
