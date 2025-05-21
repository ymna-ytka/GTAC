package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.gregtechceu.gtceu.integration.xei.handlers.item.CycleItemStackHandler;

import com.lowdragmc.lowdraglib.utils.LocalizationUtils;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTACRecipeTypes {

    public static void init() {}

    public static final GTRecipeType BRONZE_MIXER = register("bronze_mixer", MULTIBLOCK)
            .setMaxIOSize(6, 2, 3, 3)
            .setSlotOverlay(false, false, GuiTextures.DUST_OVERLAY)
            .setSlotOverlay(true, false, GuiTextures.DUST_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_MIXER, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.MIXER)
            .setEUIO(IO.IN);

    public static final GTRecipeType STEAM_BLAST_FURNACE = register("steam_blast_furnace", MULTIBLOCK)
            .setMaxIOSize(3, 1, 1, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FIRE)
            .setEUIO(IO.IN);

    public static final GTRecipeType STEAM_COKE_OVEN = register("steam_coke_oven", MULTIBLOCK)
            .setMaxIOSize(1, 1, 1, 1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FIRE)
            .setEUIO(IO.IN);

    public static final GTRecipeType STEAM_MAGIC_BATH = register("steam_magic_bath", MULTIBLOCK)
            .setMaxIOSize(3, 3, 4, 0)
            .setSlotOverlay(false, false, true, GuiTextures.BREWER_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.BATH)
            .setEUIO(IO.IN);

    public static final GTRecipeType TAR_SMOKEHOUSE = register("tar_smokehouse", MULTIBLOCK)
            .setMaxIOSize(2, 1, 0, 0)
            .setSlotOverlay(false, false, GuiTextures.FURNACE_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.FURNACE)
            .setEUIO(IO.IN);

    public static final GTRecipeType INDUCTION_FURNACE = register("induction_furnace", MULTIBLOCK)
            .setMaxIOSize(1, 1, 0, 0)
            .addDataInfo(data -> {
                int temp = data.getInt("ebf_temp");
                return LocalizationUtils.format("gtceu.recipe.temperature", temp);
            })
            .addDataInfo(data -> {
                int temp = data.getInt("ebf_temp");
                ICoilType requiredCoil = ICoilType.getMinRequiredType(temp);

                if (requiredCoil != null && requiredCoil.getMaterial() != null) {
                    return LocalizationUtils.format("gtceu.recipe.coil.tier",
                            I18n.get(requiredCoil.getMaterial().getUnlocalizedName()));
                }
                return "";
            })
            .setUiBuilder((recipe, widgetGroup) -> {
                int temp = recipe.data.getInt("ebf_temp");
                List<List<ItemStack>> items = new ArrayList<>();
                items.add(GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .filter(coil -> coil.getKey().getCoilTemperature() >= temp)
                        .map(coil -> new ItemStack(coil.getValue().get())).toList());
                widgetGroup.addWidget(new SlotWidget(new CycleItemStackHandler(items), 0,
                        widgetGroup.getSize().width - 25, widgetGroup.getSize().height - 32, false, false));
            })
            .setSound(GTSoundEntries.FURNACE)
            .setEUIO(IO.IN);

    public static final GTRecipeType NATURAL_CLEANER = register("natural_cleaner", MULTIBLOCK)
            .setMaxIOSize(3, 2, 1, 0)
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL)
            .setEUIO(IO.IN);

    public static final GTRecipeType AIR_COOLER = register("air_cooler", ELECTRIC)
            .setMaxIOSize(0, 0, 1, 1)
            .setSound(GTSoundEntries.COOLING)
            .setEUIO(IO.IN);

    public static final GTRecipeType COOLER = register("cooler", ELECTRIC)
            .setMaxIOSize(1, 1, 1, 1)
            .setSound(GTSoundEntries.COOLING)
            .setEUIO(IO.IN);

    public static final GTRecipeType FARMER = register("farmer", ELECTRIC)
            .setMaxIOSize(3, 3, 3, 3)
            .setSound(GTSoundEntries.WIRECUTTER_TOOL)
            .setEUIO(IO.IN);

    public static final GTRecipeType TWILIGHT_FARMER = register("twilight_farmer", ELECTRIC)
            .setMaxIOSize(1, 2, 1, 1)
            .setSlotOverlay(false, false, GuiTextures.INT_CIRCUIT_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL)
            .setEUIO(IO.IN);

    public static final GTRecipeType FERTILIZER_BUCK = register("fertilizer_buck", ELECTRIC)
            .setMaxIOSize(3, 3, 6, 3)
            .setSlotOverlay(false, false, false, GuiTextures.MOLECULAR_OVERLAY_1)
            .setSlotOverlay(false, false, true, GuiTextures.MOLECULAR_OVERLAY_2)
            .setSlotOverlay(false, true, false, GuiTextures.MOLECULAR_OVERLAY_3)
            .setSlotOverlay(false, true, true, GuiTextures.MOLECULAR_OVERLAY_4)
            .setSlotOverlay(true, false, GuiTextures.VIAL_OVERLAY_1)
            .setSlotOverlay(true, true, GuiTextures.VIAL_OVERLAY_2)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW_MULTIPLE, LEFT_TO_RIGHT)
            .setSound(GTValues.FOOLS.get() ? GTSoundEntries.SCIENCE : GTSoundEntries.CHEMICAL)
            .setEUIO(IO.IN);
}
