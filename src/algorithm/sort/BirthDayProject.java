import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BirthDayProject {
    public static void main(String[] args) throws IOException {
        ArrayList<Student> students = new ArrayList<Student>();
        // 파일 입력 -> 학생 정보 students ArrayList에 저장
        BufferedReader br = new BufferedReader(new FileReader("./birthday.in"));
        FileWriter fw = new FileWriter("./birthday.out");
        String str;

        while ((str = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(str);
            String name = st.nextToken();
            String birthdate = st.nextToken();
            String birthmonth = birthdate.substring(0,2);
            String birthday = birthdate.substring(2);
            students.add(new Student(name,birthmonth,birthday));
        }

        ArrayList<Student> mergedList = MergeSort(students);

        for(Student student:mergedList){
            fw.write(student.name + " " + student.birthMonth + student.birthday + "\n");
        }

        br.close();
        fw.close();
    }

    private static ArrayList<Student> MergeSort(ArrayList<Student> array) {
        if(array.size()<=1) return array;

        ArrayList<Student> left = new ArrayList<>();
        ArrayList<Student> right= new ArrayList<>();
        int mid = array.size()/2;  //중간인덱스 찾기

        for(int i = 0; i<mid; i++){
            left.add(array.get(i));
        }
        for(int i = mid; i<array.size(); i++){
            right.add(array.get(i));
        }

        left = MergeSort(left);
        right = MergeSort(right);

        return merge(left,right);
    }

    private static ArrayList<Student> merge(ArrayList<Student> left, ArrayList<Student> right){
        ArrayList<Student> mergeList = new ArrayList<>();
        int i = 0, j = 0;

        while (i<left.size() && j<right.size()){
            int leftNum = Integer.parseInt(left.get(i).birthMonth);
            int rightNum = Integer.parseInt(right.get(j).birthMonth);
            if(leftNum <= rightNum){
                mergeList.add(left.get(i));
                i++;
            }
            else{
                mergeList.add(right.get(j));
                j++;
            }
        }

        while(i<left.size()){
            mergeList.add(left.get(i));
            i++;
        }

        while(j<right.size()){
            mergeList.add(right.get(j));
            j++;
        }

        return mergeList;
    }
}


class Student{
    String name, birthMonth, birthday;

    public Student(String name, String birthMonth,String birthday){
        this.name = name;
        this.birthMonth = birthMonth;
        this.birthday = birthday;
    }
}
