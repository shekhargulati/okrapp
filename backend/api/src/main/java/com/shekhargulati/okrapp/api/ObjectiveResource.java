package com.shekhargulati.okrapp.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.shekhargulati.okrapp.core.domain.Objective;
import com.shekhargulati.okrapp.core.service.ObjectiveDto;
import com.shekhargulati.okrapp.core.service.ObjectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/api/v1/objectives")
public class ObjectiveResource {

    private ObjectiveService objectiveService;

    @Autowired
    public ObjectiveResource(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    @PostMapping
    public ResponseEntity<ObjectiveDto> create(@RequestBody @Valid ObjectiveRequest request) {
        ObjectiveDto objectiveDto = this.objectiveService.create(
                new Objective(request.title(), request.startDate(), request.endDate())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(objectiveDto);
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
record ObjectiveRequest(@NotBlank String title,
                        LocalDate startDate,
                        LocalDate endDate) {

    public ObjectiveRequest(String title) {
        this(title, null, null);
    }
}