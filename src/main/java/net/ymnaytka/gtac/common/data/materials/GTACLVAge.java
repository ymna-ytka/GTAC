package net.ymnaytka.gtac.common.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;

import net.ymnaytka.gtac.GTAC;
import net.ymnaytka.gtac.common.data.GTACElements;
import net.ymnaytka.gtac.common.data.GTACMaterials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

public class GTACLVAge {

    public static void init() {
        GTACMaterials.CarbideSilicon = new Material.Builder(GTAC.id("carbide_silicon"))
                .ingot()
                .color(0xc2C2C2C)
                .components(Silicon, 5, Carbon, 3)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_FOIL

                )
                .blastTemp(2730, BlastProperty.GasTier.LOW, 100, 4800)
                .buildAndRegister();

        GTACMaterials.Ecoferum = new Material.Builder(GTAC.id("ecoferum"))
                .ingot()
                .color(0x3CA474)
                .secondaryColor(0xa9b1b8)
                .components(Iron, 6, Nickel, 2, Rubber, 1, Copper, 1)
                .iconSet(DULL)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_FRAME)
                .buildAndRegister();

        GTACMaterials.ColdQuartz = new Material.Builder(GTAC.id("cold_quartz"))
                .gem()
                .color(0xe6f3ff)
                .iconSet(QUARTZ)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_FRAME)
                .buildAndRegister().setFormula("Si₁₃O₄H₄Al₁₂Ca₁₆Na₁₆", true);

        GTACMaterials.VitalizedIron = new Material.Builder(GTAC.id("vitalized_iron"))
                .ingot()
                .color(0x75f096)
                .secondaryColor(0xc5e8cf)
                .element(GTACElements.Vi)
                .iconSet(DULL)
                .flags(
                        MaterialFlags.GENERATE_PLATE)
                .blastTemp(1500, BlastProperty.GasTier.LOW, 96, 2000)
                .buildAndRegister();

        GTACMaterials.InfusedIron = new Material.Builder(GTAC.id("infused_iron"))
                .ingot()
                .color(0x0fd146)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_RING,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FOIL,
                        MaterialFlags.GENERATE_BOLT_SCREW)
                .blastTemp(1600, BlastProperty.GasTier.LOW, 80, 2300)
                .buildAndRegister().setFormula("?(NaCl)(H₂O)Vi₄Nt₄Tg₂", true);

        GTACMaterials.SkyIron = new Material.Builder(GTAC.id("sky_iron"))
                .ingot()
                .color(0x7ab8fa)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_RING,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FOIL,
                        MaterialFlags.GENERATE_BOLT_SCREW)
                .blastTemp(1800, BlastProperty.GasTier.LOW, 320, 2300)
                .buildAndRegister();

        GTACMaterials.TaintedGold = new Material.Builder(GTAC.id("tainted_gold"))
                .ingot()
                .color(0xe87410)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_RING,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FOIL,
                        MaterialFlags.GENERATE_BOLT_SCREW)
                .blastTemp(1700, BlastProperty.GasTier.LOW, 100, 2100)
                .buildAndRegister().setFormula("?₂Au₂Vi₂Cu₂C₂", true);

        GTACMaterials.DepthIron = new Material.Builder(GTAC.id("depth_iron"))
                .ingot()
                .color(0x5f3c85)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_RING,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FOIL,
                        MaterialFlags.GENERATE_BOLT_SCREW)
                .blastTemp(2700, BlastProperty.GasTier.LOW, 512, 2100)
                .buildAndRegister();

        GTACMaterials.TwilightGold = new Material.Builder(GTAC.id("twilight_gold"))
                .ingot()
                .ore()
                .color(0x9c7106)
                .element(GTACElements.Tg)
                .iconSet(BRIGHT)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_GEAR)
                .blastTemp(1400, BlastProperty.GasTier.LOW, 100, 160)
                .buildAndRegister();

        GTACMaterials.FieryIron = new Material.Builder(GTAC.id("fiery_iron"))
                .ingot()
                .color(0x370d0d)
                .secondaryColor(0xecb521)
                .element(GTACElements.Fi)
                .iconSet(METALLIC)
                .flags(
                        MaterialFlags.GENERATE_PLATE,
                        MaterialFlags.GENERATE_ROD,
                        MaterialFlags.GENERATE_RING,
                        MaterialFlags.GENERATE_FRAME,
                        MaterialFlags.GENERATE_GEAR,
                        MaterialFlags.GENERATE_FOIL,
                        MaterialFlags.GENERATE_BOLT_SCREW)
                .blastTemp(1700, BlastProperty.GasTier.LOW, 90, 2800)
                .buildAndRegister();

        GTACMaterials.LeafGold = new Material.Builder(GTAC.id("leaf_gold"))
                .dust()
                .color(0xf9de18)
                .secondaryColor(0xf9d518)
                .element(GTACElements.Fi)
                .iconSet(BRIGHT)
                .flags(
                        MaterialFlags.FORCE_GENERATE_BLOCK)
                .buildAndRegister().setFormula("Nr₂Tg", true);

        GTACMaterials.SoulSand = new Material.Builder(GTAC.id("soul_sand"))
                .dust()
                .color(0x4c362c)
                .secondaryColor(0x2a1f19)
                .iconSet(DULL)
                .buildAndRegister();

        GTACMaterials.BlackEssence = new Material.Builder(GTAC.id("black_essence"))
                .ingot()
                .liquid()
                .ore()
                .element(GTACElements.Bc)
                .color(0x000000)
                .secondaryColor(0x151616)
                .iconSet(FINE)
                .blastTemp(1650, BlastProperty.GasTier.LOW, 120, 3000)
                .buildAndRegister();

        GTACMaterials.LightEssence = new Material.Builder(GTAC.id("light_essence"))
                .ingot()
                .liquid()
                .ore()
                .color(0xffffff)
                .secondaryColor(0xececec)
                .element(GTACElements.Lt)
                .iconSet(FINE)
                .blastTemp(1640, BlastProperty.GasTier.LOW, 120, 2800)
                .buildAndRegister();

        GTACMaterials.NIALSteel = new Material.Builder(GTAC.id("nickel_aluminized_steel"))
                .ingot()
                .color(0x3F3F3F)
                .iconSet(METALLIC)
                .buildAndRegister();

        GTACMaterials.TwilightGas = new Material.Builder(GTAC.id("twilight_gas"))
                .gas()
                .color(0xb69e74)
                .iconSet(DULL)
                .buildAndRegister();

        GTACMaterials.LiquidTwilightGas = new Material.Builder(GTAC.id("liquid_twilight_gas"))
                .liquid()
                .color(0xb69e74)
                .iconSet(DULL)
                .buildAndRegister();

        GTACMaterials.ColdWater = new Material.Builder(GTAC.id("cold_water"))
                .liquid()
                .color(0x52CEFB)
                .iconSet(DULL)
                .buildAndRegister().setFormula("H₂O", true);

        GTACMaterials.SodiumCarbonate = new Material.Builder(GTAC.id("sodium_carbonate"))
                .liquid()
                .color(0xcabafb2)
                .iconSet(DULL)
                .buildAndRegister().setFormula("Na₂CO₃", true);

        GTACMaterials.MagicalEssence = new Material.Builder(GTAC.id("magical_essence"))
                .liquid()
                .color(0x6f7f0d)
                .iconSet(DULL)
                .buildAndRegister().setFormula("?", true);

        GTACMaterials.FieryMagicWater = new Material.Builder(GTAC.id("fiery_magic_water"))
                .liquid()
                .color(0x6c1414)
                .iconSet(DULL)
                .buildAndRegister().setFormula("?", true);

        GTACMaterials.OverworldAura = new Material.Builder(GTAC.id("overworld_aura"))
                .liquid()
                .color(0xB6F746)
                .iconSet(DULL)
                .buildAndRegister().setFormula("Nr₂", true);

        GTACMaterials.NetherAura = new Material.Builder(GTAC.id("nether_aura"))
                .liquid()
                .color(0x7e1a0b)
                .iconSet(DULL)
                .buildAndRegister().setFormula("Nr3", true);

        GTACMaterials.PollutedWater = new Material.Builder(GTAC.id("polluted_water"))
                .liquid()
                .color(0x4d4f4a)
                .iconSet(DULL)
                .buildAndRegister();
    }
}
