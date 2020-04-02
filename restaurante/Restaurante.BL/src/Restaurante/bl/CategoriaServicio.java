
package Restaurante.bl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Daniel
 */
public class CategoriaServicio {   
    public ArrayList<Categoria> getListaDeCategoria() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Transaction tx = session.beginTransaction();
        
        Criteria query = session.createCriteria(Categoria.class);
        List<Categoria> resultado = query.list();
        
        tx.commit();
        session.close();
        
        return new ArrayList<>(resultado);
        
    }
}
