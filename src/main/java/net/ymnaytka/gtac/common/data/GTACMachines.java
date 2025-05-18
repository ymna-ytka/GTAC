package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.steam.SimpleSteamMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableSteamMachineRenderer;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.machines.GTACSMachines;
import net.ymnaytka.gtac.common.registry.GTACRegistration;

import it.unimi.dsi.fastutil.Pair;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static net.ymnaytka.gtac.common.data.GTACMaterials.*;
import static net.ymnaytka.gtac.common.registry.GTACRegistration.REGISTRATE;

public class GTACMachines {

    public static void init() {
        GTACRegistration.REGISTRATE.creativeModeTab(() -> GTACreativeModTab.CORE);
        GTACSMachines.init();
    }

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             String lang,
                                                             int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], lang, VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTAC.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .workableTieredHullRenderer(GTAC.id("block/machines/" + name))
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.apply(tier), true))
                            .register();
                },
                tiers);
    }

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = GTACRegistration.REGISTRATE
                    .machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }



    public static final Pair<MachineDefinition, MachineDefinition> STEAM_MIXER = registerSteamMachines(
            "steam_mixer", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTACRecipeTypes.BRONZE_MIXER)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/mixer")))
                    .register());
    public static final Pair<MachineDefinition, MachineDefinition> STEAM_AUTOClAVE = registerSteamMachines(
            "steam_autoclave", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTRecipeTypes.AUTOCLAVE_RECIPES)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure, GTCEu.id("block/machines/autoclave")))
                    .register());


    public static final MultiblockMachineDefinition STEAM_ALLOY_SMELTER = REGISTRATE
            .multiblock("steam_alloy_smelter", SteamParallelMultiblockMachine::new)
            .langValue("Steam Alloy Smelter")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.ALLOY_SMELTER_RECIPES)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "CCC", "###", "###")
                    .aisle("AAA", "ACA", "A#A", "A#A")
                    .aisle("AAA", "CKC", "###", "###")
                    .where('C', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(3)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('#', any())
                    .where('A', blocks(FIREBOX_BRONZE.get()))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("AAA", "CCC", "###", "###")
                        .aisle("AAA", "ACA", "A#A", "A#A")
                        .aisle("FSY", "CKC", "###", "###")
                        .where('C', CASING_BRONZE_BRICKS.getDefaultState())
                        .where('F', STEAM_IMPORT_BUS, Direction.SOUTH)
                        .where('K', definition, Direction.SOUTH)
                        .where('Y', STEAM_HATCH, Direction.SOUTH)
                        .where('A', FIREBOX_BRONZE)
                        .where('S', STEAM_EXPORT_BUS, Direction.SOUTH);

                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Alloy Smelter"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTAC.id("block/multiblock/steam-pss_template"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition STEAM_BLAST_FURNACE = REGISTRATE
            .multiblock("steam_blast_furnace", SteamParallelMultiblockMachine::new)
            .langValue("Steam Blast Furnace")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.STEAM_BLAST_FURNACE)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCC", "CCC", "CCC", "CCC")
                    .aisle("CCC", "SFS", "OPO", "OFO")
                    .aisle("CCC", "SFS", "OPO", "OFO")
                    .aisle("CCC", "CKC", "CCC", "CCC")
                    .where('C', blocks(CASING_PRIMITIVE_BRICKS.get()).setMinGlobalLimited(24)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('S', blocks(STEEL_HULL.get()))
                    .where('F', blocks(FIREBOX_STEEL.get()))
                    .where('O', blocks(CASING_STEEL_SOLID.get()))
                    .where('P', blocks(CASING_STEEL_PIPE.get()))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("CCC", "CCC", "CCC", "CCC")
                        .aisle("CCC", "SFS", "OPO", "OFO")
                        .aisle("CCC", "SFS", "OPO", "OFO")
                        .aisle("MYJ", "CKC", "CCC", "CCC")
                        .where('C', CASING_PRIMITIVE_BRICKS.getDefaultState())
                        .where('J', STEAM_IMPORT_BUS, Direction.SOUTH)
                        .where('K', definition, Direction.SOUTH)
                        .where('Y', STEAM_HATCH, Direction.SOUTH)
                        .where('S', STEEL_HULL)
                        .where('F', FIREBOX_STEEL)
                        .where('O', CASING_STEEL_SOLID)
                        .where('P', CASING_STEEL_PIPE)
                        .where('M', STEAM_EXPORT_BUS, Direction.SOUTH);

                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Steam Blast Furnace"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTAC.id("block/multiblock/steam-pss_template"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition STEAM_COKE_OVEN = REGISTRATE
            .multiblock("steam_coke_oven", SteamParallelMultiblockMachine::new)
            .langValue("Steam Coke Oven")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.STEAM_COKE_OVEN)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .appearanceBlock(CASING_COKE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCC", "CCCC", "CCCC", "CCCC")
                    .aisle("CBBC", "CPPC", "CPPC", "CCCC")
                    .aisle("CBBC", "CPPC", "CPPC", "CCCC")
                    .aisle("CCCC", "CKCC", "CCCC", "CCCC")
                    .where('C', blocks(CASING_COKE_BRICKS.get()).setMinGlobalLimited(46)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM))
                            .or(abilities(PartAbility.EXPORT_FLUIDS)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('B', blocks(FIREBOX_BRONZE.get()))
                    .where('P', blocks(CASING_BRONZE_PIPE.get()))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("CCCC", "CCCC", "CCCC", "CCCC")
                        .aisle("CBBC", "CPPC", "CPPC", "CCCC")
                        .aisle("CBBC", "CPPC", "CPPC", "CCCC")
                        .aisle("FYSL", "CKCC", "CCCC", "CCCC")
                        .where('C', CASING_COKE_BRICKS.getDefaultState())
                        .where('F', STEAM_IMPORT_BUS, Direction.SOUTH)
                        .where('K', definition, Direction.SOUTH)
                        .where('Y', STEAM_HATCH, Direction.SOUTH)
                        .where('B', FIREBOX_BRONZE)
                        .where('P', CASING_BRONZE_PIPE)
                        .where('S', STEAM_EXPORT_BUS, Direction.SOUTH);

                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Steam Coke Oven"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_coke_bricks"),
                    GTAC.id("block/multiblock/smoking"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition STEAM_COMPRESSOR = REGISTRATE
            .multiblock("steam_compressor", SteamParallelMultiblockMachine::new)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .langValue("Steam Compressor")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.COMPRESSOR_RECIPES)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCCC", "CCCCC", "CCCCC")
                    .aisle("CCCCC", "CAGAC", "CCCCC")
                    .aisle("CCCCC", "CCKCC", "CCCCC")
                    .where('C', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(36)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('A', blocks(Blocks.ANVIL))
                    .where('G', blocks(CASING_BRONZE_GEARBOX.get()))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("CCCCC", "CCCCC", "CCCCC")
                        .aisle("CCCCC", "CAGAC", "CCCCC")
                        .aisle("SYFCC", "CCKCC", "CCCCC")
                        .where('C', CASING_BRONZE_BRICKS.getDefaultState())
                        .where('F', STEAM_IMPORT_BUS, Direction.SOUTH)
                        .where('K', definition, Direction.SOUTH)
                        .where('Y', STEAM_HATCH, Direction.SOUTH)
                        .where('G', CASING_BRONZE_GEARBOX)
                        .where('A', Blocks.ANVIL.defaultBlockState())
                        .where('S', STEAM_EXPORT_BUS, Direction.SOUTH);

                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Compressor"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTAC.id("block/multiblock/steam_compressor"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition STEAM_MAGIC_BATH = REGISTRATE
            .multiblock("steam_magic_bath", SteamParallelMultiblockMachine::new)
            .langValue("Steam Magic Bath")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.STEAM_MAGIC_BATH)
            .recipeModifier(SteamParallelMultiblockMachine::recipeModifier, true)
            .appearanceBlock(GTACBlocks.FERABRASS_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCFCC", "CCPCC", "CCCCC")
                    .aisle("CBBBC", "CRRRC", "CCCCC")
                    .aisle("FBBBF", "PRRRP", "CCCCC")
                    .aisle("CBBBC", "CRRRC", "CCCCC")
                    .aisle("CCFCC", "CCPCC", "CCKCC")
                    .where('C', blocks(GTACBlocks.FERABRASS_CASING.get()).setMinGlobalLimited(44)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS))
                            .or(abilities(PartAbility.STEAM)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('B', blocks(ChemicalHelper.getBlock(TagPrefix.block, Ferabrass)))
                    .where('F', blocks(FIREBOX_STEEL.get()))
                    .where('P', blocks(CASING_STEEL_PIPE.get()))
                    .where('R', blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, Ferabrass)))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("CCFCC", "CCPCC", "CCCCC")
                        .aisle("CBBBC", "CRRRC", "CCCCC")
                        .aisle("FBBBF", "PRRRP", "CCCCC")
                        .aisle("CBBBC", "CRRRC", "CCCCC")
                        .aisle("JYFYS", "CCPCC", "CCKCC")
                        .where('C', GTACBlocks.FERABRASS_CASING.getDefaultState())
                        .where('J', STEAM_IMPORT_BUS, Direction.SOUTH)
                        .where('K', definition, Direction.SOUTH)
                        .where('Y', STEAM_HATCH, Direction.SOUTH)
                        .where('B', ChemicalHelper.getBlock(TagPrefix.block, Ferabrass))
                        .where('F', FIREBOX_STEEL)
                        .where('P', CASING_STEEL_PIPE)
                        .where('R', ChemicalHelper.getBlock(TagPrefix.frameGt, Ferabrass))
                        .where('S', STEAM_EXPORT_BUS, Direction.SOUTH);

                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Steam Magic Bath"))
            .workableCasingRenderer(
                    GTAC.id("block/casing/ferabrass_casing"),
                    GTAC.id("block/multiblock/steam_magic_bath"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition INDUCTION_FURNACE = REGISTRATE
            .multiblock("induction_furnace", CoilWorkableElectricMultiblockMachine::new)
            .langValue("Induction Furnace")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.INDUCTION_FURNACE)
            .recipeModifier(GTRecipeModifiers::ebfOverclock)
            .appearanceBlock(GTACBlocks.INDUCTION_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCCC", "CGGC", "CGGC", "CCCC")
                    .aisle("CCCC", "GZZG", "GZZG", "CCCC")
                    .aisle("CCCC", "GZZG", "GZZG", "CCCC")
                    .aisle("CCCC", "CKGC", "CGGC", "CCCC")
                    .where('C', blocks(GTACBlocks.INDUCTION_CASING.get()).setMinGlobalLimited(36)
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1))
                            .or(autoAbilities(true, true, true)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('G', blocks(CASING_TEMPERED_GLASS.get()))
                    .where('Z', heatingCoils())
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("CCCC", "CGGC", "CGGC", "CCCC")
                        .aisle("CCCC", "GZZG", "GZZG", "CCCC")
                        .aisle("CCCC", "GZZG", "GZZG", "CCCC")
                        .aisle("EIOM", "CKGC", "CGGC", "CCCC")
                        .where('C', GTACBlocks.INDUCTION_CASING.getDefaultState())
                        .where('K', definition, Direction.SOUTH)
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('I', ITEM_IMPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('O', ITEM_EXPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('M', MAINTENANCE_HATCH, Direction.SOUTH)
                        .where('G', CASING_TEMPERED_GLASS);
                GTCEuAPI.HEATING_COILS.entrySet().stream()
                        .sorted(Comparator.comparingInt(entry -> entry.getKey().getTier()))
                        .forEach(
                                coil -> shapeInfo.add(builder.shallowCopy().where('Z', coil.getValue().get()).build()));
                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Induction Furnace"))
            .workableCasingRenderer(
                    GTAC.id("block/casing/induction_casing"),
                    GTAC.id("block/multiblock/induction_furnace"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition NATURAL_CLEANER = REGISTRATE
            .multiblock("natural_cleaner", SteamParallelMultiblockMachine::new)
            .langValue("Natural Cleaner")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.NATURAL_CLEANER)
            .appearanceBlock(GTACBlocks.ECOFERUM_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("CCC", "OFO", "CCC")
                    .aisle("CCC", "FRF", "CCC")
                    .aisle("CKC", "OFO", "CCC")
                    .where('C', blocks(GTACBlocks.ECOFERUM_CASING.get()).setMinGlobalLimited(11)
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                            .or(autoAbilities(true, true, false)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('O', blocks(COIL_CUPRONICKEL.getUnchecked()))
                    .where('F', blocks(GTACBlocks.FILTERING_CAMERA.get()))
                    .where('R', blocks(GTACBlocks.REACTIVE_CLEANER.get()))
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("EIO", "OFO", "CCC")
                        .aisle("MCC", "FRF", "CCC")
                        .aisle("CKC", "OFO", "CCC")
                        .where('C', GTACBlocks.ECOFERUM_CASING.getDefaultState())
                        .where('K', definition, Direction.SOUTH)
                        .where('O', COIL_CUPRONICKEL)
                        .where('F', GTACBlocks.FILTERING_CAMERA.getDefaultState())
                        .where('R', GTACBlocks.REACTIVE_CLEANER.getDefaultState())
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('I', ITEM_IMPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('O', ITEM_EXPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('M', MAINTENANCE_HATCH, Direction.SOUTH);
                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Natural Cleaner"))
            .workableCasingRenderer(
                    GTAC.id("block/casing/ecoferum_casing"),
                    GTCEu.id("block/multiblock/distillation_tower"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();

    public static final MultiblockMachineDefinition FERTILIZER_BUCK = REGISTRATE
            .multiblock("fertilizer_buck", SteamParallelMultiblockMachine::new)
            .langValue("Fertilizer Buck")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.FERTILIZER_BUCK)
            .appearanceBlock(CASING_PTFE_INERT)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAA", "AABBAA", "AABBAA", "AABBAA", "      ")
                    .aisle("AAAAAA", "B    B", "B    B", "BAAAAB", " BB   ")
                    .aisle("AAAAAA", "A CC A", "A CC A", "AAAAAA", " BB   ")
                    .aisle("AAAAAA", "B    K", "B    B", "BAAAAB", "      ")
                    .aisle("AAAAAA", "A CC A", "A CC A", "AAAAAA", "      ")
                    .aisle("AAAAAA", "B    B", "B    B", "BAAAAB", "      ")
                    .aisle("AAAAAA", "AABBAA", "AABBAA", "AABBAA", "      ")
                    .where('A', blocks(CASING_PTFE_INERT.get()).setMinGlobalLimited(88)
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMaxGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1))
                            .or(autoAbilities(true, true, false)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('B', blocks(CASING_STAINLESS_CLEAN.get()))
                    .where('C', blocks(HERMETIC_CASING_LV.get()))
                    .where(' ', air())
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("AAAAAA", "AABBAA", "AABBAA", "AABBAA", "      ")
                        .aisle("AAAAAA", "B    B", "B    B", "BAAAAB", " BB   ")
                        .aisle("AAAAAA", "A CC A", "A CC A", "AAAAAA", " BB   ")
                        .aisle("AAAAAA", "B    K", "B    B", "BAAAAB", "      ")
                        .aisle("AAAAAA", "A CC A", "A CC A", "AAAAAA", "      ")
                        .aisle("AAAAAA", "B    B", "B    B", "BAAAAB", "      ")
                        .aisle("EIOMAA", "AABBAA", "AABBAA", "AABBAA", "      ")
                        .where('A', CASING_PTFE_INERT.getDefaultState())
                        .where('K', definition, Direction.EAST)
                        .where('C', CASING_STAINLESS_CLEAN)
                        .where('B', HERMETIC_CASING_LV)
                        .where('E', ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('I', ITEM_IMPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('O', ITEM_EXPORT_BUS[GTValues.LV], Direction.SOUTH)
                        .where('M', MAINTENANCE_HATCH, Direction.SOUTH);
                return shapeInfo;
            })
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Fertilizer Buck"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_inert_ptfe"),
                    GTCEu.id("block/multiblock/large_chemical_reactor"),
                    true)
            // .compassSections(GTCompassSections.TIER[MV])
            // .compassNodeSelf()
            .register();
}
