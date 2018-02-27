package pl.codewise.internships;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceAPI implements MessageQueue {

    private List<Record> records = new ArrayList<Record>();

    private int TIME_DELTA = 5 * 60 * 1000; // In milliseconds

    public synchronized void add(Message message) {
        Record newRecord = new Record(message, new Date());
        records.add(newRecord);
    }

    public synchronized Snapshot snapshot() {
        List<Message> messagesSnapshot = new ArrayList<Message>();
        Date currentTime = new Date();

        for(int i = records.size()-1; (i >= 0 && messagesSnapshot.size() < 100); i --) {
            if((currentTime.getTime() - records.get(i).getDate().getTime()) < this.TIME_DELTA)
                messagesSnapshot.add(records.get(i).getMessage());
        }
        return new Snapshot(messagesSnapshot);
    }

    public long numberOfErrorMessages() {
        long counter = 0;
        Snapshot snapshot = this.snapshot();

        // TODO: Simple test for error code. Maybe some Regex.
        for (Message message : snapshot.getMessages()) {
            int recordErrorCode = message.getErrorCode();

            if ((recordErrorCode >= 400 && recordErrorCode <= 418) ||
                recordErrorCode == 451 ||
                (recordErrorCode >= 500 && recordErrorCode <= 511)) {
                counter ++;
            }
        }
        return counter;
    }

    public List<Record> getRecords() {
        return records;
    }
}
