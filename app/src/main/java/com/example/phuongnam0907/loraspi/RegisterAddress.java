package com.example.phuongnam0907.loraspi;

import com.google.android.things.pio.SpiDevice;

public class RegisterAddress {
    // Register names (LoRa Mode, from table 85)
    // RH_RF95_REG_**_RESERVED   is not used in LoRa mode.
    // RH_RF95_REG_**_UNUSED     is not used in LoRa mode.
    public static final byte RH_RF95_REG_00_FIFO                              = (byte) 0x00;    //FIFO read/write access
    public static final byte RH_RF95_REG_01_OP_MODE                           = (byte) 0x01;    //Operating mode & LoRa TM / FSK selection
    public static final byte RH_RF95_REG_02_RESERVED                          = (byte) 0x02;    //**NOT USED**
    public static final byte RH_RF95_REG_03_RESERVED                          = (byte) 0x03;    //**NOT USED**
    public static final byte RH_RF95_REG_04_RESERVED                          = (byte) 0x04;    //**NOT USED**
    public static final byte RH_RF95_REG_05_RESERVED                          = (byte) 0x05;    //**NOT USED**
    public static final byte RH_RF95_REG_06_FRF_MSB                           = (byte) 0x06;    //RF Carrier Frequency, Most Significant Bits
    public static final byte RH_RF95_REG_07_FRF_MID                           = (byte) 0x07;    //RF Carrier Frequency, Intermediate Bits
    public static final byte RH_RF95_REG_08_FRF_LSB                           = (byte) 0x08;    //RF Carrier Frequency, Least Significant Bits
    public static final byte RH_RF95_REG_09_PA_CONFIG                         = (byte) 0x09;    //PA selection and Output Power control
    public static final byte RH_RF95_REG_0A_PA_RAMP                           = (byte) 0x0a;    //Control of PA ramp time, low phase noise PLL
    public static final byte RH_RF95_REG_0B_OCP                               = (byte) 0x0b;    //Over Current Protection control
    public static final byte RH_RF95_REG_0C_LNA                               = (byte) 0x0c;    //LNA settings
    public static final byte RH_RF95_REG_0D_FIFO_ADDR_PTR                     = (byte) 0x0d;    //FIFO SPI pointer
    public static final byte RH_RF95_REG_0E_FIFO_TX_BASE_ADDR                 = (byte) 0x0e;    //Start Tx data
    public static final byte RH_RF95_REG_0F_FIFO_RX_BASE_ADDR                 = (byte) 0x0f;    //Start Rx data
    public static final byte RH_RF95_REG_10_FIFO_RX_CURRENT_ADDR              = (byte) 0x10;    //LoRa TM state flags
    public static final byte RH_RF95_REG_11_IRQ_FLAGS_MASK                    = (byte) 0x11;    //Optional flag mask
    public static final byte RH_RF95_REG_12_IRQ_FLAGS                         = (byte) 0x12;    //IF Frequency
    public static final byte RH_RF95_REG_13_RX_NB_BYTES                       = (byte) 0x13;    //IF Frequency
    public static final byte RH_RF95_REG_14_RX_HEADER_CNT_VALUE_MSB           = (byte) 0x14;    //Receiver timeout value
    public static final byte RH_RF95_REG_15_RX_HEADER_CNT_VALUE_LSB           = (byte) 0x15;    //Receiver timeout value
    public static final byte RH_RF95_REG_16_RX_PACKET_CNT_VALUE_MSB           = (byte) 0x16;    //LoRa TM transmit parameters
    public static final byte RH_RF95_REG_17_RX_PACKET_CNT_VALUE_LSB           = (byte) 0x17;    //LoRa TM transmit parameters
    public static final byte RH_RF95_REG_18_MODEM_STAT                        = (byte) 0x18;    //Size of preamble
    public static final byte RH_RF95_REG_19_PKT_SNR_VALUE                     = (byte) 0x19;    //Size of preamble
    public static final byte RH_RF95_REG_1A_PKT_RSSI_VALUE                    = (byte) 0x1a;    //Modem PHY config
    public static final byte RH_RF95_REG_1B_RSSI_VALUE                        = (byte) 0x1b;    //Test register
    public static final byte RH_RF95_REG_1C_HOP_CHANNEL                       = (byte) 0x1c;    //FHSS Hop period
    public static final byte RH_RF95_REG_1D_MODEM_CONFIG1                     = (byte) 0x1d;    //Number of received bytes
    public static final byte RH_RF95_REG_1E_MODEM_CONFIG2                     = (byte) 0x1e;    //Info from last header
    public static final byte RH_RF95_REG_1F_SYMB_TIMEOUT_LSB                  = (byte) 0x1f;    //Number of valid header received
    public static final byte RH_RF95_REG_20_PREAMBLE_MSB                      = (byte) 0x20;    //Number of valid packets received
    public static final byte RH_RF95_REG_21_PREAMBLE_LSB                      = (byte) 0x21;    //Live LoRa TM modem status
    public static final byte RH_RF95_REG_22_PAYLOAD_LENGTH                    = (byte) 0x22;    //Espimation of last packet SNR
    public static final byte RH_RF95_REG_23_MAX_PAYLOAD_LENGTH                = (byte) 0x23;    //Current RSSI
    public static final byte RH_RF95_REG_24_HOP_PERIOD                        = (byte) 0x24;    //RSSi of last packet
    public static final byte RH_RF95_REG_25_FIFO_RX_BYTE_ADDR                 = (byte) 0x25;    //FHSS start channel
    public static final byte RH_RF95_REG_26_MODEM_CONFIG3                     = (byte) 0x26;    //LoRa TM rx data pointer

    public static final byte RH_RF95_REG_27_RESERVED                          = (byte) 0x27;    //**NOT USED**
    public static final byte RH_RF95_REG_28_RESERVED                          = (byte) 0x28;    //**NOT USED**
    public static final byte RH_RF95_REG_29_RESERVED                          = (byte) 0x29;    //**NOT USED**
    public static final byte RH_RF95_REG_2A_RESERVED                          = (byte) 0x2a;    //**NOT USED**
    public static final byte RH_RF95_REG_2B_RESERVED                          = (byte) 0x2b;    //**NOT USED**
    public static final byte RH_RF95_REG_2C_RESERVED                          = (byte) 0x2c;    //**NOT USED**
    public static final byte RH_RF95_REG_2D_RESERVED                          = (byte) 0x2d;    //**NOT USED**
    public static final byte RH_RF95_REG_2E_RESERVED                          = (byte) 0x2e;    //**NOT USED**
    public static final byte RH_RF95_REG_2F_RESERVED                          = (byte) 0x2f;    //**NOT USED**
    public static final byte RH_RF95_REG_30_RESERVED                          = (byte) 0x30;    //**NOT USED**
    public static final byte RH_RF95_REG_31_RESERVED                          = (byte) 0x31;    //**NOT USED**
    public static final byte RH_RF95_REG_32_RESERVED                          = (byte) 0x32;    //**NOT USED**
    public static final byte RH_RF95_REG_33_RESERVED                          = (byte) 0x33;    //**NOT USED**
    public static final byte RH_RF95_REG_34_RESERVED                          = (byte) 0x34;    //**NOT USED**
    public static final byte RH_RF95_REG_35_RESERVED                          = (byte) 0x35;    //**NOT USED**
    public static final byte RH_RF95_REG_36_RESERVED                          = (byte) 0x36;    //**NOT USED**
    public static final byte RH_RF95_REG_37_RESERVED                          = (byte) 0x37;    //**NOT USED**
    public static final byte RH_RF95_REG_38_RESERVED                          = (byte) 0x38;    //**NOT USED**
    public static final byte RH_RF95_REG_39_RESERVED                          = (byte) 0x39;    //**NOT USED**
    public static final byte RH_RF95_REG_3A_RESERVED                          = (byte) 0x3a;    //**NOT USED**
    public static final byte RH_RF95_REG_3B_RESERVED                          = (byte) 0x3b;    //**NOT USED**
    public static final byte RH_RF95_REG_3C_RESERVED                          = (byte) 0x3c;    //**NOT USED**
    public static final byte RH_RF95_REG_3D_RESERVED                          = (byte) 0x3d;    //**NOT USED**
    public static final byte RH_RF95_REG_3E_RESERVED                          = (byte) 0x3e;    //**NOT USED**
    public static final byte RH_RF95_REG_3F_RESERVED                          = (byte) 0x3f;    //**NOT USED**

    public static final byte RH_RF95_REG_40_DIO_MAPPING1                      = (byte) 0x40;    //Mapping of pins DIO0 to DIO3
    public static final byte RH_RF95_REG_41_DIO_MAPPING2                      = (byte) 0x41;    //Mapping of pins DIO4 and DIO5, ClkOut frequency
    public static final byte RH_RF95_REG_42_VERSION                           = (byte) 0x42;    //Hope RF ID relating the silicon revision

    public static final byte RH_RF95_REG_44_UNUSED                            = (byte) 0x44;    //**NOT USED**

    public static final byte RH_RF95_REG_4B_TCXO                              = (byte) 0x4b;    //TCXO or XTAL input setting
    public static final byte RH_RF95_REG_4D_PA_DAC                            = (byte) 0x4d;    //Higher power settings of the PA
    public static final byte RH_RF95_REG_5B_FORMER_TEMP                       = (byte) 0x5b;    //Stored temperature during the former IQ Calibration

    public static final byte RH_RF95_REG_5D_UNUSED                            = (byte) 0x5d;    //**NOT USED**

    public static final byte RH_RF95_REG_61_AGC_REF                           = (byte) 0x61;    //Adjustment of the AGC thresholds
    public static final byte RH_RF95_REG_62_AGC_THRESH1                       = (byte) 0x62;    //Adjustment of the AGC thresholds
    public static final byte RH_RF95_REG_63_AGC_THRESH2                       = (byte) 0x63;    //Adjustment of the AGC thresholds
    public static final byte RH_RF95_REG_64_AGC_THRESH3                       = (byte) 0x64;    //Adjustment of the AGC thresholds
}