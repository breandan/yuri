public class J  {
    public static class localhost<T> extends Directory<T> {
        public static root<localhost> div(root<localhost> a) {
            return a;
        }
    }

    public static class root<T> extends Directory<T> {
        public static Directory<root> div(Directory<root> a) {
            return a;
        }
    }

    public static class bin<T> extends Directory<T> {}

    private J() {};

    public static final J uri = new J();
    public static final localhost localhost = new localhost();

    public static final root<localhost> root = new root<localhost>();
    public static final bin<root> bin = new bin<root>();


    public localhost div(localhost localhost) {
        return localhost;
    }

    static {
        localhost.div(root).div(bin);
    }
}
