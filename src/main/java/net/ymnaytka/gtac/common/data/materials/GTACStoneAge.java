package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.GTACMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTACStoneAge {

    public static void init() {
        GTACMaterials.SalterWater = new Material.Builder(GTAC.id("salter_water"))
                .liquid()
                .color(0x9de0e9)
                .buildAndRegister().setFormula("H₂O", true);

        GTACMaterials.ImpregnatingOil = new Material.Builder(GTAC.id("impregnating_oil"))
                .liquid()
                .color(0x23120D)
                .buildAndRegister();
    }
}
