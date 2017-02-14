package ru.yalymar.piped;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author slavalymar
 * @since 14.02.2017
 * @version 1
 */
public class Piped {

    private int countRead;

    public int getCountRead() {
        return countRead;
    }

    public void init(){

        try(PipedInputStream pin = new PipedInputStream();
            PipedOutputStream pout = new PipedOutputStream(pin)){

            this.countRead = 0;
            byte[] toRead = new byte[100];
            while(this.countRead<toRead.length){
                for(int i = 0; i<Math.random()*10; i++){

                    //write
                    pout.write((byte) Math.random()*127);
                }

                // check available bytes
                int willRead = pin.available();

                System.out.println("available "+willRead);
                if(willRead+this.countRead > toRead.length){

                    System.out.println("buffer is overflow!");
                    willRead = toRead.length-this.countRead;
                    this.countRead += pin.read(toRead, countRead, willRead);

                    // read 100 bytes
                    System.out.println("has been read "+this.countRead+ " bytes");
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Piped().init();
    }
}
