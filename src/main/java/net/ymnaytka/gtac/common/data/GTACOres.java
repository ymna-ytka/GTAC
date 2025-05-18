package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.veins.DikeVeinGenerator;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTOres;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.valueproviders.UniformInt;

import java.util.function.Consumer;

public class GTACOres {

    public static void init() {}

    private static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {

        return GTOres.create(GTCEu.id(name), config);

    }
}
