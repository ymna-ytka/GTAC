package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.api.item.component.IItemComponent;

import net.minecraft.world.item.Item;
import net.ymnaytka.gtac.common.registry.GTACRegistration;

import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;

import static net.ymnaytka.gtac.common.registry.GTACRegistration.REGISTRATE;

public class GTACItems {

    static {
        GTACRegistration.REGISTRATE.creativeModeTab(() -> GTACreativeModTab.CORE);
    }

    public static ItemEntry<Item> RawCleansedLeather = REGISTRATE
            .item("raw_cleansed_leather", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> TannedLeather = REGISTRATE
            .item("tanned_leather", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> TrophyTablet = REGISTRATE
            .item("trophy_tablet", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> EssenceNaga = REGISTRATE
            .item("essence_naga", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> EssenceLich = REGISTRATE
            .item("essence_lich", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> EssenceMinoshroom = REGISTRATE
            .item("essence_minoshroom", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> EssenceHydra = REGISTRATE
            .item("essence_hydra", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> EssenceUrGhast = REGISTRATE
            .item("essence_ur_ghast", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> EssenceAlphaYeti = REGISTRATE
            .item("essence_alpha_yeti", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> EssenceSnowQueen = REGISTRATE
            .item("essence_snow_queen", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> PhantomArmorSet = REGISTRATE
            .item("phantom_armor_set", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> Pebbles = REGISTRATE
            .item("pebbles", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Mini_Cobblestone = REGISTRATE
            .item("mini_cobblestone", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Wood_Mold = REGISTRATE
            .item("wood_mold", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> Stone_Ingot = REGISTRATE
            .item("stone_ingot", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Stone_Mold = REGISTRATE
            .item("stone_mold", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> Stone_Mold_Block = REGISTRATE
            .item("stone_mold_block", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> Solar_Element = REGISTRATE
            .item("solar_element", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> Copper_Voltage_Coil = REGISTRATE
            .item("copper_voltage_coil", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Whisk = REGISTRATE
            .item("whisk", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Electric_Motor = REGISTRATE
            .item("blank_lv_electric_motor", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Conveyor = REGISTRATE
            .item("blank_lv_conveyor", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Electric_Piston = REGISTRATE
            .item("blank_lv_electric_piston", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Electric_Pump = REGISTRATE
            .item("blank_lv_electric_pump", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Robot_Arm = REGISTRATE
            .item("blank_lv_robot_arm", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Sensor = REGISTRATE
            .item("blank_lv_sensor", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Emitter = REGISTRATE
            .item("blank_lv_emitter", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_LV_Field_Generator = REGISTRATE
            .item("blank_lv_field_generator", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Twilight_Gem = REGISTRATE
            .item("twilight_gem", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> Tanning_Mixture = REGISTRATE
            .item("tanning_mixture", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Steam_Piston = REGISTRATE
            .item("steam_piston", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Steam_Robot_Arm = REGISTRATE
            .item("steam_robot_arm", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Steam_Motor = REGISTRATE
            .item("steam_motor", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Steam_Pump = REGISTRATE
            .item("steam_pump", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Blank_Steam_Piston = REGISTRATE
            .item("blank_steam_piston", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_Steam_Robot_Arm = REGISTRATE
            .item("blank_steam_robot_arm", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_Steam_Motor = REGISTRATE
            .item("blank_steam_motor", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Blank_Steam_Pump = REGISTRATE
            .item("blank_steam_pump", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Wrough_Iron_Bender_Path = REGISTRATE
            .item("wrough_iron_bender_path", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Carbide_Silicon_Orb = REGISTRATE
            .item("carbide_silicon_orb", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Fill_Carbide_Silicone_Orb = REGISTRATE
            .item("fill_carbide_silicon_orb", Item::new)
            .properties(p -> p.stacksTo(4))
            .register();

    public static ItemEntry<Item> Plant_Fibers = REGISTRATE
            .item("plant_fibers", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Plant_Fiber_Mesh = REGISTRATE
            .item("plant_fiber_mesh", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Empty_Flask = REGISTRATE
            .item("empty_flask", Item::new)
            .properties(p -> p.stacksTo(4))
            .register();

    public static ItemEntry<Item> Glass_Balloon = REGISTRATE
            .item("glass_balloon", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> Ballon_Mold = REGISTRATE
            .item("ballon_mold", Item::new)
            .properties(p -> p.stacksTo(1))
            .register();

    public static ItemEntry<Item> Raw_Aluminium_Ingot = REGISTRATE
            .item("raw_aluminium_ingot", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Clean_Aluminium_Ingot = REGISTRATE
            .item("clean_aluminium_ingot", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> High_Quality_Aluminium_Ingot = REGISTRATE
            .item("high_quality_aluminium_ingot", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Flint_Dye = REGISTRATE
            .item("flint_dye", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Cleansed_Leather = REGISTRATE
            .item("cleansed_leather", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {}
}
