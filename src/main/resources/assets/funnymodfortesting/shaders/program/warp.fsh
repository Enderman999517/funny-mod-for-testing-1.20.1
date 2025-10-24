#version 150

uniform sampler2D DiffuseSampler;
uniform float STime;
uniform int Rendering;

out vec4 fragColor;
in vec2 fragCoord;
in vec2 texCoord;
uniform ivec4 ViewPort;
uniform sampler2D WarpBase;


void main() {
    float r = 0.55;
    float g = 0.85;
    float b = 1.;
    float warpSpeed =0.3;
    float warpDistance =0.02;
    vec2 uv = texCoord;
    vec4 warpTex = texture(WarpBase,texCoord);
    vec3 offTexX = texture(WarpBase, uv).rgb;
    vec3 luma = vec3(1.299, 0.587, 0.114);
    float powerX = dot(offTexX, luma);
    float powerY = dot(offTexX, luma);

                        /*freq*/
    powerX = sin(3.1415927*2.0*mod(powerX+STime*warpSpeed, 1.));
    powerY = sin(3.1415927*2.0*mod(powerY+STime*warpSpeed, 1.));
                                                 /*amplitude*/
    vec4 tex = texture2D(DiffuseSampler, uv+Rendering*0.75*(vec2(powerY, powerX)*warpDistance));
    fragColor = vec4(tex.r*pow(r,Rendering),tex.g*pow(g,Rendering),tex.b*pow(b,Rendering), 1.);
}
