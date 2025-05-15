package net.ymnaytka.gtac.common.data.machines;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils;

import net.ymnaytka.gtac.common.data.*;
import net.ymnaytka.gtac.common.registry.GTACRegistration;

import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.registerSimpleMachines;

public class GTACSMachines {

    public static void init() {
        GTACRegistration.REGISTRATE.creativeModeTab(() -> GTACreativeModTab.CORE);
    }

    public static final MachineDefinition[] AIR_COOLER = registerSimpleMachines(
            "air_cooler",
            GTACRecipeTypes.AIR_COOLER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.LV, GTValues.MV, GTValues.HV, GTValues.EV, GTValues.IV, GTValues.LuV, GTValues.ZPM);

    public static final MachineDefinition[] TWILIGHT_FARMER = registerSimpleMachines(
            "twilight_farmer",
            GTACRecipeTypes.TWILIGHT_FARMER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.LV, GTValues.MV, GTValues.HV, GTValues.EV, GTValues.IV, GTValues.LuV, GTValues.ZPM);

    public static final MachineDefinition[] COOLER = registerSimpleMachines(
            "cooler",
            GTACRecipeTypes.COOLER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.LV, GTValues.MV, GTValues.HV, GTValues.EV, GTValues.IV, GTValues.LuV, GTValues.ZPM);

    public static final MachineDefinition[] FARMER = registerSimpleMachines(
            "farmer",
            GTACRecipeTypes.FARMER,
            GTMachineUtils.hvCappedTankSizeFunction,
            true,
            GTValues.LV, GTValues.MV, GTValues.HV, GTValues.EV, GTValues.IV, GTValues.LuV, GTValues.ZPM);
}
