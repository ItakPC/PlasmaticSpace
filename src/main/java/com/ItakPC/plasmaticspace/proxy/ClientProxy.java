package com.ItakPC.plasmaticspace.proxy;

import com.ItakPC.plasmaticspace.cable.TileEntityEnergyConduit;
import com.ItakPC.plasmaticspace.cable.TileEntityRenderEnergyConduit;
import com.ItakPC.plasmaticspace.machine.windmill.RendererWindTurbine;
import com.ItakPC.plasmaticspace.machine.windmill.TileWindTurbine;
import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    public void registerProxies() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileWindTurbine.class, new RendererWindTurbine());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEnergyConduit.class, new TileEntityRenderEnergyConduit());
    }

}
