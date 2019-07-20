package patterns.singleton;

public enum  EnumSingleton {
        INTANCE;

        public static EnumSingleton getInstance(){return INTANCE;}

    public void print(String str){
        System.out.println(str);
    }
}
