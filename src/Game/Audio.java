package Game;

import javax.sound.sampled.*;
import java.io.*;

public class Audio {

    private AudioFormat audioFormat;
    private byte[] samples;

    private boolean stopRequested = false;


    public Audio(String filename) {
        try {
            AudioInputStream stream = AudioSystem
                    .getAudioInputStream(new File(filename));

            audioFormat = stream.getFormat();

            samples = getSamples(stream);
        } catch(UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public byte[] getSamples() {
        return samples;
    }

    private byte[] getSamples(AudioInputStream audioInputStream) {
        int length = (int) audioInputStream.getFrameLength() * audioFormat.getFrameSize();

        byte[] samples = new byte[length];
        DataInputStream inputStream = new DataInputStream(audioInputStream);

        try {
            inputStream.readFully(samples);
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return samples;
    }

    public void play(InputStream source) {
        int bufferSize = audioFormat.getFrameSize() *
                Math.round(audioFormat.getSampleRate() / 10);

        byte[] buffer = new byte[bufferSize];

        SourceDataLine line;

        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
            return;
        }

        line.start();

        try {
            int numBytesRead = 0;
            while(numBytesRead != -1) {
                numBytesRead = source.read(buffer, 0, buffer.length);

                if(numBytesRead != -1) {
                    line.write(buffer, 0, numBytesRead);
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        line.drain();
        line.close();
    }


    public void playRepeatedly() {
        stopRequested = false; // Reset the stop flag
        int bufferSize = audioFormat.getFrameSize() *
                Math.round(audioFormat.getSampleRate() / 10);

        byte[] buffer = new byte[bufferSize];

        SourceDataLine line;

        try {
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(audioFormat);
            line.start();
        } catch (LineUnavailableException ex) {
            ex.printStackTrace();
            return;
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(samples);

        while (!stopRequested) {
            int numBytesRead = 0;
            while (numBytesRead != -1 && !stopRequested) {
                numBytesRead = inputStream.read(buffer, 0, buffer.length);

                if (numBytesRead != -1) {
                    line.write(buffer, 0, numBytesRead);
                }
            }
            inputStream.reset(); // Reset the stream for looping
        }

        line.drain();
        line.close();
    }
}
