package jpabook.jpashop.api;


import jdk.vm.ci.meta.Local;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.repository.OrderSimpleQueryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    // one to one 과 many to one 처리

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2() { //절대 엔티티가 아닌 DTO
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        //만약에 order 가 2개 조회 된다

        List<SimpleOrderDto> result = orders.stream()
                .map(SimpleOrderDto::new)
                .collect(Collectors.toList());
        //루프를 돌면서 order내에 getMember, getDeliver 해서 첫번째 조회에 3번의 쿼리(Order, Member, Deliver)
        // 두번째에서는 (Member, Deliver) 따라서 총 5번의 쿼리문이 호출이 된다. (ex> 만약 order이 늘어난다면... 효율 X)
        // N+1 의 문제 첫번째 쿼리로 인해 이후 쿼리가 추가로 생성될때를 이 경우라고 한다.
        // 일단은 LAZY -> EAGER 로 바꾸면 쿼리가 최적화 될것 같지만 그렇지 않다. 실무에서 EAGER 는 사용하지 말아라!
        // FETCH 조인.
        return result;
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderRepository.findOrderDtos();
    }

    @Data
    static class SimpleOrderDto {
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }
}
