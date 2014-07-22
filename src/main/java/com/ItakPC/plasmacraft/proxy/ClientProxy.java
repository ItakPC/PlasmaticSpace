package com.ItakPC.plasmacraft.proxy;

import com.ItakPC.plasmacraft.machine.windmill.TileEntityRenderWindmill;
import com.ItakPC.plasmacraft.machine.windmill.TileEntityWindmill;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public void registerProxies() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWindmill.class, new TileEntityRenderWindmill());
    }

}
