package com.sessionization;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableUtils;

// Composite key used to sort the output generated by SessionizationMapper
public class CompositeKey implements WritableComparable<CompositeKey>{
    private String ip;
    private Long timestamp;
   
    public CompositeKey() {}
    
    public CompositeKey(String ip, long timestamp) {
        this.ip = ip;
        this.timestamp = timestamp;
    }
    
    @Override
    public void readFields(DataInput arg0) throws IOException {
        ip = WritableUtils.readString(arg0);
        timestamp = arg0.readLong();
    }
    
    @Override
    public void write(DataOutput arg0) throws IOException {
        WritableUtils.writeString(arg0, ip);
        arg0.writeLong(timestamp);
    }
    
    @Override
    public int compareTo(CompositeKey o) {
        int result = ip.compareTo(o.ip);
        if(0 == result) {
            result = timestamp.compareTo(o.timestamp);
        }
        return result;
    }
    
    public String getIp(){
    	return ip;
    }
    
    public Long getTimestamp(){
    	return timestamp;
    }
}