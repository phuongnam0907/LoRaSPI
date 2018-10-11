/*package com.example.phuongnam0907.loraspi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;
import com.google.android.things.pio.SpiDevice;
import com.google.android.things.pio.UartDevice;
import com.google.android.things.pio.UartDeviceCallback;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;*/

    /**
     * Skeleton of an Android Things activity.
     * <p>
     * Android Things peripheral APIs are accessible through the class
     * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
     * set it to HIGH:
     * <p>
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
    public class MainActivity extends Activity implements View.OnClickListener, NPNHomeView{

        private static final String TAG = "NPNIoTs";

        //GPIO Configuration Parameters
        private static final String LED_PIN_NAME = "BCM26"; // GPIO port wired to the LED
        private Gpio mLedGpio;

        //SPI Configuration Parameters
        private static final String SPI_DEVICE_NAME = "SPI0.1";
        private SpiDevice mSPIDevice;
        private static final String CS_PIN_NAME = "BCM12"; // GPIO port wired to the LED
        private static final String CS1_PIN_NAME = "BCM21"; // GPIO port wired to the LED
        private static final String CS2_PIN_NAME = "BCM8"; // GPIO port wired to the LED

        private Gpio mCS;
        private Gpio mCS1, mCS2;


        // UART Configuration Parameters
        private static final int BAUD_RATE = 115200;
        private static final int DATA_BITS = 8;
        private static final int STOP_BITS = 1;
        private UartDevice mUartDevice;

        byte[] test_data = new byte[]{0,(byte)0x8b,0,(byte)0x8b};

        byte[] temp_data = new byte[]{0,0};

        //private HandlerThread mInputThread;
        //private Handler mInputHandler;



        public enum ADC_STATE{
            ADC0, ADC1, ADC2, ADC3, ADC4, ADC5, ADC6, ADC7,
            WAIT_ADC0, WAIT_ADC1, WAIT_ADC2, WAIT_ADC3, WAIT_ADC4, WAIT_ADC5, WAIT_ADC6, WAIT_ADC7
        }
        ADC_STATE adc_state;

        private static final int CHUNK_SIZE = 512;

        Timer mBlinkyTimer;

        private Button btnNetworkConnect;
        private Button btnCloseApp;

        private EditText txtTemperature;// = findViewById(R.id.txtTemperature);
        private EditText txtPressure;// = findViewById(R.id.txtPressure);
        private EditText txtUsername;
        private EditText txtPassword;
        private TextView txtConsole;
        private TextView txtIPAddress;

        NPNHomeViewModel mHomeViewModel;

        private int intChannelADC0;
        private int intChannelADC1;
        private int intChannelADC2;
        private int intChannelADC3;

        private int intChannelADC4;
        private int intChannelADC5;
        private int intChannelADC6;
        private int intChannelADC7;

        private double dblTemperature;
        private double dblpH;
        private double dblSalinity;
        private double dblNH3;
        private double dblNO3;
        private double dblDO;
        private String date_time = "";

        int testCounter = 0;
        String name = "KSD";
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            btnNetworkConnect = findViewById(R.id.btnNetworkConnect);
            btnCloseApp = findViewById(R.id.btnCloseApp);

            txtUsername = findViewById(R.id.txtUsername);
            txtPassword = findViewById(R.id.txtPassword);
            txtConsole = findViewById(R.id.txtConsole);
            txtIPAddress = findViewById(R.id.txtIPAddress);

            txtTemperature = findViewById(R.id.txtTemperature);
            txtPressure = findViewById(R.id.txtPressure);
            final Context context= this;
            mHomeViewModel = new NPNHomeViewModel();
            mHomeViewModel.attach(this, this);


            initGPIO();
            initUart();
            initSPI();
            setupBlinkyTimer();


            btnNetworkConnect.setOnClickListener(this);
            btnCloseApp.setOnClickListener(this);

            btnCloseApp.requestFocus();

            adc_state = ADC_STATE.ADC0;

            btnNetworkConnect.requestFocus();
            //connectToNPN();
        }




        @Override
        public void onSuccessUpdateServer(String message) {
            //txtConsole.setText("Request server is successful");
            Log.d(TAG, "Request server is successful");
        }

        @Override
        public void onErrorUpdateServer(String message) {
            txtConsole.setText("Request server is fail");
            Log.d(TAG, "Request server is fail");
        }


        public void connectToNPN()
        {
            String txtUserName = "NPN";
            String txtPassWord = "npnlab2018";
            WifiConfiguration wifiConfig = new WifiConfiguration();
            wifiConfig.SSID = String.format("\"%s\"", txtUserName);
            wifiConfig.preSharedKey = String.format("\"%s\"", txtPassWord);

            //txtConsole.setText("Connecting...");
            Log.d("NPNServer","Connecting to Network");

            WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
            //remember id
            int netId = wifiManager.addNetwork(wifiConfig);
            wifiManager.disconnect();
            wifiManager.enableNetwork(netId, true);
            wifiManager.reconnect();
            Log.d("NPNServer","Connected to Network");
            //txtConsole.setText("Connected!!!!");
        }

        @Override
        public void onClick(View view) {

            if(view == btnNetworkConnect)
            {
                String txtUserName = txtUsername.getText().toString();
                String txtPassWord = txtPassword.getText().toString();
                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", txtUserName);
                wifiConfig.preSharedKey = String.format("\"%s\"", txtPassWord);

                txtConsole.setText("Connecting...");

                WifiManager wifiManager = (WifiManager)getSystemService(WIFI_SERVICE);
                //remember id
                int netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
                txtConsole.setText("Connected!!!!");
            }
            if(view == btnCloseApp)
            {
                try {
                    closeUart();
                    closeSPI();
                    mBlinkyTimer.cancel();
                    android.os.Process.killProcess(android.os.Process.myPid());
                    //finish();
                }
                catch (Throwable t) {
                }
            }
        }

        public void sendServerStatus() {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://iot.vtctelecom.com.vn/api/device/status/?apiKey=123&deviceId=V-01");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept","application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);

                        JSONObject jsonParam = new JSONObject();
                        jsonParam.put("timestamp", 1488873360);


                        Log.i("JSON", jsonParam.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));
                        os.writeBytes(jsonParam.toString());

                        os.flush();
                        os.close();

                        Log.i("NPNServer", String.valueOf(conn.getResponseCode()));
                        Log.i("NPNServer" , conn.getResponseMessage());

                        conn.disconnect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }

        public void sendServerData() {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        URL url = new URL("http://iot.vtctelecom.com.vn/api/device/data/?apiKey=123&deviceId=V-01");
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                        conn.setRequestProperty("Accept","application/json");
                        conn.setDoOutput(true);
                        conn.setDoInput(true);

                        JSONObject jsonParam = new JSONObject();
                        jsonParam.put("timestamp", 1488873360);


                        Log.i("JSON", jsonParam.toString());
                        DataOutputStream os = new DataOutputStream(conn.getOutputStream());
                        //os.writeBytes(URLEncoder.encode(jsonParam.toString(), "UTF-8"));

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddaHH:mm:ss");
                        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
                        String date_time =sdf.format(new Date()) + "Z";
                        if(date_time.indexOf("PM") >=0)
                        {
                            date_time= date_time.replace("PM","T");
                        }
                        else
                        {
                            date_time= date_time.replace("AM","S");
                        }


                        dblDO = dblDO * 10;
                        Log.d("NPNIoTs", "Temperature is: " + String.format("%.1f", dblTemperature));
                        Log.d("NPNIoTs", "pH is: " + String.format("%.1f", dblpH));
                        Log.d("NPNIoTs", "SAL is: " + String.format("%.1f", dblSalinity));
                        Log.d("NPNIoTs", "NH3 is: " + String.format("%.1f", dblNH3));
                        Log.d("NPNIoTs", "NO3 is: " + String.format("%.1f", dblNO3));
                        Log.d("NPNIoTs", "DO is: " + String.format("%.1f", dblDO));






                        String data = "[\n" +
                                "  {\n" +
                                "    \"SensorId\": \"P1\",\n" +
                                "    \"value\": \"" + String.format("%.1f",dblTemperature)  +  "\",\n" +
                                "    \"time\": \""+ date_time+ "\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"SensorId\": \"P2\",\n" +
                                "    \"value\": \"" + String.format("%.1f", dblpH) + "\",\n" +
                                "    \"time\": \""+date_time+"\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"SensorId\": \"P3\",\n" +
                                "    \"value\": \""+ String.format("%.1f",dblSalinity) + "\",\n" +
                                "    \"time\": \""+date_time+"\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"Id\": \"P4\",\n" +
                                "    \"SensorId\": \"P4\",\n" +
                                "    \"value\": \""+String.format("%.1f",dblNH3)+"\",\n" +
                                "    \"time\": \""+date_time+"\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"SensorId\": \"P5\",\n" +
                                "    \"value\": \""+String.format("%.1f", dblNO3)+"\",\n" +
                                "    \"time\": \""+date_time+"\"\n" +
                                "  },\n" +
                                "  {\n" +
                                "    \"SensorId\": \"P6\",\n" +
                                "    \"value\": \""+String.format("%.1f", dblDO)+"\",\n" +
                                "    \"time\": \""+date_time+"\"\n" +
                                "  }\n" +
                                "]";
                        os.writeBytes(data);

                        os.flush();
                        os.close();

                        Log.i("STATUS DATA", String.valueOf(conn.getResponseCode()));
                        Log.i("MSG MESSAGE" , conn.getResponseMessage());

                        conn.disconnect();

                        String urlChipFCServer = "http://demo1.chipfc.com/SensorValue/PushData?[0].id=8&[0].value="
                                + String.format("%.2f",dblTemperature)
                                + "&[1].id=9&[1].value=" + String.format("%.2f",dblpH)
                                + "&[2].id=10&[2].value=" + String.format("%.2f",dblSalinity)
                                + "&[3].id=11&[3].value=" + String.format("%.2f",dblNH3)
                                + "&[4].id=12&[4].value=" + String.format("%.2f",dblNO3)
                                + "&[5].id=13&[5].value=" + String.format("%.2f",dblDO);
                        mHomeViewModel.updateToServer(urlChipFCServer);

                        Log.d(TAG, urlChipFCServer);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();
        }


        private void setupBlinkyTimer()
        {
            mBlinkyTimer = new Timer();
            TimerTask blinkyTask = new TimerTask() {
                @Override
                public void run() {

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String display = "IP Address: " + Ultis.getWifiIPAddress(MainActivity.this);

                            if(Ultis.checkWifiConnected(MainActivity.this) == true)
                            {
                                display += " Wifi connected";
                            }
                            else if(Ultis.checkLanConnected(MainActivity.this) == true){
                                display += " Ethernet connected";
                            }
                            else
                            {
                                display += " No connection";
                                connectToNPN();
                            }
                            txtIPAddress.setText(display);
                        }
                    });


                    testCounter++;
                    if(testCounter > 10)
                    {
                        sendServerStatus();
                        sendServerData();
                        testCounter = 0;
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mLedGpio.setValue(!mLedGpio.getValue());

                                switch (adc_state)
                                {
                                    case ADC0:
                                        test_data[0] = 0x40;
                                        test_data[2] = 0x40;

                                        mCS2.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC0;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC1:
                                        test_data[0] = 0x50;
                                        test_data[2] = 0x50;

                                        mCS2.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC1;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC2:
                                        test_data[0] = 0x60;
                                        test_data[2] = 0x60;

                                        mCS2.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC2;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC3:
                                        test_data[0] = 0x70;
                                        test_data[2] = 0x70;

                                        mCS2.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC3;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC4:
                                        test_data[0] = 0x40;
                                        test_data[2] = 0x40;

                                        mCS1.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC4;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC5:
                                        test_data[0] = 0x50;
                                        test_data[2] = 0x50;

                                        mCS1.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC5;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC6:
                                        test_data[0] = 0x60;
                                        test_data[2] = 0x60;

                                        mCS1.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC6;
                                        sendCommand(mSPIDevice, test_data);
                                        break;
                                    case ADC7:
                                        test_data[0] = 0x70;
                                        test_data[2] = 0x70;

                                        mCS1.setValue(false);
                                        for(int i = 0; i < 1000; i++) {}

                                        adc_state = ADC_STATE.WAIT_ADC7;
                                        sendCommand(mSPIDevice, test_data);
                                        break;

                                }


                                txtConsole.setText( Integer.toString(testCounter) + ":   "
                                        //+  Integer.toString(intChannelADC0) + " - "
                                        //+  Integer.toString(intChannelADC1) + " - "
                                        +  "T: " + Integer.toString((int)(dblTemperature*10)) + " - "  //adc2
                                        +  "pH: " + Integer.toString((int)(dblpH * 10)) + " - "        //adc3
                                        +  "SAL: " + Integer.toString((int)(dblSalinity *10)) + " - "  //adc4
                                        +  "NH3: " + Integer.toString((int)(dblNH3 *10)) + " - "       //adc5
                                        +  "NO3: " + Integer.toString((int)(dblNO3 * 10)) + " - "       //adc6
                                        +  "DO: " + Integer.toString((int)(dblDO * 10)));
                            }catch(Throwable t)
                            {
                                Log.d(TAG, "Error in Blinky LED " + t.getMessage());
                            }
                        }
                    });
                }
            };
            mBlinkyTimer.schedule(blinkyTask, 10000, 1000);
        }

        public void writeUartData(UartDevice uart) {
            try {
                byte[] buffer = {'t'};
                int count = uart.write(buffer, buffer.length);
                Log.d(TAG, "Wrote " + count + " bytes to peripheral");
            }catch (IOException e)
            {
                Log.d(TAG, "Error on UART");
            }
        }


        private void initSPI()
        {
            PeripheralManager manager = PeripheralManager.getInstance();
            List<String> deviceList = manager.getSpiBusList();
            if(deviceList.isEmpty())
            {
                Log.d(TAG,"No SPI bus is not available");
            }
            else
            {
                Log.d(TAG,"SPI bus available: " + deviceList);
                //check if SPI_DEVICE_NAME is in list
                try {
                    mSPIDevice = manager.openSpiDevice(SPI_DEVICE_NAME);

                    mSPIDevice.setMode(SpiDevice.MODE1);
                    mSPIDevice.setFrequency(1000000);
                    mSPIDevice.setBitsPerWord(8);
                    mSPIDevice.setBitJustification(SpiDevice.BIT_JUSTIFICATION_MSB_FIRST);


                    Log.d(TAG,"SPI: OK... ");


                }catch (IOException e)
                {
                    Log.d(TAG,"Open SPI bus fail... ");
                }
            }
        }

        private int temperatureVal = 0;




        private void sendCommand(SpiDevice device, byte[] buffer) throws  IOException{

            device.write(buffer, buffer.length);

            //read the response
            byte[] response = new byte[4];
            device.read(response, response.length);

            mCS1.setValue(true);    mCS2.setValue(true);
            for(int i = 0; i < 1000; i++){}

            int data0 = response[0];
            int data1 = response[1];

            if(data0 < 0) data0 = 0;
            if(data1 < 0) data1+= 256;

            for(int i = 0; i< 2; i++) {

                //Log.d(TAG, "Response byte " + Integer.toString(i) + " is: " + response[i]);
            }




            double value = (double)(data0 * 256 + data1);
            double adc = value * 6.144/32768;

            //temperatureVal = (int) (adc * 100);



            txtTemperature.setText(Double.toString(adc));
            txtPressure.setText(Byte.toString(response[0]) + " " + Byte.toString(response[1]));


            double dblADC = adc * 1000;
            double raw = dblADC*1023/5000;

            if(adc_state == ADC_STATE.WAIT_ADC0)
            {
                intChannelADC3 = (int)(adc * 1000);

                //pH
                double slope_pH = -3.838;
                double intercept_pH = 13.720;
                dblpH  = adc * slope_pH + intercept_pH;

                adc_state = ADC_STATE.ADC1;

            }
            else if(adc_state == ADC_STATE.WAIT_ADC1)
            {
                intChannelADC0 = (int)(adc * 1000);
                adc_state = ADC_STATE.ADC2;

            }
            else if (adc_state == ADC_STATE.WAIT_ADC2)
            {
                intChannelADC1 = (int)(adc * 1000);
                adc_state = ADC_STATE.ADC3;


                if(Ultis.checkWifiConnected(this) || Ultis.checkLanConnected(this)) {
                    String urlNH4 = "http://demo1.chipfc.com/SensorValue/update?sensorid=3&sensorvalue=";
                    urlNH4 += intChannelADC2;
                    //mHomeViewModel.updateToServer(urlNH4);
                }

            }
            else if (adc_state == ADC_STATE.WAIT_ADC3)
            {
                intChannelADC2 = (int)(adc * 1000);
                adc_state = ADC_STATE.ADC4;

                double resistor=10000; //initialize value of fixed resistor in Vernier shield
                double resistance; //create local variable for resistance
                double temp; //create local variable for temperature

                resistance= Math.log(resistor*raw/(1024-raw)); //calculate resistance
                temp = 1 / (0.00102119 + (0.000222468 * resistance) + (0.000000133342 * resistance * resistance * resistance)); //calculate temperature using the Steinhart-Hart equation
                temp = temp - 273.15; //Convert Kelvin to Celsius

                dblTemperature = temp;

            }
            else if(adc_state == ADC_STATE.WAIT_ADC4)
            {
                intChannelADC7 = (int)(adc * 1000);

                dblDO = adc * 3.27 - 0.327;

                adc_state = ADC_STATE.ADC5;

            }
            else if(adc_state == ADC_STATE.WAIT_ADC5)
            {
                intChannelADC4 = (int)(adc * 1000);

                dblSalinity = adc * 16.3;

                adc_state = ADC_STATE.ADC6;

            }
            else if(adc_state == ADC_STATE.WAIT_ADC6)
            {
                intChannelADC5 = (int)(adc * 1000);

                dblNH3 = 4.714 * adc * 100 - 841.33;
                if(dblNH3 < 0) dblNH3 = 0;


                dblNH3 = 137.55 * adc - 168.2;

                dblNH3 = dblNH3/50;
                if(dblNH3 < 0) dblNH3 = 0;

                //dblNH3 = (dblNH3 - 60)/30;

                adc_state = ADC_STATE.ADC7;

            }
            else if(adc_state == ADC_STATE.WAIT_ADC7)
            {
                intChannelADC6 = (int)(adc * 1000);
                dblNO3 = adc * 137.55 - 168.2;

                dblNO3 = dblNO3/50;
                if(dblNO3 < 0) dblNO3 = 0;

                adc_state = ADC_STATE.ADC0;

            }
        }

        private void initGPIO()
        {
            PeripheralManager manager = PeripheralManager.getInstance();
            try {
                mLedGpio = manager.openGpio(LED_PIN_NAME);
                // Step 2. Configure as an output.
                mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);

                mCS = manager.openGpio(CS_PIN_NAME);
                mCS.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);

                mCS1 = manager.openGpio(CS1_PIN_NAME);
                mCS1.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);

                mCS2 = manager.openGpio(CS2_PIN_NAME);
                mCS2.setDirection(Gpio.DIRECTION_OUT_INITIALLY_HIGH);

            } catch (IOException e) {
                Log.d(TAG, "Error on PeripheralIO API");
            }
        }

        private void initUart()
        {
            try {
                openUart("UART0", BAUD_RATE);
            }catch (IOException e) {
                Log.d(TAG, "Error on UART API");
            }
        }
        */
/**
         * Callback invoked when UART receives new incoming data.
         *//*

        private UartDeviceCallback mCallback = new UartDeviceCallback() {
            @Override
            public boolean onUartDeviceDataAvailable(UartDevice uart) {
                //read data from Rx buffer
                try {
                    byte[] buffer = new byte[CHUNK_SIZE];
                    int noBytes = -1;
                    while ((noBytes = mUartDevice.read(buffer, buffer.length)) > 0) {
                        Log.d(TAG,"Number of bytes: " + Integer.toString(noBytes));

                        String str = new String(buffer,0,noBytes, "UTF-8");

                        Log.d(TAG,"Buffer is: " + str);


                    }
                } catch (IOException e) {
                    Log.w(TAG, "Unable to transfer data over UART", e);
                }
                return true;
            }

            @Override
            public void onUartDeviceError(UartDevice uart, int error) {
                Log.w(TAG, uart + ": Error event " + error);
            }
        };

        private void openUart(String name, int baudRate) throws IOException {
            mUartDevice = PeripheralManager.getInstance().openUartDevice(name);
            // Configure the UART
            mUartDevice.setBaudrate(baudRate);
            mUartDevice.setDataSize(DATA_BITS);
            mUartDevice.setParity(UartDevice.PARITY_NONE);
            mUartDevice.setStopBits(STOP_BITS);

            mUartDevice.registerUartDeviceCallback(mCallback);
        }

        private void closeUart() throws IOException {
            if (mUartDevice != null) {
                mUartDevice.unregisterUartDeviceCallback(mCallback);
                try {
                    mUartDevice.close();
                } finally {
                    mUartDevice = null;
                }
            }
        }

        private void closeSPI() throws IOException {
            if(mSPIDevice != null)
            {
                try {
                    mSPIDevice.close();
                }finally {
                    mSPIDevice = null;
                }

            }
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

            // Attempt to close the UART device
            try {
                closeUart();
                mUartDevice.unregisterUartDeviceCallback(mCallback);
                closeSPI();
            } catch (IOException e) {
                Log.e(TAG, "Error closing UART device:", e);
            }
        }
    }*/
