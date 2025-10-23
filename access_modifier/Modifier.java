package access_modifier;

public class Modifier{

    public String publicNum = "public";
    String defaultNum = "default";
    protected String protectedNum = "protected";
    private String privateNum = "private";

    static String staticNum = "static";

    class Nested{
        private int a = 1;
        
        void showNum() {
            System.out.println(a);
        }
    }

    void showNum(){
        Nested nested = new Nested();
        System.out.println(nested.a);
    }

    public static void main(String[] args) {

        Modifier modifier = new Modifier();
        modifier.showNum();
        System.out.println(modifier.publicNum);
        System.out.println(modifier.publicNum);
        System.out.println(modifier.defaultNum);
        System.out.println(modifier.protectedNum);
        System.out.println(modifier.privateNum);

        Modifier.Nested nested = new Modifier().new Nested();
        nested.showNum();
    }
}

class Another {
    public static void main(String[] args) {
        Modifier mod = new Modifier();
        System.out.println(mod.publicNum);
        System.out.println(mod.defaultNum);
        System.out.println(mod.protectedNum);
        // System.out.println(mod.privateNum); //Can not be accessed
    }
}