#version 110

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulate;

varying vec2 texCoord;

void main() {

    gl_FragColor = texture2D(DiffuseSampler, texCoord) * ColorModulate * 1.7; //brightness factor

}