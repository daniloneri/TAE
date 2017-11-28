/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cnec.fcsl.rest;

import br.cnec.fcsl.entidade.Concessionaria;
import br.cnec.fcsl.persistencia.EletronicoDao;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("tarifas")
public class TarifasRest {

    @Inject
    private EletronicoDao dao;
    
    public TarifasRest() {
    }

   @GET
   public List<Concessionaria> listarTarifas(){
       return dao.listConcessionaria(null);
   }
}
