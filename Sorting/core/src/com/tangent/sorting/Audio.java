package com.tangent.sorting;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class Audio implements Runnable{
    static final int bufferSize = 512;
    static final int bufferCount = 8;
    static final int sampleRate = 44100;
    static int frequency = 440;
    static int wavePos;

    private final int[] buffers = new int[bufferCount];
    private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
    private final long context = alcCreateContext(device, new int[1]);
    private final int source;
    private int bufferIndex;

    boolean generate;
    private boolean running;
    private boolean closed;

    Audio() {
        alcMakeContextCurrent(context);
        AL.createCapabilities(ALC.createCapabilities(device));
        source = alGenSources();
        for (int i = 0; i < bufferCount; i++) {
            bufferSamples(new short[0]);
        }
        alSourcePlay(source);
        catchInternalException();
    }

    @Override
    public synchronized void run() {
        while (!closed) {
            while (!running) {
                try {
                    this.wait();
                    if (closed) {
                        break;
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            int processedBuffers = alGetSourcei(source, AL_BUFFERS_PROCESSED);
            for (int i = 0; i < processedBuffers; i++) {
                short[] samples = generateSound();
                if (samples == null) {
                    running = false;
                    break;
                }

                alDeleteBuffers(alSourceUnqueueBuffers(source));
                buffers[bufferIndex] = alGenBuffers();
                bufferSamples(samples);
            }
            if (alGetSourcei(source, AL_SOURCE_STATE) != AL_PLAYING) {
                alSourcePlay(source);
            }
            catchInternalException();
        }
        alDeleteSources(source);
        alDeleteBuffers(buffers);
        alcDestroyContext(context);
        alcCloseDevice(device);
    }

    synchronized void play() {
        generate = true;
        running = true;
        notify();
    }

    void stop() {
        generate = false;
    }

    synchronized void close() {
        notify();
        closed = true;
    }

    boolean isRunning() {
        return running;
    }

    private short[] generateSound() {
        if (!generate) {
            return null;
        }
        short[] tones = new short[bufferSize];
        for (int i = 0; i < bufferSize; i++) {
            tones[i] = (short) (Short.MAX_VALUE * Math.sin((2 * Math.PI * frequency) / sampleRate * wavePos));
            wavePos++;
        }
        return tones;
    }

    private void bufferSamples(short[] samples) {
        int buffer = buffers[bufferIndex];
        bufferIndex++;
        alBufferData(buffer, AL_FORMAT_MONO16, samples, sampleRate);
        alSourceQueueBuffers(source, buffer);
        bufferIndex %= bufferCount;
    }

    private void catchInternalException() {
        int error = alcGetError(device);
        if (error != ALC_NO_ERROR) {
            throw new RuntimeException(String.valueOf(error));
        }
    }
}
