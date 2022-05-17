package tacos.data.jdbcrepos;

import tacos.Ingredient;

public interface JdbcIngredientRepository {
	Iterable<Ingredient> findAll();
	Ingredient findOne(String id);
	Ingredient save(Ingredient ingredient);
}