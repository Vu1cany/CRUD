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
            String name;
            Sex sex;
            String sexStr;
            Date birthday;
            int index;
            String birthdayStr;


            switch (args[0]){
                case "-c":
                    
                    name = args[1];

                    if (args[2].equals("м")){
                        sex = Sex.MALE;
                    } else if (args[2].equals("ж")){
                        sex = Sex.FEMALE;
                    } else {
                        throw new InvalidParameterException("Пол введен неправильно");
                    }

                    birthday = dateReadFormat.parse(args[3]);

                    if (sex == Sex.MALE) {
                        allPeople.add(Person.createMale(name, birthday));
                    } else {
                        allPeople.add(Person.createFemale(name, birthday));
                    }
                    System.out.println(allPeople.size() - 1);

                    break;
                case "-r":

                    index = Integer.parseInt(args[1]);
                    Person person = allPeople.get(index);


                    if (person.getSex() == Sex.MALE){
                        sexStr = "м";
                    } else {
                        sexStr = "ж";
                    }

                    birthdayStr = dateWriteFormat.format(person.getBirthDate());
                    System.out.println(person.getName() + " " + sexStr + " " + birthdayStr);

                    break;
                case "-u":

                    index = Integer.parseInt(args[1]);
                    name = args[2];

                    if (args[3].equals("м")){
                        sex = Sex.MALE;
                    } else if (args[3].equals("ж")){
                        sex = Sex.FEMALE;
                    } else {
                        throw new InvalidParameterException("Пол введен неправильно");
                    }

                    birthday = dateReadFormat.parse(args[4]);

                    if (sex == Sex.FEMALE) {
                        allPeople.set(index, Person.createFemale(name, birthday));
                    } else if (sex == Sex.MALE){
                        allPeople.set(index, Person.createMale(name, birthday));
                    }

                    break;
                case "-d":

                    index = Integer.parseInt(args[1]);
                    person = allPeople.get(index);
                    person.setSex(null);
                    person.setName(null);
                    person.setBirthDate(null);

                    break;
            }

        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Недостаточное количество аргументов программы");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
