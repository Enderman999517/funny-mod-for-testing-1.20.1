package net.enderman999517.funnymodfortesting;

import ladysnake.satin.api.event.PostWorldRenderCallback;
import ladysnake.satin.api.event.ShaderEffectRenderCallback;
import ladysnake.satin.api.experimental.ReadableDepthFramebuffer;
import ladysnake.satin.api.managed.ManagedShaderEffect;
import ladysnake.satin.api.managed.ShaderEffectManager;
import ladysnake.satin.api.managed.uniform.Uniform1f;
import ladysnake.satin.api.managed.uniform.Uniform1i;
import ladysnake.satin.api.managed.uniform.Uniform3f;
import ladysnake.satin.api.managed.uniform.UniformMat4;
import ladysnake.satin.api.util.GlMatrices;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;

public class DepthFx implements PostWorldRenderCallback, ShaderEffectRenderCallback, ClientTickEvents.EndTick {
    public static final Identifier FANCY_NIGHT_SHADER_ID = new Identifier(FunnyModForTesting.MOD_ID, "shaders/post/rainbow_ping.json");
    public static final Identifier WARP_ID = new Identifier(FunnyModForTesting.MOD_ID, "shaders/post/warp.json");
    public static final DepthFx INSTANCE = new DepthFx();

    private final MinecraftClient mc = MinecraftClient.getInstance();

    public final ManagedShaderEffect testShader = ShaderEffectManager.getInstance().manage(FANCY_NIGHT_SHADER_ID, shader -> {
        shader.setSamplerUniform("DepthSampler", ((ReadableDepthFramebuffer)mc.getFramebuffer()).getStillDepthMap());
        shader.setUniformValue("ViewPort", 0, 0, mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
    });
    public final ManagedShaderEffect warpShader = ShaderEffectManager.getInstance().manage(WARP_ID, shader -> {
        shader.setUniformValue("ViewPort", 0, 0, mc.getWindow().getFramebufferWidth(), mc.getWindow().getFramebufferHeight());
    });
    private final Uniform1f uniformSTime = testShader.findUniform1f("STime");
    private final Uniform1f uniformWarpSTime = warpShader.findUniform1f("STime");
    private final Uniform1i uniformWarpRendering = warpShader.findUniform1i("Rendering");
    private final UniformMat4 uniformInverseTransformMatrix = testShader.findUniformMat4("InverseTransformMatrix");
    private final Uniform3f uniformCameraPosition = testShader.findUniform3f("CameraPosition");
    private final Uniform3f uniformCenter = testShader.findUniform3f("Center");

    // fancy shader stuff
    private final Matrix4f projectionMatrix = new Matrix4f();
    private int ticks;

    private boolean isWorldNight(@Nullable PlayerEntity player) {
        if (player != null) {
            World world = player.getWorld();
            float celestialAngle = world.getSkyAngle(1.0f);
            return 0.23f < celestialAngle && celestialAngle < 0.76f;
        }
        return false;
    }

    @Override
    public void onEndTick(MinecraftClient minecraftClient) {
        if (!minecraftClient.isPaused()) {
            ticks++;
        }
    }

    @Override
    public void onWorldRendered(Camera camera, float tickDelta, long nanoTime) {
        MinecraftClient mc = MinecraftClient.getInstance();
        //if (isWorldNight(mc.player)) {
        //    uniformSTime.set((ticks + tickDelta) / 20f);
        //    uniformInverseTransformMatrix.set(GlMatrices.getInverseTransformMatrix(projectionMatrix));
        //    Vec3d cameraPos = camera.getPos();
        //    uniformCameraPosition.set((float)cameraPos.x, (float)cameraPos.y, (float)cameraPos.z); .
        //    Entity e = camera.getFocusedEntity();
        //    uniformCenter.set(lerpf(e.getX(), e.prevX, tickDelta), lerpf(e.getY(), e.prevY, tickDelta), lerpf(e.getZ(), e.prevZ, tickDelta));
        //}
        if (FunnyModForTestingClient.renderingWarp) {
            uniformWarpRendering.set(1);
            uniformWarpSTime.set((ticks + tickDelta) / 20f);
        } else uniformWarpRendering.set(0);
    }

    @Override
    public void renderShaderEffects(float tickDelta) {
        //testShader.render(tickDelta);
        warpShader.render(tickDelta);
    }

    private static float lerpf(double n, double prevN, float tickDelta) {
        return (float) MathHelper.lerp(tickDelta, prevN, n);
    }
}