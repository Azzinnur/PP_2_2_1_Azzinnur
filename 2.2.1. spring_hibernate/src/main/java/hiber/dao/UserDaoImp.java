package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public List<User> listCarHolders(String model, int series) {
      String hql = "SELECT s FROM User s LEFT JOIN FETCH s.car c WHERE c.model=:searchedModel AND c.series =:searchedSeries";
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery(hql, User.class)
              .setParameter("searchedModel", model).setParameter("searchedSeries", series);
      return query.getResultList();
   }

}
