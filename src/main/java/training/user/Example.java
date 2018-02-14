package training.user;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.PropertyProjection;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class Example {

    private final EntityManager entityManager;

    public Example(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * CriteriaQuery<(1)Integer> criteria = builder.createQuery( Integer.class );
     Root<Person> personRoot = criteria.from( Person.class );
     criteria.selec(2)t( personRoot.get( Person_.age ) );
     criteria.where( builder.equal( personRoot.get( Person_.eyeColor ), "brown" ) );
     List<Integer> (1)ages = em.createQuery( criteria ).getResultList();
     */

    @Transactional
    public void findIdBySth(){
//        SessionFactory sessionFactory = entityManager.unwrap(SessionFactory.class);
//        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
//        PropertyProjection id = Projections.property("id");
//        PropertyProjection name = Projections.property("name");
//        criteria.setProjection(Projections.projectionList().add(id).add(name));
//        List resultList = criteria.list();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery query = criteriaBuilder.createQuery();
        Root<User> user = query.from(User.class);
        List resultList = entityManager.createQuery(query).getResultList();
        System.out.println(resultList);
    }
}
