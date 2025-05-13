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
            .properties(p -> p.stacksTo(64))
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

    public static <T extends ComponentItem> NonNullConsumer<T> attach(IItemComponent... components) {
        return item -> item.attachComponents(components);
    }

    public static void init() {}
}
