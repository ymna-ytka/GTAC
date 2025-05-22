package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.*;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.FluidPipeProperties;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTMaterialAdjustments {

    public static void init() {
        addFluid(EnderPearl);
        addPipeClay(Clay);

        RedAlloy.addFlags(MaterialFlags.GENERATE_GEAR);
        Copper.addFlags(MaterialFlags.GENERATE_ROTOR);
        Obsidian.addFlags(MaterialFlags.GENERATE_ROD);
        Obsidian.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Netherite.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Netherite.addFlags(MaterialFlags.GENERATE_PLATE);

        Olivine.addFlags(MaterialFlags.GENERATE_FINE_WIRE);
        Emerald.addFlags(MaterialFlags.GENERATE_FINE_WIRE);
        Sodalite.addFlags(MaterialFlags.GENERATE_FINE_WIRE);
        Lazurite.addFlags(MaterialFlags.GENERATE_FINE_WIRE);
        Lapis.addFlags(MaterialFlags.GENERATE_FINE_WIRE);
        Amethyst.addFlags(MaterialFlags.GENERATE_FINE_WIRE);
        Ruby.addFlags(MaterialFlags.GENERATE_FINE_WIRE);

        Glass.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Clay.addFlags(MaterialFlags.GENERATE_PLATE);
        RedAlloy.addFlags(MaterialFlags.GENERATE_GEAR);
        Iron.addFlags(MaterialFlags.GENERATE_FRAME);
    }

    public static void addFluid(Material material) {
        if (!material.hasProperty(PropertyKey.FLUID)) {
            material.setProperty(PropertyKey.FLUID, new FluidProperty(FluidStorageKeys.LIQUID, new FluidBuilder()));
        }
    }

    public static void addPipeClay(Material material) {
        if (!material.hasProperty(PropertyKey.FLUID_PIPE)) {
            material.setProperty(PropertyKey.FLUID_PIPE, new FluidPipeProperties(
                    300,
                    15,
                    false,
                    false,
                    false,
                    false,
                    1));
        }
    }
}
