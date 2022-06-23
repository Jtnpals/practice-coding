package patterns.builder;

public class BuilderTest {
    public static void main(String[] args) {
        NutritionFacts.Builder builder = new NutritionFacts.Builder(240, 8);
        builder.calories(100);
        builder.sodium(35);
        builder.carbohydrate(27);
        NutritionFacts cocaCola = builder.build();
    }
}
