import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {

    public static void main(String[] args) {
        
        Scanner scan1 = new Scanner(System.in);
        System.out.println("Введите ваши данные(Фамилия имя отчество, дата рождения, телефон и ваш пол) в формате: Иванов Иван Иванович 06.10.1976 89000000000 m. Где (m - мужчина, f - женщина)"); //Фамилия Имя Отчество дата _ рождения номер _ телефона пол
       
        String userData = scan1.nextLine(); // для теста: "Иванов Иван Иванович 06.10.1976 89000000000 m" или "testSurename name name 06.10.1976 89000000000 m"
        scan1.close();
        
        String[] userDataArray = userData.split(" ");
        String pattern = "^[а-яА-ЯёЁa-zA-Z]+$";        
        try{
            if(!userDataArray[0].matches(pattern) || !userDataArray[1].matches(pattern) || !userDataArray[2].matches(pattern)){
                throw new IllegalArgumentException("Неверный тип данных при написании ФИО, пожалуйста попробуйте снова используя для ввода пример");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Исключение: " + e.getMessage());
            e.printStackTrace();
        }

        Pattern pattern2 = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        Matcher matcher = pattern2.matcher(userDataArray[3]);       
        try{
            if(!matcher.matches()){
                throw new IllegalArgumentException("Неверный тип данных при написании даты рождения, пожалуйста попробуйте снова используя для ввода пример");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Исключение: " + e.getMessage());
            e.printStackTrace();
        }
        
        Pattern pattern3 = Pattern.compile("\\d{11}");
        Matcher matcher2 = pattern3.matcher(userDataArray[4]);        
        try {
            if(!matcher2.matches()){
                throw new IllegalArgumentException("Неверный тип данных при написании телефона, пожалуйста попробуйте снова используя для ввода пример");
            }  
        } catch (IllegalArgumentException e){
            System.out.println("Исключение: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println(userDataArray[5]);// строка проверки - здесь происходит странная ошибка, несмотря на то что userDataArray[5] имеет явный тип стрин и значение м - все равно бросается исключение
        try {
            if(userDataArray[5] != "f" && userDataArray[5] != "m"){
                throw new IllegalArgumentException("Неверный тип данных при написании пола, пожалуйста попробуйте снова используя для ввода пример");
            }  
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        try {
        if (userDataArray.length != 6){
            throw new IllegalArgumentException("Неверное количество данных, пожалуйста попробуйте снова используя для ввода пример");
        }
        else{
            
            String surname = userDataArray[0];
            
                
            try {
                createOrUpdateFile(surname, userDataArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        } catch (IllegalArgumentException e) {
            System.out.println("Исключение: " + e.getMessage());
            e.printStackTrace();
        }
                
    }
    
    public static void createOrUpdateFile(String surname, String[] userDataArray) throws IOException {
        File file = new File(surname + ".txt");
        
        if (file.exists()) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write("\n" + "<" + userDataArray[0] + ">" + " " + "<" + userDataArray[1] + ">" + " " + "<" + userDataArray[2] + ">" + " " + "<" + userDataArray[3] + ">" + " " + "<" + userDataArray[4] + ">" + " " + "<" + userDataArray[5] + ">");
            }
        } else {
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("<" + userDataArray[0] + ">" + " " + "<" + userDataArray[1] + ">" + " " + "<" + userDataArray[2] + ">" + " " + "<" + userDataArray[3] + ">" + " " + "<" + userDataArray[4] + ">" + " " + "<" + userDataArray[5] + ">");
            }
        }
    }
}

