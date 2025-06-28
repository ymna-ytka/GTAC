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
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.WorkableSteamMachineRenderer;
import com.gregtechceu.gtceu.common.data.*;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.CokeOvenMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.primitive.PrimitiveBlastFurnaceMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.steam.SteamParallelMultiblockMachine;
import com.gregtechceu.gtceu.config.ConfigHolder;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.machine.GTACSMachines;
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
import static com.gregtechceu.gtceu.common.data.machines.GTMachineUtils.*;
import static net.ymnaytka.gtac.common.data.GTACMaterials.*;
import static net.ymnaytka.gtac.common.registry.GTACRegistration.REGISTRATE;

public class GTACMachines {

    static {
        GTACRegistration.REGISTRATE.creativeModeTab(() -> GTACreativeModTab.GTACore);
    }

    public static void init() {
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

    public static final Pair<MachineDefinition, MachineDefinition> STEAM_VULCANIZER = registerSteamMachines(
            "steam_vulcanizer", SimpleSteamMachine::new, (pressure, builder) -> builder
                    .rotationState(RotationState.NON_Y_AXIS)
                    .recipeType(GTACRecipeTypes.VULCANIZER)
                    .recipeModifier(SimpleSteamMachine::recipeModifier)
                    .addOutputLimit(ItemRecipeCapability.CAP, 1)
                    .renderer(() -> new WorkableSteamMachineRenderer(pressure,
                            GTCEu.id("block/machines/chemical_reactor")))
                    .register());

    public static final MultiblockMachineDefinition BRONZE_VAT = REGISTRATE
            .multiblock("bronze_vat", SteamParallelMultiblockMachine::new)
            .langValue("Bronze Vat")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.BRONZE_VAT)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle(" AAA ", " AAA ")
                    .aisle("ACCCA", "ACCCA")
                    .aisle("ACCDE", "AC CA")
                    .aisle("ACDCA", "ACCCA")
                    .aisle(" AEA ", " AKA ")
                    .where('A', Predicates.blocks(CASING_PRIMITIVE_BRICKS.get()).setMinGlobalLimited(16)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1)
                                    .setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                            .setPreviewCount(1))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where('E', blocks(FIREBOX_BRONZE.get()))
                    .where('C', blocks(GCYMBlocks.CASING_INDUSTRIAL_STEAM.get()))
                    .where('D', blocks(CASING_BRONZE_PIPE.get()))
                    .where(' ', any())
                    .build())
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Bronze Vat"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTAC.id("block/multiblock/smoking"),
                    true)
            .register();

    public static final MultiblockMachineDefinition ALLOY_BRICK_SMELTER = REGISTRATE
            .multiblock("alloy_brick_smelter", SteamParallelMultiblockMachine::new)
            .langValue("Alloy Brick Smelter")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.STONE_BRICK_SMELTER_RECIPE)
            .appearanceBlock(GTACBlocks.MASONRY_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "A A", "AAA")
                    .aisle("AAA", "A A", "AAA")
                    .aisle("AAA", "A A", "AAA")
                    .aisle("AAA", "A A", "AAA")
                    .aisle("AAA", "AKA", "AAA")
                    .where('A', blocks(GTACBlocks.MASONRY_BRICKS.get()).setMinGlobalLimited(47)
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1)
                                    .setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1)
                                    .setPreviewCount(1)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Stone Blast Furnace"))
            .workableCasingRenderer(
                    GTAC.id("block/casing/masonry_bricks"),
                    GTAC.id("block/multiblock/smoking"),
                    true)
            .register();

    public static final MultiblockMachineDefinition STONE_BLAST_FURNACE = REGISTRATE
            .multiblock("stone_blast_furnace", SteamParallelMultiblockMachine::new)
            .langValue("SBF (Stone Blast Furnace)")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.STONE_BLAST_FURNACE_RECIPE)
            .appearanceBlock(GTACBlocks.MASONRY_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", " A ", " A ")
                    .aisle("AAA", "A A", "A A", "A A")
                    .aisle("AAA", "AKA", " A ", " A ")
                    .where('A', blocks(GTACBlocks.MASONRY_BRICKS.get()).setMinGlobalLimited(22)
                            .or(Predicates.abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1)
                                    .setPreviewCount(1))
                            .or(Predicates.abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1)
                                    .setPreviewCount(1)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())

            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Stone Blast Furnace"))
            .workableCasingRenderer(
                    GTAC.id("block/casing/masonry_bricks"),
                    GTAC.id("block/multiblock/steam-pss_template"),
                    true)
            .register();

    public static final MultiblockMachineDefinition REFRACTORY_COKE_OVEN = REGISTRATE
            .multiblock("refractory_coke_oven", CokeOvenMachine::new)
            .langValue("Refractory Coke Oven")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.COKE_OVEN_RECIPES)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("     ", "     ", "     ", "  A  ", "  A  ")
                    .aisle(" CCC ", "  A  ", "  A  ", " A A ", " A A ")
                    .aisle(" CCC ", " A A ", " A A ", "A   A", "A   A")
                    .aisle(" CCC ", "  K  ", "  A  ", " A A ", " A A ")
                    .aisle("     ", "     ", "     ", "  A  ", "  A  ")
                    .where("C", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Iron)))
                    .where("A", blocks(CASING_PRIMITIVE_BRICKS.get()).setMinGlobalLimited(23))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Coke Oven"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTAC.id("block/multiblock/steam-pss_template"),
                    true)
            .register();

    public static final MultiblockMachineDefinition REFRACTORY_ALLOY_FURNACE = REGISTRATE
            .multiblock("refractory_alloy_furnace", PrimitiveBlastFurnaceMachine::new)
            .langValue("Refractory Alloy Furnace")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.REFRACTORY_ALLOY_FURNACE_RECIPE)
            .appearanceBlock(CASING_PRIMITIVE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA")
                    .aisle("AAA", "A A", "A A")
                    .aisle("AAA", "A A", "A A")
                    .aisle("AAA", "AKA", "AAA")
                    .where("A", blocks(CASING_PRIMITIVE_BRICKS.get()).setMinGlobalLimited(31))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Refractory Alloy Furnace"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_primitive_bricks"),
                    GTAC.id("block/multiblock/steam-pss_template"),
                    true)
            .register();

    public static final MultiblockMachineDefinition RIVER_DIGGER = REGISTRATE
            .multiblock("river_digger", SteamParallelMultiblockMachine::new)
            .langValue("River Digger")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.RIVER_DIGGER)
            .appearanceBlock(GCYMBlocks.CASING_INDUSTRIAL_STEAM)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("ABA", " B ", " B ", "AAA")
                    .aisle("ABA", "   ", "   ", "ABA")
                    .aisle("ABA", "   ", "   ", "ABA")
                    .aisle("AKA", " B ", " B ", "AAA")
                    .where('A', blocks(GCYMBlocks.CASING_INDUSTRIAL_STEAM.get()).setMinGlobalLimited(16)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM).setMinGlobalLimited(1).setPreviewCount(1)))
                    .where('B', Predicates.frames(GTMaterials.TreatedWood))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "River Digger"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/gcym/industrial_steam_casing"),
                    GTCEu.id("block/multiblock/steam_dredger"),
                    false)
            .register();

    public static final MultiblockMachineDefinition COAGULATION_TANK = REGISTRATE
            .multiblock("coagulation_tank", SteamParallelMultiblockMachine::new)
            .langValue("Coagulation Tank")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTACRecipeTypes.COAGULATION_TANK)
            .appearanceBlock(GTACBlocks.WOOD_LEAD_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA", "AAA")
                    .aisle("AAA", "A A", "A A", "AAA")
                    .aisle("AAA", "ABA", "ABA", "AAA")
                    .aisle("AAA", "ABA", "ABA", "AAA")
                    .aisle("AAA", "A A", "A A", "AAA")
                    .aisle("AAA", "AKA", "AAA", "AAA")
                    .where('A', blocks(GTACBlocks.WOOD_LEAD_CASING.get()).setMinGlobalLimited(60)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1)))
                    .where('B', Predicates.frames(GTMaterials.Iron))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Coagulation Tank"))
            .workableCasingRenderer(
                    GTAC.id("block/casing/wl_casing"),
                    GTCEu.id("block/multiblock/multiblock_tank"),
                    false)
            .register();

    public static final MultiblockMachineDefinition BRONZE_BRICKED_BLAST_FURNACE = REGISTRATE
            .multiblock("bronze_bricked_blast_furnace", PrimitiveBlastFurnaceMachine::new)
            .langValue("Bronze Bricked Blast Furnace")
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeType(GTRecipeTypes.PRIMITIVE_BLAST_FURNACE_RECIPES)
            .appearanceBlock(CASING_BRONZE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAA", "AAA", "AAA", "AAA")
                    .aisle("AAA", "A A", "A A", "A A")
                    .aisle("AAA", "AKA", "AAA", "AAA")
                    .where('A', blocks(CASING_BRONZE_BRICKS.get()).setMinGlobalLimited(28)
                            .or(abilities(PartAbility.STEAM_EXPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM_IMPORT_ITEMS).setMinGlobalLimited(1).setPreviewCount(1))
                            .or(abilities(PartAbility.STEAM).setMinGlobalLimited(1).setPreviewCount(1)))
                    .where('K', controller(blocks(definition.getBlock())))
                    .where(' ', any())
                    .build())
            .tooltips(
                    Component.translatable("gtceu.machine.available_recipe_map_1.tooltip", "Primitive Blast Furnace"))
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTAC.id("block/multiblock/steam-pss_template"),
                    false)
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
                            .or(abilities(PartAbility.STEAM))
                            .or(Predicates.abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1)))
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

    public static final MultiblockMachineDefinition FERTILIZER_BUCK = REGISTRATE
            .multiblock("fertilizer_buck", WorkableElectricMultiblockMachine::new)
            .langValue("Fertilizer Buck")
            .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
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
