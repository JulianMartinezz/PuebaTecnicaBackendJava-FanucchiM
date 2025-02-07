package HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.dto.response.dto;

import HR.Medical.Records.Management.System.HR.Medical.Records.Management.System.entity.StatusEntity;


public class StatusDTO {
    private Long statusId;
    private String name;
    private String description;

    public StatusDTO() {
    }

    public StatusDTO(StatusEntity statusEntity) {
        this.statusId = statusEntity.getStatusId();
        this.name = statusEntity.getName();
        this.description = statusEntity.getDescription();
    }

    // Getters and Setters
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
