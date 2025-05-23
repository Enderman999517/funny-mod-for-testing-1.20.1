/*
 * This file was created by Pyrofab. It was originally distributed as
 * part of Dead Man's Abyss Mod. Get the Source Code on github:
 * https://github.com/Ladysnake/DeadManAbyss
 */
#version 120

attribute vec4 Position;

uniform mat4 ProjMat;
uniform vec2 InSize;
uniform vec2 OutSize;

varying vec2 texCoord;
varying vec4 vPosition;

void main(){
    vec4 outPos = ProjMat * vec4(Position.xy, 0.0, 1.0);
    gl_Position = vec4(outPos.xy, 0.2, 1.0);
    vPosition = gl_Position;

    texCoord = Position.xy / OutSize;
}