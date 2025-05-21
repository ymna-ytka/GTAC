package net.ymnaytka.gtac.common.data.machines;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;
import com.gregtechceu.gtceu.common.registry.GTRegistration;

import net.minecraft.network.chat.Component;
import net.ymnaytka.gtac.api.machine.part.SteamFluidHatchPartMachine;
import net.ymnaytka.gtac.common.data.*;
import net.ymnaytka.gtac.common.registry.GTACRegistration;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerSimpleMachines;

public class GTACSMachines {

    public static void init() {
        GTACRegistration.REGISTRATE.creativeModeTab(() -> GTACreativeModTab.CORE);
    }

    public static final MachineDefinition STEAM_IMPORT_HATCH = GTRegistration.REGISTRATE
            .machine("steam_fluid_input_hatch", holder -> new SteamFluidHatchPartMachine(holder, IO.IN, 4000, 1))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.IMPORT_FLUIDS)
            .overlaySteamHullRenderer("fluid_hatch.import")
            .tooltips(Component.translatable("gtceu.machine.steam_fluid_hatch_notice"))
            .langValue("Fluid Input Hatch (Steam)")
            .register();

    public static final MachineDefinition STEAM_EXPORT_HATCH = GTRegistration.REGISTRATE
            .machine("steam_fluid_output_hatch", holder -> new SteamFluidHatchPartMachine(holder, IO.OUT, 4000, 1))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.EXPORT_FLUIDS)
            .tooltips(Component.translatable("gtceu.machine.steam_fluid_hatch_notice"))
            .overlaySteamHullRenderer("fluid_hatch.export")
            .register();

    public static final MachineDefinition[] AIR_COOLER = registerSimpleMachines(
            "air_cooler",
            GTACRecipeTypes.AIR_COOLER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.tiersBetween(LV, ZPM));

    public static final MachineDefinition[] TWILIGHT_FARMER = registerSimpleMachines(
            "twilight_farmer",
            GTACRecipeTypes.TWILIGHT_FARMER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.tiersBetween(LV, ZPM));

    public static final MachineDefinition[] COOLER = registerSimpleMachines(
            "cooler",
            GTACRecipeTypes.COOLER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.tiersBetween(LV, ZPM));

    public static final MachineDefinition[] FARMER = registerSimpleMachines(
            "farmer",
            GTACRecipeTypes.FARMER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.tiersBetween(LV, ZPM));
}
