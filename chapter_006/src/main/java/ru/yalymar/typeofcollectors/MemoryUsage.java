package ru.yalymar.typeofcollectors;

public class MemoryUsage {

    public static class User{
        public String name;
        public int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("finalize");
        }

        @Override
        public String toString() {
            return "Cache{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
        long start = System.currentTimeMillis();
        //weightOne();

        weightAll();

        //System.gc();
        System.out.println("finish");
        info();
        System.out.println((System.currentTimeMillis()-start)+" ms");
    }

    public static void weightOne(){
        info();
        User user = new User("Anatole", 90);
        info();
    }

    public static void weightAll(){
        info();
        int limit = 20000;
        for(int i = 0; i<limit; i++){
            new User("Uasya", 21);
        }
        info();
    }


    public static void info(){
        int mb = 1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [kb] #####");

        //Print free memory
        System.out.println("Used memory: "+(runtime.totalMemory() - runtime.freeMemory())/ mb);

        //Print free memory
        System.out.println("Free memory: "+runtime.freeMemory()/mb);

        //Print total available memory
        System.out.println("Total Memory: "+runtime.totalMemory()/mb);

        //Print Maximum available memory
        System.out.println("Max memory: "+runtime.maxMemory()/mb);
    }

}
