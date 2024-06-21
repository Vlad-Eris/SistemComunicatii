import java.util.ArrayList;
import java.util.List;

public class EmailTransmitter implements Transmitter {
    private List<Message> messages = new ArrayList<>();

    @Override
    public void store(Message message) {
        messages.add(message);
        notifyReceiver(message.getReceiver());
    }

    @Override
    public Message retrieve(Person receiver) {
        for (Message message : messages) {
            if (message.getReceiver().equals(receiver)) {
                messages.remove(message);
                return message;
            }
        }
        return null;
    }

    private void notifyReceiver(Person receiver) {
        receiver.notify(this);
    }
}
