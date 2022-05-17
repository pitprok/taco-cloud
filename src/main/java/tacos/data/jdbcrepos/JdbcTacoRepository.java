package tacos.data.jdbcrepos;
import tacos.Taco;

public interface JdbcTacoRepository {
	Taco save(Taco design);
}