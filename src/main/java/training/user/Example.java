//package training.user;
//
//import org.hibernate.Criteria;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//import javax.persistence.criteria.Selection;
//
//@Component
//public class Example {
//
//    private final EntityManager entityManager;
//
//    public Example(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    /**
//     * CriteriaQuery<(1)Integer> criteria = builder.createQuery( Integer.class );
//     Root<Person> personRoot = criteria.from( Person.class );
//     criteria.selec(2)t( personRoot.get( Person_.age ) );
//     criteria.where( builder.equal( personRoot.get( Person_.eyeColor ), "brown" ) );
//     List<Integer> (1)ages = em.createQuery( criteria ).getResultList();
//     */
//
//    Long findIdBySth(String sth){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
//        Root<User> from = query.from(User.class);
//        criteriaBuilder.f
//    }
//}
