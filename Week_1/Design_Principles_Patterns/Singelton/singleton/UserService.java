package singleton;

public class UserService {

    public void login(String username) {
        Logger logger = Logger.getInstance();
        logger.log(username + " logged in.");
    }
}