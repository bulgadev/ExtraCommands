package com.blg;

// Fabric stuff
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.text.Text;
import com.blg.ClientCommands; // Adjust the package path to where your ClientCommands class is located
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.word;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeAccess;

//say that it is a client
@Environment(EnvType.CLIENT)
public class Blg1Client implements ClientModInitializer {


	@Override
	//do something when client start

	public void onInitializeClient() {
		HudRenderCallback.EVENT.register((context, tickDelta) -> {
			if (ClientCommands.disp()) {
				//so basically, this mc is the minecraft, u can get the screen with mc.currentSreen, the mc.player, render text mc.textRenderer
				MinecraftClient mc = MinecraftClient.getInstance();
				//gets player info
				ClientPlayerEntity player = mc.player;

				String cords = player.getBlockPos().toString();
				cords = cords.substring(cords.indexOf('x'), cords.indexOf('}') - 1);
				String dim = player.getWorld().getRegistryKey().getValue().toString();

				//if we in game
				if(mc.player != null) {
					context.drawText(mc.textRenderer, "Cordenates", 10, 130, 0xFFFFFFFF, false);
					context.drawText(mc.textRenderer, cords, 10, 140, 0xFFFFFFFF, false);
					context.drawText(mc.textRenderer, userBiome(player), 10, 150, 0xFFFFFFFF, false);
				}
			}
		});

		ClientCommandRegistrationCallback.EVENT.register((dispathcer, registryAcess) -> {
			ClientCommands.register(dispathcer);
		});
	}

	private String userBiome(ClientPlayerEntity player) {
		//cords
		BlockPos cords = player.getBlockPos();
		//get  player chunck
		World world = player.getEntityWorld();
		//biome finally
		String bi = world.getBiome(cords).toString();

		if (bi.contains(":")) {
			bi = bi.substring(bi.indexOf(':') + 18, bi.indexOf('=') - 1);
		}

		return bi;
	}

}