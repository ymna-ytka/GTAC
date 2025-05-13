package net.ymnaytka.gtac.common.data;

import com.gregtechceu.gtceu.api.data.chemical.Element;
import com.gregtechceu.gtceu.common.data.GTElements;

public class GTACElements {

    public static void init() {}

    public static final Element Vi = GTElements.createAndRegister(26, 25, -1, null, "Vitalized Iron", "Vi", false);
    public static final Element Tg = GTElements.createAndRegister(79, 78, -1, null, "Twilight Gold", "Tg", false);
    public static final Element Fi = GTElements.createAndRegister(52, 47, -1, null, "Fiery Iron", "Fi", false);
    public static final Element Bc = GTElements.createAndRegister(40, 85, -1, null, "Black Essence", "Bc", false);
    public static final Element Lt = GTElements.createAndRegister(40, 85, -1, null, "Light Essence", "Lt", false);
}
