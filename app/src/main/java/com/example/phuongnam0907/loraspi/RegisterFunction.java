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

    public boolean restart(){
        return false;
    }
/*
    void SPICmd8bit(byte WrPara)
    {
        int bitcnt;

        digitalWrite(nsel, false);

        digitalWrite(sck, false);

        for (bitcnt=8; bitcnt != 0; bitcnt--)
        {
            digitalWrite(sck, false);

            if (WrPara & (byte) 0x80) {
                digitalWrite(mosi, true);
            }
            else {
                digitalWrite(mosi, false);
            }

            digitalWrite(sck, true);
            WrPara <<= 1;
        }

        digitalWrite(sck, false);
        digitalWrite(mosi, true);
    }

    unsigned char SPIRead8bit(void)
    {
        unsigned char RdPara = 0;
        unsigned char bitcnt;

        digitalWrite(nsel, false);
        digitalWrite(mosi, true);

        for ( bitcnt=8; bitcnt !=0; bitcnt--) {
            digitalWrite(sck, false);
            RdPara <<= 1;
            digitalWrite(sck, true);

            if(digitalRead(miso)) {
                RdPara |= 0x01;
            }
            else {
                RdPara |= 0x00;
            }

        }
        digitalWrite(sck, false);
        return(RdPara);
    }


    unsigned char SPIRead(unsigned char adr)
    {
        unsigned char tmp;
        SPICmd8bit(adr);
        tmp = SPIRead8bit();
        digitalWrite(nsel, true);
        return(tmp);
    }


    void SPIWrite(unsigned char adr, unsigned char WrPara)
    {
        digitalWrite(nsel, false);
        SPICmd8bit(adr|0x80);
        SPICmd8bit(WrPara);

        digitalWrite(sck, false);
        digitalWrite(mosi, true);
        digitalWrite(nsel, true);
    }


    void SPIBurstRead(unsigned char adr, unsigned char *ptr, unsigned char leng)
    {
        unsigned char i;
        if (leng<=1) {
            return;
        }
        else {
            digitalWrite(sck, false);
            digitalWrite(nsel, false);
            SPICmd8bit(adr);

            for (i=0; i<leng; i++ ) {
                ptr[i] = SPIRead8bit();
            }

            digitalWrite(nsel, true);
        }
    }


    void BurstWrite(unsigned char adr, unsigned char *ptr, unsigned char leng)
    {
        unsigned char i;

        if (leng <= 1) {
            return;
        }
        else {
            digitalWrite(sck, false);
            digitalWrite(nsel, false);
            SPICmd8bit(adr|0x80);

            for (i=0; i<leng; i++) {
                SPICmd8bit(ptr[i]);
            }
            digitalWrite(nsel, true);
        }
    }
    */
}