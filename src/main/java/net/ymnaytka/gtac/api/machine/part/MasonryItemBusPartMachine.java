package net.ymnaytka.gtac.api.machine.part;

import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;

public class MasonryItemBusPartMachine extends ItemBusPartMachine {

    public MasonryItemBusPartMachine(IMachineBlockEntity holder, IO io, Object... args) {
        super(holder, 1, io, args);
    }
}
