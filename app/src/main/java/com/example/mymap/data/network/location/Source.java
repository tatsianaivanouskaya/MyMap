
package com.example.mymap.data.network.location;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("DataType")
    @Expose
    private String dataType;
    @SerializedName("Source")
    @Expose
    private String source;
    @SerializedName("SourceId")
    @Expose
    private Integer sourceId;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

}
