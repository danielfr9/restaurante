
package Restaurante.bl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Daniel
 */
public class ItemServicio {    
    public ArrayList<Item> getMenuActivos(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Item.class);
        query.add(Restrictions.eq("activo", true));
        List<Item> resultado = query.list();
        
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
    }
    
    public ArrayList<Item> getMenu(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Item.class);
        List<Item> resultado = query.list();
        
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
    }
    
    public ArrayList<Item> getMenu(String buscar){   
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Item.class);
        
        Criterion nom = Restrictions.like("nombre", buscar, MatchMode.ANYWHERE);
        Criterion des = Restrictions.like("descripcion", buscar, MatchMode.ANYWHERE);
        LogicalExpression orExp = Restrictions.or(nom, des);
        query.add(orExp);
        
        List<Item> resultado = query.list();
        
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
    }
    
    public String guardar(Item item) {     
        String resultado = validarItem(item);

        if (resultado.equals("")){
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            
            try{
                session.saveOrUpdate(item);
                
                tx.commit();
                
            } catch (Exception e){
                tx.rollback();
                return e.getMessage();
            } finally {
                session.close();
            }
            
            return "";
        }
        return resultado;
    }
    
    public void eliminar(Item item){
        Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            
            try{
                session.delete(item);
                
                tx.commit();
                
            } catch (Exception e){
                tx.rollback();
                System.out.println(e.getMessage());                      
            } finally {
                session.close();
            }
    }
    
    public Item clonar(Item item){
        Item itemClonado = new Item();
        
        itemClonado.setId(item.getId());
        itemClonado.setNombre(item.getNombre());
        itemClonado.setDescripcion(item.getDescripcion());
        itemClonado.setCategoria(item.getCategoria());
        itemClonado.setTamaño(item.getTamaño());
        itemClonado.setExistencia(item.getExistencia());
        itemClonado.setPrecio(item.getPrecio());
        itemClonado.setActivo(item.getActivo());
        itemClonado.setImagen(item.getImagen());
        return itemClonado;
    }

    private String validarItem(Item item) {
        if(item.getId().equals("")){
            return "Ingrese ID";
        }
        if (item.getNombre()== null || item.getNombre().equals("")){
            return "Ingrese Nombre";
        }
        if (item.getDescripcion() == null || item.getDescripcion().equals("")){
            return "Ingrese Descripcion";
        }
        if(item.getCategoria() == null || item.getCategoria().equals("")){
            return "Ingrese Categoria";
        }
        if(item.getTamaño() == null || item.getTamaño().equals("")){
            return "Ingrese Tamaño";
        }
        if(item.getPrecio() <= 0){
            return "El precio debe ser mayor que 0";
        }
        return "";
    }
}
