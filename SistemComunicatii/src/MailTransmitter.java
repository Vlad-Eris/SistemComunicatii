import java.util.ArrayList;
import java.util.List;

public class MailTransmitter implements Transmitter {
    private List<Message> buffer = new ArrayList<>();
    private int capacity;

    public MailTransmitter(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void store(Message message) {
        buffer.add(message);
        if (buffer.size() >= capacity) {
            notifyAllReceivers();
        }
    }

    @Override
    public Message retrieve(Person receiver) {
        for (Message message : buffer) {
            if (message.getReceiver().equals(receiver)) {
                buffer.remove(message);
                return message;
            }
        }
        return null;
    }

    private void notifyAllReceivers() {
        for (Message message : new ArrayList<>(buffer)) {
            message.getReceiver().notify(this);
        }
        buffer.clear();
    }
}
