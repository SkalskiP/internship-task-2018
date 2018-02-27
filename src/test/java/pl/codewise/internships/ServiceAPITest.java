package pl.codewise.internships;

import static org.junit.Assert.assertEquals;

public class ServiceAPITest {

    private ServiceAPI serviceAPI;

    @org.junit.Before
    public void setup() {
        serviceAPI = new ServiceAPI();
    }

    @org.junit.Test
    public void add() {
        addNRecords(100, false);
        assertEquals(100, serviceAPI.getRecords().size());
    }

    @org.junit.Test
    public void snapshot_1() {
        addNRecords(10, false);
        assertEquals(10, serviceAPI.snapshot().getMessages().size());
    }

    @org.junit.Test
    public void snapshot_2() {
        addNRecords(150, false);
        assertEquals(100, serviceAPI.snapshot().getMessages().size());
    }

    @org.junit.Test
    public void snapshot_3() {
        addNRecords(99, false);
        assertEquals(99, serviceAPI.snapshot().getMessages().size());
    }

    @org.junit.Test
    public void numberOfErrorMessages_1() {
        addNRecords(39, false);
        addNRecords(15, true);
        addNRecords(39, false);
        assertEquals(15, serviceAPI.numberOfErrorMessages());
    }

    @org.junit.Test
    public void numberOfErrorMessages_2() {
        addNRecords(39, false);
        addNRecords(15, true);
        addNRecords(100, false);
        assertEquals(0, serviceAPI.numberOfErrorMessages());
    }

    @org.junit.Test
    public void numberOfErrorMessages_3() {
        addNRecords(39, false);
        addNRecords(15, true);
        addNRecords(90, false);
        assertEquals(10, serviceAPI.numberOfErrorMessages());
    }

    @org.junit.Test
    public void numberOfErrorMessages_4() {
        addNRecords(39, false);
        addNRecords(10, true);
        addNRecords(10, false);
        addNRecords(10, true);
        addNRecords(10, false);
        assertEquals(20, serviceAPI.numberOfErrorMessages());
    }

    public void addNRecords(int numberOfRecords, boolean errors) {
        for(int i = 0; i < numberOfRecords; i++) {
            serviceAPI.add(new Message("TEST" + i, errors ? 400 + i%10 : i % 400));
        }
    }
}