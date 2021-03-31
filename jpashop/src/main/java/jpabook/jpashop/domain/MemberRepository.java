package jpabook.jpashop.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository //엔티티를 찾아줌 dao 랑 비슷
public class MemberRepository {
    // entity 메니저 설정을 스프링 부트가 알아서 해줌
    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); //CQRS
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
