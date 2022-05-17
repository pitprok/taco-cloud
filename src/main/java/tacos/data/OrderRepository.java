package tacos.data;

import java.util.Date;
import java.util.List;

import tacos.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

	List<Order> findByDeliveryZip(String deliveryZip);

	List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);

	List<Order> findByDeliveryStreetAndDeliveryCityAllIgnoreCase(String deliveryStreet, String deliveryCity);

	List<Order> findByDeliveryCityOrderByDeliveryStreet(String city);

	@Query(value = "select o from Taco_Order o where o.deliveryCity='Seattle'")
//	@Query("Taco_Order o where o.deliveryCity='Seattle'")
	List<Order> readOrdersDeliveredInSeattle();
}