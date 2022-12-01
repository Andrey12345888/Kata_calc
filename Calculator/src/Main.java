import java.util.Scanner;

public class Main {
    static final int PLUS_CODE=0;
    static final int MINUS_CODE=1;
    static final int DIV_CODE=2;
    static final int MUL_CODE=3;

    static final String PLUS = "+";
    static final String MINUS = "-";
    static final String DIV = "/";
    static final String MUL = "*";
    static final String[] OPS = {PLUS,MINUS,DIV,MUL};
    static final String[] SplitCodes = {"\\+",MINUS,DIV,"\\*"};
    static final String[] rimDig = {"","I","II","III","IV","V","VI","VII","VIII","IX","X"};
    static final String[] rimDec = {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};

    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        System.out.println("Введите выражение.");
        String s = scr.nextLine();
        String res = calc(s);
        System.out.println(res);
    }

    public static String calc(String input){
        String Result;
        int op_code=-1;
        for (int i=0;i<OPS.length;i++){
            int op_pos = input.indexOf(OPS[i]);
            if (op_pos>=0){
                op_code=i;
                break;
            }
        }
        if(op_code==-1)
            throw new ArithmeticException("Недопустимая операция.");
        String[] sa=input.split(SplitCodes[op_code]);
        if(sa.length!=2)
            throw new ArithmeticException("Недопустимое число операндов.");

        boolean arabic=true;
        int v1=-1,v2=-1;
        try{
            v1 = Integer.parseInt(sa[0].trim());
            v2 = Integer.parseInt(sa[1].trim());
            if(v1<1||v2<1||v1>10||v2>10)
                throw new ArithmeticException("Недопустимые операнды.");
        }catch (NumberFormatException nfe){
            arabic=false;
        }
        if (arabic) {
            int v = exec_op(op_code, v1, v2);
            Result = Integer.toString(v);
        }else{
            v1 = rimToInt(sa[0]);
            v2 = rimToInt(sa[1]);
            int v = exec_op(op_code, v1, v2);
            Result = IntToRim(v);
        }
        return Result;
    }

    static int exec_op(int op, int v1, int v2){
        switch (op){
            case PLUS_CODE  : return v1+v2;
            case MINUS_CODE : return v1-v2;
            case DIV_CODE   : return v1/v2;
            case MUL_CODE   : return v1*v2;
        }
        throw new ArithmeticException("Недопустиая операция.");
    }

    static String IntToRim(int x){
        if (x<1||x>100)
            throw new ArithmeticException("Недопустимый результат.");
        int a = x%10;
        int b = x/10;
        return rimDec[b]+rimDig[a];
    }

    static int rimToInt(String s){
        String str = s.trim();
        for(int i=1;i<rimDig.length;i++){
            if (str.equals(rimDig[i]))
                return i;
        }
        throw new ArithmeticException("Недопустимое выражение.");
    }
}