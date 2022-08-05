package com.jazara.icu.consumecam.domain;

import java.util.List;
import java.util.Objects;

public class CamDTO {
    private Long id;
    private String name;
    private String url;

    public CamDTO(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public CamDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CamDTO camDTO = (CamDTO) o;
        return Objects.equals(id, camDTO.id) &&
                Objects.equals(name, camDTO.name) &&
                Objects.equals(url, camDTO.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}