package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private final HashMap<Long, TimeEntry> timeEntryCache = new HashMap<Long, TimeEntry>();
    private Long identitySeed = 0L;

    public TimeEntry create(TimeEntry timeEntry) {

        identitySeed++;
        timeEntry.setId(identitySeed);

        if (!timeEntryCache.containsKey(timeEntry.getId())) {
            timeEntryCache.put(timeEntry.getId(), timeEntry);
        }

        return timeEntry;

    }

    public TimeEntry find(long id) {
        return timeEntryCache.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntryCache.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        timeEntry.setId(id);
        timeEntryCache.replace(id, timeEntry);
        return timeEntry;
    }

    public void delete(Long id) {
        timeEntryCache.remove(id);

    }
}
