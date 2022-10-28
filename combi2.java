import java.util.ArrayList;
import java.util.List;

public class combi2 {
    public static List<List<Integer>> result = new ArrayList<List<Integer>>();
    public static List<Integer> comb = new ArrayList<Integer>();
    public static int start = 0; //От какой цифры подбор
    public static int end = 9; //до какой цифры подбор 
    public static int k = 2; //размер массива comb
    public static void main(String[] args) {
        //String myStr = "2? + ?5 = 69";
        String myStr = "1? + 1? = 21";
        //String myStr = "     ?      +    ?     =      12 ";
        System.out.println("Подобрать решение: " + myStr);
        myStr = myStr.trim().replaceAll("\\s+", ""); //Убираем все пробелы из строки
        char[] s = myStr.toCharArray(); 
        findAnswer(s, findSign(s));
    }
    //Функция поиска всех комбинации и записи их в массив
    public static List<List<Integer>> fillArray(List<List<Integer>> dimNumOfComb, List<Integer> comb, int start, int n,
        
        int k) {
        if (k <= 0) {
           dimNumOfComb.add(new ArrayList<Integer>(comb));
           return null;
        }
        for (int i = 0; i <= n; i++) {
           comb.add(i);
           fillArray(dimNumOfComb, comb, i, n, k - 1);
           comb.remove(comb.size() - 1);
        }
        return dimNumOfComb;
    }
    //поиск знаков ? и замена + на =
    public static ArrayList<Integer> findSign(char[] myArr){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < myArr.length; i++) {
            if (myArr[i] == '?') {
                arrayList.add(i);
            }
            if (myArr[i] == '+') {
                myArr[i] = '=';
            }    
        }
        return arrayList;
    }
    // подбор решения
    public static void findAnswer(char[] myArr1, ArrayList<Integer> arrayList){
        List<List<Integer>> result2 = new ArrayList<List<Integer>>();
        result2 = fillArray(result, comb, start, end, k);
        int resSize = result2.size();
        String answer = "";
        for (int i = 0; i < resSize; i++) {
            for (int j = 0; j < arrayList.size(); j++){
                int testCombinations = result2.get(i).get(j);
                myArr1[arrayList.get(j)] = Integer.toString(testCombinations).charAt(0);
            }
            String[] item = toStr(myArr1).split("=");
            int summ = Integer.parseInt(item[0])+Integer.parseInt(item[1]);
            if (summ == Integer.parseInt(item[2])) {
                answer = item[0] + " + " + item[1] + " = " + item[2];
                System.out.println(answer);
            }
        }
        return;
    }
    //Преобразуем массив символов в строку
    public static String toStr(char[] a) {
        StringBuilder sb = new StringBuilder();
            for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
        }
        return sb.toString();
    }
}


