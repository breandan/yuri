public class J  {
    static class localhost {
        static root div(root a) {
            return a;
        }
    }

    public static class root  {
        static bin div(bin a) {
            return a;
        }
    }

    static class bin {}

    static final localhost localhost = new localhost();
    static final root root = new root();
    static final bin bin = new bin();

    public static void main(String[] args) {
        localhost.div(root); // Try to complete: localhost.div(<caret>). First suggestion is "args"!
    }
}
