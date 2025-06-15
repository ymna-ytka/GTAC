package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.GTACMaterials;

public class GTACStoneAge {

    public static void init() {
        GTACMaterials.ImpregnatingOil = new Material.Builder(GTAC.id("impregnating_oil"))
                .liquid()
                .color(0x23120D)
                .buildAndRegister();

        GTACMaterials.Tannin = new Material.Builder(GTAC.id("tannin"))
                .liquid()
                .color(0xa68a6d)
                .buildAndRegister();

        GTACMaterials.LimestoneWater = new Material.Builder(GTAC.id("limestone_water"))
                .liquid()
                .color(0xdcd8c7)
                .buildAndRegister();
    }
}
