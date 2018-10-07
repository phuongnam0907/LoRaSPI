package com.example.phuongnam0907.loraspi;

import com.google.android.things.pio.SpiDevice;

import java.io.IOException;

public class RegisterFunction {
    SpiDevice spiDevice;

    public void init(){

    }

    public void spiRead(byte reg) throws IOException {
        byte[] response = new byte[2];
        byte[] dataSend = new byte[2];
        dataSend[0] = (byte) (reg & ~RegisterValue.RH_SPI_WRITE_MASK);
        dataSend[1] = (byte) 0x00;
        spiDevice.transfer(dataSend,response,dataSend.length);
    }

    public void spiWrite(byte reg, byte val){

    }

    public void spiBurstRead(byte reg, byte dest, byte len){

    }

    public void spiBurstWrite(byte reg, byte dest, byte len){

    }

    public void setSlaveSelectPin(byte slaveSelectPin){

    }

    public void send (){

    }

    public void recv(){

    }
}