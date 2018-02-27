package pl.codewise.internships;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Debugging code
        ServiceAPI api = new ServiceAPI();
        api.add(new Message("TEST1", 100));
        api.add(new Message("TEST2", 402));
        api.add(new Message("TEST3", 100));
        api.add(new Message("TEST4", 410));
        api.add(new Message("TEST5", 500));

        Thread.sleep(5000);

        Snapshot test = api.snapshot();

        for(Message message : test.getMessages()) {
            System.out.println(message.getUserAgent());
        }

        System.out.println(api.numberOfErrorMessages());
    }
}
