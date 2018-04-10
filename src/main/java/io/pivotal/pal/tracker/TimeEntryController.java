package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }


    @PostMapping("/time-entries")
    @ResponseBody
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity read(@PathVariable Long id) {

        TimeEntry timeEntry = timeEntryRepository.find(id);

        return timeEntry != null
                ? new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK)
                : new ResponseEntity(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    @ResponseBody
    public ResponseEntity list() {
        return new ResponseEntity<List<TimeEntry>>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Long id, @RequestBody TimeEntry timeEntryToUpdate) {

        TimeEntry timeEntry = timeEntryRepository.update(id, timeEntryToUpdate);

        return timeEntry != null
                ? new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK)
                : new ResponseEntity(null, HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/time-entries/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable Long id) {
        timeEntryRepository.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
