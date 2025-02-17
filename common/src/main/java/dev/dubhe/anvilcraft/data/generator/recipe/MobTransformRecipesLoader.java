package dev.dubhe.anvilcraft.data.generator.recipe;

import com.tterrag.registrate.providers.RegistrateRecipeProvider;
import dev.dubhe.anvilcraft.data.recipe.transform.MobTransformRecipe;
import net.minecraft.world.entity.EntityType;

public class MobTransformRecipesLoader {
    /**
     * 生物被腐化信标照射时的转化配方
     *
     * @param provider 提供器
     */
    public static void init(RegistrateRecipeProvider provider) {

        MobTransformRecipe.builder("cow")
                .input(EntityType.COW)
                .result(EntityType.RAVAGER, 1)
                .accept(provider);

        MobTransformRecipe.builder("pig")
                .input(EntityType.PIG)
                .result(EntityType.HOGLIN, 1)
                .accept(provider);

        MobTransformRecipe.builder("guardian")
                .input(EntityType.GUARDIAN)
                .result(EntityType.ELDER_GUARDIAN, 1)
                .accept(provider);

        MobTransformRecipe.builder("piglin")
                .input(EntityType.PIGLIN)
                .result(EntityType.PIGLIN_BRUTE, 1)
                .accept(provider);

        MobTransformRecipe.builder("villager")
                .input(EntityType.VILLAGER)
                .result(EntityType.PILLAGER, 0.3)
                .result(EntityType.VINDICATOR, 0.6)
                .result(EntityType.EVOKER, 0.1)
                .accept(provider);

        MobTransformRecipe.builder("allay")
                .input(EntityType.ALLAY)
                .result(EntityType.VEX, 1)
                .accept(provider);

        MobTransformRecipe.builder("bat")
                .input(EntityType.BAT)
                .result(EntityType.PHANTOM, 1)
                .accept(provider);

        MobTransformRecipe.builder("horse")
                .input(EntityType.HORSE)
                .result(EntityType.SKELETON_HORSE, 0.9)
                .result(EntityType.ZOMBIE_HORSE, 0.1)
                .accept(provider);

        MobTransformRecipe.builder("silverfish")
                .input(EntityType.SILVERFISH)
                .result(EntityType.ENDERMITE, 1)
                .accept(provider);
    }
}
