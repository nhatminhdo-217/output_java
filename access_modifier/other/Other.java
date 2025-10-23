package access_modifier.other;

import access_modifier.Modifier;

public class Other extends Modifier{
    public static void main(String[] args) {

        Other other = new Other();
        System.out.println(other.publicNum);
        System.out.println(other.protectedNum); //Can be accessed through inheritance
    }
}
