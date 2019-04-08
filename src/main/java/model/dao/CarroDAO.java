/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import model.bean.Carro;
import model.bean.Usuario;
import java.sql.PreparedStatement;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Criteria;

/**
 *
 * @author Menacho
 */
public class CarroDAO {
    
    EntityManager em = new ConnectionFactory().getConnection();
    public CarroDAO() {
    }

    public Carro cadastrarCarro(Carro carro) {
        

        try {

            em.getTransaction().begin();
            em.persist(carro);
            em.getTransaction().commit();

        } catch (Exception e) {
            //Se der algo errado, então desfazer
            System.err.println(e);
            em.getTransaction().rollback();

        } finally {
            //Fechar conexão
            em.close();
        }

        return carro;

    }

  
    public List<Carro> listarCarros() {
        EntityManager em = new ConnectionFactory().getConnection();
        
        CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
        criteria.select(criteria.from(Carro.class));
    

        List<Carro> lista = em.createQuery(criteria).getResultList();

        return lista;

    }

    public Carro deletarCarro(int id) {

        EntityManager em = new ConnectionFactory().getConnection();
        Carro carro = new Carro();

        try {
            carro = em.find(Carro.class, id);
            em.getTransaction().begin();
            em.remove(carro);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return carro;

    }
    
    public Carro listarDadosCarro(int id) {

        EntityManager em = new ConnectionFactory().getConnection();
        Carro carro = new Carro();

        try {
            carro = em.find(Carro.class, id);
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return carro;

    }
    
    public Carro updateDadosCarro(int id,String ano, String capacidade,String combustivel, String cor, String quilometragem, String marca,String modelo,String placa){
        EntityManager em = new ConnectionFactory().getConnection();
        Carro carro = new Carro();
        
         try {
            carro = em.find(Carro.class, id);
            em.getTransaction().begin();
            carro.setAno(ano);
            carro.setCapacidade(capacidade);
            carro.setCombustivel(combustivel);
            carro.setCor(cor);
            carro.setQuilometragem(quilometragem);
            carro.setMarca(marca);
            carro.setModelo(modelo);
            carro.setPlaca(placa);
         
            
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        return carro;
    }


}
