package acklib.utils.paths;

import acklib.utils.os.OsUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author hacke
 *
 * Collection of useful utilities for file operations
 */
public final class FileUtils {
    /*
     * Utility class, cannot be instantiated
     */
    private FileUtils(){}

    /**
     * Returns whether or not a filename is valid or not, platform independent
     *
     * @param fileName name of file
     * @return  fileName is a valid filename on the platform
     */
    public static boolean validFilename(final String fileName){
        if(OsUtils.isWindows()){
            return fileName.matches("^[^\"/\\\\\\|:><\\?\\*]+$");
        }else if(OsUtils.isMac()){
            return fileName.matches("^[^\\s:<>\\?\"\\\\/\\.,\\[\\]\\*'\\{\\}\\(\\)!]+$]");
        }else if(OsUtils.isUnix() || OsUtils.isSolaris()){
            return fileName.matches("^[^/]+$]");
        }
        return false;
    }
}
