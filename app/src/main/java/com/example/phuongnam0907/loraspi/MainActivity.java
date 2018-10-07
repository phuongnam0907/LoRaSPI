package com.example.phuongnam0907.loraspi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
import com.google.android.things.pio.SpiDevice;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
    /*
    http://www.arduinolab.net/wp-content/uploads/2017/05/ArduinoUnoR3ISPconnector.jpg
    http://www.libelium.com/downloads/documentation/v12/waspmote-interruptions-programming_guide.pdf
    http://www.libelium.com/uploads/2013/02/waspmote-technical_guide_eng.pdf
    https://www.amebaiot.com/en/ameba-arduino-rtl8195-dragino-lora/
    https://github.com/dragino/Lora/blob/master/Lora%20Shield/Examples/lora_shield_ttn/lora_shield_ttn.ino
    http://wiki.dragino.com/index.php?title=Lora_Shield#Example1_--_Use_with_LMIC_library_for_LoRaWAN_compatible
    https://developer.android.com/things/hardware/raspberrypi#io-pinout
    https://www.instructables.com/id/Use-Lora-Shield-and-RPi-to-Build-a-LoRaWAN-Gateway/
    https://github.com/sandeepmistry/arduino-LoRa/blob/master/API.md
    https://github.com/sandeepmistry/arduino-LoRa
    https://github.com/Lora-net/LoRaMac-node/issues/92

    */

public class MainActivity extends Activity {

    RegisterAddress regAdd;
    private static final String TAG = MainActivity.class.getSimpleName();
    private SpiDevice mDevice;
    private Timer timer;
    private Gpio mGpio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PeripheralManager manager = PeripheralManager.getInstance();
        Log.d(TAG,"List of Devices support SPI : "+ manager.getSpiBusList());
        try {
            mGpio = manager.openGpio("BCM25");
            Log.d(TAG,"Name: " + mGpio.getName());

            mGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);

            mDevice = manager.openSpiDevice("SPI0.0");
            Log.d(TAG,"Name: " + mDevice.getName());
            configSPIDevice(mDevice);

            sendConfig();
            setup_Timer();
            //spiRead(RegisterAddress.RH_RF95_REG_1F_SYMB_TIMEOUT_LSB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mGpio != null) {
            try {
                mGpio.close();
                mGpio = null;
            } catch (IOException e) {
                Log.w(TAG, "Unable to close GPIO", e);
            }
        }


        if (mDevice != null) {
            try {
                mDevice.close();
                mDevice = null;
            } catch (IOException e) {
                Log.w(TAG, "Unable to close SPI device", e);
            }
        }

    }

    private void configSPIDevice(SpiDevice device) throws IOException {
        device.setMode(SpiDevice.MODE1);
        device.setFrequency(RegisterValue.RH_RF95_FXOSC); //1MHz
        device.setBitJustification(SpiDevice.BIT_JUSTIFICATION_MSB_FIRST);
        device.setBitsPerWord(8);
        Log.d(TAG,"SPI OK now ....");

    }

    public void sendCommand(SpiDevice device, byte[] buffer) throws IOException {

        //device.write(buffer, buffer.length);

        //read the response
        byte[] response = new byte[64];
        //device.read(response, response.length);
        device.transfer(buffer, response, buffer.length);

        for(int i = 0; i< 39; i++) {
            Log.d(TAG, "Response byte " + Integer.toHexString(i) + " is: " + Integer.toHexString(response[i]));
        }
        //if (response[1] != -120) sendConfig();

    }

    private void setup_Timer(){
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                try {
                    spiRead(RegisterAddress.RH_RF95_REG_09_PA_CONFIG);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //sendConfig();
            }
        };
        timer.schedule(timerTask,20000, 10000);
    }


    // Full-duplex data transfer
    public void sendCommandFullDuplex(SpiDevice device, byte[] buffer) throws IOException {
        byte[] response = new byte[buffer.length];
        device.transfer(buffer, response, buffer.length);
    }

    private void setText(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
    private void sendConfig(){
        try {
            byte[] test_data = new byte[64]; //Totally, 4 bytes are sent by the master
            test_data[0] = (byte)(0x00); //0x00 //FIFO
            test_data[1] = (byte)(0x8b); //0x01 //Use LoRa mode + Sleep mode
            test_data[2] = (byte)(0x00); //0x02 **RESERVED**
            test_data[3] = (byte)(0x00); //0x03 **RESERVED**
            test_data[4] = (byte)(0x00); //0x04 **RESERVED**
            test_data[5] = (byte)(0x00); //0x05 **RESERVED**
            test_data[6] = (byte)(0x6C); //0x06 /*  Set Frequency                 */
            test_data[7] = (byte)(0x80); //0x07 /*  MSB of RF carrier frequency   */
            test_data[8] = (byte)(0x00); //0x08 /*  0x6C8000 = 434 MHz; page 107  */
            test_data[9] = (byte)(0x4f); //0x09 //0f if use PA_BOOST
            test_data[10] = (byte)(0x09); //0x0A //Use default 40us
            test_data[11] = (byte)(0x20); //0x0B //20 or 1F
            test_data[12] = (byte)(0x00); //0x0C //default
            test_data[13] = (byte)(0x00); //0x0D ???????
            test_data[14] = (byte)(0x80); //0x0E ???????
            test_data[15] = (byte)(0x00); //0x0F ???????
            test_data[16] = (byte)(0x00); //0x10 **READ**
            test_data[17] = (byte)(0x00); //0x11 ???????
            test_data[18] = (byte)(0x00); //0x12 **READ/CLEAR**
            test_data[19] = (byte)(0x00); //0x13 **READ**
            test_data[20] = (byte)(0x00); //0x14 **READ**
            test_data[21] = (byte)(0x00); //0x15 **READ**
            test_data[22] = (byte)(0x00); //0x16 **READ/CLEAR**
            test_data[23] = (byte)(0x00); //0x17 **READ**
            test_data[24] = (byte)(0x10); //0x18 **READ**
            test_data[25] = (byte)(0x00); //0x19 **READ**
            test_data[26] = (byte)(0x00); //0x1A **READ**
            test_data[27] = (byte)(0x00); //0x1B **READ**
            test_data[28] = (byte)(0x00); //0x1C **READ**
            test_data[29] = (byte)(0x72); //0x1D //125 kHz + 4/5
            test_data[30] = (byte)(0x74); //0x1E //128 chips / symbol
            test_data[31] = (byte)(0x64); //0x1F ???????
            test_data[32] = (byte)(0x00); //0x20 ???????
            test_data[33] = (byte)(0x08); //0x21 ???????
            test_data[34] = (byte)(0x01); //0x22 ???????
            test_data[35] = (byte)(0xFF); //0x23 ???????
            test_data[36] = (byte)(0x00); //0x24 ???????
            test_data[37] = (byte)(0x00); //0x25 **READ**
            test_data[38] = (byte)(0x04); //0x26 ???????
            test_data[39] = (byte)(0x00); //0x27 **RESERVED**
            test_data[40] = (byte)(0x00); //0x28 **RESERVED**
            test_data[41] = (byte)(0x00); //0x29 **RESERVED**
            test_data[42] = (byte)(0x00); //0x2A **RESERVED**
            test_data[43] = (byte)(0x00); //0x2B **RESERVED**
            test_data[44] = (byte)(0x00); //0x2C **RESERVED**
            test_data[45] = (byte)(0x00); //0x2D **RESERVED**
            test_data[46] = (byte)(0x00); //0x2E **RESERVED**
            test_data[47] = (byte)(0x00); //0x2F **RESERVED**
            test_data[48] = (byte)(0x00); //0x30 **RESERVED**
            test_data[49] = (byte)(0x00); //0x31 **RESERVED**
            test_data[50] = (byte)(0x00); //0x32 **RESERVED**
            test_data[51] = (byte)(0x00); //0x33 **RESERVED**
            test_data[52] = (byte)(0x00); //0x34 **RESERVED**
            test_data[53] = (byte)(0x00); //0x35 **RESERVED**
            test_data[54] = (byte)(0x00); //0x36 **RESERVED**
            test_data[55] = (byte)(0x00); //0x37 **RESERVED**
            test_data[56] = (byte)(0x00); //0x38 **RESERVED**
            test_data[57] = (byte)(0x00); //0x39 **RESERVED**
            test_data[58] = (byte)(0x00); //0x3A **RESERVED**
            test_data[59] = (byte)(0x00); //0x3B **RESERVED**
            test_data[60] = (byte)(0x00); //0x3C **RESERVED**
            test_data[61] = (byte)(0x00); //0x3D **RESERVED**
            test_data[62] = (byte)(0x00); //0x3E **RESERVED**
            test_data[63] = (byte)(0x00); //0x3F **RESERVED**
            mGpio.setValue(true);
            sendCommand(mDevice, test_data);
            mGpio.setValue(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG,"sent!! ~ ");
    }

    private void spiRead(byte reg) throws IOException {
        mGpio.setValue(true);
        byte[] response = new byte[10];
        byte[] dataSend = new byte[2];
        for(int i = 0; i< 10; i++) {
            response[i] = 1;
        }
        for(int i = 0; i< 10; i++) {
            Log.d(TAG, "Response byte " + Integer.toHexString(i) + " is: " + Integer.toHexString(response[i]));
        }
        dataSend[0] = (byte) (reg & ~RegisterValue.RH_SPI_WRITE_MASK);
        dataSend[1] = 0;
        mDevice.transfer(dataSend,response,dataSend.length);
        mGpio.setValue(false);
        for(int i = 0; i< 10; i++) {
            Log.d(TAG, "Response byte " + Integer.toHexString(i) + " is: " + Integer.toHexString(response[i]));
        }
    }
}
