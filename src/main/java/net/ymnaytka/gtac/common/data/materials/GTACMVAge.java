package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;

import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.GTACMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.DULL;

public class GTACMVAge {

    public static void init() {
        GTACMaterials.BasicLiquidFertilizer = new Material.Builder(GTAC.id("basic_liquid_fertilizer"))
                .liquid()
                .color(0x00ff80)
                .iconSet(DULL)
                .buildAndRegister().setFormula("H₂ONH₄ClH₃PO₄K₂SO₄", true);
    }
}
