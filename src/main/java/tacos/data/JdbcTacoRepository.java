package tacos.data;
import tacos.Taco;

public interface JdbcTacoRepository {
	Taco save(Taco design);
}