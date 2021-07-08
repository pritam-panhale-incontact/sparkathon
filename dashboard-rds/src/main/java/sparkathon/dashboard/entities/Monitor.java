package sparkathon.dashboard.entities;


import javax.persistence.*;

@Entity
@Table(name = "monitor")
public class Monitor {

    public Monitor() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Monitor{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
