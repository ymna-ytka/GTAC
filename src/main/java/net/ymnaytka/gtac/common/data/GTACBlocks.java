package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.block.ActiveBlock;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.common.block.CoilBlock;
import com.gregtechceu.gtceu.common.data.GTModels;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.registry.GTACRegistration;

import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import java.util.function.Supplier;

import static net.ymnaytka.gtac.common.registry.GTACRegistration.REGISTRATE;

public class GTACBlocks {

    static {
        GTACRegistration.REGISTRATE.creativeModeTab(() -> GTACreativeModTab.CORE);
    }

    public static final BlockEntry<CoilBlock> COIL_STEEL_ECD = createCoilBlock(GTACCoilBlock.CoilType.STEEL_ECD);

    public static final BlockEntry<Block> FERABRASS_CASING = createCasingBlock("ferabrass_casing",
            GTAC.id("block/casing/ferabrass_casing"));

    public static final BlockEntry<Block> INDUCTION_CASING = createCasingBlock("induction_casing",
            GTAC.id("block/casing/induction_casing"));

    public static final BlockEntry<Block> MASONRY_BRICKS = createCasingBlock("masonry_bricks",
            GTAC.id("block/casing/masonry_bricks"));

    public static final BlockEntry<Block> CAST_IRON_CASING = createCasingBlock("cast_iron_casing",
            GTAC.id("block/casing/cast_iron_casing"));

    public static BlockEntry<Block> createGlassCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, GlassBlock::new, texture, () -> Blocks.GLASS,
                () -> RenderType::translucent);
    }

    public static BlockEntry<Block> createCasingBlock(String name, ResourceLocation texture) {
        return createCasingBlock(name, Block::new, texture, () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    @SuppressWarnings("removal")
    public static BlockEntry<Block> createCasingBlock(String name,
                                                      NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                      ResourceLocation texture,
                                                      NonNullSupplier<? extends Block> properties,
                                                      Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate(GTModels.cubeAllModel(name, texture))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    public static BlockEntry<Block> createSidedCasingBlock(String name, ResourceLocation sideTexture,
                                                           ResourceLocation topTexture) {
        return createSidedCasingBlock(name, Block::new, sideTexture, topTexture,
                () -> Blocks.IRON_BLOCK,
                () -> RenderType::cutoutMipped);
    }

    @SuppressWarnings("removal")
    public static BlockEntry<Block> createSidedCasingBlock(String name,
                                                           NonNullFunction<BlockBehaviour.Properties, Block> blockSupplier,
                                                           ResourceLocation sideTexture,
                                                           ResourceLocation topTexture,
                                                           NonNullSupplier<? extends Block> properties,
                                                           Supplier<Supplier<RenderType>> type) {
        return REGISTRATE.block(name, blockSupplier)
                .initialProperties(properties)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(type)
                .blockstate((ctx, prov) -> prov.simpleBlock(ctx.get(), prov.models()
                        .cubeBottomTop(name, sideTexture, topTexture, topTexture)))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
    }

    @SuppressWarnings("removal")
    public static BlockEntry<ActiveBlock> createActiveCasing(String name, String baseModelPath) {
        return REGISTRATE.block(name, ActiveBlock::new)
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(GTModels.createActiveModel(GTAC.id(baseModelPath)))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .model((ctx, prov) -> prov.withExistingParent(prov.name(ctx), GTAC.id(baseModelPath)))
                .build()
                .register();
    }

    @SuppressWarnings("removal")
    private static BlockEntry<CoilBlock> createCoilBlock(ICoilType coilType) {
        var coilBlock = REGISTRATE
                .block("%s_coil_block".formatted(coilType.getName()), p -> new CoilBlock(p, coilType))
                .initialProperties(() -> Blocks.IRON_BLOCK)
                .properties(p -> p.isValidSpawn((state, level, pos, ent) -> false))
                .addLayer(() -> RenderType::cutoutMipped)
                .blockstate(GTModels.createCoilModel("%s_coil_block".formatted(coilType.getName()), coilType))
                .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .item(BlockItem::new)
                .build()
                .register();
        GTCEuAPI.HEATING_COILS.put(coilType, coilBlock);
        return coilBlock;
    }

    public static void init() {}
}
