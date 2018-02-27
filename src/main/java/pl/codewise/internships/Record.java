package pl.codewise.internships;

import java.util.Date;

public class Record {
    private Message message;
    private Date date;

    public Record(Message message, Date date) {
        this.message = message;
        this.date = date;
    }

    public Message getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
