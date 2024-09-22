package com.tangent.sorting;

import com.tangent.sorting.controls.MainController;

import javax.sound.midi.*;

public class Audio {
    private Synthesizer synthesizer;
    private MidiChannel channel;

    public static final float minPitch = 1;
    public static final float maxPitch = 50;
    public static final float midPitch = (minPitch + maxPitch) / 2;
    public static final float minVolume = 0;
    public static final float maxVolume = 10;


    public Audio() {
        try {
            this.synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
            this.channel = synthesizer.getChannels()[0];
            channel.programChange(getInstrument("square").getPatch().getProgram());

        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }


    public void playSound(int n) {
        if (!channel.getMute()) {
            stopSound();
            channel.noteOn(28 + 40 * (n) / MainController.arrayController.getLength(), 90);
        }
    }

    public void mute() {
        channel.allNotesOff();
        channel.setMute(!channel.getMute());
    }

    public void stopSound() {
        channel.allSoundOff();
        channel.allNotesOff();
    }

    public void setPitch(double num) {
        float scale = 8192 / midPitch;
        channel.setPitchBend((int) (8192 + (num - midPitch) * scale));
    }

    public void setVolume(int num) {
        float scale = 125 / maxVolume;
        channel.controlChange(7, (int) (num * scale));
    }

    public int getVolume() {
        float scale = 125 / maxVolume;
        return (int) (channel.getController(7) / scale);
    }

    private Instrument getInstrument(String name) {
        for (Instrument instrument : synthesizer.getLoadedInstruments()) {
            if (instrument.getName().toLowerCase().contains(name)) {
                return instrument;
            }
        }
        return null;
    }

}
