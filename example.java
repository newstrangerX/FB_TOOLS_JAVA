package <<YOUR PACKAGE NAME>>;

public class example extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                MAECREACTOR C = new MAECREACTOR(getApplicationContext());
                C.ADDCOOKIE("USER COOKIES FB EXAMPLE: datr=XXXXX;c_user=111XXX2222;");
                C.selectReact(C.ANGRY());
                C.send("pfbid02epmfE7YRPUoiMFbpwnqpnMSXpYUrL7ACVWXwypkPTfvrc5DJ2C5PM85kte7Z244gl");
    }
}
