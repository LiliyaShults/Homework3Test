import objects.Curator;
import objects.Group;
import objects.Student;
import tables.CuratorTables;
import tables.GroupTables;
import tables.StudentTables;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws SQLException {
        CuratorTables curatorTables = new CuratorTables();
        ArrayList<Curator> curators = curatorTables.selectAll();
        if (curators.size() < 3) {
            curatorTables.insert(new Curator("Иванов Иван Иванович", 1));
            curatorTables.insert(new Curator("Миронова Вика Викторовна", 2));
            curatorTables.insert(new Curator("Федоров Дмитрий Юрьевич", 3));
            curators = curatorTables.selectAll();
        }

        for (Curator tmp : curators) {
            System.out.println(tmp.toString());
        }

        GroupTables groupTables = new GroupTables();
        ArrayList<Group> groups = groupTables.selectAll();
        if (groups.size() < 3) {
            groupTables.insert(new Group(1, "K-3211", 1));
            groupTables.insert(new Group(2, "K-3212", 2));
            groupTables.insert(new Group(3, "K-3213", 3));
            groups = groupTables.selectAll();
        }

        for (Group tmp : groups) {
            System.out.println(tmp.toString());
        }

        StudentTables studentTables = new StudentTables();
        ArrayList<Student> students = studentTables.selectAll();
        if (students.size() < 15) {
            studentTables.insert(new Student(1, "Папова Мария Сергеевна", "женщина", 1));
            studentTables.insert(new Student(2, "Миронова Светлана Валерьевна", "женщина", 1));
            studentTables.insert(new Student(3, "Надеждина Надежда Петровна", "женщина", 1));
            studentTables.insert(new Student(4, "Шульц Лилия Николаевна", "женщина", 1));
            studentTables.insert(new Student(5, "Чистякова Надежда Михайловна", "женщина", 1));
            studentTables.insert(new Student(6, "Яковенко Елена Александровна", "женщина", 1));
            studentTables.insert(new Student(7, "Шумилкина Мария Александровна", "женщина", 2));
            studentTables.insert(new Student(8, "Шульц Денис Сергеевич", "мужчина", 2));
            studentTables.insert(new Student(9, "Денисюк Михаил Семенович", "мужчина", 2));
            studentTables.insert(new Student(10, "Чистяков Василий Иванович", "мужчина", 2));
            studentTables.insert(new Student(11, "Петров Петор Петрович", "мужчина", 2));
            studentTables.insert(new Student(12, "Соловьев Олег Михайлович", "мужчина", 3));
            studentTables.insert(new Student(13, "Журавлев Андрей Михайлович", "мужчина", 3));
            studentTables.insert(new Student(14, "Шарафудинов Максим Кирилович", "мужчина", 3));
            studentTables.insert(new Student(15, "Непомнящий Денис Валерьевич", "мужчина", 3));
            students = studentTables.selectAll();
        }

        for (Student tmp : students) {
            System.out.println(tmp.toString());
        }
        System.out.println();
        students = studentTables.selectBySex("женщина");
        for (Student tmp : students) {
            System.out.println(tmp.toString());
        }
        System.out.println();
        students = studentTables.selectBySex("мужчина");
        for (Student tmp : students) {
            System.out.println(tmp.toString());
        }
        System.out.println();
        Integer count = studentTables.selectCount("мужчина");
        System.out.println(count);
//        for (Student tmp:students) {
//            System.out.println(tmp.toString());
//        }

        studentTables.selectStudensInfo();

        System.out.println();

        curators.get(1).setFio("Силянин Синевич Синий");
        curatorTables.update(curators.get(1));

        for (Curator tmp : curators) {
            System.out.println(tmp.toString());
        }


    }

}


