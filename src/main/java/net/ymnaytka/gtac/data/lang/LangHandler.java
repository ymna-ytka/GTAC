package net.ymnaytka.gtac.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class LangHandler extends com.gregtechceu.gtceu.data.lang.LangHandler {

    public static void init(RegistrateLangProvider provider) {
        replace(provider, "gtceu.machine.steam_fluid_hatch_notice",
                "This hatch is for Fluid ingredients! Not to power with steam!");
        replace(provider, "block.gtceu.steam_fluid_output_hatch", "Bronze Output Hatch");
        replace(provider, "block.gtceu.steam_fluid_input_hatch", "Bronze Input Hatch");
        replace(provider, "block.gtceu.masonry_item_export_bus", "Masonry Export Bus");
        replace(provider, "block.gtceu.masonry_item_import_bus", "Masonry Import Bus");
        replace(provider, "block.gtceu.lv_air_cooler", "Basic Air Cooler §r");
        replace(provider, "block.gtceu.mv_air_cooler", "§bAdvanced Air Cooler §r");
        replace(provider, "block.gtceu.hv_air_cooler", "§6Advanced Air Cooler II§r");
        replace(provider, "block.gtceu.ev_air_cooler", "§5Advanced Air Cooler III§r");
        replace(provider, "block.gtceu.iv_air_cooler", "§dElite Air Cooler §r");
        replace(provider, "block.gtceu.luv_air_cooler", "§dElite Air Cooler II§r");
        replace(provider, "block.gtceu.zpm_air_cooler", "§cElite Air Cooler III§r");
        replace(provider, "block.gtceu.lv_cooler", "Basic Cooler §r");
        replace(provider, "block.gtceu.mv_cooler", "§bAdvanced Cooler §r");
        replace(provider, "block.gtceu.hv_cooler", "§6Advanced Cooler II§r");
        replace(provider, "block.gtceu.ev_cooler", "§6Advanced Cooler III§r");
        replace(provider, "block.gtceu.iv_cooler", "§cElite Cooler §r");
        replace(provider, "block.gtceu.luv_cooler", "§cElite Cooler II§r");
        replace(provider, "block.gtceu.zpm_cooler", "§cElite Cooler III§r");
        replace(provider, "block.gtceu.lv_farmer", "Basic Farmer §r");
        replace(provider, "block.gtceu.mv_farmer", "§bAdvanced Farmer §r");
        replace(provider, "block.gtceu.hv_farmer", "§6Advanced Farmer II§r");
        replace(provider, "block.gtceu.ev_farmer", "§6Advanced Farmer III§r");
        replace(provider, "block.gtceu.iv_farmer", "§cElite Farmer §r");
        replace(provider, "block.gtceu.luv_farmer", "§cElite Farmer II§r");
        replace(provider, "block.gtceu.zpm_farmer", "§cElite Farmer III§r");
        replace(provider, "block.gtceu.lv_twilight_farmer", "Basic Twilight Farmer §r");
        replace(provider, "block.gtceu.mv_twilight_farmer", "§bAdvanced Twilight Farmer §r");
        replace(provider, "block.gtceu.hv_twilight_farmer", "§6Advanced Twilight Farmer II§r");
        replace(provider, "block.gtceu.ev_twilight_farmer", "§6Advanced Twilight Farmer III§r");
        replace(provider, "block.gtceu.iv_twilight_farmer", "§cElite Twilight Farmer §r");
        replace(provider, "block.gtceu.luv_twilight_farmer", "§cElite Twilight Farmer II§r");
        replace(provider, "block.gtceu.zpm_twilight_farmer", "§cElite Twilight Farmer III§r");
        replace(provider, "block.gtceu.lp_steam_mixer", "Low Pressure Steam Mixer");
        replace(provider, "block.gtceu.lp_steam_vulcanizer", "Low Pressure Steam Vulcanizer");
        replace(provider, "block.gtceu.hp_steam_mixer", "High Pressure Steam Mixer");
        replace(provider, "block.gtceu.hp_steam_vulcanizer", "High Pressure Steam Vulcanizer");
        replace(provider, "block.gtceu.tar_smokehouse", "Tar Smokehouse");

        provider.add("gtceu.air_cooler", "Air Cooler");
        provider.add("gtceu.cooler", "Cooling");
        provider.add("gtceu.twilight_farmer", "Twilight Farmer");
        provider.add("gtceu.farmer", "Farmer");
        provider.add("gtceu.bronze_mixer", "Bronze Mixer");
        provider.add("gtceu.steam_blast_furnace", "Steam Blast Furnace");
        provider.add("gtceu.steam_coke_oven", "Steam Coke Oven");
        provider.add("gtceu.steam_magic_bath", "Steam Magic Bath");
        provider.add("gtceu.tar_smokehouse", "Smoking");
        provider.add("gtceu.induction_furnace", "Induction Furnace");
        provider.add("gtceu.natural_cleaner", "Natural Cleaner");
        provider.add("gtceu.fertilizer_buck", "Fertilizer Buck");
        provider.add("gtceu.vulcanizer", "Vulcanization");
        provider.add("gtceu.coagulation", "Coagulation");
    }
}
