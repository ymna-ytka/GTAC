package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.api.registry.registrate.SoundEntryBuilder;

import net.ymnaytka.gtac.GTAC;

public class GTACSounds {

    public static void init() {}

    public static SoundEntryBuilder sound(String name) {
        return new SoundEntryBuilder(GTAC.id(name));
    }
}
