import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        SimpleDateFormat dateReadFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        SimpleDateFormat dateWriteFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        try {
            switch (args[0]){
                case "-c":
                    if (args[1] != null && args[2] != null && args[3] != null){
                        String name = args[1];

                        Sex sex;
                        if (args[2].equals("м")){
                            sex = Sex.MALE;
                        } else if (args[2].equals("ж")){
                            sex = Sex.FEMALE;
                        } else {
                            throw new InvalidParameterException("Пол введен неправильно");
                        }

                        Date birthday = dateReadFormat.parse(args[3]);

                        if (sex == Sex.MALE) {
                            allPeople.add(Person.createMale(name, birthday));
                        } else {
                            allPeople.add(Person.createFemale(name, birthday));
                        }
                        System.out.println(allPeople.size() - 1);

                    } else {
                        throw new NullPointerException("Введены не все аргументы");
                    }
                    break;
                case "-r":
                    if (args[1] != null) {
                        int index = Integer.parseInt(args[1]);
                        Person person = allPeople.get(index);

                        String sex;
                        if (person.getSex() == Sex.MALE){
                            sex = "м";
                        } else {
                            sex = "ж";
                        }

                        String birthday = dateWriteFormat.format(person.getBirthDate());
                        System.out.println(person.getName() + " " + sex + " " + birthday);

                    } else {
                        throw new NullPointerException("Введены не все аргументы");
                    }
                    break;
                case "-u":
                    if (args[1] != null && args[2] != null && args[3] != null && args[4] != null){
                        int index = Integer.parseInt(args[1]);
                        String name = args[2];

                        Sex sex;
                        if (args[3].equals("м")){
                            sex = Sex.MALE;
                        } else if (args[3].equals("ж")){
                            sex = Sex.FEMALE;
                        } else {
                            throw new InvalidParameterException("Пол введен неправильно");
                        }

                        Date birthday = dateReadFormat.parse(args[4]);

                        if (sex == Sex.FEMALE) {
                            allPeople.set(index, Person.createFemale(name, birthday));
                        } else if (sex == Sex.MALE){
                            allPeople.set(index, Person.createMale(name, birthday));
                        }

                    } else {
                        throw new NullPointerException("Введены не все аргументы");
                    }
                    break;
                case "-d":
                    if (args[1] != null) {
                        int index = Integer.parseInt(args[1]);
                        Person person = allPeople.get(index);
                        person.setSex(null);
                        person.setName(null);
                        person.setBirthDate(null);

                    } else {
                        throw new NullPointerException("Введены не все аргументы");
                    }
                    break;
                default:

            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
