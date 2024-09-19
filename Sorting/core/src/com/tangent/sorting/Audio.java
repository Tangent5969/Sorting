package com.tangent.sorting;

import com.tangent.sorting.controls.MainController;

import javax.sound.midi.*;

public class Audio {
    private Synthesizer synthesizer;
    private MidiChannel channel;

    public static final float minPitch = 1;
    public static final float maxPitch = 50;
    public static final float midPitch = (minPitch + maxPitch) / 2;


    public Audio() {
        try {
            this.synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
            this.channel = synthesizer.getChannels()[10];
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

    public void setPitch(double n) {
        float scale = 8192 / midPitch;
        channel.setPitchBend((int) (8192 + (n - midPitch) * scale));
        System.out.println(channel.getPitchBend());
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
