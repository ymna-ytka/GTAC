package net.ymnaytka.gtac.data;

import net.ymnaytka.gtac.common.registry.GTACRegistration;
import net.ymnaytka.gtac.data.lang.LangHandler;

import com.tterrag.registrate.providers.ProviderType;

public class GTACDataGen {

    public static void init() {
        GTACRegistration.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}
