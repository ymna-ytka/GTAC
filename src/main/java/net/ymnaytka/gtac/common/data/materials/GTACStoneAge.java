package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.GTACMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTACStoneAge {

    public static void init() {
        GTACMaterials.SpongeCopper = new Material.Builder(GTAC.id("sponge_copper"))
                .ingot()
                .color(0xdea08a)
                .iconSet(DULL)
                .buildAndRegister().setFormula("Cu", true);

        GTACMaterials.SpongeIron = new Material.Builder(GTAC.id("sponge_iron"))
                .ingot()
                .color(0xb5b5b5)
                .iconSet(DULL)
                .buildAndRegister().setFormula("Fe", true);

        GTACMaterials.SpongeMetal = new Material.Builder(GTAC.id("sponge_metal"))
                .ingot()
                .color(0xa88274)
                .iconSet(DULL)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_SMALL_GEAR,
                        MaterialFlags.GENERATE_BOLT_SCREW,
                        MaterialFlags.GENERATE_RING)
                .buildAndRegister().setFormula("FeCu", true);
    }
}
