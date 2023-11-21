package com.example.spring2023.model;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Getter
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;
    @NotEmpty(message = "{task.name.not.empty}")
    private String name;
    @NotEmpty(message = "{task.description.not.empty}")
    @Column(length = 1200)
    @Size(max = 1200, message = "{task.description.size}")
    private String description;
    @NotNull(message = "{task.date.not.null}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private boolean isCompleted;
    private String creatorName;
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    public long daysLeftUntilDeadline(LocalDate date) {
        return ChronoUnit.DAYS.between(LocalDate.now(), date);
    }

    public Task() {
    }

    public Task(@NotEmpty String name,
                @NotEmpty @Size(max = 1200) String description,
                @NotNull LocalDate date,
                boolean isCompleted,
                String creatorName) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.isCompleted = isCompleted;
        this.creatorName = creatorName;
    }

    public Task(@NotEmpty String name,
                @NotEmpty @Size(max = 1200) String description,
                @NotNull LocalDate date,
                boolean isCompleted,
                String creatorName,
                User owner) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.isCompleted = isCompleted;
        this.creatorName = creatorName;
        this.owner = owner;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }


    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }


    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isCompleted == task.isCompleted &&
                Objects.equals(id, task.id) &&
                name.equals(task.name) &&
                description.equals(task.description) &&
                date.equals(task.date) &&
                Objects.equals(creatorName, task.creatorName) &&
                Objects.equals(owner, task.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, date, isCompleted, creatorName, owner);
    }
}