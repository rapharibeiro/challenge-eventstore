package net.intelie.challenges;

import java.util.Objects;
import java.util.UUID;

/**
 * This is just an event stub, feel free to expand it if needed.
 */
public class Event {
    
    private final String type;
    private final long timestamp;
    private final String id;

    public Event(String type, long timestamp) {
        this.type = type;
        this.timestamp = timestamp;
        this.id = UUID.randomUUID().toString();
    }

    public String getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Event other = (Event) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Event{"  + "ID=" + id +  "\nType=" + type + '}';
    }
}
