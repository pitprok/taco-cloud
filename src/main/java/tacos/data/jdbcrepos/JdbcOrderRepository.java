package tacos.data.jdbcrepos;
import tacos.Order;

public interface JdbcOrderRepository {
	Order save(Order order);
}