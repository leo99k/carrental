package carrental;

public class MessageSent extends AbstractEvent {

    private Long id;

    public MessageSent(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
