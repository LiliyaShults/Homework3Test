package objects;

public class Student {
    private String fio;
    private String sex;
    private long id, id_group;

    public Student(int id, String fio, String sex, int id_group) {
        this.fio = fio;
        this.sex = sex;
        this.id = id;
        this.id_group = id_group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fio='" + fio + '\'' +
                ", sex='" + sex + '\'' +
                ", id=" + id +
                ", id_group=" + id_group +
                '}';
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_group() {
        return id_group;
    }

    public void setId_group(long id_group) {
        this.id_group = id_group;
    }


}
