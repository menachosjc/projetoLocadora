/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import model.bean.Usuario;

import javax.persistence.*;

/**
 *
 * @author Administrator
 */
public class UsuarioDAO {
    
    EntityManager em = new ConnectionFactory().getConnection();
    public UsuarioDAO() {
        //Conexão toda vez que a DAO for chamada

    }

    public Usuario cadastrarUsuario(Usuario usuario) {
      
        try {

            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

        } catch (Exception e) {
            //Se der algo errado, então desfazer
            System.err.println(e);
            em.getTransaction().rollback();

        } finally {
            //Fechar conexão
            em.close();
        }

        return usuario;
    }
    
    public Usuario loginUsuario(String email, String senha){
        try {
            Usuario usuario = (Usuario) em.createQuery("select u from Usuario u where u.email = :email and u.senha = :senha").setParameter("email", email).setParameter("senha", senha).getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
       
    }
    
    public Usuario procurarEmail(String email) {
     

        Query query = null;
        Usuario usuario = null;

        try {
            query = em.createQuery("select u from Usuario u where u.email = :email");
            query.setParameter("email", email);
            usuario = (Usuario) query.getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }

        return usuario;

    }

}
