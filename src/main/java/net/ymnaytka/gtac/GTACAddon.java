package net.ymnaytka.gtac;

import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;

import net.minecraft.data.recipes.FinishedRecipe;
import net.ymnaytka.gtac.common.data.GTACElements;
import net.ymnaytka.gtac.common.data.GTACOres;
import net.ymnaytka.gtac.common.data.GTACRecipeTypes;
import net.ymnaytka.gtac.common.data.GTACSounds;
import net.ymnaytka.gtac.common.registry.GTACRegistration;

import java.util.function.Consumer;

@SuppressWarnings("unused")
@com.gregtechceu.gtceu.api.addon.GTAddon
public class GTACAddon implements IGTAddon {

    @Override
    public GTRegistrate getRegistrate() {
        return GTACRegistration.REGISTRATE;
    }

    @Override
    public void registerTagPrefixes() {}

    @Override
    public void initializeAddon() {
        GTAC.LOGGER.info("GT Aurora addon has loaded!");
    }

    @Override
    public void registerElements() {
        GTACElements.init();
    }

    @Override
    public String addonModId() {
        return GTAC.MOD_ID;
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTACRecipeTypes.init();
    }

    @Override
    public void registerRecipeCapabilities() {}

    @Override
    public void registerOreVeins() {
        GTACOres.init();
    }

    @Override
    public void registerSounds() {
        GTACSounds.init();
    }
}
