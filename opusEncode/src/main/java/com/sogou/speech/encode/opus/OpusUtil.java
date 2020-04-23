package com.sogou.speech.encode.opus;

/**
 * Created by Administrator on 2020/4/7.
 */

public class OpusUtil {
    private long mEncoderHandle = 0;
    private long mDecoderHandle = 0;

    static {
        try {
            System.loadLibrary("opus_v1");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    private native static long createEncoder(int sampleRateInHz, int channelConfig, int complexity);

    private native static long createDecoder(int sampleRateInHz, int channelConfig);

    private native static int encode(long handle, short[] in, int offset, byte[] out);

    private native static int decode(long handle, byte[] in, short[] out);

    private native static void destroyEncoder(long handle);

    private native static void destroyDecoder(long handle);

    public void createOpusEncoder(int sampleRateInHz, int channelConfig, int complexity) {
        try {
            mEncoderHandle = createEncoder(sampleRateInHz, channelConfig, complexity);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }

    }

    public void createOpusDecoder(int sampleRateInHz, int channelConfig) {
        try {
            mDecoderHandle = createDecoder(sampleRateInHz, channelConfig);
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }

    }

    public int encodeShortArray(short[] in, int offset, byte[] out) {
        if (mEncoderHandle > 0) {
            try {
                return encode(mEncoderHandle, in, offset, out);
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    public int decodeByteArray(byte[] in, short[] out) {
        if (mDecoderHandle > 0) {
            try {
                return decode(mDecoderHandle, in, out);
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    public void destroyEnc() {
        if (mEncoderHandle > 0) {
            try {
                destroyEncoder(mEncoderHandle);
                mEncoderHandle = 0;
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }

        }
    }

    public void destroyDec() {
        if (mDecoderHandle > 0) {
            try {
                destroyDecoder(mDecoderHandle);
                mDecoderHandle = 0;
            } catch (UnsatisfiedLinkError e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void finalize() throws Throwable {
        destroyEnc();
        super.finalize();
    }
}
