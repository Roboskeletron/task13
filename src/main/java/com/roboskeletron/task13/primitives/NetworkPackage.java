package com.roboskeletron.task13.primitives;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class NetworkPackage {
    private int packageSize;

    public NetworkPackage(int packageSize){
        this.packageSize = packageSize;
    }

    public int getPackageSize(){
        return packageSize;
    }

    public void setPackageSize(int size){
        packageSize = size > 0 ? size : packageSize;
    }

    public byte[] readPackage(InputStream stream) throws IOException {
        return stream.readNBytes(packageSize);
    }

    public void sendPackage(byte[] data, OutputStream stream) throws IOException {
        int length = data.length;
        int count = 0;

        while (length > 0){
            int offset = count * packageSize;
            if (packageSize <= length)
                stream.write(data, offset, packageSize);
            else
                stream.write(data, offset, length);
            count++;
            length-=packageSize;
        }
        stream.flush();
    }
}
