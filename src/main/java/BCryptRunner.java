import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptRunner {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        System.out.println(encoder.encode("admin"));
        System.out.println("------------");
        System.out.println(encoder.encode("witcher"));
    }
}
