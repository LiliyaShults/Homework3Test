package objects;

public class Curator {
    private String fio;
    private long id;

    public Curator(String fio, int id) {
        this.fio = fio;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Curator{" +
                "fio='" + fio + '\'' +
                ", id=" + id +
                '}';
    }

   /* public Curator(String fio, int id) {
        this.fio = fio;
        this.id = id;
    }*/

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
