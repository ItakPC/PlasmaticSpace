package com.ItakPC.plasmaticspace.proxy;

import com.ItakPC.plasmaticspace.cable.TileEntityEnergyConduit;
import com.ItakPC.plasmaticspace.cable.TileEntityRenderEnergyConduit;
import com.ItakPC.plasmaticspace.machine.windmill.TileEntityRenderWindmill;
import com.ItakPC.plasmaticspace.machine.windmill.TileEntityWindmill;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public void registerProxies() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityRenderWindmill());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyConduit.class, new TileEntityRenderEnergyConduit());
    }

}
