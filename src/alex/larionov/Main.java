package alex.larionov;

import java.util.*;

public class Main {
    public static void main(String [] args){
        System.out.println("Ввод кол-ва формул");
        Scanner sc = new Scanner(System.in);
        HashSet<String> input=new HashSet<>();
        int n=sc.nextInt();
        System.out.println("Ввод формул:");
        for(int i = 0; i <= n; ++i) {
            input.add(sc.nextLine());
        }

        HashSet<String> buffer=new HashSet<>(input);
        HashSet<String> res = getModusPonens(new ArrayList<String>(buffer));
        HashSet<String> prev = new HashSet<>(buffer);
        buffer.addAll(res);
        while (buffer.size() > prev.size()) {
            prev.addAll(buffer);
            res = getModusPonens(new ArrayList<>(buffer));
            buffer.addAll(res);
        }
        buffer.removeAll(input);
        for(String tmp:res)
            System.out.println(tmp);

    }
    private static HashSet<String> getModusPonens(ArrayList<String> in){
        HashSet<String> res = new HashSet<>();
        for(int i = 0;i<in.size();++i){
            String first = in.get(i);
            for(int j=0;j<in.size();++j) {
                if(i!=j){
                    String sec=in.get(j);
                    if(sec.contains(">") && isModusPonens(first, sec)) {
                        res.add(separate(sec).get(1));
                    }
                }
            }
        }

        return res;
    }
    public static boolean isModusPonens(String first, String sec){
        if(first.length()>=sec.length()) return false;
        ArrayList<String> separate = separate(sec);
        return separate.get(0).equals(first);
    }
    public static ArrayList<String> separate(String input) {
        ArrayList<String> out = new ArrayList<>();
        int index;
        String input_1;
        String input_2;
        if(input.length() > 1) {
            if (input.charAt(0) == '(') {
                index = input.indexOf(')');
                input_1 = input.substring(0, index + 1);
                input_2 = input.substring(index + 2);
                out.add(input_1);
                out.add(input_2);
            } else if (input.contains(">(")) {
                index = input.indexOf('(');
                input_1 = input.substring(0, index - 1);
                input_2 = input.substring(index);
                out.add(input_1);
                out.add(input_2);
            } else {
                index = input.indexOf('>');
                input_1 = input.substring(0, index);
                input_2 = input.substring(index+1);
                out.add(input_1);
                out.add(input_2);
            }
        }

        return out;
    }
}

