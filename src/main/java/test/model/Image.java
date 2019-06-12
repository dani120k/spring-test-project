package test.model;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    private Long id;

    @Column(unique = true)
    private String uri;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
