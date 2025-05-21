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

    public static ItemEntry<Item> Pebbles = REGISTRATE
            .item("pebbles", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Mini_Cobblestone = REGISTRATE
            .item("mini_cobblestone", Item::new)
            .properties(p -> p.stacksTo(32))
            .register();

    public static ItemEntry<Item> Solar_Element = REGISTRATE
            .item("solar_element", Item::new)
            .properties(p -> p.stacksTo(16))
            .register();

    public static ItemEntry<Item> Copper_Voltage_Coil = REGISTRATE
            .item("copper_voltage_coil", Item::new)
            .properties(p -> p.stacksTo(64))
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

    public static ItemEntry<Item> Piece_Wooden_Rubber = REGISTRATE
            .item("piece_wooden_rubber", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Iron_Bloom = REGISTRATE
            .item("iron_bloom", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static ItemEntry<Item> Refined_Iron_Bloom = REGISTRATE
            .item("refined_iron_bloom", Item::new)
            .properties(p -> p.stacksTo(64))
            .register();

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {}
}
