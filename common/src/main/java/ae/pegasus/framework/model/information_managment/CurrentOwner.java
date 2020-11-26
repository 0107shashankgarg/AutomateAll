
package ae.pegasus.framework.model.information_managment;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({
        "ownerId",
        "ownerName",
        "type"
})
public class CurrentOwner {

    @JsonProperty("ownerId")
    private String ownerId;
    @JsonProperty("ownerName")
    private String ownerName;
    @JsonProperty("type")
    private String type;
    @JsonProperty("ownerFullName")
    private String ownerFullName;
    @JsonProperty("attributes")
    private Map<String, Object> attributes;


    public String getOwnerId() {
        return ownerId;
    }


    @JsonProperty("ownerId")
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @JsonProperty("ownerName")
    public String getOwnerName() {
        return ownerName;
    }

    @JsonProperty("ownerName")
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("ownerFullName")
    public String getOwnerFullName() {
        return ownerFullName;
    }

    @JsonProperty("ownerFullName")
    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }

    @JsonProperty("attributes")
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}

