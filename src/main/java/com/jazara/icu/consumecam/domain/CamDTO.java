package com.jazara.icu.consumecam.domain;

import java.util.Objects;

public class CamDTO {
    private String id;
    private Long room_id;
    private String name;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
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

    public CamDTO() {
    }

    public CamDTO(String id, Long room_id, String name, String url) {
        this.id = id;
        this.room_id = room_id;
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CamDTO camDTO = (CamDTO) o;
        return Objects.equals(id, camDTO.id) &&
                Objects.equals(room_id, camDTO.room_id) &&
                Objects.equals(name, camDTO.name) &&
                Objects.equals(url, camDTO.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room_id, name, url);
    }
}
