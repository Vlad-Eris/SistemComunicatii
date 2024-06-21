
public class Person {
    private String name;
    private Transmitter transmitter;

    public Person(String name) {
        this.name = name;
    }

    public void setTransmitter(Transmitter transmitter) {
        this.transmitter = transmitter;
    }

    public void send(Person receiver, String content) {
        Message message = new Message(this, receiver, content);
        transmitter.store(message);
    }
    
    public void notify(Transmitter transmitter) {
        Message message = transmitter.retrieve(this);
        if (message != null) {
            System.out.println(this.name + " said to " + message.getReceiver().getName() + " (" + (transmitter instanceof EmailTransmitter ? "EMAIL" : "MAIL") + "): \"" + message.getContent() + "\"");
        }
    }

    public String getName() {
        return name;
    }
}
