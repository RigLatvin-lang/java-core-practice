
public class Main {

    public static void main(String[] args) throws InterruptedException{
        MyStringBuilder sb = new MyStringBuilder();
        sb.append("Hello").append(" World");
        System.out.println(sb);   // Hello World
        sb.undo();
        System.out.println(sb);   // Hello
        sb.undo();
        System.out.println("[" + sb + "]");   // []
    }    

}



