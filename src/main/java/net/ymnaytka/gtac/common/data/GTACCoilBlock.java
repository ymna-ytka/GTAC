package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.common.block.CoilBlock;

import com.lowdragmc.lowdraglib.client.renderer.IBlockRendererProvider;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.BlockState;
import net.ymnaytka.gtac.GTAC;

import org.jetbrains.annotations.Nullable;

public class GTACCoilBlock extends CoilBlock implements IBlockRendererProvider {

    private final IRenderer renderer, activeRenderer;

    public GTACCoilBlock(Properties properties, ICoilType coilType, @Nullable IRenderer renderer,
                         @Nullable IRenderer activeRenderer) {
        super(properties, coilType);
        this.renderer = renderer;
        this.activeRenderer = activeRenderer;
    }

    @Nullable
    @Override
    public IRenderer getRenderer(BlockState state) {
        return state.getValue(ACTIVE) ? activeRenderer : renderer;
    }

    public enum CoilType implements ICoilType, StringRepresentable {

        STEEL_ECD("steel_ecd", 1500, 1, 1, GTACMaterials.NIALSteel,
                GTAC.id("block/casing/coils/machine_coil_steel_ecd"));

        private final String name;
        private final int coilTemp;
        private final int energyDiscount;
        private final int tier;
        private final Material material;
        private final ResourceLocation texture;

        CoilType(String name, int coilTemp, int energyDiscount,
                 int tier, Material material, ResourceLocation texture) {
            this.name = name;
            this.coilTemp = coilTemp;
            this.energyDiscount = energyDiscount;
            this.tier = tier;
            this.material = material;
            this.texture = texture;
        }

        // ICoilType implementation
        @Override
        public Material getMaterial() {
            return material;
        }

        @Override
        public int getCoilTemperature() {
            return coilTemp;
        }

        @Override
        public int getEnergyDiscount() {
            return energyDiscount;
        }

        @Override
        public int getTier() {
            return tier;
        }

        @Override
        public int getLevel() {
            return tier - GTValues.LV;
        }

        @Override
        public ResourceLocation getTexture() {
            return texture;
        }

        @Override
        public String getName() {
            return name;
        }

        // StringRepresentable implementation
        @Override
        public String getSerializedName() {
            return name;
        }

        // Additional utility method
        public ResourceLocation getActiveTexture() {
            return new ResourceLocation(texture.getNamespace(), texture.getPath() + "_active");
        }
    }
}
