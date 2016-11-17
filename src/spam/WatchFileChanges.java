package spam;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by EKomarov on 16.11.2016.
 */
public class WatchFileChanges {

    public void watchService(String directory) throws IOException, InterruptedException {

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
                    FileReader test = new FileReader();
                    System.out.println(test.addFileToList(test.listOfFiles(directory),directory, String.valueOf(fileName)));
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
