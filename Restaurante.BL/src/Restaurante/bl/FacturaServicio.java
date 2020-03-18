/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Restaurante.bl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Daniel
 */
public class FacturaServicio {
    
    //No verifica si la existencia es 0
    public void guardar(Factura factura) {    
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
          
        try{
            session.saveOrUpdate(factura);
            
            for(FacturaDetalle detalle: factura.getFacturaDetalle()){
                Integer id = detalle.getItem().getId();
                
                Criteria query = session.createCriteria(Item.class);
                query.add(Restrictions.eq("id", id));
                query.setMaxResults(1);
                
                Item itemExistente = (Item) query.uniqueResult();
                
                Integer nuevaExistencia = 
                        itemExistente.getExistencia() - detalle.getCantidad();
                
                itemExistente.setExistencia(nuevaExistencia);
                
                session.saveOrUpdate(itemExistente);
            }
            
            tx.commit();
                
        } catch (Exception e){
            tx.rollback();
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}
