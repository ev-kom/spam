package spam;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by ЕГОР on 12.11.2016.
 */
public class MyProperties {

    public Properties getProperties() throws IOException {

        Properties props = new Properties();
        Path propDir = Paths.get(System.getProperty("user.home"), "spamdir", "properties.txt");
        if (propDir.toFile().exists()) {
            props.load(new FileInputStream(propDir.toFile()));
        } else {
            props.load(new FileInputStream(Paths.get(System.getProperty("user.dir"), "default_properties.txt").toString()));
        }
            return  props;
    }

}
