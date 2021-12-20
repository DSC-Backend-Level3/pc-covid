package dto;

import java.io.Serializable;

public class LocationDTO implements Serializable {
    private String provinceName;
    private String districtName;
    private String wardName;

    public LocationDTO() {

    }

    public LocationDTO(String provinceName, String districtName, String wardName) {
        this.provinceName = provinceName;
        this.districtName = districtName;
        this.wardName = wardName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}
