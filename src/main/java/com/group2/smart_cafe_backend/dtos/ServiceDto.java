package com.group2.smart_cafe_backend.dtos;

import com.group2.smart_cafe_backend.models.emum.ServiceStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalTime;

public class ServiceDto {
    @NotNull
    private Long typeId;

    @NotNull
    @Size(min = 1, max = 50)
    private String serviceCode;

    @Size(max = 100)
    private String serviceName;

    @NotNull
    @Min(0)
    private BigDecimal price;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String imageUrl;

    private LocalTime waitTime;

    @NotNull
    private ServiceStatus status;

    @NotNull
    private boolean isDelete = false;

    // Getters and setters
    public @NotNull Long getTypeId() {
        return typeId;
    }

    public void setTypeId(@NotNull Long typeId) {
        this.typeId = typeId;
    }

    public @NotNull @Size(min = 1, max = 50) String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(@NotNull @Size(min = 1, max = 50) String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public @Size(max = 100) String getServiceName() {
        return serviceName;
    }

    public void setServiceName(@Size(max = 100) String serviceName) {
        this.serviceName = serviceName;
    }

    public @NotNull @Min(0) BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@NotNull @Min(0) BigDecimal price) {
        this.price = price;
    }

    public @Size(max = 255) String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 255) String description) {
        this.description = description;
    }

    public @Size(max = 255) String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@Size(max = 255) String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalTime getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(LocalTime waitTime) {
        this.waitTime = waitTime;
    }


    public @NotNull ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull ServiceStatus status) {
        this.status = status;
    }

    public @NotNull boolean isIsDelete() {
        return isDelete;
    }

    public void setIsDelete(@NotNull boolean isDelete) {
        this.isDelete = isDelete;
    }
}