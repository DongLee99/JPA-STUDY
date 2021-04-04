package jpabook.jpashop.repository;


import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) { //저장하기 전까지는 id 값이 없음 = 새로 생성하는 상태라는것
            em.persist(item);
        } else {
            em.merge(item); //이미 db에 등록된값을 가져와 업데이트와 유사하다고 보면된다
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select  i from Item i", Item.class)
                .getResultList();
    }
}
