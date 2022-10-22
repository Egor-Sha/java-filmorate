package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Film extends StorageData {
    private long id;
    @NotBlank
    private String name;
    @Size(min = 1)
    private String description;
    @Past
    private LocalDate releaseDate;
    @Min(1)
    private int duration;
    @JsonIgnore
    private Set<Long> userIds = new HashSet<>();
    @JsonIgnore
    private long rate = 0;
    public void addLike(long userId) {
        userIds.add(userId);
        rate = userIds.size();
    }
    public void removeLike(long userId) {
        userIds.remove(userId);
        rate = userIds.size();
    }
}
