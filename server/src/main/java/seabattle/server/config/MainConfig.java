package seabattle.server.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainConfig {
    private String mainConfigPath = "src/main/resources/server/config/mainConfig.txt";

    private String socketConfigPath;

    public MainConfig() throws IOException {
        setProperties();
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfigPath);
        properties.load(fileReader);

        socketConfigPath = properties.getProperty("socketConfigPath");
    }

    public String getMainConfigPath() {
        return mainConfigPath;
    }

    public String getSocketConfigPath() {
        return socketConfigPath;
    }
}
