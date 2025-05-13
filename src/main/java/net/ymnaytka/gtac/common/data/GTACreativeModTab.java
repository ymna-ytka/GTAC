package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;

import net.minecraft.world.item.CreativeModeTab;
import net.ymnaytka.gtac.GTAC;

import com.tterrag.registrate.util.entry.RegistryEntry;

import static net.ymnaytka.gtac.common.registry.GTACRegistration.REGISTRATE;

public class GTACreativeModTab {

    public static RegistryEntry<CreativeModeTab> CORE = REGISTRATE.defaultCreativeTab("core",
            builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("core", REGISTRATE))
                    .title(REGISTRATE.addLang("itemGroup", GTAC.id("core"), "GT Aurora Core"))
                    .build())
            .register();
}
