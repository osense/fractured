//FRACTAL JULIA false

precision highp float;

#define ITER 150

uniform vec2 u_translation;
uniform float u_zoom, u_aspectratio;
uniform sampler2D gradient;

varying vec2 TexCoord;

vec2 cmul(in vec2 a, in vec2 b)
{
    float r, i;
    r = a.x*b.x - a.y*b.y;
    i = a.x*b.y + a.y*b.x;
    return vec2(r, i);
}

void main()
{
    vec2 z = vec2(0.0, 0.0);
    vec2 coord = ((TexCoord + u_translation) * u_zoom) - vec2(u_zoom / 2.0) * vec2(1.0, u_aspectratio);
    int i = 0;

    for(; i < ITER; i++)
    {
        vec2 absz = vec2(abs(z.x), -abs(z.y));
        vec2 newz = cmul(absz, absz);
        newz += coord;

        if (length(newz) > 2.0) break;
        z = newz;
    }

    float escape = 0.0;
    if (i < ITER)
    {
        escape = float(i) / float(ITER);
    }

    vec3 color = texture2D(gradient, vec2(escape, 0.5)).rgb;
    gl_FragColor = vec4(color, 1.0);
}
