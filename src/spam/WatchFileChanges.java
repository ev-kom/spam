package spam;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by EKomarov on 16.11.2016.
 */
public class WatchFileChanges {

    public void watchService(String directory) throws IOException, InterruptedException {

        FileReader test = new FileReader();
        List<String> testList = test.firstStartList(directory);
        Map<String,String> testMap = test.filesReader(testList, directory);

        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path dir = Paths.get(directory);
        dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        System.out.println("Watch Service registered for dir: " + dir.getFileName());

        while(true){
            WatchKey key;
            key = watcher.take();

            for (WatchEvent<?> event : key.pollEvents()){
                WatchEvent.Kind<?> kind = event.kind();

                @SuppressWarnings("unchecked")
                WatchEvent<Path> ev = (WatchEvent<Path>) event;
                Path fileName = ev.context();

                System.out.println(kind.name() + ": " + fileName);

                if (kind == OVERFLOW){
                    continue;
                } else if (kind == ENTRY_CREATE){
                    System.out.println("File was created");
                    System.out.println(test.addFileToList(testList,directory, String.valueOf(fileName)));
                    System.out.println(test.newFileReader(testMap,directory, String.valueOf(fileName)));
                } else if (kind == ENTRY_DELETE){
                    System.out.println("File was deleted");
                } else if (kind == ENTRY_MODIFY){
                    System.out.println("File was modified");
                }
            }
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
