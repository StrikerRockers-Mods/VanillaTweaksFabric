package io.github.strikerrocker.vt;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.strikerrocker.vt.base.Module;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeModule extends Module {
    public static final Map<Identifier, JsonObject> recipes = new HashMap<>();

    /**
     * Creates and registers shaped recipe via code
     */
    public static void createShapedRecipeJson(List<Character> keys, List<Identifier> items, List<String> type, List<String> pattern, Identifier output, int count) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:crafting_shaped");
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(pattern.get(0));
        jsonArray.add(pattern.get(1));
        jsonArray.add(pattern.get(2));

        json.add("pattern", jsonArray);

        JsonObject individualKey;
        JsonObject keyList = new JsonObject();

        for (int i = 0; i < keys.size(); ++i) {
            individualKey = new JsonObject();
            individualKey.addProperty(type.get(i), items.get(i).toString());
            keyList.add(keys.get(i) + "", individualKey);
        }
        json.add("key", keyList);

        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);
        json.add("result", result);
        recipes.put(output, json);
    }

    /**
     * Creates and registers shapeless recipe via code
     */
    public static void createShapelessRecipeJson(List<Identifier> ingredients, List<String> types
            , Identifier output, int count) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:crafting_shapeless");
        JsonArray ingredientArray = new JsonArray();
        for (int i = 0; i < ingredients.size(); i++) {
            String type = types.get(i);
            JsonObject item = new JsonObject();
            item.addProperty(type, ingredients.get(i).toString());
            ingredientArray.add(item);
        }
        json.add("ingredients", ingredientArray);
        JsonObject result = new JsonObject();
        result.addProperty("item", output.toString());
        result.addProperty("count", count);
        json.add("result", result);
        recipes.put(output, json);
    }

    @Override
    public void addFeatures() {
        if (VanillaTweaks.config.recipe.betterChestRecipe)
            createShapedRecipeJson(Lists.newArrayList('O'), Lists.newArrayList(new Identifier("logs")), Lists.newArrayList("tag"),
                    Lists.newArrayList(
                            "OOO",
                            "O O",
                            "OOO"
                    ), new Identifier("minecraft:chest"), 4);
        if (VanillaTweaks.config.recipe.nameTag) {
            createShapedRecipeJson(Lists.newArrayList('I', 'P'), Lists.newArrayList(new Identifier("iron_ingot"), new Identifier("paper")),
                    Lists.newArrayList("item", "item"), Lists.newArrayList(
                            "  I",
                            " P ",
                            "P  "
                    ), new Identifier("name_tag"), 1);
        }
        if (VanillaTweaks.config.recipe.woolToString)
            createShapelessRecipeJson(Lists.newArrayList(new Identifier("wool")), Lists.newArrayList("tag"), new Identifier("string"), 4);
        if (VanillaTweaks.config.recipe.betterRepeater)
            createShapedRecipeJson(Lists.newArrayList('S', 'R', 'O'), Lists.newArrayList(new Identifier("stone"), new Identifier("redstone"), new Identifier("stick")),
                    Lists.newArrayList("item", "item", "item"), Lists.newArrayList(
                            "R R",
                            "ORO",
                            "SSS"
                    ), new Identifier("repeater"), 1);
        if (VanillaTweaks.config.recipe.betterTrappedChestRecipe)
            createShapedRecipeJson(Lists.newArrayList('P', 'H'), Lists.newArrayList(new Identifier("minecraft:planks"), new Identifier("tripwire_hook")),
                    Lists.newArrayList("tag", "item"), Lists.newArrayList(
                            "PPP",
                            "PHP",
                            "PPP"
                    ), new Identifier("trapped_chest"), 1);
    }
}