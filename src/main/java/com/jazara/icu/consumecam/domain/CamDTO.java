package com.jazara.icu.consumecam.domain;

import java.util.List;
import java.util.Objects;

public class CamDTO {
    private Long id;
    private Long room_id;
    private String name;
    private String url;
    private List<Long> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Long> getPersons() {
        return persons;
    }

    public void setPersons(List<Long> persons) {
        this.persons = persons;
    }

    public CamDTO() {
    }

    public CamDTO(Long id, Long room_id, String name, String url, List<Long> persons) {
        this.id = id;
        this.room_id = room_id;
        this.name = name;
        this.url = url;
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CamDTO camDTO = (CamDTO) o;
        return Objects.equals(id, camDTO.id) &&
                Objects.equals(room_id, camDTO.room_id) &&
                Objects.equals(name, camDTO.name) &&
                Objects.equals(url, camDTO.url) &&
                Objects.equals(persons, camDTO.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, room_id, name, url, persons);
    }
}
