//#version 150
//
//uniform ivec4 ViewPort;
//uniform float STime;
//uniform sampler2D DiffuseSampler;
//uniform int Rendering;
//
//in vec2 texCoord;
//out vec4 fragColor;
//out vec4 fragCoord;
//
//
//float random (in vec2 _st) {
//    return fract(sin(dot(_st.xy,
//                         vec2(12.9898,78.233)))*
//                 43758.5453123);
//}
//
//// Based on Morgan McGuire @morgan3d
//// https://www.shadertoy.com/view/4dS3Wd
//float noise (in vec2 _st) {
//    vec2 i = floor(_st);
//    vec2 f = fract(_st);
//
//    // Four corners in 2D of a tile
//    float a = random(i);
//    float b = random(i + vec2(1.0, 0.0));
//    float c = random(i + vec2(0.0, 1.0));
//    float d = random(i + vec2(1.0, 1.0));
//
//    vec2 u = f * f * (3.0 - 2.0 * f);
//
//    return mix(a, b, u.x) +
//    (c - a)* u.y * (1.0 - u.x) +
//    (d - b) * u.x * u.y;
//}
//
//float fbm ( in vec2 _st) {
//    float v = 0.0;
//    float a = 0.5;
//    vec2 shift = vec2(1000.0);
//    // Rotate to reduce axial bias
//    mat2 rot = mat2(cos(0.5), sin(0.5),
//    -sin(0.5), cos(0.50));
//    for (int i = 0; i < 5; ++i) {
//        v += a * noise(_st);
//        _st = rot * _st * 2.0 + shift;
//        a *= 0.5;
//    }
//    return v;
//}
//
//void main() {
//    vec2 uv = texCoord;
//    vec4 tex = texture2D(DiffuseSampler, texCoord);
//
//    vec2 st = gl_FragCoord.xy/vec2(ViewPort.xy);
//    // st += st * abs(sin(u_time*0.1)*3.0);
//    vec3 color = vec3(0.0);
//
//    vec2 q = vec2(0.);
//    q.x = fbm( st + 1.00*STime);
//    q.y = fbm( st + vec2(1.0));
//
//    vec2 r = vec2(0.);
//    r.x = fbm( st + 1.0*q + vec2(1.7,9.2)+ 0.15*STime);
//    r.y = fbm( st + 1.0*q + vec2(8.3,2.8)+ 0.126*STime);
//
//    float f = fbm(st+r);
//
//    color = mix(vec3(0.101961,0.619608,0.666667),
//                vec3(0.666667,0.666667,0.498039),
//                clamp((f*f)*4.0,0.0,1.0));
//
//    color = mix(color,
//                vec3(0,0,0.164706),
//                clamp(length(q),0.0,1.0));
//
//    color = mix(color,
//                vec3(0.666667,1,1),
//                clamp(length(r.x),0.0,1.0));
//
//    float warp = pow((fbm(uv + 0.05*STime))*1.4142135623730950488,Rendering);
//    uv *= vec2(warp, warp);
//    fragColor = texture(DiffuseSampler, uv);
//}

//#version 150
//uniform ivec4 ViewPort;
//uniform float STime;
//uniform sampler2D DiffuseSampler;
//uniform int Rendering;
//
//in vec2 texCoord;
//out vec4 fragColor;
//out vec2 fragCoord;
//
//vec4 tex = texture2D(DiffuseSampler, texCoord);
//
//float rand(vec2 n) {
//    return fract(sin(dot(n, vec2(12.9898, 4.1414))) * 43758.5453);
//}
//
//float noise(vec2 p){
//    vec2 ip = floor(p);
//    vec2 u = fract(p);
//    u = u*u*(3.0-2.0*u);
//
//    float res = mix(
//        mix(rand(ip),rand(ip+vec2(1.0,0.0)),u.x),
//        mix(rand(ip+vec2(0.0,1.0)),rand(ip+vec2(1.0,1.0)),u.x),u.y);
//    return res*res;
//}
//
//const mat2 mtx = mat2(0.80, 0.60, -0.60, 0.80);
//
//float fbm(vec2 p)
//{
//    float f = 0.0;
//
//    f += 0.500000*noise(p + STime); p = mtx*p*2.02;
//    f += 0.031250*noise(p); p = mtx*p*2.01;
//    f += 0.250000*noise(p); p = mtx*p*2.03;
//    f += 0.125000*noise(p); p = mtx*p*2.01;
//    f += 0.062500*noise(p); p = mtx*p*2.04;
//    f += 0.015625*noise(p + sin(STime));
//
//    return f/0.96875;
//}
//
//float pattern(in vec2 p) {
//    return fbm(p + fbm(p + fbm(p)));
//}
//
//void main()
//{
//    vec2 uv = fragCoord/ViewPort.x;
//    float shade = pattern(uv);
//    fragColor = vec4(tex.rgb, shade);
//}


//#version 150
//
//uniform sampler2D DiffuseSampler;
//uniform vec2 InSize;
//uniform float STime;
//uniform int Rendering;
//
//in vec2 texCoord;
//out vec4 fragColor;
//
//void main() {
//    vec2 uv = texCoord;
//
//    // Apply a sin-based warp
//    float warpX = Rendering*(sin(uv.y * 10. + STime) * 0.05);
//    float warpY = Rendering*(sin(uv.x * 10. + STime) * 0.05);
//
//    uv += vec2(warpX, warpY);
//
//    fragColor = texture(DiffuseSampler, uv);
//}

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
    //fragColor = texture(DiffuseSampler, uv+Rendering*0.75*(vec2(powerY, powerX)*warpDistance));
    vec4 tex = texture2D(DiffuseSampler, uv+Rendering*0.75*(vec2(powerY, powerX)*warpDistance));
    //fragColor = vec4(tex.r+1.-pow(1.,Rendering),tex.g+1.-pow(g,Rendering),tex.b+1-pow(b,Rendering), 1.);
    fragColor = vec4(tex.r*pow(r,Rendering),tex.g*pow(g,Rendering),tex.b*pow(b,Rendering), 1.);
}
