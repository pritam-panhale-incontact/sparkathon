package sparkathon.dashboard.entities;

import javax.persistence.*;

@Entity
@Table(name = "servicemonitor")
public class ServiceMonitor {

    public ServiceMonitor() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "service_name")
    private String serviceName;

    @Column(name = "timestamp")
    private long timestamp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServiceMonitor{");
        sb.append("id=").append(id);
        sb.append(", status='").append(status).append('\'');
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", timestamp=").append(timestamp);
        sb.append('}');
        return sb.toString();
    }
}
