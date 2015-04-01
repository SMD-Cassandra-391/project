package output;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;


/**
 * collection of utility methods to deal with files
 * @author slmyers
 *
 */

public class FileUtils {

	/**
	 * deletes all files in a folder. used to clear SSTables
	 * @param dir
	 */

	public static void deleteDir(String dir) {
		Path directory = Paths.get(dir);
		try {
			Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			// empty catch directory doesn't exist it's cool
		}
	}
}