package com.example.phuongnam0907.loraspi;

public class RegisterValue {

    public static final byte RH_SPI_WRITE_MASK                                = (byte) 0x80;

    // This is the maximum number of byteerrupts the driver can support
    // Most Arduinos can handle 2, Megas can handle more
    public static final byte RH_RF95_NUM_byteERRUPTS = 3;

    // Max number of octets the LORA Rx/Tx FIFO can hold
    public static final byte RH_RF95_FIFO_SIZE = (byte) 255;

    // This is the maximum number of bytes that can be carried by the LORA.
    // We use some for headers, keeping fewer for RadioHead messages
    public static final byte RH_RF95_MAX_PAYLOAD_LEN = RH_RF95_FIFO_SIZE;

    // The length of the headers we add.
    // The headers are inside the LORA's payload
    public static final byte RH_RF95_HEADER_LEN = 4;

    // This is the maximum message length that can be supported by this driver.
    // Can be pre-defined to a smaller size (to save SRAM) prior to including this header
    // Here we allow for 1 byte message length, 4 bytes headers, user data and 2 bytes of FCS
    public static final byte RH_RF95_MAX_MESSAGE_LEN = (RH_RF95_MAX_PAYLOAD_LEN - RH_RF95_HEADER_LEN);

    // The crystal oscillator frequency of the module
    public static final int RH_RF95_FXOSC = 32000000;

    // The Frequency Synthesizer step = RH_RF95_FXOSC / 2^^19
    public static final byte RH_RF95_FSTEP = (RH_RF95_FXOSC / 524288);

    // RH_RF95_REG_01_OP_MODE                           = (byte) 0x01
    public static final byte RH_RF95_LONG_RANGE_MODE                     = (byte) 0x80;
    public static final byte RH_RF95_ACCESS_SHARED_REG                   = (byte) 0x40;
    public static final byte RH_RF95_MODE                                = (byte) 0x07;
    public static final byte RH_RF95_MODE_SLEEP                          = (byte) 0x00;
    public static final byte RH_RF95_MODE_STDBY                          = (byte) 0x01;
    public static final byte RH_RF95_MODE_FSTX                           = (byte) 0x02;
    public static final byte RH_RF95_MODE_TX                             = (byte) 0x03;
    public static final byte RH_RF95_MODE_FSRX                           = (byte) 0x04;
    public static final byte RH_RF95_MODE_RXCONTINUOUS                   = (byte) 0x05;
    public static final byte RH_RF95_MODE_RXSINGLE                       = (byte) 0x06;
    public static final byte RH_RF95_MODE_CAD                            = (byte) 0x07;

    // RH_RF95_REG_09_PA_CONFIG                         = (byte) 0x09
    public static final byte RH_RF95_PA_SELECT                           = (byte) 0x80;
    public static final byte RH_RF95_OUTPUT_POWER                        = (byte) 0x0f;

    // RH_RF95_REG_0A_PA_RAMP                           = (byte) 0x0a
    public static final byte RH_RF95_LOW_PN_TX_PLL_OFF                   = (byte) 0x10;
    public static final byte RH_RF95_PA_RAMP                             = (byte) 0x0f;
    public static final byte RH_RF95_PA_RAMP_3_4MS                       = (byte) 0x00;
    public static final byte RH_RF95_PA_RAMP_2MS                         = (byte) 0x01;
    public static final byte RH_RF95_PA_RAMP_1MS                         = (byte) 0x02;
    public static final byte RH_RF95_PA_RAMP_500US                       = (byte) 0x03;
    public static final byte RH_RF95_PA_RAMP_250US                       = (byte) 0x0;
    public static final byte RH_RF95_PA_RAMP_125US                       = (byte) 0x05;
    public static final byte RH_RF95_PA_RAMP_100US                       = (byte) 0x06;
    public static final byte RH_RF95_PA_RAMP_62US                        = (byte) 0x07;
    public static final byte RH_RF95_PA_RAMP_50US                        = (byte) 0x08;
    public static final byte RH_RF95_PA_RAMP_40US                        = (byte) 0x09;
    public static final byte RH_RF95_PA_RAMP_31US                        = (byte) 0x0a;
    public static final byte RH_RF95_PA_RAMP_25US                        = (byte) 0x0b;
    public static final byte RH_RF95_PA_RAMP_20US                        = (byte) 0x0c;
    public static final byte RH_RF95_PA_RAMP_15US                        = (byte) 0x0d;
    public static final byte RH_RF95_PA_RAMP_12US                        = (byte) 0x0e;
    public static final byte RH_RF95_PA_RAMP_10US                        = (byte) 0x0f;

    // RH_RF95_REG_0B_OCP                               = (byte) 0x0b
    public static final byte RH_RF95_OCP_ON                              = (byte) 0x20;
    public static final byte RH_RF95_OCP_TRIM                            = (byte) 0x1f;

    // RH_RF95_REG_0C_LNA                               = (byte) 0x0c
    public static final byte RH_RF95_LNA_GAIN                            = (byte) 0xe0;
    public static final byte RH_RF95_LNA_BOOST                           = (byte) 0x03;
    public static final byte RH_RF95_LNA_BOOST_DEFAULT                   = (byte) 0x00;
    public static final byte RH_RF95_LNA_BOOST_150PC                     = (byte) 0x11;

    // RH_RF95_REG_11_IRQ_FLAGS_MASK                    = (byte) 0x11
    public static final byte RH_RF95_RX_TIMEOUT_MASK                     = (byte) 0x80;
    public static final byte RH_RF95_RX_DONE_MASK                        = (byte) 0x40;
    public static final byte RH_RF95_PAYLOAD_CRC_ERROR_MASK              = (byte) 0x20;
    public static final byte RH_RF95_VALID_HEADER_MASK                   = (byte) 0x10;
    public static final byte RH_RF95_TX_DONE_MASK                        = (byte) 0x08;
    public static final byte RH_RF95_CAD_DONE_MASK                       = (byte) 0x04;
    public static final byte RH_RF95_FHSS_CHANGE_CHANNEL_MASK            = (byte) 0x02;
    public static final byte RH_RF95_CAD_DETECTED_MASK                   = (byte) 0x01;

    // RH_RF95_REG_12_IRQ_FLAGS                         = (byte) 0x12
    public static final byte RH_RF95_RX_TIMEOUT                          = (byte) 0x80;
    public static final byte RH_RF95_RX_DONE                             = (byte) 0x40;
    public static final byte RH_RF95_PAYLOAD_CRC_ERROR                   = (byte) 0x20;
    public static final byte RH_RF95_VALID_HEADER                        = (byte) 0x10;
    public static final byte RH_RF95_TX_DONE                             = (byte) 0x08;
    public static final byte RH_RF95_CAD_DONE                            = (byte) 0x04;
    public static final byte RH_RF95_FHSS_CHANGE_CHANNEL                 = (byte) 0x02;
    public static final byte RH_RF95_CAD_DETECTED                        = (byte) 0x01;

    // RH_RF95_REG_18_MODEM_STAT                        = (byte) 0x18
    public static final byte RH_RF95_RX_CODING_RATE                      = (byte) 0xe0;
    public static final byte RH_RF95_MODEM_STATUS_CLEAR                  = (byte) 0x10;
    public static final byte RH_RF95_MODEM_STATUS_HEADER_INFO_VALID      = (byte) 0x08;
    public static final byte RH_RF95_MODEM_STATUS_RX_ONGOING             = (byte) 0x04;
    public static final byte RH_RF95_MODEM_STATUS_SIGNAL_SYNCHRONIZED    = (byte) 0x02;
    public static final byte RH_RF95_MODEM_STATUS_SIGNAL_DETECTED        = (byte) 0x01;

    // RH_RF95_REG_1C_HOP_CHANNEL                       = (byte) 0x1c
    public static final byte RH_RF95_PLL_TIMEOUT                         = (byte) 0x80;
    public static final byte RH_RF95_RX_PAYLOAD_CRC_IS_ON                = (byte) 0x40;
    public static final byte RH_RF95_FHSS_PRESENT_CHANNEL                = (byte) 0x3f;

    // RH_RF95_REG_1D_MODEM_CONFIG1                     = (byte) 0x1d
    public static final byte RH_RF95_BW                                  = (byte) 0xc0;
    public static final byte RH_RF95_BW_125KHZ                           = (byte) 0x00;
    public static final byte RH_RF95_BW_250KHZ                           = (byte) 0x40;
    public static final byte RH_RF95_BW_500KHZ                           = (byte) 0x80;
    public static final byte RH_RF95_BW_RESERVED                         = (byte) 0xc0;
    public static final byte RH_RF95_CODING_RATE                         = (byte) 0x38;
    public static final byte RH_RF95_CODING_RATE_4_5                     = (byte) 0x00;
    public static final byte RH_RF95_CODING_RATE_4_6                     = (byte) 0x08;
    public static final byte RH_RF95_CODING_RATE_4_7                     = (byte) 0x10;
    public static final byte RH_RF95_CODING_RATE_4_8                     = (byte) 0x18;
    public static final byte RH_RF95_IMPLICIT_HEADER_MODE_ON             = (byte) 0x04;
    public static final byte RH_RF95_RX_PAYLOAD_CRC_ON                   = (byte) 0x02;
    public static final byte RH_RF95_LOW_DATA_RATE_OPTIMIZE              = (byte) 0x01;

    // RH_RF95_REG_1E_MODEM_CONFIG2                     = (byte) 0x1e
    public static final byte RH_RF95_SPREADING_FACTOR                    = (byte) 0xf0;
    public static final byte RH_RF95_SPREADING_FACTOR_64CPS              = (byte) 0x60;
    public static final byte RH_RF95_SPREADING_FACTOR_128CPS             = (byte) 0x70;
    public static final byte RH_RF95_SPREADING_FACTOR_256CPS             = (byte) 0x80;
    public static final byte RH_RF95_SPREADING_FACTOR_512CPS             = (byte) 0x90;
    public static final byte RH_RF95_SPREADING_FACTOR_1024CPS            = (byte) 0xa0;
    public static final byte RH_RF95_SPREADING_FACTOR_2048CPS            = (byte) 0xb0;
    public static final byte RH_RF95_SPREADING_FACTOR_4096CPS            = (byte) 0xc0;
    public static final byte RH_RF95_TX_CONTINUOUS_MODE                   = (byte) 0x08;
    public static final byte RH_RF95_AGC_AUTO_ON                         = (byte) 0x04;
    public static final byte RH_RF95_SYM_TIMEOUT_MSB                     = (byte) 0x03;

    // RH_RF95_REG_4D_PA_DAC                            = (byte) 0x4d
    public static final byte RH_RF95_PA_DAC_DISABLE                      = (byte) 0x04;
    public static final byte RH_RF95_PA_DAC_ENABLE                       = (byte) 0x07;
}
