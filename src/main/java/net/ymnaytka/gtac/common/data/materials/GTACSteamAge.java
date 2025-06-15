package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.GTACMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTACSteamAge {

    public static void init() {
        GTACMaterials.Tumbaga = new Material.Builder(GTAC.id("tumbaga"))
                .ingot()
                .color(0xfabd52)
                .components(Copper, 3, Gold, 7)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_ROD

                )
                .buildAndRegister();

        GTACMaterials.Ferabrass = new Material.Builder(GTAC.id("ferabrass"))
                .ingot()
                .components(Steel, 5, Iron, 1, Brass, 3)
                .color(0xc4a87b)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FRAME)
                .buildAndRegister();

        GTACMaterials.Constantan = new Material.Builder(GTAC.id("constantan"))
                .ingot()
                .components(Copper, 2, Nickel, 1)
                .color(0xcc9966)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_RING,
                        MaterialFlags.GENERATE_LONG_ROD)
                .buildAndRegister();

        GTACMaterials.CastIron = new Material.Builder(GTAC.id("cast_iron"))
                .ingot()
                .color(0x3a3c40)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FRAME)
                .buildAndRegister().setFormula("Fe", true);

        GTACMaterials.Latex = new Material.Builder(GTAC.id("latex"))
                .liquid()
                .color(0xebe7e7)
                .buildAndRegister();
    }
}
