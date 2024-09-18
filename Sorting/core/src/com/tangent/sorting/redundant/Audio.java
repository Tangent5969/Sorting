package com.tangent.sorting.redundant;

/*
import com.tangent.utils.Utils;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

public class Audio implements Runnable{
    static final int bufferSize = 32;
    static final int bufferCount = 32;
    static final int sampleRate = 44100;
    static final int minFrequency = 150;
    static final int maxFrequency = 750;
    static double frequency;
    static int wavePos;

    private final int[] buffers = new int[bufferCount];
    private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
    private final long context = alcCreateContext(device, new int[1]);
    private final int source;
    private int bufferIndex;

    boolean generate;
    private boolean running;
    private boolean closed;

    private boolean pulse;
    private int pulseCount;


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

    public void pulse(int n) {
        frequency = Utils.mapToScale(n, 0, Controller.totalElements - 1, minFrequency, maxFrequency);
        pulse = true;
        play();

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
        if (pulse) {
            if (pulseCount == 50) {
                pulseCount = 0;
                generate = false;
            }
            pulseCount++;
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
 */