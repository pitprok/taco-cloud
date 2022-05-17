package tacos.data;
import tacos.Order;

public interface JdbcOrderRepository {
	Order save(Order order);
}