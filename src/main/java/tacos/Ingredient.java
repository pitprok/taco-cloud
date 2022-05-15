package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
//@RequiredArgsConstructor Not needed because @Data includes it
public class Ingredient {
    private final String id;
    private final String name;
    private final Type type;
    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}