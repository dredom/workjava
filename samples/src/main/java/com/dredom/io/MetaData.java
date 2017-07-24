/**
 *
 */
package com.dredom.io;

/**
 * Meta data about the file.
 *
 * @author andre
 */
public class MetaData {
    private String filename;
    private long size;

    public MetaData(String filename, long size) {
        super();
        this.filename = filename;
        this.size = size;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public long getSize() {
        return size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    @Override
    public String toString() {
        return "MetaData [filename=" + filename + ", size=" + size + "]";
    }

}
